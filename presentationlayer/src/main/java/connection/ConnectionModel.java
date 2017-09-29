package connection;

import base.Model;

/**
 *  @author Michel Schlatter
 *  */
public class ConnectionModel extends Model {
    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    private String Url;
    private int Port;

}
