package org.example.utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log {

    private static Log instance = null;
    private final Logger logger;

    private Log() throws IOException {
        String FILENAME = "server/log.txt";
        final FileHandler fileHandler = new FileHandler(FILENAME, true);
        this.logger = Logger.getLogger("server");
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
    }

    public static Log getLogger() throws IOException {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void logInfo(String message) {
        logger.info(message);
    }
}
