package usermanagement;

import base.View;
import com.weddingcrashers.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.geometry.Pos.TOP_RIGHT;
import static javafx.scene.text.FontWeight.NORMAL;

/**
 * The user management view.
 *
 * @author dmpliamplias
 */
public class UserManagementView extends View<UserManagementModel> {

    // ---- Members

    protected ListView<User> listView;

    /** The edit button. */
    protected Button editButton;
    /** The go to login button. */
    protected Button goToLoginButton;
    /** The go to lobby button. */
    protected Button goToLobbyButton;

    /** The users. */
    protected ObservableList<User> users = FXCollections.observableArrayList();


    // ---- Constructor

    /**
     * Set any options for the stage in the subclass constructor
     *
     * @param stage the stage.
     * @param model the model.
     */
    public UserManagementView(Stage stage, UserManagementModel model) {
        super(stage, model);
    }


    // ---- Methods

    @Override
    protected Scene create_GUI() {
        stage.setHeight(700);
        stage.setWidth(1000);

        // layout
        final VBox root = new VBox();
        root.setSpacing(15);
        root.setPadding(new Insets(20));

        root.getChildren().addAll(addTitle(), addListView(), addHBox());

        final Scene scene = new Scene(root);
        stage.setScene(scene);

        return scene;
    }

    private ListView<User> addListView() {
        listView = new ListView<>(users);
        return listView;
    }

    private Label addTitle() {
        final Label title = new Label();
        title.setText("User Management Dominion");
        title.setFont(Font.font("Arial", NORMAL, 35));
        return title;
    }

    private HBox addHBox() {
        final HBox hBox = new HBox();
        hBox.setSpacing(150);
        hBox.getChildren().addAll(addEditButton(), addGoToLobbyButton(), addGoToLoginButton());
        return hBox;
    }

    private Button addGoToLoginButton() {
        goToLoginButton = new Button();
        goToLoginButton.setText("Go back to Login");
        return goToLoginButton;
    }

    private Button addGoToLobbyButton() {
        goToLobbyButton = new Button();
        goToLobbyButton.setText("Go to Lobby");
        return goToLobbyButton;
    }

    private Button addEditButton() {
        editButton = new Button();
        editButton.setAlignment(TOP_RIGHT);
        editButton.setText("Edit User");
        return editButton;
    }

    @Override
    protected void setTexts() {

    }

}
