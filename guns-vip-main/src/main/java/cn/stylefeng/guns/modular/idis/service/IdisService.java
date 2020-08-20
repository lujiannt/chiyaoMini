package cn.stylefeng.guns.modular.idis.service;


import cn.stylefeng.guns.modular.idis.entity.IdisIdentityDetail;
import cn.stylefeng.guns.modular.idis.entity.IdisParam;

/**
 * @Description: idis 同步接口
 * @Author husl
 * @Date 2019/10/30 14:40
 * @Version v1.0
 */
public interface IdisService {
    /**
     * 初始化token (初始登录)
     */
    boolean validateIdisAccount(IdisParam param);

    /**
     * 获取idis 详情（同步）
     */
    IdisIdentityDetail getIdentity(IdisParam idisParam, String idisCode);
}
