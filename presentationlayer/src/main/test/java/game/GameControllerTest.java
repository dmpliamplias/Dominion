package game;

import Game.GameController;
import com.weddingcrashers.servermodels.WinningInformation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameControllerTest {

    private List<WinningInformation> winningInformations;
    private WinningInformation winningInformation1;
    private WinningInformation winningInformation2;
    private WinningInformation winningInformation3;
    private WinningInformation winningInformation4;

    @Before
    public void setup() {
        winningInformations = new ArrayList<>();

        winningInformation1 = new WinningInformation(1, 1, 2000);
        winningInformation1.setPosition(1);
        winningInformations.add(winningInformation1);

        winningInformation2 = new WinningInformation(2, 2, 1500);
        winningInformation2.setPosition(2);
        winningInformations.add(winningInformation2);

        winningInformation3 = new WinningInformation(3, 3, 1000);
        winningInformation3.setPosition(3);
        winningInformations.add(winningInformation3);

        winningInformation4 = new WinningInformation(4, 4, 500);
        winningInformation4.setPosition(4);
        winningInformations.add(winningInformation4);
    }

    @Test
    public void testDetermineGameResultForWinnerCase() {
        final GameController gameController = new GameController();
        final HashMap<WinningInformation, Map<Integer, Boolean>> winningInformationMapHashMap = gameController.determineGameResult(winningInformations, null);
        for (Map.Entry<WinningInformation, Map<Integer, Boolean>> winningInformationTo : winningInformationMapHashMap.entrySet()) {
            for (Map.Entry<Integer, Boolean> positionToDrawEnrty : winningInformationTo.getValue().entrySet()) {
                
            }
        }
//        Assert.assertEquals();
    }

}
