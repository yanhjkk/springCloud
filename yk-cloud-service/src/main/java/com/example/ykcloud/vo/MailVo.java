package com.example.ykcloud.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailVo {
    private static final long serialVersionUID = -2116367492649751914L;


    @ApiModelProperty(value = "邮件标题")
    private String subject;

    @ApiModelProperty(value = "邮件内容")
    private String content;

   @ApiModelProperty(value = "接收者")
    private String recipient;




}
