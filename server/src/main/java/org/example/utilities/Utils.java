package org.example.utilities;

import org.example.exceptions.FileNotFound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String settingsFilename = "server/settings.txt"; //путь файла с настройками
    private static String host = "localhost"; //значение хоста по умолчанию
    private static int port = 8080; //значение порта по умолчанию

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }

    public static void setHost(String host) {
        Utils.host = host;
    }

    public static void setPort(int port) {
        Utils.port = port;
    }

    public static void readSettings() throws FileNotFound {
        File settingsFile = new File(settingsFilename);
        if (!settingsFile.exists()) {
            throw new FileNotFound(settingsFilename);
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(settingsFilename))) {
                String currentString;
                List<String> settings = new ArrayList<>();
                while ((currentString = br.readLine()) != null) {
                    settings.add(currentString);
                }
                host = settings.get(0);
                port = Integer.parseInt(settings.get(1));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
