package register;

public class RegisterModel {

    /** @author Murat Kelleci
 **/

    private String email;
    private String password;
    private String password_confirm;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getPassword() {
        return password;

    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getPassword_confirm(){
        return password_confirm;

    }

    public void setPassword_confirm(String password_confirm){

        this.password_confirm=password_confirm;
    }

}


