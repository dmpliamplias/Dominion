package connection;

import base.Model;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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

    public void CopytoCbPort (){
        String str = Integer.toString(getPort());
        StringSelection stringS = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringS, stringS);
    }

    public void CopytoCbIP (){
        String str = getIP();
        StringSelection stringS = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringS, stringS);
    }

}
