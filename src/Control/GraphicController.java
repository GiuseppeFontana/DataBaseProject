package Control;

import Boundary.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class GraphicController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Login login = new Login();
        login.start(primaryStage);

    }

    public void incorrectLogin(String message) throws Exception{

        //String prova = "stampato.";
        /*Alert alert = new Alert();
        alert.showAlert(message);*/

        IncorrectLoginFields incorrectLoginFields = new IncorrectLoginFields();
        incorrectLoginFields.incorrectLoginField(message);
    }

    public void homeUser() throws Exception{
        Home_User home_user = new Home_User();
        home_user.start();
    }

    public void homeAdmin() throws Exception {
        Home_Admin home_admin = new Home_Admin();
        home_admin.start();
    }

    public void adminReqsPage() throws Exception{
        Req_3_Page req_3_page = new Req_3_Page();
        req_3_page.start();
    }

    /*public void incorrectPasswords() throws Exception {
        IncorrectPasswords incorrectPasswords = new IncorrectPasswords();
        incorrectPasswords.incorrectPasswordsField();
    }*/

    public void req32Page() throws Exception{
        RegisterPage register_page = new RegisterPage();
        register_page.start();
    }

    /*public void incorrectFields() throws Exception {
        IncorrectFields incorrectFields = new IncorrectFields();
        incorrectFields.incorrectField();
    }*/
}
