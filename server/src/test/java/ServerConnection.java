import org.example.Server;
import org.example.exceptions.FileNotFound;
import org.example.utilities.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnection {

    @Test
    public void correctConnection () throws IOException, FileNotFound {
        final ServerSocket serverSocket = new ServerSocket(9000);
        final Server server = new Server(serverSocket);
        Runtime.getRuntime().exec("nc -vz localhost 9000"); //подключение к серверу через netcat
        server.start();
        Assertions.assertEquals(serverSocket.getLocalPort(), 9000);
        Assertions.assertNotNull(server);
    }

}
