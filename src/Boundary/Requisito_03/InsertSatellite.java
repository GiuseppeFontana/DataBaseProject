package Boundary.Requisito_03;

import Control.DBController;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.time.LocalDate;


public class InsertSatellite {

    @FXML
    private TextField Req33SatelliteText;
    @FXML
    private DatePicker Req33BeginActDate;
    @FXML
    private DatePicker Req33EndActDate;
    @FXML
    private TextField Req33AgencyText;
    @FXML
    private Button Req33InsertButton;
    @FXML
    private Button Req33BackButton;



    public void Inserisci(ActionEvent actionEvent) throws Exception{


        String satellite = Req33SatelliteText.getText();
        LocalDate beginact = Req33BeginActDate.getValue();
        LocalDate endact = Req33EndActDate.getValue();
        String agency = Req33AgencyText.getText();

        if (satellite.equals("") || agency.equals("")){
            GraphicController graphicController = new GraphicController();
            String msg1 = "Inserire tutti i campi";
            graphicController.alertError(msg1);
        }
        else {
            DBController dbController = new DBController();
            if (dbController.inserimentoSatellite(satellite, beginact, endact, agency)) {
                GraphicController graphicController = new GraphicController();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                graphicController.adminReqsPage();
                String msg2 = "Inserimento effettuato con successo.";
                graphicController.alertError(msg2);
            }
        }

    }

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Requisito_03/insertSatellite.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void backHome(ActionEvent actionEvent) throws Exception {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.adminReqsPage();
    }

}
