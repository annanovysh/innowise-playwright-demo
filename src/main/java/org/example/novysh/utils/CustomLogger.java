package org.example.novysh.utils;

import io.qameta.allure.Step;
import org.slf4j.LoggerFactory;

public class CustomLogger {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    /**
     * Logs informational step messages (sent to console and Allure report)
     */
    @Step("STEP: {message}")
    public static void info(String message) {
        logger.info("[INFO] {}", message);
    }

    /**
     * Logs warning messages
     */
    @Step("WARN: {message}")
    public static void warn(String message) {
        logger.warn("[WARN] {}", message);
    }

    /**
     * Logs error messages with an exception stacktrace
     */
    public static void error(String message, Throwable throwable) {
        logger.error("[ERROR] {}", message, throwable);
    }

    /**
     * Logs error messages without stacktrace
     */
    @Step("ERROR: {message}")
    public static void error(String message) {
        logger.error("[ERROR] {}", message);
    }
}
