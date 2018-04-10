package Control;

import Dao.RegisterDao;
import Dao.Req5Dao;
import Dao.Req6Dao;
import Dao.UserDao;
import Entity.Structure;

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

    public boolean ricercaPerContrastoEdEllitticita(String satellite, double percBrillanza, double elliptMin, double elliptMax) throws Exception {
        ArrayList<Structure> structures = new ArrayList<>();

        if(!Req6Dao.req6(structures, satellite, percBrillanza, elliptMin, elliptMax)){
            GraphicController graphicController = new GraphicController();
            String msg = "Nessuna Struttura trovata\ncon queste caratteristiche.";
            graphicController.alertError(msg);
            return false;
        }

        /*
        TODO finire
         */
        GraphicController graphicController = new GraphicController();
        //graphicController.req6result(structures);
        return true;

    }
}
