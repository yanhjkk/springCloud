package com.example.ykcloud.Service.Impl;

import com.example.ykcloud.Service.MailService;
import com.example.ykcloud.vo.MailVo;
import com.example.ykcloud.mapper.EmailMapper;
import com.example.ykcloud.utils.MailUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MailServiceImpl  implements MailService {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private EmailMapper emailMapper;

//    @Transactional
    @Override
    public String sendMail(MailVo user) {

           String result =  emailMapper.getName(user.getRecipient());
       if (StringUtils.isNotEmpty(result)) {
            String email = emailMapper.getEmailByName(user.getRecipient());
            if (StringUtils.isNotBlank(email)) {
                user.setRecipient(email); //接收者
                user.setSubject(user.getSubject()); //标题
                user.setContent(user.getContent() + new Date());//邮件内容
                mailUtil.sendSimpleMail(user);
                return "邮件发送成功！";
            }
            return "用户信息为空";
       }
      return "没有找到用户信息。";
    }
}
