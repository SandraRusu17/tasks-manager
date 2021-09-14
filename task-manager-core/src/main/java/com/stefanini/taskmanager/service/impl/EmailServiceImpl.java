package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.entity.Email;
import com.stefanini.taskmanager.repository.impl.TaskHibernateRepositoryImpl;
import com.stefanini.taskmanager.service.EmailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class EmailServiceImpl implements EmailService {
    public static EmailServiceImpl INSTANCE;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmailServiceImpl.class);

    public static EmailServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmailServiceImpl();
        }
        return INSTANCE;
    }


    @Override
    public void sendEmail(final Email email)  {
        final Properties properties = getServerProperties();
        Session session = Session.getInstance(properties, getAuthenticator(properties.getProperty("smtp.username"),
                properties.getProperty("smtp.password")));

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("smtp.username")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getRecipient()));
            message.setSubject(email.getSubject());
            message.setContent(email.getContent(), "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            log.info(e.getMessage(), e);
        }
    }

    protected Authenticator getAuthenticator(String username, String password) {

        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
    }

    private Properties getServerProperties() {
        Properties properties = new Properties();
        try (InputStream ip = UserServiceImpl.class.getClassLoader().getResourceAsStream("email.properties")) {
            properties.load(ip);

        } catch (IOException e) {
            log.trace("Oops, something went wrong during reading email.properties {}", e.getMessage());
        }
        return properties;
    }



}

