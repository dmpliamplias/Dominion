package usermanagement;

import base.View;
import com.weddingcrashers.model.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    /** The name field. */
    private TextField name;
    /** The email field */
    private TextField email;
    /** The password field. */
    private TextField password;
    /** The is blocked checkbox. */
    private CheckBox isBlocked;

    /** The edit button. */
    private Button editButton;
    /** The confirm button. */
    private Button confirmButton;
    /** The cancel button. */
    private Button cancelButton;


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
        return null;
    }

    @Override
    protected void setTexts() {

    }

}
