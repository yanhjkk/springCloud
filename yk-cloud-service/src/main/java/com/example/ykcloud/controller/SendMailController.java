package com.example.ykcloud.controller;


import com.example.ykcloud.Common.JsonResult;
import com.example.ykcloud.Service.MailService;
import com.example.ykcloud.annotation.AnnotationTest;
import com.example.ykcloud.vo.MailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mail")
@Api(value = "邮件发送", tags = "邮件发送")
public class SendMailController {


    @Autowired
    private MailService mailService;

    //    @TestContents
    @AnnotationTest(type = "123", name = "name")
    @PostMapping("/sendmail")
    @ApiOperation(value = "邮件发送", httpMethod = "POST", notes = "邮件发送")
    public JsonResult sendMail(@RequestBody MailVo user) {
        if (user != null && StringUtils.isNotEmpty(user.getRecipient())) {
            String result = mailService.sendMail(user);
            if (result.equals("邮件发送成功！")) {
                return JsonResult.success(200, null, result);
            }
            return JsonResult.error(200, "邮件发送失败!", result);
        }
        return JsonResult.error("用户信息不能为空，请填写用户信息");
    }
}
