package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.entity.Email;
import com.stefanini.taskmanager.service.EmailService;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
public class EmailServiceImpl implements EmailService {
    public static final String FROM_EMAIL_ADDRESS = "com.stefanini.taskman@gmail.com";
    public static EmailServiceImpl INSTANCE;

    public static EmailServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmailServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public void sendEmail(final Email email) {
        Session session = Session.getInstance(getServerProperties(), getAuthenticator());

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL_ADDRESS));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getRecipient()));
            message.setSubject(email.getSubject());
            message.setContent(email.getContent(), "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            log.info(e.getMessage(), e);
        }
    }

    private Authenticator getAuthenticator() {
        final String username = "com.stefanini.taskman@gmail.com";
        final String password = "1qa2ws3ed4rf5tg";

        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        };
    }

    private Properties getServerProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }
}

