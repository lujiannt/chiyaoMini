package cn.stylefeng.guns.modular.drag.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonDragRecord {
    /**
     * 用户id
     */
    private long id;
    /**
     * 小程序openId
     */
    private String miniOpenId;
    /**
     * 药品id
     */
    private long dragId;
    /**
     * 服药时间
     */
    private Date takeTime;
    /**
     * 创建时间
     */
    private Date createTime;
}
