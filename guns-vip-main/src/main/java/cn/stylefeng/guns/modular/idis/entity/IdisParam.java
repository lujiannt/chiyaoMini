package cn.stylefeng.guns.modular.idis.entity;

import lombok.Data;

@Data
public class IdisParam {

    /**
     * 企业id, 会使用这个企业的idis 信息登录
     */
    private Long tenantId;

    /**
     * 操作用户id
     */
    private Long userId;


    private String userName;

    /**
     * idis 用户名 (如果指定了tenant 则可以不填)
     */
    private String idisUserName;

    /**
     * idis 密码 (如果指定了tenant 则可以不填)
     */
    private String idisPassword;

    public static IdisParam fromShiroUser(long tenantId, long userId, String userName) {
        IdisParam res = new IdisParam();
        res.setTenantId(tenantId);
        res.setUserId(userId);
        res.setUserName(userName);
        return res;
    }

}
