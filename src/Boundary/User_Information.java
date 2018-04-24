package Boundary;

import Entity.User;
import Singletons.SingletonUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class User_Information{

    @FXML
    private Button btnOk;
    @FXML
    private Label infrLabel;


    public void start() throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("user_Information.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 326, 189 );

        User user = SingletonUser.getInstance().getUser();
        String name = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String username = user.getUsername();

        final Label nome = new Label();
        final Label cognome = new Label();
        final Label mail = new Label();
        final Label usrn = new Label();

        nome.setText("Nome: " + name);
        nome.setLayoutX(40);
        nome.setLayoutY(47);

        cognome.setText("Cognome: " + surname);
        cognome.setLayoutX(40);
        cognome.setLayoutY(72);

        mail.setText("Email: " + email);
        mail.setLayoutX(40);
        mail.setLayoutY(95);

        usrn.setText("Username: " + username);
        usrn.setLayoutX(40);
        usrn.setLayoutY(118);

        root.getChildren().addAll(nome, cognome, mail, usrn);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent actionEvent) {

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }
}
