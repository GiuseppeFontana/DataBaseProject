package Boundary;

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
    @FXML
    private Button Req32BackButton;
    @FXML
    private Button Req32RegisterButton;

    private boolean type;

    public void Registra(ActionEvent actionEvent) throws Exception{
        String name = Req32NameText.getText();
        String surname = Req32SurnameText.getText();
        String username = Req32UserText.getText();
        String pass1 = Req32Password1Text.getText();
        String pass2 = Req32Password2Text.getText();
        String email = Req32EmailText.getText();

/*
        if (name.equals("") || surname.equals("") || username.equals("") ||
                pass1.equals("") || pass2.equals("") || email.equals("") ||
                (!(Req32Radio1.isSelected()) && (!Req32Radio2.isSelected()))){
            GraphicController graphicController = new GraphicController();
            graphicController.incorrectFields();
        }

        else if (!pass1.equals(pass2)){
            GraphicController graphicController = new GraphicController();
            graphicController.incorrectPasswords();
        }
        else {
            DBController dbController = new DBController();
            dbController.registraUtente(name,surname,username,pass1,email,type);
        }*/
    }

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registerPage.fxml"));
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

    public void check1(ActionEvent actionEvent) throws Exception{
        if (Req32Radio2.isSelected()){
            Req32Radio2.setSelected(false);
        }
        type = false;
    }

    public void check2(ActionEvent actionEvent) throws Exception {
        if (Req32Radio1.isSelected()){
            Req32Radio1.setSelected(false);
        }
        type = true;
    }
}
