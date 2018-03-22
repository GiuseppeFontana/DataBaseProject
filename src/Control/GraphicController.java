package Control;

import Boundary.Home_User;
import Boundary.IncorrectLoginFields;
import Boundary.Login;
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

}
