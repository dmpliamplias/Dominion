package Game;

import app.PLServiceLocator;
import app.SeverConnectionServiceTest;
import com.weddingcrashers.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Manuel on 04.10.2017.
 */
public class GameControllerTest {

    SeverConnectionServiceTest serverConnection_client1;
    SeverConnectionServiceTest serverConnection_client2;



    // Method from ServerConnectionService
    private SocketAddress createServer(int port) {
        try {
            Server.startServer( port, 4 );
            join( "localhost", port, true );
            return PLServiceLocator.getPLServiceLocator().getServerConnectionService().getConnection().getRemoteSocketAddress();
        } catch (Exception e) {
        }
        return null;
    }

   // Method from ServerConnectionService + added 2 clients
    private void join(String url, int port, boolean isHoster) {
        try {
            if (PLServiceLocator.getPLServiceLocator().getServerConnectionService() == null) {
                Socket socket_c1 = new Socket( url, port );
                serverConnection_client1 = new SeverConnectionServiceTest( socket_c1 );

                Socket socket_c2 = new Socket( url, port );
                serverConnection_client2 = new SeverConnectionServiceTest( socket_c2 );

            }
        } catch (IOException e) {
        }
    }


    // Start Server and 2 Clients
    @Before
    public void init() {
        try {
            this.createServer( 8899 );
        } catch (Exception e) {
        }
    }

    // Method for testing the communication between server and client
    @Test
    public void Test_Chat() {
        // serverConnection_client1.sendObject( null );
        // serverConnection_client2.receiveObject();
    }

    // Close Clients and Server
    @After
    public void finish() {
        Server.dispose();
        serverConnection_client1.dispose();
        serverConnection_client2.dispose();
    }



}
