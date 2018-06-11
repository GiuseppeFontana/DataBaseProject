package Control;

import Bean.Req12_BeanToShow;
import Bean.Req6_8SquareBean;
import Bean.Req9_10Bean;
import Dao.*;
import Dao.SatelliteDao;
import Singletons.SingletonReq10;
import Singletons.SingletonReq12;
import Singletons.SingletonReq8;
import Singletons.SingletonReq9;

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
        return SatelliteDao.insertSatellite(satellite, beginact,endact,agency);
    }

    public boolean inserimentoStrumento(String instrument, String sat) throws Exception{
        if (!SatelliteDao.insertInstrument(instrument, sat)){
            return false;
        }
        return true;
    }

    public boolean inserimentoBanda(String instrument, double strip) {
        if (!SatelliteDao.insertStrip(instrument, strip)){
            return false;
        }
        return true;
    }

    public void importaCSV(String table, String instrument, String path){
        ImportDao importDao = new ImportDao();
        importDao.importa(table, instrument, path);
    }

    public boolean infoDerivateFilamento(String instrument, int input) throws Exception {

        if (!Req5Dao.req5(instrument, input)) {
            return false;
        } else {  //calcola centroide, max e min coordinate e crea l'interfaccia

            /*infoFilamento[6] = infoFilamento[4] - infoFilamento[2];     //estensione longitudinale
            infoFilamento[7] = infoFilamento[5] - infoFilamento[3];     //estensione latitudinale*/

            GraphicController graphicController = new GraphicController();
            graphicController.req5result();
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
        System.out.println("Inizio ricerca...");
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

    public boolean ricercaInRegione(String tipoRicerca, Double dimension, Double longitude, Double latitude) throws Exception {
        if (tipoRicerca.equals("square")){
            Double minLong = longitude - dimension/2;
            Double maxLong = longitude + dimension/2;
            Double minLat = latitude - dimension/2;
            Double maxLat = latitude + dimension/2;
            if (!Req8Dao.req8Square(minLong, maxLong, minLat, maxLat)){
                GraphicController graphicController = new GraphicController();
                String msg = "Nessuna Struttura trovata\ncon queste caratteristiche.";
                graphicController.alertError(msg);
                return false;
            }
            GraphicController graphicController = new GraphicController();
            graphicController.req8result();
            return true;
        }
        //if (tipoRicerca.equals("circular")){
        else {
            Double minLong = longitude - dimension;
            Double maxLong = longitude + dimension;
            Double minLat = latitude - dimension;
            Double maxLat = latitude + dimension;
            if (!Req8Dao.req8Circ(minLong, maxLong, minLat, maxLat)){
                GraphicController graphicController = new GraphicController();
                String msg = "Nessuna Struttura trovata\ncon queste caratteristiche.";
                graphicController.alertError(msg);
                return false;
            }
            Controller controller = new Controller();
            if (!controller.circSearch(dimension, longitude, latitude)){
                GraphicController graphicController = new GraphicController();
                String msg = "Nessuna Struttura trovata\ncon queste caratteristiche.";
                graphicController.alertError(msg);
                return false;
            }
            ArrayList<Req6_8SquareBean> beans = new ArrayList<>();
            SingletonReq8.getInstance().setBeans(beans);

            for (int i=0; i<SingletonReq8.getInstance().getReq8CircularBeans().size(); i++){
                Req6_8SquareBean bean = controller.createReq6_8Bean(
                        SingletonReq8.getInstance().getReq8CircularBeans().get(i).getId(),
                        SingletonReq8.getInstance().getReq8CircularBeans().get(i).getName(),
                        SingletonReq8.getInstance().getReq8CircularBeans().get(i).getSatellite());

                SingletonReq8.getInstance().getBeans().add(bean);
            }

            GraphicController graphicController = new GraphicController();
            graphicController.req8result();
            return true;
        }
    }

    public boolean ricercaStelleInStruttura(String satellite, int id) throws Exception{
        if (!Req9Dao.getStars()){
            return false;
        }
        if (!Req9Dao.getBounds(id, satellite)){
            return false;
        }

        Controller controller = new Controller();
        controller.scanStars9();

        if (SingletonReq9.getInstance().getStars().size() == 0){
            return false;
        }

        GraphicController graphicController = new GraphicController();
        graphicController.req9result();
        return true;
    }

    public void showStar(int id) throws Exception{
        if(!StarDao.searchStar(id)){
            System.out.println("something's gone wrong.");
        }else {
            GraphicController graphicController = new GraphicController();
            graphicController.showStar();
        }
    }

    public boolean ricercaStelleInRegione(double extLon, double extLat, double centreLon, double centreLat) {

        double lonMin = centreLon - extLon/2;
        double lonMax = centreLon + extLon/2;
        double latMin = centreLat - extLat/2;
        double latMax = centreLat + extLat/2;

        System.out.println("Ricerca stelle nella regione...");
        if (!Req10Dao.searchStarsInArea(lonMin, lonMax, latMin, latMax)){
            return false;
        }

        System.out.println("Ricerca strutture nella regione...");
        if (Req10Dao.searchBoundsInArea(lonMin, lonMax, latMin, latMax)){

            Controller controller = new Controller();
            // vedere quali stelle cadono nelle strutture
            System.out.println("Calcolo appartenenza alle strutture...");
            for (int j = 0; j < SingletonReq10.getInstance().getStructuresInBeans().size(); j++){
                // riempire l'array dei bound
                if (Req10Dao.getBounds(
                        SingletonReq10.getInstance().getStructuresInBeans().get(j).getId(),
                        SingletonReq10.getInstance().getStructuresInBeans().get(j).getSatellite())){
                    // effettuare il calcolo
                    for (int k = 0; k < SingletonReq10.getInstance().getStarBeans().size(); k++){
                        if ((!SingletonReq10.getInstance().getStarBeans().get(k).isInStructure()) && controller.isInStruct10(SingletonReq10.getInstance().getStarBeans().get(k).getStar())){
                            SingletonReq10.getInstance().getStarBeans().get(k).setInStructure(true);
                        }
                    }
                }else {
                    //never reached
                    System.out.println("Something's gone wrong.");
                }

            }
        }

        ArrayList<Req9_10Bean> beans = new ArrayList<>();
        SingletonReq10.getInstance().setBeansToShow(beans);
        Controller controller = new Controller();
        for (int i = 0; i < SingletonReq10.getInstance().getStarBeans().size(); i++){
            SingletonReq10.getInstance().getBeansToShow().add(
                    controller.createReq9_10Bean(
                            SingletonReq10.getInstance().getStarBeans().get(i).getStar().getId(),
                            SingletonReq10.getInstance().getStarBeans().get(i).getStar().getName()
                    )
            );
        }
        return true;
    }

    public boolean valuesControl(String sat, int id){

        ValuesControl valuesControl = new ValuesControl();
        return valuesControl.max_min(sat, id);
    }

    public boolean Req11_segment(String sat, int id){

            return Req11Dao.numeroSegmenti(sat, id);

    }

    public ArrayList Req11_distance(int segmento){

        return Req11Dao.coordinate(segmento);

    }

    public boolean Req12(String sat, int id) throws Exception {

        if (!Req9Dao.getStars()){
            return false;
        }
        if (!Req9Dao.getBounds(id, sat)){
            return false;
        }

        Controller controller = new Controller();
        controller.scanStars12();

        if (!Req12Dao.riempiSpina(id, sat)){
            System.out.println("errore accesso riempispina");
            return false;
        }


        if (SingletonReq12.getInstance().getBeans().size() == 0){
            System.out.println("array bean vuoto");
            return false;
        }

        controller.calcolaDistanze12();

        ArrayList<Req12_BeanToShow> beanToShows = new ArrayList<>();
        SingletonReq12.getInstance().setBeanToShows(beanToShows);
        for (int i = 0; i < SingletonReq12.getInstance().getBeans().size(); i++){
            SingletonReq12.getInstance().getBeanToShows().add(
                    controller.createReq12_BeanToShow(
                            SingletonReq12.getInstance().getBeans().get(i).getStar().getId(),
                            SingletonReq12.getInstance().getBeans().get(i).getStar().getFlux(),
                            SingletonReq12.getInstance().getBeans().get(i).getDistance()));
        }
        return true;
    }

    public boolean cercaTuttiGliId(String satellite) throws Exception{
        if(!StructDao.showAllId(satellite)){
            System.out.println("something's gone wrong.");
            return false;
        }else {
            GraphicController graphicController = new GraphicController();
            graphicController.showAllIds();
            return true;
        }
    }

    /*public boolean Req12_Distance(String sat, int id){
        Req12Dao req12Dao = new Req12Dao();
        return req12Dao.spineDistance(sat, id);
    }*/
}
