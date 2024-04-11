package com.example.ykcloud.utils;

import com.example.ykcloud.vo.MailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

    @Value("${spring.mail.username}")
    private String MAIL_SENDER; //邮件发送者

    @Autowired
    private JavaMailSender javaMailSender;//注入QQ发送邮件的bean

    private Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 发送文本邮件
     *
     * @param mailvo
     */
    public void sendSimpleMail(MailVo mailvo) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(MAIL_SENDER);//发送者
            mailMessage.setTo(mailvo.getRecipient());//接收者
            mailMessage.setSubject(mailvo.getSubject());//邮件标题
            mailMessage.setText(mailvo.getContent());//邮件内容
            javaMailSender.send(mailMessage);//发送邮箱
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }
}
