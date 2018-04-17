package Control;

import Dao.*;
import Entity.Structure;
import Bean.Req7Bean;
import javafx.scene.control.DatePicker;

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
        ArrayList<Structure> structures = new ArrayList<>();
        int struttureTotali[] = new int[1];

        if(!Req6Dao.req6(structures, percBrillanza, elliptMin, elliptMax, struttureTotali)){
            GraphicController graphicController = new GraphicController();
            String msg = "Nessuna Struttura trovata\ncon queste caratteristiche.";
            graphicController.alertError(msg);
            return false;
        }

        int[] page = new int[1];
        page[0]=1;
        System.out.println(page[0]);
        GraphicController graphicController = new GraphicController();
        graphicController.req6result(structures, struttureTotali, page);
        return true;

    }

    public boolean ricercaPerNumeroSegmenti(int min, int max) throws Exception{
        ArrayList<Req7Bean> beans = new ArrayList<>();
        if (!Req7Dao.req7(beans, min, max)){
            return false;
        }
        GraphicController graphicController = new GraphicController();
        graphicController.req7result(beans);
        return true;
    }



}
