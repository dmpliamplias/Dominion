package app;

import com.weddingcrashers.servermodels.Container;
import org.junit.Before;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * Created by Manual on 07.10.2017.
 */

// SeverConnectionServiceTest Class just to do some Testing
    // -> To use sendObject and receiveObject


public class SeverConnectionServiceTest {

    private  Socket connection;

    public SeverConnectionServiceTest(Socket s)
    {
        connection = s;
    }


    // Method from ServerConnectionService + changed in public
    public  <T extends Container> void  sendObject(T object) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    // Method from ServerConnectionService
    public <T extends  Container> T receiveObject() throws  IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(connection.getInputStream());
        return (T)objectInputStream.readObject();
    }

    // Method to dispose Connection
    public void dispose(){
        if(connection!= null && !connection.isClosed()){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
