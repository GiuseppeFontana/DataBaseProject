package Boundary.Requisito_05;

import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_5_Page {
    @FXML
    private RadioButton Req5PageRadio1;
    @FXML
    private RadioButton Req5PageRadio2;
    @FXML
    private TextField Req5PageText;
    /*@FXML
    private Button Req5PageSearchButton;*/

    private String satellite;         // "Herschel" o "Spitzer"

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_5_Page.class.getResource("req_5_page.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/../Requisito_03/5/req_5_page.fxml"));
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

    public void check1(ActionEvent actionEvent) throws Exception{
        /*if (Req5PageRadio2.isSelected()){
            Req5PageRadio2.setSelected(false);
        }*/
        satellite = "herschel";
    }

    public void check2(ActionEvent actionEvent) throws Exception {
        /*if (Req5PageRadio1.isSelected()){
            Req5PageRadio1.setSelected(false);
        }*/
        satellite = "spitzer";
    }


    public void search(ActionEvent actionEvent) {
        try {
            int input = Integer.parseInt(Req5PageText.getText());
            if (input<1 || (!Req5PageRadio1.isSelected() && !Req5PageRadio2.isSelected())) {
                String msg1 = "Input non valido.";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg1);
            } else {
                DBController dbController = new DBController();
                if (!dbController.infoDerivateFilamento(satellite, input)) {
                    String msg2 = "Filamento non trovato.";
                    GraphicController graphicController = new GraphicController();
                    graphicController.alertError(msg2);
                }

            }
        }catch (NumberFormatException nfe){
            try {
                String msg1 = "Input numerico scoretto.";
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


