package com.example.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class LogUtility {
    private static final Logger LOGGER = Logger.getLogger(LogUtility.class.getName());
    private static final Path LOG_FILE = Path.of("target", "test-execution.log");
    private static FileHandler fileHandler;

    private LogUtility() {
    }

    public static synchronized void startFileLogging() {
        closeFileLogging();

        try {
            Files.createDirectories(LOG_FILE.getParent());
            fileHandler = new FileHandler(LOG_FILE.toString(), true);
            fileHandler.setEncoding(StandardCharsets.UTF_8.name());
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to initialize log file: " + LOG_FILE, exception);
        }
    }

    public static synchronized void closeFileLogging() {
        if (fileHandler != null) {
            LOGGER.removeHandler(fileHandler);
            fileHandler.close();
            fileHandler = null;
        }
    }

    public static void logInfo(String message) {
        LOGGER.info(message);
    }

    public static void logError(String message) {
        LOGGER.severe(message);
    }

    public static void logError(String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, message, throwable);
    }

    public static void logDebug(String message) {
        LOGGER.fine(message);
    }

    public static void logWarning(String message) {
        LOGGER.warning(message);
    }

    public static Logger getLogger() {
		return LOGGER;
	}
}