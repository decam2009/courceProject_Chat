package org.example;

import org.example.utilities.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class BroadcastClient implements Runnable {

    private static final List<BroadcastClient> broadcastClientList = new ArrayList<>();
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    private final Log log = Log.getLogger();

    public BroadcastClient(Socket client) throws IOException {
        this.client = client;
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.username = in.readLine();
        broadcastClientList.add(this);
        sendMessagesallClients(username + " зашел в чат");
    }

    private void sendMessagesallClients(String message) {
        for (BroadcastClient client : broadcastClientList) {
            if (!client.username.equals(username)) {
                client.out.println(message);
                log.logInfo(message);
            }
        }
    }

    public void removeBroadcastClient() {
        broadcastClientList.remove(this);
        sendMessagesallClients(username + " покинул чат");
    }

    @Override
    public void run() {
        String message;
        while (true) {
            try {
                message = in.readLine();
                sendMessagesallClients(message);
            } catch (IOException e) {
                closeEverything(client, out, in);
                break;
            }
        }
    }

    public void closeEverything(Socket socket, PrintWriter out, BufferedReader in) {
        try {
            removeBroadcastClient();
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
