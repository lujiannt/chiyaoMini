package cn.stylefeng.guns.modular.idis.service.impl;

import cn.stylefeng.guns.modular.idis.entity.IdisIdentityDetail;
import cn.stylefeng.guns.modular.idis.entity.IdisParam;
import cn.stylefeng.guns.modular.idis.entity.IdisResult;
import cn.stylefeng.guns.modular.idis.service.IdisService;
import cn.stylefeng.guns.modular.idis.util.IDISConst;
import cn.stylefeng.guns.modular.idis.util.OkHttpCli;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


/**
 * @Description: 同步idis数据
 * @Author husl
 * @Date 2019/10/30 14:41
 * @Version v1.0
 */
@Service
@Slf4j
public class IdisServiceImpl implements IdisService {

    @Autowired
    private OkHttpCli okHttpCli;

    @Autowired
    private IdisLoginSupport login;

    @Override
    public boolean validateIdisAccount(IdisParam param) {
        try {
            login.getTokenFromIdis(param);
            return true;
        } catch (ServiceException err) {
            log.error("IDIS 账户名密码验证失败", err);
            return false;
        }
    }


    @Override
    public IdisIdentityDetail getIdentity(IdisParam idisParam, String idisCode) {
        String idisToken = login.getToken(idisParam);
        String retStr = okHttpCli.doGetWithBearerToken(
                IDISConst.URL_DATA_DETAIL, Collections.singletonMap("handle", idisCode), idisToken);
        IdisResult<IdisIdentityDetail> res = JSON.parseObject(retStr,
                new TypeReference<IdisResult<IdisIdentityDetail>>() {});
//        log.info("查询IDIS:getIdentity:{}", res.getData());
        return res.getData();
    }


}
