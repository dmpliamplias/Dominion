package usermanagement.dialog.user;

import com.weddingcrashers.model.User;
import com.weddingcrashers.service.Translator;

/**
 * The create user dialog.
 *
 * @author dmpliamplias
 */
public class CreateUserDialog extends BaseUserDialog {

    // ---- Constructor

    /**
     * Constructor.
     *
     * @param user the user.
     */
    public CreateUserDialog(final User user, Translator translator) {
        super(user, translator);
    }


    // ---- Methods

    @Override
    protected void fillFields() {
        idField.setText("generated");
    }

    @Override
    protected String title(Translator translator) {
        return translator.getString("usermanagementview.createDialog.title");
    }

    @Override
    protected String headerText(Translator translator) {
        return translator.getString("usermanagementview.createDialog.headerText");
    }

}
