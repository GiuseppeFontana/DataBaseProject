package Boundary.Requisito_10;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Prove.Classona;
import Singletons.SingletonReq10;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_10_Page {
    @FXML
    private Button Req10SearchButton;
    @FXML
    private Button Req10BackButton;
    @FXML
    private TextField LatText;
    @FXML
    private TextField LonText;
    @FXML
    private TextField CenterLonText;
    @FXML
    private TextField CenterLatText;


    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_10_Page.class.getResource("req_10_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
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

    public void search(ActionEvent actionEvent){
        try{
            double extLat = Double.parseDouble(LatText.getText());
            double extLon = Double.parseDouble(LonText.getText());
            double centreLat = Double.parseDouble(CenterLatText.getText());
            double centreLon = Double.parseDouble(CenterLonText.getText());

            if (extLat<=0 || extLon<=0){
                GraphicController graphicController = new GraphicController();
                String msg = "Input scorretto.\nControlla le estensioni.";
                graphicController.alertError(msg);
                return;
            }

            if (centreLat<-90 || centreLat>90 || centreLon<0 || centreLon>=360 ||
                    extLat>180 || extLon>360){
                String msg = "Ricontrolla l'input; nell'ordine:\n(0;360]\n(0;180]\n[0;360)\n[-90; 90]";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg);
                return;
            }

            DBController dbController = new DBController();
            GraphicController graphicController = new GraphicController();
            if (!dbController.ricercaStelleInRegione(extLon, extLat, centreLon, centreLat)){
                String msg = "Nessuna stella presente\nnella regione.";
                graphicController.alertError(msg);
                Controller controller = new Controller();
                controller.resetSingleton10();
            }
            else {
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                graphicController.req10result();
            }
        }
        catch (NumberFormatException nfe){
            try{
                String msg = "Input non valido.\nScrivi numeri reali.";
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
}