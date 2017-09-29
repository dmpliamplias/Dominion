package com.weddingcrashers.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client extends Thread {
    Socket _clientSocket;

    public Client(Socket clientSocket) {
        _clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStream stream =  _clientSocket.getInputStream();
            // Listen to what he says....send Object with PlayMethod, Cards, usw.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket get_clientSocket() {
        return _clientSocket;
    }
}