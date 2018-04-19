package Control;

import Prove.Req6Bean;
import Prove.Prova2Req6res;
import Dao.*;
import Entity.Structure;
import Bean.Req7Bean;
import Prove.provaReq6Dao;
import Singletons.SingletonReq6;

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

    public void ricercaReq6(double percBrillanza, double elliptMin, double elliptMax) throws Exception {
        SingletonReq6.getInstance().setBeans(provaReq6Dao.req6(percBrillanza, elliptMin, elliptMax));
        if (SingletonReq6.getInstance() == null){
            GraphicController graphicController = new GraphicController();
            String msg = "Nessuna Struttura trovata\ncon queste caratteristiche.";
            graphicController.alertError(msg);
        }
        else {
            nextResult(1);
            /*Prova2Req6res prova2Req6res = new Prova2Req6res();
            prova2Req6res.setCounterPage(1);
            for (int i = 0; i< beans.size(); i++){
                prova2Req6res.parseBean(beans.get(i).getId(),beans.get(i).getName(),beans.get(i).getSatellite(), beans.get(i).getTotaleStrutture());

            }*/
            /*Prova2Req6res prova2Req6res = new Prova2Req6res();
            int i = 0;
            for (Req6Bean bean : beans){
                if (i < 20){
                    prova2Req6res.parseBean(bean.getId(),bean.getName(),bean.getSatellite(), bean.getTotaleStrutture());
                    i++;
                }

            }*/
        }
    }

    public void nextResult(Integer counter){
        Prova2Req6res prova2Req6res = new Prova2Req6res();
        prova2Req6res.setCounterPage(counter);
        int size = SingletonReq6.getInstance().getBeans().size();
        if (size % 20 != 0){
            prova2Req6res.setTotalPages(size/20+1);
        }
        else {
            prova2Req6res.setTotalPages(size/20);
        }
        System.out.println("totalPages: "+prova2Req6res.getTotalPages());
        System.out.println("currentPage: "+prova2Req6res.getCounterPage());
        for (int i = (counter-1)*20; i< 20*(counter-1)+20; i++){
            prova2Req6res.parseBean(SingletonReq6.getInstance().getBeans().get(i).getId(),
                    SingletonReq6.getInstance().getBeans().get(i).getName(),
                    SingletonReq6.getInstance().getBeans().get(i).getSatellite(),
                    SingletonReq6.getInstance().getBeans().get(i).getTotaleStrutture());
        }
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
