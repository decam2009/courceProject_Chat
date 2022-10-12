package org.example;

import org.example.utilities.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    private PrintWriter out;
    private BufferedReader in;
    private String username;
    private Socket socket;
    private Log log;

    public Client(Socket socket, String username) {
        try {
            this.username = username;
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.log = new Log();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        out.println(username);
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                String message = input.nextLine();
                if ("exit".equals(message)) {
                    disconnect();
                    return;
                }
                log.logInfo(username + " : " + message);
                out.println(username + " : " + message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listener() {
        Thread thread = new Thread(() -> {
            String messageToSend = "";
            try {
                while (true) {
                    messageToSend = in.readLine();
                    System.out.println(messageToSend);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public synchronized void disconnect() throws IOException {
        Thread.currentThread().interrupt();
        closeEverything(socket, out, in);
    }


    public void closeEverything(Socket socket, PrintWriter out, BufferedReader in) throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
        if (socket != null) {
            socket.close();
        }
    }
}


