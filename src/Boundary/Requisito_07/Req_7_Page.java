package Boundary.Requisito_07;

import Control.Controller;
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

public class Req_7_Page {

    @FXML
    private TextField Req7PageMinText;
    @FXML
    private TextField Req7PageMaxText;

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


    public void search(ActionEvent actionEvent){
        try {

            int Min = Integer.parseInt(Req7PageMinText.getText());
            int Max = Integer.parseInt(Req7PageMaxText.getText());

            if (Min <= 2 || Min > Max){
                String msg1 = "Input non valido.\n(Il minimo deve essere >2\ncontrolla min e max)";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg1);
            }
            else {
                DBController dbController = new DBController();
                if(!dbController.ricercaPerNumeroSegmenti(Min, Max)){
                    String msg2 = "Nessun filamento trovato.";
                    GraphicController graphicController = new GraphicController();
                    graphicController.alertError(msg2);
                }
                else {
                    ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                }
            }
        }
        catch (NumberFormatException nfe){
            try {
                String msg1 = "Input numerico scoretto.\nScrivi numeri interi.";
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

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_7_Page.class.getResource("req_7_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
