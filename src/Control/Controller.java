package Control;

import Bean.Req7Bean;
import Entity.*;
import Bean.Req6Bean;
import Singletons.SingletonReq6;
import Singletons.SingletonReq7;
import Singletons.SingletonStruct;
import Singletons.SingletonUser;

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

    public Req6Bean createReq6Bean(Integer id, String name, String satellite){
        Req6Bean bean = new Req6Bean(id, name, satellite);
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
}
