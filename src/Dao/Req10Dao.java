package Dao;

import Bean.Req10StarBean;
import Control.Controller;
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

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Req10StarBean> starBeans = new ArrayList<>();
                SingletonReq10.getInstance().setStarBeans(starBeans);

                Req10StarBean bean1 = controller.createReq10StarBean(
                        controller.createStar(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("lon"),
                                rs.getDouble("lat"),
                                rs.getDouble("flux"),
                                rs.getString("type")
                        )
                );

                SingletonReq10.getInstance().getStarBeans().add(bean1);

                while (rs.next()){
                    Req10StarBean bean = controller.createReq10StarBean(
                            controller.createStar(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getDouble("lon"),
                                    rs.getDouble("lat"),
                                    rs.getDouble("flux"),
                                    rs.getString("type")
                            )
                    );

                    SingletonReq10.getInstance().getStarBeans().add(bean);
                }
            }

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs.close();
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

    //TODO finire
}
