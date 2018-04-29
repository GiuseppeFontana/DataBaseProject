package Boundary.Requisito_09;

import Control.Controller;
import Control.GraphicController;
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

public class Req_9_Page {

    @FXML
    private RadioButton RadioHerschel;
    @FXML
    private RadioButton RadioSpitzer;
    @FXML
    private TextField IDStructuresText;
    @FXML
    private Button Req9SearchButton;
    @FXML
    private Button Req9BackButton;

    private String satellite;

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_9_Page.class.getResource("req_9_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void search(ActionEvent actionEvent) {
    }

    public void backHome(ActionEvent actionEvent) throws Exception  {
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

    public void checkSpitzer(ActionEvent actionEvent) throws Exception{
        satellite = "spitzer";
    }

    public void checkHerschel(ActionEvent actionEvent) throws Exception {
        satellite = "herschel";
    }
}
