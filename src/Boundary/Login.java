package Boundary;

import Control.DBController;
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

        DBController dbController = new DBController();
        dbController.login(username, password);

        /*User user = new User();
        user.reset();
        user.setUsername(username);
        user.setPassword(password);
        if (user.validate()){

            // TODO passa alla schermata successiva
        }*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage thirdStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/login.fxml"));
        AnchorPane root = loader.load();
        thirdStage.setTitle("DB Project");
        Scene scene = new Scene(root, 600, 400);
        thirdStage.setScene(scene);
        thirdStage.show();
    }
}
