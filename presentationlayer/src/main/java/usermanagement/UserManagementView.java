package usermanagement;

import base.View;
import com.weddingcrashers.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.scene.text.FontWeight.NORMAL;

/**
 * The user management view.
 *
 * @author dmpliamplias
 */
public class UserManagementView extends View<UserManagementModel> {

    // ---- Members

    /** The list view. */
    protected ListView<User> listView;

    /** The edit button. */
    protected Button editButton;
    /** The create button. */
    protected Button createButton;
    /** The delete button. */
    protected Button deleteButton;
    /** The go to login button. */
    protected Button goToLoginButton;

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

        root.getChildren().addAll(createTitle(), createListView(), createHBox());

        final Scene scene = new Scene(root);
        stage.setScene(scene);

        return scene;
    }

    /**
     * Creates the list view.
     *
     * @return the list view.
     */
    private ListView<User> createListView() {
        listView = new ListView<>(users);
        listView.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(final User item, final boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                }
                else {
                    setText(item.getUserName() + " (" + item.getUserEmail() + ")");
                }
            }
        });
        return listView;
    }

    /**
     * Creates the title.
     *
     * @return the title.
     */
    private Label createTitle() {
        final Label title = new Label();
        title.setText("User Management Dominion");
        title.setFont(Font.font("Arial", NORMAL, 35));
        return title;
    }

    /**
     * Creates the HBox.
     *
     * @return The HBox.
     */
    private HBox createHBox() {
        final HBox hBox = new HBox();
        hBox.setSpacing(150);
        hBox.getChildren().addAll(createEditButton(), createCreateButton(), createDeleteButton(), createGoToLoginButton());
        return hBox;
    }

    /**
     * Creates the go to login button.
     *
     * @return the go to login button
     */
    private Button createGoToLoginButton() {
        goToLoginButton = new Button();
        goToLoginButton.setText("Go back to Login");
        return goToLoginButton;
    }

    /**
     * Creates the edit button.
     *
     * @return the edit button.
     */
    private Button createEditButton() {
        editButton = new Button();
        editButton.setText("Edit User");
        return editButton;
    }

    /**
     * Creates the delete button.
     *
     * @return the delete button.
     */
    private Button createDeleteButton() {
        deleteButton = new Button();
        deleteButton.setText("Delete user");
        return deleteButton;
    }

    /**
     * Creates the create button.
     *
     * @return the create button.
     */
    private Button createCreateButton() {
        createButton = new Button();
        createButton.setText("Create user");
        return createButton;
    }

    @Override
    protected void setTexts() {
        // nop
    }

}
