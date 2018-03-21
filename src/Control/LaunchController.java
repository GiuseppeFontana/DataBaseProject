package Control;

import Boundary.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class LaunchController extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Login login = new Login();
        login.start(primaryStage);

    }
}
