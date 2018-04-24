package Boundary.Requisito_06;

import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_6_Page {

    @FXML
    private TextField Req6PageBrillanzaText;
    @FXML
    private TextField Req6PageElliptMinText;
    @FXML
    private TextField Req6PageElliptMaxText;

    public void start() throws Exception{
        Stage stage = new Stage();
/*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/giuseppe/IdeaProjects/DataBaseProject2/src/Boundary/Requisito_06/req_6_page.fxml"));
*/
        FXMLLoader loader = new FXMLLoader(Req_6_Page.class.getResource("req_6_page.fxml"));

        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void backHome(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        boolean admin = controller.getUserSingleton().getUser().getAdmin();
        if(!admin){
            GraphicController graphicController = new GraphicController();
            graphicController.homeUser();
        }
        if (admin){
            GraphicController graphicController = new GraphicController();
            graphicController.homeAdmin();
        }
    }


    public void search(ActionEvent actionEvent) {
        try {
            Double percBrillanza = Double.parseDouble(Req6PageBrillanzaText.getText());
            Double elliptMax = Double.parseDouble(Req6PageElliptMaxText.getText());
            Double elliptMin = Double.parseDouble(Req6PageElliptMinText.getText());
            GraphicController graphicController = new GraphicController();

            if (percBrillanza<0.0 || elliptMin <= 1.0 || elliptMax >= 10.0 || elliptMin > elliptMax){
                String msg1 = "Input non valido.";
                graphicController.alertError(msg1);
            }
            else {
                DBController dbController = new DBController();
                dbController.ricercaPerContrastoEdEllitticita(percBrillanza, elliptMin,elliptMax);
                //graphicController.req6Res(percBrillanza,elliptMin, elliptMax);

                /*DBController dbController = new DBController();
                if(!dbController.ricercaReq6(percBrillanza, elliptMin, elliptMax)){
                    String msg2 = "Nessun filamento trovato.";
                    GraphicController graphicController = new GraphicController();
                    graphicController.alertError(msg2);
                }
                else {
                    ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                }*/
            }
        }
        catch (NumberFormatException nfe){
            try {
                String msg1 = "Input numerico scoretto:\nla % deve essere maggiore di 0,\ni limiti di ellitticità maggiori di 0 e\nminori di 10.\n(es. 23; 5.1; 7)";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg1);
                nfe.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
