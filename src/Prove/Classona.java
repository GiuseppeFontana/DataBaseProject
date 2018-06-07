package Prove;

import Bean.Req10AllBoundsBean;
import Bean.Req10StarBean;
import Control.Controller;
import Control.GraphicController;
import Entity.Bound;
import Entity.Star;
import Singletons.SingletonReq10;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class Classona {

    public static String reqBound = "SELECT * FROM %s_boundaries WHERE id = '%s'";

    public boolean ricercaRequisito10(double extLat, double extLon, double centreLat, double centreLon) throws Exception{
        double minLat = centreLat - extLat/2;
        double maxLat = centreLat + extLat/2;
        double minLon = centreLon - extLon/2;
        double maxLon = centreLon + extLon/2;

        if (!recuperaStelle(minLat,maxLat,minLon,maxLon)){
            GraphicController graphicController = new GraphicController();
            graphicController.alertError("nessuna stella");
            return false;
        }
        if (!recuperaStrutture(minLat,maxLat,minLon,maxLon)){
            GraphicController graphicController = new GraphicController();
            graphicController.alertError("errore2");
            return false;
        }

        System.out.println("inizio conti;\nstrutture: "+ SingletonReq10.getInstance().getStructuresInBeans().size());

        int counter = 0;
        for (int i = 0; i< SingletonReq10.getInstance().getStructuresInBeans().size(); i++){
            counter++;
            if (counter%1000==0){
                System.out.println("analizzate "+counter+" strutture.");
            }
            recuperaContorni(SingletonReq10.getInstance().getStructuresInBeans().get(i));
            for (int k = 0; k< SingletonReq10.getInstance().getStarBeans().size(); k++){
                if (!SingletonReq10.getInstance().getStarBeans().get(k).isInStructure()
                        && checkIn(SingletonReq10.getInstance().getStarBeans().get(k).getStar(),
                        SingletonReq10.getInstance().getStructureBounds())){
                    SingletonReq10.getInstance().getStarBeans().get(k).setInStructure(true);
                }
            }
        }
        return true;
    }

    private boolean checkIn(Star star, ArrayList<Bound> structureBounds) {
            double x = 0;
            for (int i=0; i< structureBounds.size()-1; i++){
                double k = Math.atan(
                        ((structureBounds.get(i).getLongitude()-star.getgLon())*(structureBounds.get(i+1).getLatitude()-star.getgLat())
                                -(structureBounds.get(i).getLatitude()-star.getgLat())*(structureBounds.get(i+1).getLongitude()-star.getgLon()))
                        /
                                ((structureBounds.get(i).getLongitude()-star.getgLon())*(structureBounds.get(i+1).getLongitude()-star.getgLon())
                                        +(structureBounds.get(i).getLatitude()-star.getgLat())*(structureBounds.get(i+1).getLatitude()-star.getgLat())));
                x= x+k;
            }
            x = Math.abs(x);
            if (x>=0.01){
                return true;
            }
            return false;
    }

    private void recuperaContorni(Req10AllBoundsBean req10AllBoundsBean) {
        // STEP 1: dichiarazioni
        Statement stmt1 = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = String.format(Classona.reqBound, req10AllBoundsBean.getSatellite(), req10AllBoundsBean.getId());
            //System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);
            if (!rs.first()){
                //System.out.println("Errore 3");
                return;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Bound> beans = new ArrayList<>();
                SingletonReq10.getInstance().setStructureBounds(beans);

                Bound bean1 = controller.createBound(
                            rs.getInt("id"),
                            rs.getDouble("lon"),
                            rs.getDouble("lat"));

                SingletonReq10.getInstance().getStructureBounds().add(bean1);

                while (rs.next()){
                    Bound bean = controller.createBound(
                            rs.getInt("id"),
                            rs.getDouble("lon"),
                            rs.getDouble("lat"));

                    SingletonReq10.getInstance().getStructureBounds().add(bean);
                }
            }

            conn.commit();
            rs.close();
            stmt1.close();
            conn.close();

            //System.out.println("Accesso effettuato con successo");
            return;

        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        }

        //System.out.println("Accesso fallito.");
        return;
    }

    private boolean recuperaStrutture(double minLat, double maxLat, double minLon, double maxLon) {
        // STEP 1: dichiarazioni
        Statement stmt1 = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ArrayList<Req10AllBoundsBean> structArray = new ArrayList<>();
            SingletonReq10.getInstance().setStructuresInBeans(structArray);

            String sql1 = String.format(Strings.strReq102, "herschel", minLon, maxLon, minLat, maxLat);
            //System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);
            if (!rs.first()){
                //System.out.println("Resultset vuoto.");
            }
            else {
                Controller controller = new Controller();

                Req10AllBoundsBean bean1 = controller.createReq10AllBoundsBean(
                        rs.getInt("id"), "herschel");

                SingletonReq10.getInstance().getStructuresInBeans().add(bean1);

                while (rs.next()){
                    Req10AllBoundsBean bean = controller.createReq10AllBoundsBean(
                            rs.getInt("id"), "herschel");

                    SingletonReq10.getInstance().getStructuresInBeans().add(bean);
                }
            }

            String sql2 = String.format(Strings.strReq102, "spitzer", minLon, maxLon, minLat, maxLat);
            //System.out.println(sql2);

            rs = stmt1.executeQuery(sql2);
            if (!rs.first()){
                //System.out.println("Resultset vuoto.");
            }
            else {
                Controller controller = new Controller();

                Req10AllBoundsBean bean1 = controller.createReq10AllBoundsBean(
                        rs.getInt("id"), "spitzer");

                SingletonReq10.getInstance().getStructuresInBeans().add(bean1);

                while (rs.next()){
                    Req10AllBoundsBean bean = controller.createReq10AllBoundsBean(
                            rs.getInt("id"), "spitzer");

                    SingletonReq10.getInstance().getStructuresInBeans().add(bean);
                }
            }

            conn.commit();
            rs.close();
            stmt1.close();
            conn.close();

            //System.out.println("Accesso effettuato con successo");
            return true;

        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        }

        //System.out.println("Accesso fallito.");
        return false;

    }

    private boolean recuperaStelle(double minLat, double maxLat, double minLon, double maxLon) {
        // STEP 1: dichiarazioni
        Statement stmt1 = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = String.format(Strings.strReq101, minLon, maxLon, minLat, maxLat);
            //System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);
            if (!rs.first()){
                //System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Req10StarBean> beans = new ArrayList<>();
                SingletonReq10.getInstance().setStarBeans(beans);

                Req10StarBean bean1 = controller.createReq10StarBean(
                        controller.createStar(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("lon"),
                                rs.getDouble("lat"),
                                rs.getDouble("flux"),
                                rs.getString("type")));

                SingletonReq10.getInstance().getStarBeans().add(bean1);

                while (rs.next()){
                    Req10StarBean bean = controller.createReq10StarBean(
                            controller.createStar(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getDouble("lon"),
                                    rs.getDouble("lat"),
                                    rs.getDouble("flux"),
                                    rs.getString("type")));

                    SingletonReq10.getInstance().getStarBeans().add(bean);
                }
            }

            conn.commit();
            rs.close();
            stmt1.close();
            conn.close();

            //System.out.println("Accesso effettuato con successo");
            return true;

        } catch (Exception e) {
        // Errore nel loading del driver
        e.printStackTrace();
        }

        //System.out.println("Accesso fallito.");
        return false;

    }

    public static void main(String args[]) throws Exception{
        Classona classona = new Classona();
        classona.ricercaRequisito10(80,80,10,10);

        int in = 0;
        int out = 0;
        int counter = 0;
        for (int i= 0; i< SingletonReq10.getInstance().getStarBeans().size(); i++){
            counter++;
            if (counter%100==0){
                System.out.println("dentro "+ in +" dopo " + counter+ " iterazioni");
            }
            if (SingletonReq10.getInstance().getStarBeans().get(i).isInStructure()){
                in++;
            } else {
                out++;
            }
        }

        System.out.println("total "+ SingletonReq10.getInstance().getStarBeans().size());
        System.out.println("in "+ in);
        System.out.println("out "+ out);
    }
}
