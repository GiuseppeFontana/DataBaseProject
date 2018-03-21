package Boundary;

import Dao.UserDao;
import Entity.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login extends Application {
    @FXML
    private PasswordField loginPassowrdField;
    @FXML
    private TextField loginUserTextField;
    @FXML
    private Button LoginButton;


    public void LoginMethod(ActionEvent actionEvent) {

        String username = loginUserTextField.getText();
        String password = loginPassowrdField.getText();
        //System.out.println("hai inserito :\nuser " + username + "\npass:" + password);

        User user = new User();
        user.reset();
        user.setUsername(username);
        user.setPassword(password);
        boolean result = user.validate();
        System.out.println("result:\n"+result);
        System.out.println("admin:\n"+user.getAdmin());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage thirdStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/login.fxml"));
        AnchorPane root = loader.load();
        thirdStage.setTitle("DB Project");
        Scene scene = new Scene(root, 800, 500);
        thirdStage.setScene(scene);
        thirdStage.show();
    }
}
