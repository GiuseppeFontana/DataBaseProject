package Boundary.Alerts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Alert {
    @FXML
    private Button btnTurnBack;

    public void incorrectLoginField(String message) throws Exception {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Alert.class.getResource("alert.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 321, 182 );

        final Label usrn = new Label();

        usrn.setText(message);
        usrn.setLayoutX(36);
        usrn.setLayoutY(16);

        root.getChildren().addAll(usrn);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void turnToLogin(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
