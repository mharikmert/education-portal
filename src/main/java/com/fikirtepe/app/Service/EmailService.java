package com.fikirtepe.app.Service;

import com.fikirtepe.app.Model.User;

public interface EmailService {
    void sendRegistrationReceivedMail(User user);
    void sendRegistrationApprovedMail(User user);
    void sendRegistrationRejectedMail(User user);
}
