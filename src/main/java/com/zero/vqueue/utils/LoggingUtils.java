package com.zero.vqueue.utils;

import org.slf4j.Logger;

public class LoggingUtils {
    public static <T> T logDebug(Logger log, T currentObject, String message, Object... params) {
        log.debug(message, params);
        return currentObject;
    }
    
    public static <T> T logInfo(Logger log, T currentObject, String message, Object... params) {
        log.info(message, params);
        return currentObject;
    }
    
    public static <T> T logError(Logger log, T currentObject, Throwable exception, String message) {
        log.error(message, exception);
        return currentObject;
    }
    
    public static <T> T logError(Logger log, T currentObject, String message, Object... params) {
        log.error(message, params);
        return currentObject;
    }
}
