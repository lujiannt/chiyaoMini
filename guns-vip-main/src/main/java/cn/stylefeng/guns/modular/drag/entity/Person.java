package cn.stylefeng.guns.modular.drag.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Person {
    /**
     * id
     */
    private long id;
    /**
     * 企业id【工业码云平台数据】
     */
    private long tenantId;
    /**
     * 操作用户id【工业码云平台数据】
     */
    private long userId;
    /**
     * 操作用户姓名【工业码云平台数据】
     */
    private String userName;
    /**
     * 小程序openId
     */
    private String miniOpenId;
    /**
     * 创建时间
     */
    private Date createTime;


}
