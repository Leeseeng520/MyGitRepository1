package com.it.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Component
@Data//自动生成get和set方法，包括有参和无参构造 (简化代码)
public class User {
    @TableId(type = IdType.ID_WORKER)//mp自带策略，生成19位值，数字类型使用这种策略，比如long
    //@TableId(type = IdType.ID_WORKER_STR) //mp自带策略，生成19位值，字符串类型使用这种策略
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)//表示添加操作时，自动生成添加时间的注解
    private Date createTime;
    //@TableField(fill = FieldFill.UPDATE)//表示修改操作时，自动生成添加时间的注解
    @TableField(fill = FieldFill.INSERT_UPDATE)//添加和修改时都有值
    private Date updateTime;

    @Version//设置乐观锁，添加版本号属性c
    @TableField(fill = FieldFill.INSERT)//设置乐观锁，第一次插入数据时版本号为1
    private Integer version;

    @TableLogic//设置逻辑删除注解
    @TableField(fill = FieldFill.INSERT)//第一次插入时默认值为0
    private Integer deleted;
}
