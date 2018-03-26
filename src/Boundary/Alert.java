package Boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Alert {

    public void showAlert(String message) throws Exception {
        Stage sixthStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Boundary/alert.fxml"));
        sixthStage.setScene(new Scene(root, 257, 154));
        sixthStage.setResizable(false);

        final Label label = new Label();

        label.setText(message);
        label.setLayoutX(24);
        label.setLayoutY(25);
        root.getChildrenUnmodifiable().addAll(label);

        sixthStage.show();
    }

    public void turnBack(ActionEvent actionEvent) throws Exception {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
