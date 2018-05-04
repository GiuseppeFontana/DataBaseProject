package Boundary.Requisito_03;

import Boundary.Alert;
import Dao.ImportDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Import {


    public Button btnChoose;

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


    public void start() throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Import.class.getResource("import.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void newCsv(ActionEvent actionEvent) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        String path = file.getAbsolutePath();
        System.out.println(path);
        //setPath(path);

        textPath.clear();
        textPath.setText(path);

    }

    public void DBInsert(ActionEvent actionEvent) throws Exception {

        String instrument = null;
        String table = null;

        if (rbHerschel.isSelected()){
            instrument = "Herschel";
        }
        if (rbSpitzer.isSelected()){
            instrument="Spitzer";
        }
        if (rbContorni.isSelected()){
            table="Contorni";
        }
        if (rbScheletri.isSelected()) {
            table="Scheletri";
        }
        if (rbStelle.isSelected()){
            table="Stelle";
        }
        if (rbStrutture.isSelected()){
            table="Strutture";
        }

        if (textPath.getText().isEmpty()){
            Alert alert = new Alert();
            alert.incorrectLoginField("Inserire il percorso del nuovo file CSV");
        }else {

            System.out.println("Ecco il path:   " + textPath.getText());

            ImportDao importDao = new ImportDao();
            importDao.importa(table, instrument, /*getPath() */textPath.getText());


        }
    }
}
