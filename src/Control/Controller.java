package Control;

import Bean.*;
import Entity.*;
import Singletons.*;

import java.util.ArrayList;

public class Controller {

    /////////////////// SINGLETONS

    public void createUser(String username, String password, String email, String name, String surname, Boolean admin){

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAdmin(admin);
        user.setPassword(password);
        user.setUsername(username);
        user.setSurname(surname);

        //--------CREAZIONE DEL SINGLETON-----------//

        SingletonUser.getInstance().setUser(user);
    }

    public void resetUser() {
        SingletonUser.getInstance().getUser().reset();
    }

    public SingletonUser getUserSingleton(){
        return SingletonUser.getInstance();
    }

    public void resetStructSingleton(){
        SingletonStruct.getInstance().setStructure(null);
        SingletonStruct.getInstance().setNumeroSegmenti(0);
    }

    public void resetSingleton6(){
        SingletonReq6.getInstance().setBeans(null);
        SingletonReq6.getInstance().setTotaleStrutture(null);
    }

    public void resetSingleton7(){
        SingletonReq7.getInstance().setBeans(null);
    }

    public void resetSingleton8(){
        SingletonReq8.getInstance().setBeans(null);
        SingletonReq8.getInstance().setReq8CircularBeans(null);
    }



    ////////////////// ENTITY

    public Structure createStructure(int id, String name, double flux, double meanDens, Double meanTemp, Double ellipt, Double contrast, String satellite, String instrument){

        Structure structure = new Structure();

        structure.setId(id);
        structure.setName(name);
        structure.setFlux(flux);
        structure.setMeanDens(meanDens);
        structure.setMeanTemp(meanTemp);
        structure.setEllipt(ellipt);
        structure.setContrast(contrast);
        structure.setSatellite(satellite);
        structure.setInstrument(instrument);

        return structure;
    }

    public Bound createBound (int id, double longitude, double latitude){

        Bound bound = new Bound();

        bound.setId(id);
        bound.setLongitude(longitude);
        bound.setLatitude(latitude);

        return bound;
    }

    public SkeletonPoint createSkeletonPoint(int idStructure, int idBranch, int nProgressive, char type, double longitude, double latitude, double flux){

        SkeletonPoint skeletonPoint = new SkeletonPoint();
        skeletonPoint.setIdStructure(idStructure);
        skeletonPoint.setIdBranch(idBranch);
        skeletonPoint.setnProgressive(nProgressive);
        skeletonPoint.setType(type);
        skeletonPoint.setLongitude(longitude);
        skeletonPoint.setLatitude(latitude);
        skeletonPoint.setFlux(flux);

        return skeletonPoint;
    }

    public Star createStar(long id, String name, float gLon, float gLat, double flux, String type){

        Star star = new Star();

        star.setId(id);
        star.setName(name);
        star.setgLon(gLon);
        star.setgLat(gLat);
        star.setFlux(flux);
        star.setType(type);

        return star;
    }





    ///////////////     BEANS

    public Req_6_8Square_Bean createReq6_8Bean(Integer id, String name, String satellite){
        Req_6_8Square_Bean bean = new Req_6_8Square_Bean(id, name, satellite);
        return bean;
    }

    public Req8CircularBean createReq8CircBean(Integer id, String name, String satellite, Double longitude, Double latitude){
        Req8CircularBean bean = new Req8CircularBean(id,name, satellite, longitude, latitude);
        return bean;
    }

    public Req7Bean createReq7Bean(int id, String name, String satellite, int nSegmenti){

        Req7Bean req7Bean = new Req7Bean();
        req7Bean.setIdStructure(id);
        req7Bean.setNameStructure(name);
        req7Bean.setSegmenti(nSegmenti);
        req7Bean.setSatellite(satellite);

        return req7Bean;
    }

    public boolean circSearch(Double dimension, Double longitude, Double latitude) {
        //TODO per mattia, ottimizzare
        // scansione e cancellazione
        System.out.println("scansione punti inscritti alla regione;\nAttendere...");
        for (int i = 0; i<SingletonReq8.getInstance().getReq8CircularBeans().size(); i++){
            if (!circCheck(SingletonReq8.getInstance().getReq8CircularBeans().get(i), longitude, latitude, dimension)){
                for (int k = 0; k < SingletonReq8.getInstance().getReq8CircularBeans().size(); k++){
                    if (SingletonReq8.getInstance().getReq8CircularBeans().get(i).getId() ==
                            SingletonReq8.getInstance().getReq8CircularBeans().get(k).getId() &&
                            SingletonReq8.getInstance().getReq8CircularBeans().get(i).getSatellite().equals(
                                    SingletonReq8.getInstance().getReq8CircularBeans().get(k).getSatellite())){
                        SingletonReq8.getInstance().getReq8CircularBeans().remove(k);
                    }
                }
            }
        }

        // cancellazione duplicati      TODO resta qualche duplicato
        System.out.println("Cancellazione duplicati;\nAttendere...");
        for (int i = 0; i<SingletonReq8.getInstance().getReq8CircularBeans().size(); i++){
            for (int k = 0; k<SingletonReq8.getInstance().getReq8CircularBeans().size(); k++){
                if (k!=i &&
                        SingletonReq8.getInstance().getReq8CircularBeans().get(i).getId() ==
                                SingletonReq8.getInstance().getReq8CircularBeans().get(k).getId() &&
                        SingletonReq8.getInstance().getReq8CircularBeans().get(i).getSatellite().equals(
                                SingletonReq8.getInstance().getReq8CircularBeans().get(k).getSatellite())){
                    SingletonReq8.getInstance().getReq8CircularBeans().remove(k);
                }
            }
        }

        if (SingletonReq8.getInstance().getReq8CircularBeans().size() != 0){
            return true;
        }
        return false;
    }

    private boolean circCheck(Req8CircularBean bean, Double centreLongitude, Double centreLatitude, Double dimension) {
        if (Math.sqrt((bean.getBoundLong()-centreLongitude)*(bean.getBoundLong()-centreLongitude)+
                (bean.getBoundLat() -centreLatitude)*(bean.getBoundLat()-centreLatitude))> dimension){
            return false;
        }
        return true;
    }

}
