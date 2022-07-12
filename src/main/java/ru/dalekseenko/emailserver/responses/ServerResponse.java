/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dalekseenko.emailserver.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author fif90
 */
public class ServerResponse {

    @JsonProperty("result")
    ResponseCode result;
    @JsonProperty("msg")
    String msg;

    public ServerResponse() {
    }

    public void setResponse(ResponseCode code, String msg) {
        this.result = code;
        this.msg = msg;
    }

    public ResponseCode getCode() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

}
