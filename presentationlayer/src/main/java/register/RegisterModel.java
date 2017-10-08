package register;

import base.Model;



/**
 * @author Murat Kelleci
 */

public class RegisterModel extends Model {



    public String email;
    public String userName;
    public String password;
    public String password_confirm;
    public String error;

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

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public String getPassword() {
        return password;

    }


    public void setPassword(String password){
        this.password = password;

    }

    public String getPassword_confirm(){
        return password_confirm;

    }

    public void setPassword_confirm(String password_confirm){
        this.password_confirm = password_confirm;
    }


}


