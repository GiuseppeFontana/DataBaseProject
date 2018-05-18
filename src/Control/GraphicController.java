package Control;

import Boundary.*;
import Boundary.Requisito_01.Login;
import Boundary.Requisito_03.*;
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
import Boundary.Requisito_10.Req_10_Page;
import Boundary.Requisito_10.Req_10_Result;
import Boundary.Requisito_11.Req_11_Page;
import Boundary.Requisito_11.Req_11_Result;
import Boundary.Requisito_12.Req_12_Page;
import Boundary.Requisito_12.Req_12_Result;
import Boundary.ShowElement.Show_Star;
import Boundary.ShowElement.Show_Struct;
import javafx.application.Application;
import javafx.stage.Stage;

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

    public void InserisciBanda() throws Exception{
        InsertStrip insertStrip = new InsertStrip();
        insertStrip.start();
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
        Show_Struct showStruct = new Show_Struct();
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
        Show_Star showStar = new Show_Star();
        showStar.show();
    }

    public void req10page() throws Exception{
        Req_10_Page req_10_page = new Req_10_Page();
        req_10_page.start();
    }

    public void req10result() throws Exception{
        Req_10_Result req_10_result = new Req_10_Result();
        req_10_result.start();
    }

    public void req11page() throws Exception{
        Req_11_Page req_11_page = new Req_11_Page();
        req_11_page.start();
    }


    public void req11result() throws Exception{
        Req_11_Result req_11__result = new Req_11_Result();
        req_11__result.start();
    }

    public void req12page() throws Exception{
        Req_12_Page req_12_page = new Req_12_Page();
        req_12_page.start();
    }

    public void req12result() throws Exception{
        Req_12_Result req_12_result = new Req_12_Result();
        req_12_result.start();
    }

    public void CSVImport() throws Exception{
        Import Import = new Import();
        Import.start();
    }

}
