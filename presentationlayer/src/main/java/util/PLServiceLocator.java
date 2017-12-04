package util;

import com.weddingcrashers.model.User;
/***
 * @Author Michel Schlatter
 */
public class PLServiceLocator {
    private static PLServiceLocator sl;

    public  static PLServiceLocator getPLServiceLocator(){
        if(sl == null){
            sl = new PLServiceLocator();
        }
        return sl;
    }

    private  ServerConnectionService serverConnectionService;
    private User user;

    public ServerConnectionService getServerConnectionService() {
        return serverConnectionService;
    }

    public void setServerConnectionService(ServerConnectionService serverConnectionService) {
        this.serverConnectionService = serverConnectionService;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
