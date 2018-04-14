package Boundary;

import Control.Controller;
import Control.GraphicController;
import Entity.Structure;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Prova_Req_6_Res {
    public Button Req6ResBackButton;
    public Button Req6ResNext;
    public Button Req6ResPrev;

    public Label labelResult;
    public Label labelPage;

    private int nCurrentPage;
    private int nTotalPages;

    public void start(ArrayList<Structure> structures, int[] struttureTotali) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/prova_req_6_res.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        labelResult = new Label();
        labelResult.setText("Trovate "+structures.size()+" strutture su "+struttureTotali[0]);
        labelResult.setLayoutX(220);
        labelResult.setLayoutY(50);
        root.getChildren().addAll(labelResult);

        nCurrentPage = 1;
        if (structures.size()%20!=0){
            nTotalPages = structures.size()/20 + 1;
        }
        else {
            nTotalPages = structures.size()/20;
        }
        labelPage = new Label();
        labelPage.setText("Pagina "+nCurrentPage+" di "+nTotalPages);
        labelPage.setLayoutX(300);
        labelPage.setLayoutY(520);
        root.getChildren().addAll(labelPage);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void next(ActionEvent actionEvent) {

    }

    public void prev(ActionEvent actionEvent) {
    }

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
}
