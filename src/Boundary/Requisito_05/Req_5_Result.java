package Boundary.Requisito_05;

import Control.Controller;
import Singletons.SingletonReq5;
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
        Controller controller = new Controller();
        controller.resetingleton5();
    }

    public void start() throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_5_Result.class.getResource("req_5_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        final Label nome = new Label();
        nome.setText("Nome Filamento: "+ SingletonReq5.getInstance().getBean().getName());
        nome.setLayoutX(60);
        nome.setLayoutY(100);

        final Label id = new Label();
        id.setText("ID Filamento: "+ SingletonReq5.getInstance().getBean().getId());
        id.setLayoutX(60);
        id.setLayoutY(130);

        final Label centroide1 = new Label();
        centroide1.setText("Longitudine centroide: "+ SingletonReq5.getInstance().getBean().getLonCenter());
        centroide1.setLayoutX(60);
        centroide1.setLayoutY(200);

        final Label centroide2 = new Label();
        centroide2.setText("Latitudine centroide: "+ SingletonReq5.getInstance().getBean().getLatCenter());
        centroide2.setLayoutX(60);
        centroide2.setLayoutY(230);

        final Label estensione1 = new Label();
        estensione1.setText("Estensione longitudinale: "+ SingletonReq5.getInstance().getBean().getLonExtension());
        estensione1.setLayoutX(60);
        estensione1.setLayoutY(300);

        final Label estensione2 = new Label();
        estensione2.setText("Estensione latitudinale: "+ SingletonReq5.getInstance().getBean().getLatExtension());
        estensione2.setLayoutX(60);
        estensione2.setLayoutY(330);

        final Label segmenti = new Label();
        segmenti.setText("Numero complessivo di segmenti: "+ SingletonReq5.getInstance().getBean().getnSegmenti());
        segmenti.setLayoutX(60);
        segmenti.setLayoutY(400);

        root.getChildren().addAll(id);
        root.getChildren().addAll(nome);
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
