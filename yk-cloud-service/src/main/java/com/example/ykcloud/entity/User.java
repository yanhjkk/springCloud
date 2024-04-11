package com.example.ykcloud.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;


@Entity(name = "USER")
@Table(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {


    private static final long serialVersionUID = 6111899065812654266L;


    @Id
    @Column(name = "id", nullable = false)
    @ApiModelProperty("主键id")
    private UUID id;

    @ApiModelProperty("账号")
    private String userId;

    @Column(name = "username")
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty("密码")
    @Column(name = "password")
    private String passWord;

    @ApiModelProperty("角色")
    private String role;

}
