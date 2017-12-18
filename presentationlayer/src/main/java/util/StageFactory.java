package util;

import base.View;
import help.HelpController;
import help.HelpModel;
import help.HelpView;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import ranking.RankingController;
import ranking.RankingModel;
import ranking.RankingView;

/**
 * Factory for various stages.
 *
 * @author dmpliamplias
 */
public class StageFactory {

    /**
     * Creates and returns a stage with the help content.
     *
     * @return the help stage.
     */
    public static Stage createHelpStage() {
        final HelpModel helpModel = new HelpModel();
        final Stage stage = new Stage();
        final HelpView helpView = new HelpView(stage, helpModel);
        new HelpController(helpModel, helpView);

        return stage;
    }

    /**
     * Creates and returns
     *
     * @param view the view.
     * @param <T> the type of the calling view.
     * @return the creates login view.
     */
    public static<T extends View> LoginView createLoginView(T view) {
        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView(view.getStage(), loginModel);
        new LoginController(loginView, loginModel);

        return loginView;
    }

    /**
     * Creates a new ranking view and returns the view,
     *
     * @return the ranking view.
     */
    public static RankingView crateRankingView() {
        final RankingModel model = new RankingModel();
        final Stage stage = new Stage();
        final RankingView rankingView = new RankingView(stage, model);
        new RankingController(model, rankingView);

        return rankingView;
    }

}
