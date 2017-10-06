package Game;

import app.ServerConnectionService;
import org.junit.Before;

/**
 * Created by Manuel on 04.10.2017.
 */
public class GameControllerTest {

    @Before
    private void init(){
        try {
            new ServerConnectionService("localhost", 8080);

        }catch (Exception e){

        }
    }
}
