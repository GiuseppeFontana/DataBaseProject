package Boundary.ShowElement;

import Control.Controller;
import Singletons.SingletonStar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Show_Star {

    public void show() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show_star.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 500, 550 );

        final Label labelName = new Label();
        final Label labelId = new Label();
        final Label labelFlux = new Label();
        final Label labelType = new Label();
        final Label labelLon = new Label();
        final Label labelLat = new Label();

        labelName.relocate(100,50);
        labelId.relocate(100, 90);
        labelFlux.relocate(100, 130);
        labelType.relocate(100, 170);
        labelLon.relocate(100, 210);
        labelLat.relocate(100, 250);


        labelName.setText("Nome: "+ SingletonStar.getInstance().getStar().getName());
        labelId.setText("ID: "+ SingletonStar.getInstance().getStar().getId());
        labelFlux.setText("Flusso: "+ SingletonStar.getInstance().getStar().getFlux());
        labelType.setText("Tipo di stella: "+ SingletonStar.getInstance().getStar().getType());
        labelLon.setText("Longitudine: "+ SingletonStar.getInstance().getStar().getgLon());
        labelLat.setText("Latitudine: "+ SingletonStar.getInstance().getStar().getgLat());


        root.getChildren().addAll(labelName);
        root.getChildren().addAll(labelId);
        root.getChildren().addAll(labelFlux);
        root.getChildren().addAll(labelType);
        root.getChildren().addAll(labelLon);
        root.getChildren().addAll(labelLat);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent actionEvent){
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetStarSingleton();
    }
}
