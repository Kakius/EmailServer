/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dalekseenko.emailserver.Controllers;

import ru.dalekseenko.emailserver.responses.ResponseCode;
import ru.dalekseenko.emailserver.responses.ServerResponse;
import ru.dalekseenko.emailserver.utils.ClassLoger;
import ru.dalekseenko.emailserver.utils.EmailSender;

/**
 *
 * @author fif90
 */
public class EmailSenderControllerImpl {

    public EmailSenderControllerImpl() {
        ClassLoger.log(EmailSenderControllerImpl.class);
    }

    public static final EmailSenderControllerImpl instance = new EmailSenderControllerImpl();

    public ServerResponse sendEmail(String login, String password, String emailFrom, String emailTo, String message) {
        ClassLoger.logInfo("Email from: " + emailFrom);
        ClassLoger.logInfo("Email to: " + emailTo);
        ClassLoger.logInfo("Message: " + message);
        ServerResponse ret = new ServerResponse();

        EmailSender sender = new EmailSender(login, password);
        sender.send(emailFrom, emailTo, message);
        ServerResponse response = new ServerResponse();
        response.setResponse(ResponseCode.OK, "Успешно");
        return ret;
    }
}
