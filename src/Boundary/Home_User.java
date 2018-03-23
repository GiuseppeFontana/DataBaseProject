package Boundary;

import Entity.User;
import Utils.ClassicSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;

public class Home_User {
    @FXML
    private Button btnInformation;
    @FXML
    private Button HomeBtnLogOut;


    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/home_user.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        User user = ClassicSingleton.getInstance().getUser();
        String name = user.getName();

        final Label HomeLabel1 = new Label();

        HomeLabel1.setText("Benvenuto " + name);
        HomeLabel1.setLayoutX(320);
        HomeLabel1.setLayoutY(42);
        root.getChildren().addAll(HomeLabel1);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void information(ActionEvent actionEvent) throws Exception {

        User_Information user_information = new User_Information();
        user_information.start();

    }

    public void Logout(ActionEvent actionEvent) throws Exception{

        User user = ClassicSingleton.getInstance().getUser();
        user.reset();
        System.out.println("Logged out\n");

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

        Login login = new Login();
        Stage stage = new Stage();
        login.start(stage);

    }
}
