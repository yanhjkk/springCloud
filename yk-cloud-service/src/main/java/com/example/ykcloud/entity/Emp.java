package com.example.ykcloud.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "emp")
@Table(name = "emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("密码")
    private  String deg;

    @ApiModelProperty("收入")
    private  String salary;

    @ApiModelProperty("部门编号")
    private  String dept;

    @ApiModelProperty("是否删除")
    private Integer del;

    @ApiModelProperty("电子邮件")
    private Integer email;
}
