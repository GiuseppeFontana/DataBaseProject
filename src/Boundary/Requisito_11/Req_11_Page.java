package Boundary.Requisito_11;

import Boundary.Alerts.Alert;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import java.lang.Integer;
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
            int id = Integer.parseInt(TextIDFil.getText());
            if (rbHerschel.isSelected()) {
                sat = "herschel";
            } else {
                sat = "spitzer";
            }
            if (id < 45 || id > 227) {
                Alert alert = new Alert();
                alert.incorrectLoginField("Inserire un id intero compreso tra [45,227]");
            } else {

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                DBController dbController = new DBController();
                dbController.Req11_segment(sat, id);

                GraphicController graphicController = new GraphicController();
                graphicController.req11result();
            }
        }catch (NumberFormatException n){
            Alert alert = new Alert();
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

}

