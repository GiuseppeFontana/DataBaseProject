package Boundary.Requisito_11;

import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonId;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import java.lang.Integer;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_11_Page {
    @FXML
    private RadioButton rbHerschel;
    @FXML
    private TextField TextIDFil;

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_11_Page.class.getResource("req_11_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void search(ActionEvent actionEvent) throws Exception {

        String sat;

        try {
            DBController dbController = new DBController();

            int id = Integer.parseInt(TextIDFil.getText());
            if (rbHerschel.isSelected()) {
                sat = "herschel";
            } else {
                sat = "spitzer";
            }

            if (dbController.valuesControl(sat, id)) {
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                dbController.Req11_segment(sat, id);

                GraphicController graphicController = new GraphicController();
                graphicController.req11result();
            }

        }catch (NumberFormatException n){
            Boundary.Alerts.Alert alert = new Boundary.Alerts.Alert();
            alert.incorrectLoginField("Inserire un id intero positivo");

        }
    }


    public void backHome(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        boolean admin = controller.getUserSingleton().getUser().getAdmin();
        if (!admin) {
            GraphicController graphicController = new GraphicController();
            graphicController.homeUser();
        }
        if (admin) {
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

