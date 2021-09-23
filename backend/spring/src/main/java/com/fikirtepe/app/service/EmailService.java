package com.fikirtepe.app.service;

import com.fikirtepe.app.model.User;

public interface EmailService {
    void sendRegistrationReceivedMail(User user);
    void sendRegistrationApprovedMail(User user);
    void sendRegistrationRejectedMail(User user);
}
