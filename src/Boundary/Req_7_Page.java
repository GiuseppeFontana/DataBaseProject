package Boundary;

import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Entity.Structure;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Req_7_Page {

    public Button Req7PageBackButton;
    public RadioButton Req7PageRadio1;
    public RadioButton Req7PageRadio2;
    public Button Req7PageSearchButton;
    public TextField Req7PageMinText;
    public TextField Req7PageMaxText;
    private String satellite;

    public void backHome(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        boolean admin = controller.getSingleton().getUser().getAdmin();
        if(!admin){
            GraphicController graphicController = new GraphicController();
            graphicController.homeUser();
        }
        if (admin){
            GraphicController graphicController = new GraphicController();
            graphicController.homeAdmin();
        }
    }

    public void check1(ActionEvent actionEvent) {
        satellite = "herschel";
    }

    public void check2(ActionEvent actionEvent) {
        satellite = "spitzer";
    }

    public void search(ActionEvent actionEvent) {
        try {
            int Max = Integer.parseInt(Req7PageMaxText.getText());
            int Min = Integer.parseInt(Req7PageMinText.getText());

            if (Min < 2 || Min > Max || (!Req7PageRadio1.isSelected() && !Req7PageRadio2.isSelected())){
                String msg1 = "Input non valido.\n(Il minimo deve essere >1)";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg1);
            }
            else {
                DBController dbController = new DBController();
                ArrayList<Structure> structures = new ArrayList<>();
                if(!dbController.ricercaPerNumeroSegmenti(structures, satellite, Min, Max)){
                    String msg2 = "Nessun filamento trovato.";
                    GraphicController graphicController = new GraphicController();
                    graphicController.alertError(msg2);
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
}
