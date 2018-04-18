package Prove;

import Control.Controller;
import Control.GraphicController;
import Entity.Structure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Prova_Req_6_Res {
    @FXML
    public Button Req6ResBackButton;
    @FXML
    public Button Req6ResNext;
    @FXML
    public Button Req6ResPrev;
    @FXML
    public Label labelResult;
    @FXML
    public Label labelPage;
    @FXML
    public int nCurrentPage;
    @FXML
    public int nTotalPages;
    @FXML
    public ArrayList<Structure> strutture = new ArrayList<>();
    @FXML
    public int struttTotali;

    public void start(ArrayList<Structure> structures, int[] struttureTotali, int[]page) throws Exception{

        //this.strutture = structures;
        for(int i = 0; i<structures.size(); i++){
            this.strutture.add(structures.get(i));
        }
        this.struttTotali = struttureTotali[0];


        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("prova_req_6_res.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        labelResult = new Label();
        labelResult.setText("Trovate "+strutture.size()+" strutture su "+ this.struttTotali);
        labelResult.setLayoutX(220);
        labelResult.setLayoutY(50);
        root.getChildren().addAll(labelResult);

        this.nCurrentPage = page[0];
        if (strutture.size()%20!=0){
            nTotalPages = strutture.size()/20 + 1;
        }
        else {
            nTotalPages = strutture.size()/20;
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


    public void next(ActionEvent actionEvent) throws Exception{
        /*int[] nextPage = new int[1];
        nextPage[0] = this.nCurrentPage +1;
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

        int[] newTotali = new int[1];
        newTotali[0] = this.struttTotali;
        int[] newPage = new int[1];
        newPage[0] = this.nCurrentPage+1;*/

        System.out.println("size:"+this.strutture.size());

        /*GraphicController graphicController = new GraphicController();
        graphicController.req6result(this.strutture, newTotali, newPage);*/
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
