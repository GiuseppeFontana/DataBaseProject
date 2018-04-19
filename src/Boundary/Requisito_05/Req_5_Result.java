package Boundary.Requisito_05;

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

    public void start(String input, double[] infoContorno, int nSegmenti) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_5_Result.class.getResource("req_5_result.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/Requisito_05/req_5_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        final Label id = new Label();
        id.setText("ID Filamento: "+ input);
        id.setLayoutX(60);
        id.setLayoutY(100);

        final Label centroide1 = new Label();
        centroide1.setText("Longitudine centroide: "+ infoContorno[0]);
        centroide1.setLayoutX(60);
        centroide1.setLayoutY(150);

        final Label centroide2 = new Label();
        centroide2.setText("Latitudine centroide: "+ infoContorno[1]);
        centroide2.setLayoutX(60);
        centroide2.setLayoutY(180);

        final Label estensione1 = new Label();
        estensione1.setText("Estensione longitudinale: "+ infoContorno[6]);
        estensione1.setLayoutX(60);
        estensione1.setLayoutY(250);

        final Label estensione2 = new Label();
        estensione2.setText("Estensione latitudinale: "+ infoContorno[7]);
        estensione2.setLayoutX(60);
        estensione2.setLayoutY(280);

        final Label segmenti = new Label();
        segmenti.setText("Numero complessivo di segmenti: "+ nSegmenti);
        segmenti.setLayoutX(60);
        segmenti.setLayoutY(350);

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
