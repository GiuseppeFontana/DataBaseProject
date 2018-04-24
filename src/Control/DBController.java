package Control;

import Dao.*;
import Bean.Req7Bean;

import java.time.LocalDate;
import java.util.ArrayList;

public class DBController {


    public DBController() {

    }

    public boolean login(String username, String password) {
        return UserDao.daoLogin(username, password);
    }

    public boolean registraUtente(String name, String surname, String username, String pass1, String email, boolean type) {
        return RegisterDao.req32(name, surname, username, pass1, email, type);
    }

    public boolean inserimentoSatellite(String satellite, LocalDate beginact, LocalDate endact, String agency) {
        return InsertSatelliteDao.req33(satellite, beginact,endact,agency);
    }

    public boolean inserimentoStrumento(String instrument, String sat, String strip ) {
        return InsertInstrumentDao.req34(instrument, sat, strip);
    }
    public boolean infoDerivateFilamento(String instrument, String input) throws Exception {

        int nSegmenti[] = new int[1];
        double infoFilamento[] = new double[8];
        if (!Req5Dao.req5(instrument, input, infoFilamento, nSegmenti)) {
            return false;
        } else {  //calcola centroide, max e min coordinate e crea l'interfaccia

            infoFilamento[6] = infoFilamento[4] - infoFilamento[2];     //estensione longitudinale
            infoFilamento[7] = infoFilamento[5] - infoFilamento[3];     //estensione latitudinale

            GraphicController graphicController = new GraphicController();
            graphicController.req5result(input, infoFilamento, nSegmenti[0]);
            return true;
        }
    }

    public boolean ricercaPerContrastoEdEllitticita(double percBrillanza, double elliptMin, double elliptMax) throws Exception {

        if(!Req6Dao.req6(percBrillanza, elliptMin, elliptMax)){
            GraphicController graphicController = new GraphicController();
            String msg = "Nessuna Struttura trovata\ncon queste caratteristiche.";
            graphicController.alertError(msg);
            return false;
        }
        GraphicController graphicController = new GraphicController();
        graphicController.req6result();
        return true;

    }

    public boolean ricercaPerNumeroSegmenti(int min, int max) throws Exception{
        if (!Req7Dao.req7(min, max)){
            GraphicController graphicController = new GraphicController();
            String msg = "Nessuna Struttura trovata\ncon queste caratteristiche.";
            graphicController.alertError(msg);
            return false;
        }
        GraphicController graphicController = new GraphicController();
        graphicController.req7result();
        return true;
    }

    public void showStruct(int id, String satellite) throws Exception{
        if(!StructDao.searchStruct1(id, satellite)){
            System.out.println("something' gone wrong.");
        }else {
            GraphicController graphicController = new GraphicController();
            graphicController.showStruct();
        }
    }
}
