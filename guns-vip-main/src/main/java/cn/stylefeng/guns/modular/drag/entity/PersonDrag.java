package cn.stylefeng.guns.modular.drag.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonDrag {
    /**
     * id
     */
    private long id;
    /**
     * 状态
     */
    private int status;
    /**
     * 小程序openId
     */
    private String miniOpenId;
    /**
     * 产品id【工业码云平台数据】
     */
    private long productId;
    /**
     * 产品名称【工业码云平台数据】
     */
    private String productName;
    /**
     * 产品说明【工业码云平台数据】
     */
    private String productRemark;
    /**
     * idiscode【工业码云平台数据】
     */
    private String idisCode;
    /**
     * 创建时间
     */
    private Date createTime;
}
