package Control;

import Bean.Req7Bean;
import Boundary.*;
import Boundary.Requisito_03.InsertInstrument;
import Boundary.Requisito_03.InsertSatellite;
import Boundary.Requisito_03.RegisterPage;
import Boundary.Requisito_03.Req_3_Page;
import Boundary.Requisito_05.Req_5_Page;
import Boundary.Requisito_05.Req_5_Result;
import Boundary.Requisito_06.Req_6_Page;
import Boundary.Requisito_07.Req_7_Page;
import Entity.Structure;
import Prove.Prova2Req6res;
import Prove.Prova_Req_6_Res;
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
        IncorrectLoginFields incorrectLoginFields = new IncorrectLoginFields();
        incorrectLoginFields.incorrectLoginField(message);
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

    /*public void incorrectPasswords() throws Exception {
        IncorrectPasswords incorrectPasswords = new IncorrectPasswords();
        incorrectPasswords.incorrectPasswordsField();
    }*/

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

    public void req5result(String input, double[] infoContorno, int n) throws Exception{
        Req_5_Result req_5_result = new Req_5_Result();
        req_5_result.start(input, infoContorno, n);
    }

    public void req6page() throws Exception{
        Req_6_Page req_6_page = new Req_6_Page();
        req_6_page.start();
    }

    //TODO PROVA
    public void req6Res(Double percBrillanza, Double elliptMin, Double elliptMax) throws Exception{
        Prova2Req6res prova2Req6res = new Prova2Req6res();
        prova2Req6res.start(percBrillanza,elliptMin,elliptMax);
    }

    public void req7result(ArrayList<Req7Bean> beans) {
        /*
        TODO gui
         */

    }


    public void req7page() throws Exception{
        Req_7_Page req_7_page = new Req_7_Page();
        req_7_page.start();
    }

    public void req6result(ArrayList<Structure> structures, int[] struttureTotali, int[]page) throws Exception{
        Prova_Req_6_Res prova_req_6_res = new Prova_Req_6_Res();
        prova_req_6_res.start(structures, struttureTotali, page);
    }


}
