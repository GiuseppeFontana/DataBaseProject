package Boundary;

import Control.Controller;
import Control.GraphicController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Req_7_Page {
    @FXML
    private RadioButton Req7PageRadio1;
    @FXML
    private RadioButton Req7PageRadio2;
    @FXML
    private TextField Req7PageTextMin;
    @FXML
    private TextField Req7PageTextMax;

    private String satellite;

    public void start() throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/req_7_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void backHome(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        boolean admin = controller.getSingleton().getUser().getAdmin();
        if (!admin) {
            GraphicController graphicController = new GraphicController();
            graphicController.homeUser();
        }
        if (admin) {
            GraphicController graphicController = new GraphicController();
            graphicController.homeAdmin();
        }
    }

    public void check1(ActionEvent actionEvent) throws Exception {
        /*if (Req7PageRadio2.isSelected()) {
            Req7PageRadio2.setSelected(false);
        }*/
        satellite = "herschel";
    }

    public void check2(ActionEvent actionEvent) throws Exception {
        /*if (Req7PageRadio1.isSelected()) {
            Req7PageRadio1.setSelected(false);
        }*/
        satellite = "spitzer";
    }
}


