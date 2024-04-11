package com.example.ykcloud.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询未删除Vo")
public class SelectListVo {

    //private String id;
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "英文名")
    private String deg;

    @ApiModelProperty(value = "收入")
    private String salary;

    @ApiModelProperty(value = "部门")
    private String dept;
    //    @ApiModelProperty(value = "是否删除（0未删除，1删除）")
//    private String del;

    @ApiModelProperty(value = "电子邮件")
    private String email;
}
