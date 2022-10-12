package org.example.utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log {

    private final Logger logger;

    public Log() throws IOException {
        String FILENAME = "client/log.txt";
        final FileHandler fileHandler = new FileHandler(FILENAME, true);
        this.logger = Logger.getLogger("client");
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
    }

    public void logInfo(String message) {
        logger.info(message);
    }
}
