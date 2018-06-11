package Control;

import Bean.*;
import Entity.*;
import Singletons.*;

import java.util.ArrayList;
import java.util.Optional;

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

    public void resetStarSingleton() {
        SingletonStar.getInstance().setStar(null);
    }

    public void resetSingleton5() {
        SingletonReq5.getInstance().setBean(null);
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

    public void resetSingleton9(){
        SingletonReq9.getInstance().setStructureBounds(null);
        SingletonReq9.getInstance().setStars(null);
        SingletonReq9.getInstance().setBeans(null);
        SingletonReq9.getInstance().setUnbound(0);
        SingletonReq9.getInstance().setPrestellar(0);
        SingletonReq9.getInstance().setProtostellar(0);
    }

    public void resetSingleton10(){
        SingletonReq10.getInstance().setStructuresInBeans(null);
        SingletonReq10.getInstance().setBeansToShow(null);
        SingletonReq10.getInstance().setStarBeans(null);
        SingletonReq10.getInstance().setStructureBounds(null);

        SingletonReq10.getInstance().setTotal_false(0);
        SingletonReq10.getInstance().setTotal_true(0);
        SingletonReq10.getInstance().setUnbound_false(0);
        SingletonReq10.getInstance().setUnbound_true(0);
        SingletonReq10.getInstance().setPrestellar_false(0);
        SingletonReq10.getInstance().setPrestellar_true(0);
        SingletonReq10.getInstance().setProtostellar_false(0);
        SingletonReq10.getInstance().setProtostellar_true(0);
    }

    public void resetSingleton11(){

        SingletonReq11.getInstance().setBeans(null);
        SingletonReq11.getInstance().setId(0);
        SingletonReq11.getInstance().setSat(null);

    }

    public void resetSingleton12() {
        SingletonReq12.getInstance().setBeanToShows(null);
        SingletonReq12.getInstance().setBeans(null);
        SingletonReq12.getInstance().setSkeletonPoints(null);
    }

    public void resetSingletonId() {
        SingletonId.getInstance().setBeans(null);
        SingletonId.getInstance().setSatellite(null);
    }

    public void resetAllSingleton(){
        resetStructSingleton();
        resetStarSingleton();
        resetSingletonId();
        resetSingleton5();
        resetSingleton6();
        resetSingleton7();
        resetSingleton8();
        resetSingleton9();
        resetSingleton10();
        resetSingleton11();
        resetSingleton12();
    }

    // requisito 9
    public void scanStars9() {
        for (int i = SingletonReq9.getInstance().getStars().size()-1; i>=0; i--){
            if (!isInStruct9(SingletonReq9.getInstance().getStars().get(i))){
                SingletonReq9.getInstance().getStars().remove(i);
            }
        }

        SingletonReq9.getInstance().setBeans(new ArrayList<>());
        for (int i = 0; i < SingletonReq9.getInstance().getStars().size(); i++){
            SingletonReq9.getInstance().getBeans().add(
                    createReq9_10Bean(SingletonReq9.getInstance().getStars().get(i).getId(),
                            SingletonReq9.getInstance().getStars().get(i).getName()));
        }
    }

    private boolean isInStruct9(Star star) {

        double k = 0;
        ArrayList<Bound> array = SingletonReq9.getInstance().getStructureBounds();

        for (int i = 0; i < array.size()-1; i++){
            /*double j = Math.atan(((array.get(i).getLongitude()-star.getgLon())*(array.get(i+1).getLatitude()-star.getgLat())-
                    (array.get(i).getLatitude()-star.getgLat())*(array.get(i+1).getLongitude()-star.getgLon()))
                    /
                    ((array.get(i).getLongitude()-star.getgLon())*(array.get(i+1).getLongitude()-star.getgLon())+
                            (array.get(i).getLatitude()-star.getgLat())*(array.get(i+1).getLatitude()-star.getgLat())));
            k += j;*/

            double e = ((array.get(i).getLongitude()-star.getgLon())*(array.get(i+1).getLatitude()-star.getgLat())-
                    (array.get(i).getLatitude()-star.getgLat())*(array.get(i+1).getLongitude()-star.getgLon()))
                    /
                    ((array.get(i).getLongitude()-star.getgLon())*(array.get(i+1).getLongitude()-star.getgLon())+
                            (array.get(i).getLatitude()-star.getgLat())*(array.get(i+1).getLatitude()-star.getgLat()));

            double j = Math.atan((e*Math.PI)/180);
            k += j;

        }

        if (Math.abs(k) >= 0.01){
            return true;
        }
        return false;
    }

    public double round (double n){
        return (double) ((int)(n*100))/100;
    }

    // requisito 10
    public boolean isInStruct10(Star star) {

        double k = 0;
        ArrayList<Bound> array = SingletonReq10.getInstance().getStructureBounds();

        for (int i = 0; i < array.size()-1; i++){
            /*double j = Math.atan(((array.get(i).getLongitude()-star.getgLon())*(array.get(i+1).getLatitude()-star.getgLat())-
                    (array.get(i).getLatitude()-star.getgLat())*(array.get(i+1).getLongitude()-star.getgLon()))
                    /
                    ((array.get(i).getLongitude()-star.getgLon())*(array.get(i+1).getLongitude()-star.getgLon())+
                            (array.get(i).getLatitude()-star.getgLat())*(array.get(i+1).getLatitude()-star.getgLat())));
            k += j;*/

            double e = ((array.get(i).getLongitude()-star.getgLon())*(array.get(i+1).getLatitude()-star.getgLat())-
                    (array.get(i).getLatitude()-star.getgLat())*(array.get(i+1).getLongitude()-star.getgLon()))
                    /
                    ((array.get(i).getLongitude()-star.getgLon())*(array.get(i+1).getLongitude()-star.getgLon())+
                            (array.get(i).getLatitude()-star.getgLat())*(array.get(i+1).getLatitude()-star.getgLat()));

            double j = Math.atan((e*Math.PI)/180);
            k += j;
        }

        if (Math.abs(k) >= 0.01){
            return true;
        }
        //System.out.println("k:"+k+"\tstar:"+star.getId()+"\tstruct:"+array.get(0).getId());
        return false;
    }

    public void scanStars12() {
        for (int i = SingletonReq9.getInstance().getStars().size()-1; i>=0; i--){
            if (!isInStruct9(SingletonReq9.getInstance().getStars().get(i))){
                SingletonReq9.getInstance().getStars().remove(i);
            }
        }

        ArrayList<Req12_Bean> beans = new ArrayList<>();
        SingletonReq12.getInstance().setBeans(beans);

        for (int i = 0; i < SingletonReq9.getInstance().getStars().size(); i++){
            SingletonReq12.getInstance().getBeans().add(
                    createReq12Bean(SingletonReq9.getInstance().getStars().get(i)));
        }

        System.out.println("SIZE 9: " + SingletonReq9.getInstance().getStars().size());

        System.out.println("SIZE 12: " + SingletonReq12.getInstance().getBeans().size());

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

    public SkeletonPoint createSkeletonPoint(int idStructure, int idBranch, int nProgressive, String type, double longitude, double latitude, double flux){

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

    public Star createStar(int id, String name, double gLon, double gLat, double flux, String type){

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

    public Req6_8SquareBean createReq6_8Bean(Integer id, String name, String satellite){
        Req6_8SquareBean bean = new Req6_8SquareBean(id, name, satellite);
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

    public Req9_10Bean createReq9_10Bean(int id, String name){
        Req9_10Bean bean = new Req9_10Bean();
        bean.setId(id);
        bean.setName(name);

        return bean;
    }

    public Req10AllBoundsBean createReq10AllBoundsBean(int id, String satellite){
        Req10AllBoundsBean bean = new Req10AllBoundsBean();
        bean.setId(id);
        bean.setSatellite(satellite);

        return  bean;
    }

    public Req10StarBean createReq10StarBean (Star star){
        Req10StarBean bean = new Req10StarBean();
        bean.setStar(star);
        bean.setInStructure(false);

        return bean;
    }

    public Req11_Bean createReq11_Bean(int nSegmenti, int Segmenti){
        Req11_Bean req11_bean = new Req11_Bean();
        req11_bean.setSegmenti(Segmenti);
        req11_bean.setnSegmenti(nSegmenti);

        return req11_bean;
    }

    public Req12_Bean createReq12Bean(Star star/*, double i*/) {
        Req12_Bean bean = new Req12_Bean();
        bean.setStar(star);
        bean.setDistance(0);
        return bean;
    }

    public Req12_BeanToShow createReq12_BeanToShow(int id, double flux, double distance){
        Req12_BeanToShow bean = new Req12_BeanToShow();
        bean.setId(id);
        bean.setFlux(flux);
        bean.setDistance(distance);

        return bean;
    }

    public BeanId createBeanId(int i){
        BeanId b = new BeanId();
        b.setId(i);
        return b;
    }

    public boolean circSearch(Double dimension, Double longitude, Double latitude) {

        //scansione e cancellazione
        try {
            System.out.println("scansione punti inscritti alla regione;\nAttendere...");
            for (int i = SingletonReq8.getInstance().getReq8CircularBeans().size() - 1; i >= 0; i--) {
                if (!circCheck(SingletonReq8.getInstance().getReq8CircularBeans().get(i), longitude, latitude, dimension)) {
                    for (int k = SingletonReq8.getInstance().getReq8CircularBeans().size() - 1; k > i; k--) {
                        if (SingletonReq8.getInstance().getReq8CircularBeans().get(i).getId() ==
                                SingletonReq8.getInstance().getReq8CircularBeans().get(k).getId() &&
                                SingletonReq8.getInstance().getReq8CircularBeans().get(i).getSatellite().equals(
                                        SingletonReq8.getInstance().getReq8CircularBeans().get(k).getSatellite())) {
                            SingletonReq8.getInstance().getReq8CircularBeans().remove(k);
                        }
                    }
                }
            }

            // cancellazione duplicati
            System.out.println("Cancellazione duplicati;\nAttendere...");
            for (int i = SingletonReq8.getInstance().getReq8CircularBeans().size()-2; i >= 0; i--) {
                for (int k = SingletonReq8.getInstance().getReq8CircularBeans().size()-1; k > i; k--) {
                    if (SingletonReq8.getInstance().getReq8CircularBeans().get(i).getId() ==
                            SingletonReq8.getInstance().getReq8CircularBeans().get(k).getId() &&
                            SingletonReq8.getInstance().getReq8CircularBeans().get(i).getSatellite().equals(
                                    SingletonReq8.getInstance().getReq8CircularBeans().get(k).getSatellite())) {
                        SingletonReq8.getInstance().getReq8CircularBeans().remove(k);
                    }
                }
            }
            if (SingletonReq8.getInstance().getReq8CircularBeans().size() != 0) {
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException i) {
            i.printStackTrace();
        }
        return false;
    }

    private boolean circCheck(Req8CircularBean bean, Double centreLongitude, Double centreLatitude, Double dimension) {
        if (Math.sqrt((bean.getBoundLong()-centreLongitude)*(bean.getBoundLong()-centreLongitude)+
                (bean.getBoundLat()-centreLatitude)*(bean.getBoundLat()-centreLatitude))> dimension){
            return false;
        }
        return true;
    }

    public void calcolaTipi9() {
        for (int i = 0; i < SingletonReq9.getInstance().getStars().size(); i++){
            if (SingletonReq9.getInstance().getStars().get(i).getType().equals("UNBOUND")){
                SingletonReq9.getInstance().setUnbound(SingletonReq9.getInstance().getUnbound()+1);
            }
            if (SingletonReq9.getInstance().getStars().get(i).getType().equals("PRESTELLAR")){
                SingletonReq9.getInstance().setPrestellar(SingletonReq9.getInstance().getPrestellar()+1);
            }
            if (SingletonReq9.getInstance().getStars().get(i).getType().equals("PROTOSTELLAR")) {
                SingletonReq9.getInstance().setProtostellar(SingletonReq9.getInstance().getProtostellar() + 1);
            }
        }
    }

    public void calcolaTipi10() {
        for (int i = 0; i < SingletonReq10.getInstance().getStarBeans().size(); i++){
            if (SingletonReq10.getInstance().getStarBeans().get(i).getStar().getType().equals("UNBOUND")){
                if (SingletonReq10.getInstance().getStarBeans().get(i).isInStructure()){
                    SingletonReq10.getInstance().setUnbound_true(SingletonReq10.getInstance().getUnbound_true()+1);
                }
                else {
                    SingletonReq10.getInstance().setUnbound_false(SingletonReq10.getInstance().getUnbound_false()+1);
                }
            }
            if (SingletonReq10.getInstance().getStarBeans().get(i).getStar().getType().equals("PRESTELLAR")){
                if (SingletonReq10.getInstance().getStarBeans().get(i).isInStructure()){
                    SingletonReq10.getInstance().setPrestellar_true(SingletonReq10.getInstance().getPrestellar_true()+1);
                }
                else {
                    SingletonReq10.getInstance().setPrestellar_false(SingletonReq10.getInstance().getPrestellar_false()+1);
                }
            }
            if (SingletonReq10.getInstance().getStarBeans().get(i).getStar().getType().equals("PROTOSTELLAR")){
                if (SingletonReq10.getInstance().getStarBeans().get(i).isInStructure()){
                    SingletonReq10.getInstance().setProtostellar_true(SingletonReq10.getInstance().getProtostellar_true()+1);
                }
                else {
                    SingletonReq10.getInstance().setProtostellar_false(SingletonReq10.getInstance().getProtostellar_false()+1);
                }
            }
        }

        SingletonReq10.getInstance().setTotal_true(
                SingletonReq10.getInstance().getUnbound_true()+
                SingletonReq10.getInstance().getPrestellar_true()+
                SingletonReq10.getInstance().getProtostellar_true()
        );

        SingletonReq10.getInstance().setTotal_false(
                SingletonReq10.getInstance().getUnbound_false()+
                        SingletonReq10.getInstance().getPrestellar_false()+
                        SingletonReq10.getInstance().getProtostellar_false()
        );

        /*System.out.println("total in: "+ SingletonReq10.getInstance().getTotal_true());
        System.out.println("total unbound in: "+ SingletonReq10.getInstance().getUnbound_true());
        System.out.println("total pre in: "+ SingletonReq10.getInstance().getPrestellar_true());
        System.out.println("total proto in: "+ SingletonReq10.getInstance().getProtostellar_true());
        System.out.println("total out: "+ SingletonReq10.getInstance().getTotal_false());
        System.out.println("total unbound out: "+ SingletonReq10.getInstance().getUnbound_false());
        System.out.println("total pre out: "+ SingletonReq10.getInstance().getPrestellar_false());
        System.out.println("total proto out: "+ SingletonReq10.getInstance().getProtostellar_false());*/
    }

    public void calcolaDistanze12() {
        ArrayList<Double> distanze = new ArrayList<>();

        for (int i = 0; i < SingletonReq12.getInstance().getBeans().size(); i++){
            for (int k = 0; k < SingletonReq12.getInstance().getSkeletonPoints().size(); k++){
                Double distanza_1 = Math.sqrt(
                        (SingletonReq12.getInstance().getBeans().get(i).getStar().getgLon() - SingletonReq12.getInstance().getSkeletonPoints().get(k).getLongitude())
                                *
                                (SingletonReq12.getInstance().getBeans().get(i).getStar().getgLon() - SingletonReq12.getInstance().getSkeletonPoints().get(k).getLongitude())
                                +
                                (SingletonReq12.getInstance().getBeans().get(i).getStar().getgLat() - SingletonReq12.getInstance().getSkeletonPoints().get(k).getLatitude())
                        *
                        (SingletonReq12.getInstance().getBeans().get(i).getStar().getgLat() - SingletonReq12.getInstance().getSkeletonPoints().get(k).getLatitude()));

                distanze.add(distanza_1);
            }
            Optional<Double> distanzaMinima= distanze.stream().reduce(Double::min);
/*
            String distance = String.valueOf(distanzaMinima).substring(9);
*/
            String distance = String.valueOf(distanzaMinima).substring(
                    9,
                    String.valueOf(distanzaMinima).length()-1);



            SingletonReq12.getInstance().getBeans().get(i).setDistance(Double.parseDouble(distance));
        }
    }

}
