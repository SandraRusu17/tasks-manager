package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entity.Email;

import java.io.IOException;

public interface EmailService {
    void sendEmail(Email email) throws IOException;
}
