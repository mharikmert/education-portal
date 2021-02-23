package com.fikirtepe.app.Service;

import com.fikirtepe.app.Model.User;

public interface EmailService {
    void sendRegistrationEmail(User user);
}
