package com.example.ykcloud.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class DeleteEmpVo {

    /**
     * 姓名
     */
  //  @ApiModelProperty("姓名")
    private String name;

    /**
     * 密码
     */
   // @ApiModelProperty("密码")
    private  String deg;

    /**
     * 收入
     */
    //@ApiModelProperty("收入")
    private  String salary;

    /**
     * 部门编号
     */
    //@ApiModelProperty("部门编号")
    private  String dept;

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    private Integer del;

    /**
     * 电子邮件
     */
    //@ApiModelProperty("电子邮件")
    private Integer email;
}
