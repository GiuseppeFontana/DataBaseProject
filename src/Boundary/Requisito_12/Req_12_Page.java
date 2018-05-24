package Boundary.Requisito_12;

import Boundary.Alerts.Alert;
import Control.DBController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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


    public void search(ActionEvent actionEvent) throws Exception {

        try {
            int id = Integer.parseInt(textFil.getText());
            if (rbHerschel.isSelected()) {
                sat = "herschel";
            } else {
                sat = "spitzer";
            }
            if (id < 45 || id > 227) {
                Alert alert = new Alert();
                alert.incorrectLoginField("");
            } else {

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                DBController dbController = new DBController();
                dbController.Req12(sat, id);

                /*GraphicController graphicController = new GraphicController();
                //graphicController.req12result();
                graphicController.req12result();*/
            }
        }catch (NumberFormatException n){
            Alert alert = new Alert();
            alert.incorrectLoginField("Inserire un id intero positivo");

        }
    }



}
