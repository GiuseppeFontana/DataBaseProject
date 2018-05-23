package Boundary.Requisito_08;

import Control.Controller;
import Control.DBController;
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

public class Req_8_Page {
    @FXML
    private RadioButton Radio1;
    @FXML
    private RadioButton Radio2;
    @FXML
    private TextField LongCentreText;
    @FXML
    private TextField LatCentreText;
    @FXML
    private TextField DimensionText;
    @FXML
    private Button SearchButton;
    @FXML
    private Button BackButton;

    private static String tipoRicerca;

    public static String getTipoRicerca() {
        return tipoRicerca;
    }

    public static void setTipoRicerca(String tr) {
        tipoRicerca = tr;
    }

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_8_Page.class.getResource("req_8_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void check1(ActionEvent actionEvent) {
        setTipoRicerca("square");
    }

    public void check2(ActionEvent actionEvent) {
        setTipoRicerca("circular");
    }

    public void search(ActionEvent actionEvent) {
        try {
            if (LongCentreText.getText().equals("") || LatCentreText.getText().equals("") || DimensionText.getText().equals("") ||
                    (!Radio1.isSelected() && !Radio2.isSelected())){
                String msg1 = "Input non valido.";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg1);
            }
            else {
                Double longitude = Double.parseDouble(LongCentreText.getText());
                Double latitude = Double.parseDouble(LatCentreText.getText());
                Double dimension = Double.parseDouble(DimensionText.getText());
                if (latitude<-90 || latitude>90 || longitude<0 || longitude>=360 || dimension>360){
                    String msg = "Ricontrolla le coordinate;\nlatitudine [-90; 90]\nlongitudine [0;360)\nestensione (0;360]";
                    GraphicController graphicController = new GraphicController();
                    graphicController.alertError(msg);
                }
                else {
                    DBController dbController = new DBController();
                    if(dbController.ricercaInRegione(getTipoRicerca(), dimension, longitude, latitude)){
                        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                    }
                }
            }
        }catch (NumberFormatException nfe){
            try {
                String msg1 = "Input numerico scoretto.";
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

    public void backHome(ActionEvent actionEvent) throws Exception{
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


}
