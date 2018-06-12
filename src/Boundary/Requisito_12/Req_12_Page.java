package Boundary.Requisito_12;

import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonId;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_12_Page {

    public TextField textFil;
    public RadioButton rbHerschel;
    public RadioButton rbSpitzer;

    public static String sat;

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("req_12_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void search(ActionEvent actionEvent){

        try {
            DBController dbController = new DBController();
            int id = Integer.parseInt(textFil.getText());
            if (rbHerschel.isSelected()) {
                sat = "herschel";
            } else {
                sat = "spitzer";
            }
            if (!dbController.valuesControl(sat, id)){
                System.out.println("error in valuescontrol");
                return;
            }
            if (!dbController.Req12(sat, id)){
                String msg = "Non ci sono stelle all'interno\n del filamento scelto";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg);
                return;
            }
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            GraphicController graphicController = new GraphicController();
            graphicController.req12result();
        }catch (NumberFormatException n){
            try {
                Boundary.Alerts.Alert alert = new Boundary.Alerts.Alert();
                alert.incorrectLoginField("Inserire un id intero positivo");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void backHome(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetSingleton8();
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


    public void searchId(ActionEvent actionEvent) throws Exception{
        if (rbHerschel.isSelected()) {
            SingletonId.getInstance().setSatellite("herschel");
        }
        else {
            SingletonId.getInstance().setSatellite("spitzer");
        }

        DBController dbController = new DBController();
        System.out.println("satellite: "+SingletonId.getInstance().getSatellite());
        if (!dbController.cercaTuttiGliId(SingletonId.getInstance().getSatellite())) {
            String msg2 = "Errore imprevisto.";
            GraphicController graphicController = new GraphicController();
            graphicController.alertError(msg2);
        }
    }
}
