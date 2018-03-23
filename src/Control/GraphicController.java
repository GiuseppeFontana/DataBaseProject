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

    public void incorrect() throws Exception{
        IncorrectLoginFields incorrectLoginFields = new IncorrectLoginFields();
        incorrectLoginFields.incorrectLoginField();
    }

    public void homeUser() throws Exception{
        Home_User home_user = new Home_User();
        home_user.start();
    }

    public void homeAdmin() throws Exception {
        Home_Admin home_admin = new Home_Admin();
        home_admin.start();
    }

    public void adminReqs() throws Exception{
        Req_3_Page req_3_page = new Req_3_Page();
        req_3_page.start();
    }
}
