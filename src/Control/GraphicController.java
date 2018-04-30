package Control;

import Bean.Req7Bean;
import Boundary.*;
import Boundary.Requisito_01.Login;
import Boundary.Requisito_03.InsertInstrument;
import Boundary.Requisito_03.InsertSatellite;
import Boundary.Requisito_03.RegisterPage;
import Boundary.Requisito_03.Req_3_Page;
import Boundary.Requisito_05.Req_5_Page;
import Boundary.Requisito_05.Req_5_Result;
import Boundary.Requisito_06.Req_6_Page;
import Boundary.Requisito_07.Req_7_Page;
import Boundary.Requisito_06.Req_6_Result;
import Boundary.Requisito_07.Req_7_Result;
import Boundary.Requisito_08.Req_8_Page;
import Boundary.Requisito_08.Req_8_Result;
import Boundary.Requisito_09.Req_9_Page;
import Boundary.Requisito_09.Req_9_Result;
import Boundary.ShowElement.ShowStar;
import Boundary.ShowElement.ShowStruct;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GraphicController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Login login = new Login();
        login.start(primaryStage);

    }

    public void alertError(String message) throws Exception{
        Alert alert = new Alert();
        alert.incorrectLoginField(message);
    }

    public void homeUser() throws Exception{
        Home_User home_user = new Home_User();
        home_user.start();
    }

    public void homeAdmin() throws Exception {
        Home_Admin home_admin = new Home_Admin();
        home_admin.start();
    }

    public void adminReqsPage() throws Exception{
        Req_3_Page req_3_page = new Req_3_Page();
        req_3_page.start();
    }

    public void req32Page() throws Exception{
        RegisterPage register_page = new RegisterPage();
        register_page.start();
    }

    public void req33Page() throws  Exception {
        InsertSatellite insert_satellite = new InsertSatellite();
        insert_satellite.start();
    }

    public void req34Page() throws Exception{
        InsertInstrument insert_Instrument =new InsertInstrument();
        insert_Instrument.start();
    }

    public void req5page() throws Exception{
        Req_5_Page req_5_page = new Req_5_Page();
        req_5_page.start();
    }

    public void req5result() throws Exception{
        Req_5_Result req_5_result = new Req_5_Result();
        req_5_result.start();
    }

    public void req6page() throws Exception{
        Req_6_Page req_6_page = new Req_6_Page();
        req_6_page.start();
    }

    public void req6result() throws Exception{
        Req_6_Result prova_req_6_res = new Req_6_Result();
        prova_req_6_res.start();
    }

    public void showStruct() throws Exception {
        ShowStruct showStruct = new ShowStruct();
        showStruct.show();
    }

    public void req7page() throws Exception{
        Req_7_Page req_7_page = new Req_7_Page();
        req_7_page.start();
    }

    public void req7result() throws Exception{
        Req_7_Result req_7_result = new Req_7_Result();
        req_7_result.start();
    }

    public void req8page()  throws Exception{
        Req_8_Page req_8_page = new Req_8_Page();
        req_8_page.start();
    }

    public void req8result() throws Exception{
        Req_8_Result req_8_result = new Req_8_Result();
        req_8_result.start();
    }

    public void req9page() throws Exception{
        Req_9_Page req_9_page = new Req_9_Page();
        req_9_page.start();
    }

    public void req9result() throws Exception{
        Req_9_Result req_9_result = new Req_9_Result();
        req_9_result.start();
    }

    public void showStar() throws Exception{
        ShowStar showStar = new ShowStar();
        showStar.show();
    }
}
