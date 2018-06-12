package Boundary.Requisito_03;

import Control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterPage {
    @FXML
    private TextField Req32EmailText;
    @FXML
    private TextField Req32NameText;
    @FXML
    private TextField Req32SurnameText;
    @FXML
    private TextField Req32UserText;
    @FXML
    private PasswordField Req32Password1Text;
    @FXML
    private PasswordField Req32Password2Text;
    @FXML
    private RadioButton Req32Radio1;
    @FXML
    private RadioButton Req32Radio2;

    private boolean type;   //true:admin

    public void Registra(ActionEvent actionEvent) throws Exception{
        String name = Req32NameText.getText();
        String surname = Req32SurnameText.getText();
        String username = Req32UserText.getText();
        String pass1 = Req32Password1Text.getText();
        String pass2 = Req32Password2Text.getText();
        String email = Req32EmailText.getText();


        if (name.equals("") || surname.equals("") || username.equals("") ||
                pass1.equals("") || pass2.equals("") || email.equals("") ||
                (!(Req32Radio1.isSelected()) && (!Req32Radio2.isSelected()))){
            GraphicController graphicController = new GraphicController();
            String msg1 = "Dati non corretti!";
            graphicController.alertError(msg1);
        }

        if (!pass1.equals(pass2)){
            GraphicController graphicController = new GraphicController();
            String msg2 = "Le password non coincidono.";
            graphicController.alertError(msg2);
        }
        if (username.length()<6 || pass1.length()<6){
            String msg3 = "Username o password troppo corti\n(min. 6 caratteri)";
            GraphicController graphicController = new GraphicController();
            graphicController.alertError(msg3);
        }
        else {
            DBController dbController = new DBController();
            if (dbController.registraUtente(name,surname,username,pass1,email,type)){
                GraphicController graphicController = new GraphicController();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                graphicController.adminReqsPage();
                String msg4 = "Registrazione effettuata con successo.";
                graphicController.alertError(msg4);
            }
            else {
                GraphicController graphicController = new GraphicController();
                String msg4 = "Registrazione fallita.\nUtente già registrato";
                graphicController.alertError(msg4);
            }
        }
    }

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(RegisterPage.class.getResource("registerPage.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void backHome(ActionEvent actionEvent) throws Exception {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.adminReqsPage();
    }

    public void check1(ActionEvent actionEvent){
        if (Req32Radio2.isSelected()){
            Req32Radio2.setSelected(false);
        }
        type = false;
    }

    public void check2(ActionEvent actionEvent) {
        if (Req32Radio1.isSelected()){
            Req32Radio1.setSelected(false);
        }
        type = true;
    }
}
