package cn.stylefeng.guns.modular.idis.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.modular.idis.entity.IdisParam;
import cn.stylefeng.guns.modular.idis.entity.IdisResult;
import cn.stylefeng.guns.modular.idis.entity.Tenant;
import cn.stylefeng.guns.modular.idis.service.TenantService;
import cn.stylefeng.guns.modular.idis.util.CacheUtil;
import cn.stylefeng.guns.modular.idis.util.IDISConst;
import cn.stylefeng.guns.modular.idis.util.OkHttpCli;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * idis 登录相关接口数据
 */
@Slf4j
@Component
public class IdisLoginSupport {

    @Autowired
    private OkHttpCli okHttpCli;

    @Autowired
    private TenantService tenantService;

    /**
     * 获取对应用户的token
     */
    public String getToken(IdisParam param) {
        String token = getTokenFromCache(param);
        if (token == null) {
            synchronized (this) {
                token = getTokenFromCache(param);
                if (token == null) {
                    token = getTokenFromIdis(param);
                    String key = getCachedKey(param);
                    CacheUtil.put(IDISConst.CACHE_KEY_TOKEN, key, token);
                    CacheUtil.put(IDISConst.CACHE_KEY_TOKEN_SET_TIME, key, new Date());
                }
            }
        }
        return token;
    }

    private String getTokenFromCache(IdisParam param) {
        String key = getCachedKey(param);
        // token 为空或者过期（有效期为24小时，此处20小时重新刷新一次）
        String token = CacheUtil.get(IDISConst.CACHE_KEY_TOKEN, key);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Date tokenSetTime = CacheUtil.get(IDISConst.CACHE_KEY_TOKEN_SET_TIME, key);
        if (tokenSetTime == null || DateUtil.between(tokenSetTime, new Date(), DateUnit.HOUR) > 20) {
            // 没必要删掉, 之后会马上更新的
//            CacheUtil.remove(IDISConst.CACHE_KEY_TOKEN, tenantId);
//            CacheUtil.remove(IDISConst.CACHE_KEY_TOKEN_SET_TIME, tenantId);
            return null;
        }
        return token;
    }

    private String getCachedKey(IdisParam param) {
        if (StringUtils.isNotEmpty(param.getIdisUserName())) {
            return "User-" + param.getIdisUserName();
        } else if (param.getTenantId() != null) {
            return "Tenant-" + param.getTenantId();
        } else {
            throw new ServiceException(500, "无法获取idis 的账号名密码");
        }
    }

    /**
     * 从idis 获取用户信息
     */
    public String getTokenFromIdis(IdisParam param) {
        // 重新获取token
        JSONObject jsonObject = new JSONObject();
        String username, password;
        if (StringUtils.isNotEmpty(param.getIdisUserName())) {
            username = param.getIdisUserName();
            password = param.getIdisPassword();
        } else if (param.getTenantId() != null) {
            Tenant tenant = tenantService.getById(param.getTenantId());
            username = tenant.getIdisUName();
            password = tenant.getIdisPwd();
        } else {
            throw new ServiceException(500, "无法获取idis 的账号名密码");
        }
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        String json = jsonObject.toJSONString();

        String retStr = okHttpCli.doPostJson(IDISConst.URL_TOKEN, json);
        IdisResult<Map<String, Object>> ret = JSON.parseObject(retStr,
                new TypeReference<IdisResult<Map<String, Object>>>() {});

        if (ret.hasData()) {
            String token = (String) ret.getData().get("token");
            CacheUtil.put(IDISConst.CACHE_KEY_TOKEN, param.getTenantId(), token);
            CacheUtil.put(IDISConst.CACHE_KEY_TOKEN_SET_TIME, param.getTenantId(), new Date());
            log.info("初始化获取IDIS接口 token={}", token);
            return token;
        }
        throw new ServiceException(500, "IDIS 登录失败");
    }

}
