package Dao;

import Bean.Req12_Bean;
import Control.Controller;
import Control.GraphicController;
import Entity.SkeletonPoint;
import Singletons.SingletonReq12;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class Req12Dao {


    /*public static boolean spineDistance(String sat, int id){

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

            String query1 = String.format(Strings.strReq12_p, sat);
            //String query2 = String.format(Strings.strReq12_lat, sat);

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
    }*/

    public static boolean riempiSpina(int id, String sat) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = String.format(Strings.strReqNew12, sat, id);
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.first()){
                System.out.println("punti spina dorsale non trovati");
                return false;
            }

            SingletonReq12.getInstance().setSkeletonPoints(new ArrayList<>());

            Controller controller = new Controller();
            SkeletonPoint sp1 = controller.createSkeletonPoint(
                    rs.getInt("idfil"),
                    rs.getInt("idbranch"),
                    rs.getInt("n"),
                    rs.getString("type"),
                    rs.getDouble("lon"),
                    rs.getDouble("lat"),
                    rs.getDouble("flux")
            );

            SingletonReq12.getInstance().getSkeletonPoints().add(sp1);

            while (rs.next()){
                SkeletonPoint sp = controller.createSkeletonPoint(
                        rs.getInt("idfil"),
                        rs.getInt("idbranch"),
                        rs.getInt("n"),
                        rs.getString("type"),
                        rs.getDouble("lon"),
                        rs.getDouble("lat"),
                        rs.getDouble("flux")
                );

                SingletonReq12.getInstance().getSkeletonPoints().add(sp);
            }

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Accesso DB effettuato con successo");
        return true;
    }
}
