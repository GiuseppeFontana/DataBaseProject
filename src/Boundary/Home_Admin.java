package Boundary;

import Control.Controller;
import Control.GraphicController;
import Entity.User;
import Singletons.SingletonUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Home_Admin {

    @FXML
    private Button HomeAdminReq5Button;
    @FXML
    private Button HomeAdminReq6Button;
    @FXML
    private Button HomeAdminReq7Button;
    @FXML
    private Button HomeAdminReq8Button;
    @FXML
    private Button HomeAdminReq9Button;
    @FXML
    private Button HomeAdminReq10Button;
    @FXML
    private Button HomeAdminReq11Button;
    @FXML
    private Button HomeAdminReq12Button;

    @FXML
    private Button btnSystemExit;
    @FXML
    private Button btnInformation;
    @FXML
    private Button HomeBtnLogOut;

    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/home_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        User user = SingletonUser.getInstance().getUser();
        String name = user.getName();

        final Label HomeLabel1 = new Label();

        HomeLabel1.setText("Benvenuto " + name);
        HomeLabel1.setLayoutX(220);
        HomeLabel1.setLayoutY(40);
        root.getChildren().addAll(HomeLabel1);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    //-----------------INFORMAZIONI USER LOGGATO-----------------//

    public void information(ActionEvent actionEvent) throws Exception {

        User_Information user_information = new User_Information();
        user_information.start();

    }

    //---------------PULSANTE LOGOUT---------------------//

    public void logout(ActionEvent actionEvent) throws Exception {

        Controller controller = new Controller();
        controller.resetUser();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        Stage stage = null;
        graphicController.start(stage);

    }

    //-----------------PULSANTE CHIUSURA APPLICAZIONE-------------------//

    public void systemExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //--------------Requisiti funzionali-------------------//


    public void jumpReq3Page(ActionEvent actionEvent) throws Exception{     //Funzionalità Admin
        GraphicController graphicController = new GraphicController();
        graphicController.adminReqsPage();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
    public void jumpReq5Page(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.req5page();
    }

    public void jumpReq6Page(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.req6page();
    }

    public void jumpReq7Page(ActionEvent actionEvent)  throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.req7page();
    }

    public void jumpReq8Page(ActionEvent actionEvent) throws Exception {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.req8page();
    }

    public void jumpReq9Page(ActionEvent actionEvent) throws Exception {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.req9page();
    }

    public void jumpReq10Page(ActionEvent actionEvent) {
    }

    public void jumpReq11Page(ActionEvent actionEvent) {
    }

    public void jumpReq12Page(ActionEvent actionEvent) {
    }



}
