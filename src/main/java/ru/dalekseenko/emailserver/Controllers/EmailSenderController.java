/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dalekseenko.emailserver.Controllers;

import java.util.Base64;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dalekseenko.emailserver.responses.ResponseCode;
import ru.dalekseenko.emailserver.responses.ServerResponse;
import ru.dalekseenko.emailserver.utils.ClassLoger;
import ru.dalekseenko.emailserver.utils.JsonParser;

/**
 *
 * @author fif90
 *
 * Methods: creatAccount Login getDataAcoount updateDataAccount
 */
@RestController
public class EmailSenderController {

    public EmailSenderController() {
        ClassLoger.log(EmailSenderController.class);
    }
    @RequestMapping(value = "/sendEmail")
    public ResponseEntity<ServerResponse> sendEmail(@RequestHeader(name = "Authorization") String auth, @RequestBody String data) {
        if (data == null) {
            return error("Пустой пакет данных");
        }
        JsonParser json = new JsonParser(data);
        if (!json.has("from")) {
            return error("Не найден параметр \"from\"");
        } else if (!json.has("to")) {
            return error("Не найден параметр \"to\"");
        } else if (!json.has("msg")) {
            return error("Не найден параметр \"msg\"");
        }
        AuthDecode decode = new AuthDecode(auth);
        String login = decode.getLogin();
        String password = decode.getPassword();

        String from = json.getString("from");
        String to = json.getString("to");
        String msg = json.getString("msg");
        ServerResponse response = EmailSenderControllerImpl.instance.sendEmail(login, password, from, to, msg);
        return new ResponseEntity<ServerResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<ServerResponse> error(String msg) {
        ServerResponse err = new ServerResponse();
        err.setResponse(ResponseCode.FAIL, msg);
        return ResponseEntity.ok(err);
    }
}

class AuthDecode {

    private String login = "";
    private String password = "";

    public AuthDecode(String auth) {
        ClassLoger.log(AuthDecode.class);
        decode(auth);
    }

    private void decode(String auth) {
        auth = auth.replaceAll("Basic ", "");
        byte[] arrBase64 = Base64.getDecoder().decode(auth);
        String logpwd = new String(arrBase64);
        String[] logpwdArr = logpwd.split(Pattern.quote(":"));
        login = logpwdArr[0];
        password = logpwdArr[1];
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
