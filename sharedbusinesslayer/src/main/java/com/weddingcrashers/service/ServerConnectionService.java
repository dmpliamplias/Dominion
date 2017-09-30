package com.weddingcrashers.service;

import com.weddingcrashers.servermodels.ConnectionContainer;
import com.weddingcrashers.servermodels.Container;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *  @author Michel Schlatter
 *  */
public class ServerConnectionService {
    private  Socket _connection;
    private int _clientId;

    public ServerConnectionService(String url, int port) throws IOException{
        _connection = new Socket(url,port);

        try {
            _clientId = this.<ConnectionContainer>receiveObject().getId();
        }catch(ClassNotFoundException ex){}
    }

    public Socket getConnection() {
        return _connection;
    }

    public int get_clientId() {
        return _clientId;
    }

    public  <T extends Container> void  sendObject(T object) throws  IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(_connection.getOutputStream());
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    public <T extends  Container> T receiveObject() throws  IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(_connection.getInputStream());
        return (T)objectInputStream.readObject();
    }

    /**
     * you should not use this methode, until you want to ignore all other
     * messages from server till your expected answer / class comes.
     * @param targetClass
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public <T extends  Container> T receiveObjectUnsafe(Class<T> targetClass) throws  IOException, ClassNotFoundException{
        Object obj = null;
        ObjectInputStream objectInputStream = new ObjectInputStream(_connection.getInputStream());
        obj = objectInputStream.readObject();
        T res = null;
        while(obj == null || targetClass.isAssignableFrom(obj.getClass())){
            res = (T)obj;
        }
        return res;
    }


}
