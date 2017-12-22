package util;

import com.weddingcrashers.model.User;
import javafx.concurrent.Task;

import java.applet.AudioClip;

/***
 * @author Michel Schlatter
 */
public class PLServiceLocator {


    public javafx.scene.media.AudioClip audioClip;
    private static PLServiceLocator sl;
    public boolean soundIsOn = new Boolean( true );
    public boolean noiseIsOn = new Boolean( true );

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

    public boolean isSoundIsOn() {
        return soundIsOn;
    }

    public javafx.scene.media.AudioClip getAudioClip() {
        return audioClip;
    }

    public boolean isNoiseIsOn() {
        return noiseIsOn;
    }
}
