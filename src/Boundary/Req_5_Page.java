package Boundary;

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

/*
REQ-FN-5	Recupero	informazioni	derivate	di	un	filamento
Un	utente	registrato	potrà	ricercare	un	filamento	per	id	o	designazione,	e	di	questo
visualizzare:
1) La	posizione	del	centroide	del	contorno.	Il	centroide	sarà	calcolato	come	media	delle
latitudini	e	delle	longitudini.
2) L’estensione	del	contorno.	L’estensione	sarà	calcolata	come	la	distanza	tra	il	minimo
massimo	delle	posizioni	longitudinali,	e	tra	il	minimo	e	massimo	delle	posizioni	latitudinali.
3) Il	numero	di	segmenti	relativi.
 */

public class Req_5_Page {
    @FXML
    private RadioButton Req5PageRadio3;
    @FXML
    private RadioButton Req5PageRadio4;
    @FXML
    private RadioButton Req5PageRadio1;
    @FXML
    private RadioButton Req5PageRadio2;
    @FXML
    private TextField Req5PageText;
    @FXML
    private Button Req5PageSearchButton;

    private String satellite;         // false: Herschel; true: Spitzer
    private String typeSearch;       // false: ricerca per ID; true: ricerca per nome

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/req_5_page.fxml"));
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

    public void check1(ActionEvent actionEvent) throws Exception{
        if (Req5PageRadio2.isSelected()){
            Req5PageRadio2.setSelected(false);
        }
        typeSearch = "id";
    }

    public void check2(ActionEvent actionEvent) throws Exception {
        if (Req5PageRadio1.isSelected()){
            Req5PageRadio1.setSelected(false);
        }
        typeSearch = "name";
    }

    public void check3(ActionEvent actionEvent) throws Exception{
        if (Req5PageRadio4.isSelected()){
            Req5PageRadio4.setSelected(false);
        }
        satellite = "herschel";     //satellite = Herschel
    }

    public void check4(ActionEvent actionEvent) throws Exception{
        if (Req5PageRadio3.isSelected()){
            Req5PageRadio3.setSelected(false);
        }
        satellite = "spitzer";     //satellite = Spitzer
    }

    public void search(ActionEvent actionEvent) throws Exception {
        String input = Req5PageText.getText();
        if (input.equals("") || (!Req5PageRadio1.isSelected() && !Req5PageRadio2.isSelected())){
            String msg1 = "Input non valido.";
            GraphicController graphicController = new GraphicController();
            graphicController.incorrectLogin(msg1);
        }
        else {
            DBController dbController = new DBController();
            if(!dbController.ricercaFilamentoPerIdONome(typeSearch, satellite, input)){
                String msg2 = "Filamento non trovato.";
                GraphicController graphicController = new GraphicController();
                graphicController.incorrectLogin(msg2);
            }
        }
    }


}


