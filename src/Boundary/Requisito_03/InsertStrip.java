package Boundary.Requisito_03;

import Control.DBController;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InsertStrip {


    public TextField Req34InstrumentText;
    public TextField Req34StripText;

    public void backHome(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.adminReqsPage();
    }

    public void Inserisci(ActionEvent actionEvent){
        try {
            String instrument = Req34InstrumentText.getText();
            double strip = Double.parseDouble(Req34StripText.getText());

            if (instrument.equals("")){
                GraphicController graphicController = new GraphicController();
                String msg1 = "Inserire il nome dello Strumento.";
                graphicController.alertError(msg1);
            } else {
                DBController dbController = new DBController();
                if (!dbController.inserimentoBanda(instrument, strip)) {
                    GraphicController graphicController = new GraphicController();
                    String msg2 = "Inserimento Banda fallito.";
                    graphicController.alertError(msg2);
                }
                else {
                    GraphicController graphicController = new GraphicController();
                    String msg2 = "Inserimento Banda effettuato \ncon successo.";
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                    graphicController.adminReqsPage();
                    graphicController.alertError(msg2);
                }
            }
        }
        catch (NumberFormatException nfe){
            try {
                String msg = "Input Banda non valido.\nScrivi un numero decimale.";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertInstrument.class.getResource("insertStrip.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
