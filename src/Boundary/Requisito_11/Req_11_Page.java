package Boundary.Requisito_11;

import Control.DBController;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_11_Page {

    @FXML
    private RadioButton rbHerschel;
    @FXML
    private RadioButton rbSpitzer;
    @FXML
    private TextField TextIDFil;
    @FXML
    private Button btnSearch;

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

        int id = Integer.parseInt(TextIDFil.getText());

        if (rbHerschel.isSelected()){
            sat = "herschel";
        }else {
            sat = "spitzer";
        }

        DBController dbController = new DBController();
        dbController.Req11_segment(sat, id, 0);

        GraphicController graphicController = new GraphicController();
        graphicController.req11result();

        /*Req11Dao req11Dao = new Req11Dao();
        req11Dao.numeroSegmenti(sat, id);*/


    }

}

