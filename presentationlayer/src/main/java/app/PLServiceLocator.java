package app;

public class PLServiceLocator {
    private static PLServiceLocator sl;

    public  static PLServiceLocator getPLServiceLocator(){
        if(sl == null){
            sl = new PLServiceLocator();
        }
        return sl;
    }

    private  ServerConnectionService serverConnectionService;

    public ServerConnectionService getServerConnectionService() {
        return serverConnectionService;
    }

    public void setServerConnectionService(ServerConnectionService serverConnectionService) {
        this.serverConnectionService = serverConnectionService;
    }


}
