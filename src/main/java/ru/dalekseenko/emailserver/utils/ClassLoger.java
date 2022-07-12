/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dalekseenko.emailserver.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fif90
 */
public class ClassLoger {

    private static Logger LOGGER;

    public static void log(Class cls) {
        LOGGER = Logger.getLogger(cls.getName());
    }

    public static void logInfo(String msg) {
        LOGGER.log(Level.INFO, msg);
    }

    public static void logError(String msg) {
        LOGGER.log(Level.WARNING, msg);
    }
}
