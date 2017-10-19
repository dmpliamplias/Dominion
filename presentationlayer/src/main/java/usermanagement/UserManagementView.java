package usermanagement;

import base.View;
import com.weddingcrashers.model.User;
import com.weddingcrashers.service.ServiceLocator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import static com.weddingcrashers.service.ServiceLocator.getServiceLocator;

/**
 * The user management view.
 *
 * @author dmpliamplias
 */
public class UserManagementView extends View<UserManagementModel> {

    // ---- Members

    /** The list view. */
    private ListView<User> listView;

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

    private final ServiceLocator serviceLocator;


    // ---- Constructor

    /**
     * Set any options for the stage in the subclass constructor
     *
     * @param stage the stage.
     * @param model the model.
     */
    public UserManagementView(Stage stage, UserManagementModel model) {
        super(stage, model);

        serviceLocator = getServiceLocator();
        final List<User> users = serviceLocator.getUserService().list();
        this.users.addAll(users);
    }


    // ---- Methods

    @Override
    protected Scene create_GUI() {
        stage.setHeight(900);
        stage.setWidth(1500);

        // layout
        final BorderPane root = new BorderPane();
        final GridPane gridPane = new GridPane();
        final HBox topBox = new HBox();
        final HBox bottomBox = new HBox();
        final VBox leftBox = new VBox();
        final VBox rightBox = new VBox();

        // set layout
        root.setCenter(gridPane);
        root.setTop(topBox);
        root.setBottom(bottomBox);
        root.setLeft(leftBox);
        root.setRight(rightBox);

        // initialize fields
        listView = new ListView<>(users);
        name = new TextField();
        email = new TextField();
        password = new TextField();
        isBlocked = new CheckBox();
        isSuperUser = new CheckBox();
        editButton = new Button();
        confirmButton = new Button();
        cancelButton = new Button();


//        hBox.getChildren().addAll();
//        gridPane.add(listView, 1, 1);

        final Scene scene = new Scene(root);
        stage.setScene(scene);

        return scene;
    }

    @Override
    protected void setTexts() {

    }

}
