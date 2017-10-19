package usermanagement;

import base.View;
import com.weddingcrashers.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The user management view.
 *
 * @author dmpliamplias
 */
public class UserManagementView extends View<UserManagementModel> {

    // ---- Members

    /** The list view. */
    private ListView<User> listView;

    /** The title label. */
    private Label title;

    /** The name field. */
    private TextField name;
    /** The email field */
    private TextField email;
    /** The password field. */
    private TextField password;
    /** The is blocked checkbox. */
    private CheckBox isBlocked;
    /** The is super user checkbox. */
    private CheckBox isSuperUser;

    /** The edit button. */
    private Button editButton;
    /** The confirm button. */
    private Button confirmButton;
    /** The cancel button. */
    private Button cancelButton;

    /** The users. */
    private ObservableList<User> users = FXCollections.observableArrayList();


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

        // initialize fields
        listView = new ListView<>(users);
        title = new Label();
        name = new TextField();
        email = new TextField();
        password = new TextField();
        isBlocked = new CheckBox();
        isSuperUser = new CheckBox();
        editButton = new Button();
        confirmButton = new Button();
        cancelButton = new Button();

        title.setText("User Management Dominion");
        editButton.setText("Edit User");
        root.getChildren().addAll(title, listView, editButton);

        final Scene scene = new Scene(root);
        stage.setScene(scene);

        return scene;
    }

    @Override
    protected void setTexts() {

    }

}
