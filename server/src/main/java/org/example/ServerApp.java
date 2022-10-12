package org.example;

import org.example.exceptions.FileNotFound;
import org.example.utilities.Utils;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerApp {
    public static void main(String[] args) throws IOException, FileNotFound {
        Utils.readSettings();
        int port = Utils.getPort();
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        server.start();
    }
}