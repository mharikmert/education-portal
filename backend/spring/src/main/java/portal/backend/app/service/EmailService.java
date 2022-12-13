package portal.backend.app.service;

import portal.backend.app.model.User;

public interface EmailService {
    void sendRegistrationReceivedMail(User user);

    void sendRegistrationApprovedMail(User user);

    void sendRegistrationRejectedMail(User user);
}
