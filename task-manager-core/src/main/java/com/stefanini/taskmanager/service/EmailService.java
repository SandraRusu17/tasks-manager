package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.entity.Email;

public interface EmailService {
    void sendEmail(Email email);
}
