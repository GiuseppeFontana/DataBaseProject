package Dao;

import Bean.Req10AllBoundsBean;
import Bean.Req10StarBean;
import Control.Controller;
import Entity.Bound;
import Singletons.SingletonReq10;
import Utils.Credenziali;
import Utils.Strings;
import java.sql.*;
import java.util.ArrayList;

public class Req10Dao {

    public static boolean searchStarsInArea(double lonMin, double lonMax, double latMin, double latMax) {
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

            String sql1 = String.format(Strings.strReq101, lonMin, lonMax, latMin, latMax);
            System.out.println(sql1);

            ResultSet rs1 = stmt1.executeQuery(sql1);

            if (!rs1.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Req10StarBean> starBeans = new ArrayList<>();
                SingletonReq10.getInstance().setStarBeans(starBeans);

                Req10StarBean bean1 = controller.createReq10StarBean(
                        controller.createStar(
                                rs1.getInt("id"),
                                rs1.getString("name"),
                                rs1.getDouble("lon"),
                                rs1.getDouble("lat"),
                                rs1.getDouble("flux"),
                                rs1.getString("type")
                        )
                );

                SingletonReq10.getInstance().getStarBeans().add(bean1);

                while (rs1.next()){
                    Req10StarBean bean = controller.createReq10StarBean(
                            controller.createStar(
                                    rs1.getInt("id"),
                                    rs1.getString("name"),
                                    rs1.getDouble("lon"),
                                    rs1.getDouble("lat"),
                                    rs1.getDouble("flux"),
                                    rs1.getString("type")
                            )
                    );

                    SingletonReq10.getInstance().getStarBeans().add(bean);
                }
            }





            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs1.close();
            stmt1.close();
            conn.close();

            System.out.println("Accesso Stelle effettuato con successo");
            return true;

        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null)
                    stmt1.close();
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
        System.out.println("Accesso Stelle fallito.");
        return false;
    }

    public static boolean searchBoundsInArea(double lonMin, double lonMax, double latMin, double latMax){
        // STEP 1: dichiarazioni
        Statement stmt1 = null;
        Statement stmt2 = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            // STEP 4: creazione ed esecuzione della query
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String satellite1 = "herschel";
            String satellite2 = "spitzer";

            String sql1 = String.format(Strings.strReq102, satellite1, lonMin, lonMax, latMin, latMax);
            System.out.println(sql1);

            String sql2 = String.format(Strings.strReq102, satellite1, lonMin, lonMax, latMin, latMax);
            System.out.println(sql2);

            ResultSet rs1 = stmt1.executeQuery(sql1);
            ResultSet rs2 = stmt1.executeQuery(sql2);

            System.out.println(rs1.isClosed());
            //TODO i resultset sono chiusi già da qui, quindi non va avanti

            if (!rs1.first() && !rs2.first()) {
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {

                Controller controller = new Controller();
                ArrayList<Req10AllBoundsBean> array = new ArrayList<>();
                SingletonReq10.getInstance().setAllBoundsBeans(array);

                if (rs1.first()) {
                    Req10AllBoundsBean bean1 = controller.createReq10AllBoundsBean(rs1.getInt("id"), satellite1);
                    SingletonReq10.getInstance().getAllBoundsBeans().add(bean1);

                    while (rs1.next()) {
                        Req10AllBoundsBean bean = controller.createReq10AllBoundsBean(rs1.getInt("id"), satellite1);
                        SingletonReq10.getInstance().getAllBoundsBeans().add(bean);
                    }
                }

                if (rs2.first()) {
                    Req10AllBoundsBean bean1 = controller.createReq10AllBoundsBean(
                            rs2.getInt("id"),
                            satellite2
                    );

                    SingletonReq10.getInstance().getAllBoundsBeans().add(bean1);

                    while (rs2.next()) {
                        Req10AllBoundsBean bean = controller.createReq10AllBoundsBean(
                                rs2.getInt("id"),
                                satellite2
                        );

                        SingletonReq10.getInstance().getAllBoundsBeans().add(bean);
                    }
                }
            }

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs1.close();
            rs2.close();
            stmt1.close();
            stmt2.close();
            conn.close();

            System.out.println("Accesso Contorni effettuato con successo");
            return true;

        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null)
                    stmt1.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (stmt2 != null)
                    stmt2.close();
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
        System.out.println("Accesso Contorni fallito.");
        return false;
    }

    public static boolean getBounds(int id, String satellite) {
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

            String sql1 = String.format(Strings.strReq92, satellite, Integer.toString(id));
            //System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Bound> array = new ArrayList<>();
                SingletonReq10.getInstance().setStructureBounds(array);

                Bound bound1 = controller.createBound(rs.getInt("id"),
                        rs.getDouble("lon"),
                        rs.getDouble("lat"));
                SingletonReq10.getInstance().getStructureBounds().add(bound1);

                while (rs.next()){
                    Bound bound = controller.createBound(rs.getInt("id"),
                            rs.getDouble("lon"),
                            rs.getDouble("lat"));
                    SingletonReq10.getInstance().getStructureBounds().add(bound);
                }
            }

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt1.close();
            conn.close();

            //System.out.println("Accesso Contorni effettuato con successo");
            return true;

        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null)
                    stmt1.close();
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
        //System.out.println("Accesso Contorni fallito.");
        return false;
    }

}
