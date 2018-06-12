package Boundary.Requisito_09;

import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonId;
import Singletons.SingletonReq9;
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

    private String satellite;

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

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
        try{
            GraphicController graphicController = new GraphicController();
            Controller controller = new Controller();
            if ((!RadioHerschel.isSelected() && !RadioSpitzer.isSelected()) ||
                    IDStructuresText.getText().equals("")){
                String msg = "Input non valido.\nSeleziona un satellite e\nimmetti un ID.";
                graphicController.alertError(msg);
            }
            else {
                String satellite = getSatellite();
                int id = Integer.parseInt(IDStructuresText.getText());
                DBController dbController = new DBController();
                if (!dbController.ricercaStelleInStruttura(satellite, id)){
                    if (SingletonReq9.getInstance().getStars().size() == 0){
                        String msg = "Nessuna stella presente\nnella struttura.";
                        graphicController.alertError(msg);
                    }
                    if (SingletonReq9.getInstance().getStructureBounds() == null){
                        String msg = "Filamento non trovato.";
                        graphicController.alertError(msg);
                    }
                    controller.resetSingleton9();
                }
                else {
                    ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                }
            }
        }
        catch (NumberFormatException nfe){
            try{
                String msg = "Input non valido.\nScrivi un numero intero positivo.";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

    public void checkSpitzer(ActionEvent actionEvent){
        setSatellite("spitzer");
    }

    public void checkHerschel(ActionEvent actionEvent){
        setSatellite("herschel");
    }

    public void searchid(ActionEvent actionEvent) throws Exception {
        if (!RadioHerschel.isSelected() && !RadioSpitzer.isSelected()) {
            String msg1 = "Seleziona un Satellite.";
            GraphicController graphicController = new GraphicController();
            graphicController.alertError(msg1);
        } else {
            DBController dbController = new DBController();
            SingletonId.getInstance().setSatellite(getSatellite());
            System.out.println("satellite: "+SingletonId.getInstance().getSatellite());
            if (!dbController.cercaTuttiGliId(getSatellite())) {
                String msg2 = "Errore imprevisto.";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg2);
            }
        }
    }
}
