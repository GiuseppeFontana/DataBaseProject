package Boundary.Requisito_03;

import Boundary.Requisito_06.Req_6_Page;
import Control.DBController;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InsertInstrument {

    @FXML
    public TextField Req34InstrumentText;
    @FXML
    public TextField Req34SatText;
    @FXML
    public Button Req34InsertButton;
    @FXML
    public Button Req34BackButton;


    public void start() throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertInstrument.class.getResource("insertInstrument.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void backHome(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.adminReqsPage();
    }

    public void Inserisci(ActionEvent actionEvent) {
        try {
            String instrument = Req34InstrumentText.getText();
            String sat = Req34SatText.getText();

            if ((instrument.equals("") || sat.equals(""))){
                GraphicController graphicController = new GraphicController();
                String msg1 = "Inserire tutti i campi";
                graphicController.alertError(msg1);
            } else {
                DBController dbController = new DBController();
                if (!dbController.inserimentoStrumento(instrument,sat)) {
                    String msg = "Inserimento Strumento fallito.";
                    GraphicController graphicController = new GraphicController();
                    graphicController.alertError(msg);
                } else {
                    GraphicController graphicController = new GraphicController();
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                    graphicController.adminReqsPage();
                    String msg2 = "Inserimento effettuato\n con successo.";
                    graphicController.alertError(msg2);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
