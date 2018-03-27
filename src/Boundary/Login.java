package Boundary;

import Control.DBController;
import Control.GraphicController;
import Dao.UserDao;
import Entity.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    private boolean b;


    public void LoginMethod(ActionEvent actionEvent) throws Exception {

        String username = loginUserTextField.getText();
        String password = loginPassowrdField.getText();

        DBController dbController = new DBController();

        if(username.isEmpty() || password.isEmpty()){
            GraphicController graphicController = new GraphicController();
            String message = "Completare tutti i campi!";
            graphicController.incorrectLogin(message);
        }else {
            if (dbController.login(username, password)){                           //Se il login va a buon fine, chiude
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();   //la pagina di login e lancia home user
            }                                                                      //altrimenti lancia messaggio di incorect fields
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage thirdStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/login.fxml"));
        AnchorPane root = loader.load();
        thirdStage.setTitle("Interstellar");
        Scene scene = new Scene(root, 600, 338);
        thirdStage.setScene(scene);
        thirdStage.show();
    }
}
