package usermanagement;

import base.View;
import com.weddingcrashers.model.User;
import com.weddingcrashers.service.Translator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import static util.StyleSheetPath.USERMANAGEMENT;

/**
 * The user management view.
 *
 * @author dmpliamplias
 */
public class UserManagementView extends View<UserManagementModel> {

    // ---- Members

    /** The list view. */
    protected ListView<User> listView;

    /** The title label. */
    protected Label title;
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

        // root
        final VBox root = new VBox();

        // menu
        final VBox menu = new VBox();
        menuBar.getMenuSettings().getItems().remove(menuBar.getMenuSound());
        menuBar.getMenuSettings().getItems().remove(menuBar.getMenuMusic());
        menu.getChildren().add(menuBar);

        // content
        final VBox content = new VBox();
        content.setId("content");
        content.getChildren().addAll(createTitle(), createListView(), createHBox());

        root.getChildren().addAll(menu, content);

        final Scene scene = new Scene(root);
        stage.setScene(scene);

        setStylesheet(scene, USERMANAGEMENT);

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
                    final StringBuilder sb = new StringBuilder();
                    sb.append(item.getUsername()).append(" (").append(item.getUserEmail()).append(")");
                    if (item.isDeleted()) {
                        sb.append(" [DELETED]");
                    }
                    setText(sb.toString());
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
        title = new Label();
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

        // initialize buttons
        editButton = new Button();
        createButton = new Button();
        deleteButton = new Button();
        goToLoginButton = new Button();

        // add all
        hBox.getChildren().addAll(editButton, createButton, deleteButton, goToLoginButton);
        return hBox;
    }

    @Override
    protected void setTexts() {
        title.setText(getText("usermanagementview.title"));
        goToLoginButton.setText(getText("usermanagementview.gotologin"));
        editButton.setText(getText("usermanagementview.edit"));
        deleteButton.setText(getText("usermanagementview.delete"));
        createButton.setText(getText("usermanagementview.create"));
    }

    Translator getTranslator() {
        return translator;
    }

}
