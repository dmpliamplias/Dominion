package register;

import java.lang.StringBuilder;
import register.RegisterModel;
import app.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;

/**
 * @author Murat Kelleci
 */

public class RegisterValidator{

    // Simple Email Validation per Regex. Du könntest auch anstelle einer Regex eine Library verwenden. siehe http://crunchify.com/how-to-validate-email-address-using-java-mail-api/
    private static String EMAIL_VALIDATION = "[A-Z0-9._%+-][A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{3}";

    public String validateModel(RegisterModel model){
        StringBuilder sb = new StringBuilder();
        String emailMessage = validateEmail(model.getEmail());
        if(emailMessage != null){
            sb.append(emailMessage);
            sb.append("\n");
        }

        String pwMessage = validatePW(model.getPassword(), model.getPassword_confirm();
        if(pwMessage != null){
            sb.append(pwMessage);
        }

        // Wenn eine Message gesetzt ist, dann wird sie zurückgegeben, ansonsten wird null zurückgegeben.
        return sb.length() >= 0 ? sb.toString() : null;
    }

    private String validateEmail(String email){
        if(email.matches(EMAIL_VALIDATION)){
            return ServiceLocator.getServiceLocator().
                    getTranslator().getString("RegisterView_Error_EmailSyntaxFailed");
        }

        User user =  ServiceLocator.getUserService().getUserByEmail(email);
        if(user != null){
            return ServiceLocator.getServiceLocator().
                    getTranslator().getString("RegisterView_Error_EmailAlreadyExists");
        }
        return null;
    }

    private String validatePW(String password, String passwordConfirm){
        if(!password.equals(passwordConfirm)){
            return ServiceLocator.getServiceLocator().
                    getTranslator().getString("RegisterView_Error_PWConfirm_False");
        }
        return null;
    }

}
