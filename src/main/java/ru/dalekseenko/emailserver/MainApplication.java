/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dalekseenko.emailserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author fif90
 */
@ComponentScan
@EnableAutoConfiguration
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);        
        System.out.println("Test commit 2.0");
    }
}
