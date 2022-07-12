/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dalekseenko.emailserver.responses;

/**
 *
 * @author fif90
 */
public enum ResponseCode {
    OK(200),
    FAIL(500);

    Integer code;

    ResponseCode(Integer code) {
        this.code = code;
    }
}
