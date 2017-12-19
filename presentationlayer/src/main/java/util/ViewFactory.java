package util;

import help.HelpController;
import help.HelpModel;
import help.HelpView;
import javafx.stage.Stage;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import ranking.RankingController;
import ranking.RankingModel;
import ranking.RankingView;
import register.RegisterController;
import register.RegisterModel;
import register.RegisterView;
import usermanagement.UserManagementController;
import usermanagement.UserManagementModel;
import usermanagement.UserManagementView;

/**
 * Factory for various stages.
 *
 * @author dmpliamplias
 */
public final class ViewFactory {

    /**
     * Creates and returns a stage with the help content.
     *
     * @param stage the stage.
     * @return the help stage.
     */
    public static Stage createHelpStage(Stage stage) {
        final HelpModel helpModel = new HelpModel();
        final HelpView helpView = new HelpView(stage, helpModel);
        new HelpController(helpModel, helpView);

        return stage;
    }

    /**
     * Creates and returns
     *
     * @param stage the stage.
     * @return the creates login view.
     */
    public static LoginView createLoginView(Stage stage) {
        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView(stage, loginModel);
        new LoginController(loginView, loginModel);

        return loginView;
    }

    /**
     * Creates a new ranking view and returns the view.
     *
     * @param stage the stage.
     * @return the ranking view.
     */
    public static RankingView createRankingView(Stage stage) {
        final RankingModel model = new RankingModel();
        final RankingView rankingView = new RankingView(stage, model);
        new RankingController(model, rankingView);

        return rankingView;
    }

    /**
     * Creates a new user management view.
     *
     * @param stage the stage.
     * @return the user management view.
     */
    public static UserManagementView createUserManagementView(Stage stage) {
        final UserManagementModel userManagementModel = new UserManagementModel();
        final UserManagementView userManagementView = new UserManagementView(stage, userManagementModel);
        new UserManagementController(userManagementModel, userManagementView);

        return userManagementView;
    }

    /**
     * Creates a new lobby view.
     *
     * @param stage the stage.
     * @return the lobby view.
     */
    public static LobbyView createLobbyView(Stage stage) {
        final LobbyModel lobbyModel = new LobbyModel();
        final LobbyView lobbyView = new LobbyView(stage, lobbyModel);
        new LobbyController(lobbyView, lobbyModel);

        return lobbyView;
    }

    /**
     * Creates a new register view.
     *
     * @param stage the stage.
     * @return the register view.
     */
    public static RegisterView createRegisterView(Stage stage) {
        final RegisterModel registerModel = new RegisterModel();
        final RegisterView registerView = new RegisterView(stage, registerModel);
        new RegisterController(registerView, registerModel);

        return registerView;
    }

}
