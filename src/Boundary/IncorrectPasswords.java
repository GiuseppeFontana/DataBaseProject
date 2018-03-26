package Boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class IncorrectPasswords {
    @FXML
    private Button btnTurnBack;

    public void incorrectPasswordsField() throws Exception {
        Stage sixthStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Boundary/incorrectPasswords.fxml"));
        sixthStage.setScene(new Scene(root, 257, 154));
        sixthStage.setResizable(false);
        sixthStage.show();
    }

    public void turnToLogin(ActionEvent actionEvent) throws Exception {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }
}
