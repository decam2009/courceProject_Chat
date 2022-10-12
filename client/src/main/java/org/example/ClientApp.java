package org.example;

import org.example.exceptions.FileNotFound;
import org.example.utilities.Utils;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) throws IOException, FileNotFound {
        Utils.readSettings();
        int port = Utils.getPort();
        String host = Utils.getHost();
        Scanner input = new Scanner(System.in);
        System.out.println("What is your name?");
        String username = input.nextLine();
        Socket clientSocket = new Socket(host, port);
        Client client = new Client(clientSocket, username);
        client.listener();
        client.sendMessage();
    }
}