package usermanagement;

import base.View;
import com.weddingcrashers.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    /** The name field. */
    protected TextField name;
    /** The email field */
    protected TextField email;
    /** The password field. */
    protected TextField password;
    /** The is blocked checkbox. */
    protected CheckBox isBlocked;
    /** The is super user checkbox. */
    protected CheckBox isSuperUser;

    /** The edit button. */
    protected Button editButton;
    /** The confirm button. */
    protected Button confirmButton;
    /** The cancel button. */
    protected Button cancelButton;

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

        // initialize fields
        name = new TextField();
        email = new TextField();
        password = new TextField();
        isBlocked = new CheckBox();
        isSuperUser = new CheckBox();
        confirmButton = new Button();
        cancelButton = new Button();

        root.getChildren().addAll(addTitle(), addListView(), addEditButton());

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
