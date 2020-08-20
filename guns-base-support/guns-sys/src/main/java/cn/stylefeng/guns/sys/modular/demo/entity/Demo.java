package cn.stylefeng.guns.sys.modular.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@TableName("test_demo")
@Data
public class Demo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private int id;

    /**
     * 日志名称
     */
    private String name;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
