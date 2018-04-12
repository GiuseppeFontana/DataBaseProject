package Boundary;

import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_6_Page {

    @FXML
    private RadioButton Req6PageRadio1;
    @FXML
    private RadioButton Req6PageRadio2;
    @FXML
    private TextField Req6PageBrillanzaText;
    @FXML
    private TextField Req6PageElliptMinText;
    @FXML
    private TextField Req6PageElliptMaxText;
    private String satellite;

/*
REQ-FN-6	Ricerca	di	una	struttura	per	contrasto	ed	ellitticità
Un	utente	registrato	potrà	effettuare	una	ricerca	di	una	struttura	estesa	la	cui	brillanza	è
maggiore	di	una	certa	percentuale	rispetto	al	bordo	esterno	e	la	cui	ellitticità	è	compresa
all’interno	di	un	range	selezionabile.
Si	consideri	che	il	valore	del	contrasto	è	pari	a	[1+(%Brillanza/100)].	Ad	esempio,	richiedere
una	luminosità	maggiore	del	10%,	vorrà	dire	ricercare	le	strutture	che	hanno	un	contrasto
maggiore	1.1.
L’applicazione	dovrà	rifiutare	valori	percentuali	minore	di	0	e	i	valori	di	ellitticità	dovranno
essere	compresi	tra	1	e	10	esclusi.
La	GUI	visualizzerà	al	massimo	20	oggetti	per	volta.
L’applicazione	dovrà	restituire	anche	la	frazione	di	filamenti	trovati	rispetto	l’intero	catalogo.
 */

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/req_6_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
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

    public void check1(ActionEvent actionEvent) throws Exception {
        /*if (Req6PageRadio2.isSelected()){
            Req6PageRadio2.setSelected(false);
        }*/
        satellite = "herschel";
    }

    public void check2(ActionEvent actionEvent) throws Exception {
        /*if (Req6PageRadio1.isSelected()){
            Req6PageRadio1.setSelected(false);
        }*/
        satellite = "spitzer";
    }

    public void search(ActionEvent actionEvent) {
        try {
            double percBrillanza = Double.parseDouble(Req6PageBrillanzaText.getText());
            double elliptMax = Double.parseDouble(Req6PageElliptMaxText.getText());
            double elliptMin = Double.parseDouble(Req6PageElliptMinText.getText());

            if (percBrillanza<0.0 || elliptMin <= 1.0 || elliptMax >= 10.0 || elliptMin > elliptMax || (!Req6PageRadio1.isSelected() && !Req6PageRadio2.isSelected())){
                String msg1 = "Input non valido.";
                GraphicController graphicController = new GraphicController();
                graphicController.alertError(msg1);
            }
            else {
                DBController dbController = new DBController();
                if(!dbController.ricercaPerContrastoEdEllitticita(satellite, percBrillanza, elliptMin, elliptMax)){
                    String msg2 = "Nessun filamento trovato.";
                    GraphicController graphicController = new GraphicController();
                    graphicController.alertError(msg2);
                }
                else {
                    /*
                    TODO finire con gui
                     */
                }
            }
        }
        catch (NumberFormatException nfe){
            try {
                String msg1 = "Input numerico scoretto:\nla % deve essere maggiore di 0,\ni limiti di ellitticità maggiori di 0 e\nminori di 10.\n(es. 23; 5.1; 7)";
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
}
