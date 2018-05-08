package Boundary.Requisito_03;

import Boundary.Alert;
import Control.DBController;
import Control.GraphicController;
import Dao.ImportDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;

public class Import {


    public Button btnChoose;

    @FXML
    private Button BackButton;
    @FXML
    private TextField textPath;
    @FXML
    private RadioButton rbHerschel;
    @FXML
    private RadioButton rbSpitzer;
    @FXML
    private RadioButton rbContorni;
    @FXML
    private RadioButton rbScheletri;
    @FXML
    private RadioButton rbStelle;
    @FXML
    private RadioButton rbStrutture;
    @FXML
    private Button btnImporta;


    /*public String getPath() {
        return textPath.getText();
    }

    public void setPath(String path) {
        this.textPath.setText(path);
    }*/


    /*public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }*/

    public void backHome(ActionEvent actionEvent) throws Exception {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.adminReqsPage();
    }

    public void start() throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Import.class.getResource("import.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void newCsv(ActionEvent actionEvent) throws Exception {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        String path = file.getAbsolutePath();
        System.out.println(path);
        //setPath(path);

        String extension = path.substring(path.lastIndexOf("."));


        //---------------------CONTROLLO DELL'ESTENSIONE DEL FILE----------------//

        if (!extension.equals(".csv")){

            Alert alert = new Alert();
            alert.incorrectLoginField("Il file selezionato non è un csv");

        }else {
            textPath.clear();
            textPath.setText(path);
        }
    }

    public void DBInsert(ActionEvent actionEvent) throws Exception {

        String instrument = null;
        String table = null;

        if (rbHerschel.isSelected()){
            instrument = "herschel";
        }
        if (rbSpitzer.isSelected()){
            instrument="spitzer";
        }
        if (rbContorni.isSelected()){
            table="boundaries";
        }
        if (rbScheletri.isSelected()) {
            table="skeletons";
        }
        if (rbStelle.isSelected()){
            table="stars";
        }
        if (rbStrutture.isSelected()){
            table="structures";
        }

        if (textPath.getText().isEmpty()){
            Alert alert = new Alert();
            alert.incorrectLoginField("Inserire il percorso del nuovo file CSV");
        }else {

            //System.out.println("Ecco il path:   " + textPath.getText());

            DBController dbController = new DBController();
            dbController.importaCSV(table, instrument, /*getPath() */textPath.getText());
            /*ImportDao importDao = new ImportDao();
            importDao.importa(table, instrument, *//*getPath() *//*textPath.getText());*/
            // TODO controllo della riuscita ed eventuale chiusura della pagina

        }
    }


}
