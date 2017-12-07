package util;

import com.weddingcrashers.model.User;
import javafx.concurrent.Task;

import java.applet.AudioClip;

/***
 * @Author Michel Schlatter
 */
public class PLServiceLocator {


    public javafx.scene.media.AudioClip audioClip;
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

    public javafx.scene.media.AudioClip getAudioClip() {
        return audioClip;
    }
}
