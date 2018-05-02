package Boundary.ShowElement;

import Control.Controller;
import Singletons.SingletonStruct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Show_Struct {
    @FXML
    private Button btnTurnBack;

    public void show() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show_struct.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 500, 550 );

        final Label labelName = new Label();
        final Label labelId = new Label();
        final Label labelFlux = new Label();
        final Label labelDens = new Label();
        final Label labelTemp = new Label();
        final Label labelEllipt = new Label();
        final Label labelContrast = new Label();
        final Label labelSatellite = new Label();
        final Label labelInstrument = new Label();

        labelName.relocate(100,50);
        labelId.relocate(100, 90);
        labelFlux.relocate(100, 130);
        labelDens.relocate(100, 170);
        labelTemp.relocate(100, 210);
        labelEllipt.relocate(100, 250);
        labelContrast.relocate(100, 290);
        labelSatellite.relocate(100, 330);
        labelInstrument.relocate(100, 370);

        labelName.setText("Nome: "+ SingletonStruct.getInstance().getStructure().getName());
        labelId.setText("ID: "+ SingletonStruct.getInstance().getStructure().getId());
        labelFlux.setText("Flusso: "+ SingletonStruct.getInstance().getStructure().getFlux());
        labelDens.setText("Densità media: "+ SingletonStruct.getInstance().getStructure().getMeanDens());
        labelTemp.setText("Temperatura media: "+ SingletonStruct.getInstance().getStructure().getMeanTemp());
        labelEllipt.setText("Ellitticità: "+ SingletonStruct.getInstance().getStructure().getEllipt());
        labelContrast.setText("Contrasto: "+ SingletonStruct.getInstance().getStructure().getContrast());
        labelSatellite.setText("Satellite: "+ SingletonStruct.getInstance().getStructure().getSatellite());
        labelInstrument.setText("Strumento: "+ SingletonStruct.getInstance().getStructure().getInstrument());

        root.getChildren().addAll(labelName);
        root.getChildren().addAll(labelId);
        root.getChildren().addAll(labelFlux);
        root.getChildren().addAll(labelDens);
        root.getChildren().addAll(labelTemp);
        root.getChildren().addAll(labelEllipt);
        root.getChildren().addAll(labelContrast);
        root.getChildren().addAll(labelSatellite);
        root.getChildren().addAll(labelInstrument);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent actionEvent) throws Exception {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetStructSingleton();
    }
}
