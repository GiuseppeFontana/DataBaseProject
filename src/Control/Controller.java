package Control;

import Bean.Req7Bean;
import Entity.*;
import Utils.ClassicSingleton;

public class Controller {

    public void createUser(String username, String password, String email, String name, String surname, Boolean admin){

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAdmin(admin);
        user.setPassword(password);
        user.setUsername(username);
        user.setSurname(surname);

        //--------CREAZIONE DEL SINGLETON-----------//

        ClassicSingleton singleton = ClassicSingleton.getInstance();
        singleton.setUser(user);

    }

    public void resetUser() {
        ClassicSingleton.getInstance().getUser().reset();
    }

    public ClassicSingleton getSingleton(){
        return ClassicSingleton.getInstance();
    }


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



    public Req7Bean createReq7Bean(int id, String name, double flux, double meanDens, double meanTemp, double ellipt, double contrast, String satellite, String instrument, int nSegmenti){
        Structure structure = createStructure(id,name,flux,meanDens,meanTemp,ellipt,contrast,satellite,instrument);

        Req7Bean req7Bean = new Req7Bean();

        req7Bean.setStructure(structure);
        req7Bean.setnSegmenti(nSegmenti);

        return req7Bean;
    }
}
