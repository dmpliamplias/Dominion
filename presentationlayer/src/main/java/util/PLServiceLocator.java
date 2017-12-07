package util;

import com.weddingcrashers.model.User;
import javafx.concurrent.Task;

/***
 * @Author Michel Schlatter
 */
public class PLServiceLocator {

    public Task task;
    public Thread ThreadMusic;

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

    public Task getTask() {
        return task;
    }

    public Thread getThreadMusic() {
        return ThreadMusic;
    }
}
