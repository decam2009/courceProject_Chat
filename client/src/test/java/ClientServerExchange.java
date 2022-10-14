import org.example.Client;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientServerExchange {
    @Test
    public void send_recieve_message() throws IOException {
        Socket socket = new Socket("localhost", 9000);
        Client client = new Client(socket, "Boris");
        client.listener();
        client.sendMessage();
        Mockito server = new Mockito(Server::class);
    }
}
