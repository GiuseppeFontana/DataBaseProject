package Control;

import Dao.RegisterDao;
import Dao.Req5Dao;
import Dao.UserDao;
import Entity.Bound;
import Entity.SkeletonPoint;
import Entity.Structure;

import java.util.ArrayList;

public class DBController {

    public DBController(){

    }

    public boolean login(String username, String password) {
        return UserDao.daoLogin(username, password);
    }

    public boolean registraUtente(String name, String surname, String username, String pass1, String email, boolean type) {
        return RegisterDao.req32(name, surname, username, pass1, email, type);
    }


    public boolean infoDerivateFilamento(String instrument, String input) {
        ArrayList<Bound> bounds = Req5Dao.req51(instrument, input);
        int nBranches = Req5Dao.req52(instrument, input);
        if (bounds == null || nBranches == 0){
            return false;
        }
        else {

            //TODO calcola centroide, max e min coordinate e crea l'interfaccia
            }
            return  true;
        }

    }
