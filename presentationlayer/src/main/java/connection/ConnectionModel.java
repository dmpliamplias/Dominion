package connection;

import base.Model;

/**
 *  @author  Michel Schlatter & Vanessa Cajochen
 *  */
public class ConnectionModel extends Model {
    private int Port;
    private String IP;
    
    public String getIP() {
        return IP;
    }
    
    public void setIP(String ip){
        IP = ip;
    }
    
    public int getPort() {
        return Port;
    }
    
    public void setPort(int port){
        Port = port;
    }

}
