package com.weddingcrashers.server;

import com.weddingcrashers.servermodels.Container;
import com.weddingcrashers.servermodels.ErrorContainer;

import java.io.ObjectOutputStream;
import java.net.SocketException;

/**
 *  @author Michel Schlatter
 *  */
public class ServerUtils {

    public static <T extends Container> void sendObject(Client c, T object)  {
        try {
            if(!c.get_clientSocket().isClosed() ) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(c.get_clientSocket().getOutputStream());
                objectOutputStream.writeObject(object);
                objectOutputStream.flush();
            }
        }catch(SocketException sEx){
            sEx.printStackTrace();
        }
        catch (Exception ex){
            ServerUtils.sendError(c,ex);
        }
    }

    public static void sendError(Client c, Exception ex)  {
        ErrorContainer errorContainer = new ErrorContainer(ex.getMessage());
        ServerUtils.sendObject(c, errorContainer);
    }


}
