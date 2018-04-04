package Boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_5_Result {


    public void close(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void start(String input, double[] infoContorno) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/req_5_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        final Label id = new Label();
        id.setText("ID Filamento: "+ input);
        id.setLayoutX(36);
        id.setLayoutY(16);

        final Label centroide1 = new Label();
        centroide1.setText("Longitudine centroide: "+ infoContorno[0]);
        centroide1.setLayoutX(36);
        centroide1.setLayoutY(36);

        final Label centroide2 = new Label();
        centroide2.setText("Latitudine centroide: "+ infoContorno[1]);
        centroide2.setLayoutX(36);
        centroide2.setLayoutY(46);

        final Label estensione1 = new Label();
        estensione1.setText("Estensione longitudinale: "+ infoContorno[6]);
        estensione1.setLayoutX(36);
        estensione1.setLayoutY(66);

        final Label estensione2 = new Label();
        estensione2.setText("Estensione latitudinale: "+ infoContorno[7]);
        estensione2.setLayoutX(36);
        estensione2.setLayoutY(76);

        final Label segmenti = new Label();
        segmenti.setText("Numero complessivo di segmenti: "+ infoContorno[7]);
        segmenti.setLayoutX(36);
        segmenti.setLayoutY(96);

        root.getChildren().addAll(id);
        root.getChildren().addAll(centroide1);
        root.getChildren().addAll(centroide2);
        root.getChildren().addAll(estensione1);
        root.getChildren().addAll(estensione2);
        root.getChildren().addAll(segmenti);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
