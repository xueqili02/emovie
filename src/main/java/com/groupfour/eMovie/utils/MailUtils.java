package com.groupfour.eMovie.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailUtils {

    private final String from = "2609587707@qq.com";

    private final String password = "mypwdlkjwespebec";

    public void send(String email, String subject, String content) {
        // 设置邮件服务器的属性
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com"); // 设置 SMTP 服务器地址
        properties.put("mail.smtp.port", "587"); // 设置 SMTP 端口号
        properties.put("mail.smtp.auth", "true"); // 启用身份验证
        properties.put("mail.smtp.starttls.enable", "true"); // 启用 STARTTLS 加密

        // 创建会话对象
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // 创建消息对象
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // 发件人地址
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); // 收件人地址
            message.setSubject(subject); // 邮件主题
            message.setText(content); // 邮件内容

            // 发送邮件
            Transport.send(message);
//            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
