package Dao;

import Bean.Req12_Bean;
import Control.GraphicController;
import Singletons.SingletonReq12;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class Req12Dao {

    public static boolean controlValues(String sat, int id){

        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);


            //---------------------Controllo valori inseriti-------------------------//

            int min = 0;
            int max = 0;

            String minIdfil = String.format(Strings.strReq11_contrMin, sat);
            ResultSet controlMin = stmt.executeQuery(minIdfil);



            if (controlMin.next()) {

                min = controlMin.getInt(1);
            }

            String maxIdfil = String.format(Strings.strReq11_contrMax, sat);
            ResultSet controlMax = stmt.executeQuery(maxIdfil);

            if (controlMax.next()){
                max = controlMax.getInt(1);
            }

            if (min > id || max < id){

                GraphicController graphicController = new GraphicController();
                graphicController.alertError("Inserire un valore compreso tra:\n\n\t " + min + " e " + max);
                return false;

            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public static boolean spineDistance(String sat, int id){

        Statement stmt = null;
        Connection conn = null;

        Double flusso = null;
        Double distanza = null;

        Req12_Bean req12_bean = new Req12_Bean();


        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //------------------Trovo valore del flusso della stella selezionata----------------------//

            String query = String.format(Strings.strReq12, id);
            ResultSet flux = stmt.executeQuery(query);

            if (flux.next()){
                flusso = flux.getDouble(1);
                req12_bean.setFlux(flusso);
            }



            //------------------Trovo le coordinate dei punti della spina dorsale (type = 's' spine dorsali)---/

            ArrayList<Double> lon = new ArrayList<>();
            ArrayList<Double> lat = new ArrayList<>();

            String query1 = String.format(Strings.strReq12_lon, sat);
            String query2 = String.format(Strings.strReq12_lat, sat);

            ResultSet lon_spine = stmt.executeQuery(query1);

            while (lon_spine.next()){
                lon.add(lon_spine.getDouble(1));
            }

            System.out.println("Card lon = " + lon.size());

            ResultSet lat_spine = stmt.executeQuery(query2);

            while (lat_spine.next()){
                lat.add(lat_spine.getDouble(1));
            }

            System.out.println("Card lat = " + lat.size());


            //-----------------------Trovo coordinate delle stelle--------------------//

            ArrayList<Double> lon_stars = new ArrayList<>();
            ArrayList<Double> lat_stars = new ArrayList<>();

            String query3 = String.format(Strings.strReq13_lon, id);
            String query4 = String.format(Strings.strReq13_lat, id);

            ResultSet lon_star = stmt.executeQuery(query3);

            while (lon_star.next()){
                lon_stars.add(lon_star.getDouble(1));
            }

            System.out.println("Card lon_stars = " + lon_stars.size());

            ResultSet lat_star = stmt.executeQuery(query4);

            while (lat_star.next()){
                lat_stars.add(lat_star.getDouble(1));
            }

            System.out.println("Card lat_stars = " + lat_stars.size());

            //--------------------Calcolo la distanza minima di ogni stella dalla spina dorsale---------------------//

            ArrayList<Double> distanze = new ArrayList<>();

            for (int i = 0; i < lon_stars.size(); i++){
                for (int s = 0; s < lon.size(); s++){

                    Double distanza_1 = (((lon_stars.get(i)-lon.get(s))*(lon_stars.get(i)-lon.get(s))) + ((lat_stars.get(i)-lat.get(s))*(lat_stars.get(i)-lat.get(s))));

                    distanza = Math.sqrt(distanza_1);

                    distanze.add(distanza);
                }
            }

            Optional<Double> distanzaMinima= distanze.stream().reduce(Double::min);

            String distance = String.valueOf(distanzaMinima).substring(8);

            req12_bean.setDistance(distance);

            SingletonReq12.getInstance().setBeans(req12_bean);

            System.out.println(distanzaMinima);

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /*public static void main(String arg[]){
        spineDistance("herschel", 23813);
    }*/

}
