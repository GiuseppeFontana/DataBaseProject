package Dao;

import Bean.Req8CircularBean;
import Bean.Req6_8SquareBean;
import Control.Controller;
import Singletons.SingletonReq8;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class Req8Dao {
    public static boolean req8Square(Double minLong, Double maxLong, Double minLat, Double maxLat) {
        Statement stmt1 = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = String.format(Strings.strReq81, Double.toString(minLong), Double.toString(maxLong),
                    Double.toString(minLat), Double.toString(maxLat), Double.toString(minLong), Double.toString(maxLong),
                    Double.toString(minLat), Double.toString(maxLat));
            System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Req6_8SquareBean> beans = new ArrayList<>();
                SingletonReq8.getInstance().setBeans(beans);

                Req6_8SquareBean bean1 = controller.createReq6_8Bean(rs.getInt("id"),
                        rs.getString("name"),rs.getString("satellite"));
                SingletonReq8.getInstance().getBeans().add(bean1);

                while (rs.next()){
                    Req6_8SquareBean bean = controller.createReq6_8Bean(rs.getInt("id"),
                            rs.getString("name"),rs.getString("satellite"));
                    SingletonReq8.getInstance().getBeans().add(bean);
                }
            }

            conn.commit();

            rs.close();
            stmt1.close();
            conn.close();

            System.out.println("Accesso Strutture e Contorni effettuato con successo");
            return true;

        } catch (Exception e) {
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
        System.out.println("Accesso Strutture e Contorni fallito.");
        return false;
    }

    public static boolean req8Circ(Double minLong, Double maxLong, Double minLat, Double maxLat) {
        Statement stmt1 = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = String.format(Strings.strReq82, Double.toString(minLong), Double.toString(maxLong),
                    Double.toString(minLat), Double.toString(maxLat), Double.toString(minLong), Double.toString(maxLong),
                    Double.toString(minLat), Double.toString(maxLat));
            System.out.println(sql1);
            System.out.println("scansione nel quadrato circoscritto alla regione;\nAttendere...");

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Req8CircularBean> beans = new ArrayList<>();
                SingletonReq8.getInstance().setReq8CircularBeans(beans);

                Req8CircularBean bean1 = controller.createReq8CircBean(rs.getInt("id"),
                        rs.getString("name"),rs.getString("satellite"), rs.getDouble("lon"),
                        rs.getDouble("lat"));
                SingletonReq8.getInstance().getReq8CircularBeans().add(bean1);

                while (rs.next()){
                    Req8CircularBean bean = controller.createReq8CircBean(rs.getInt("id"),
                            rs.getString("name"),rs.getString("satellite"), rs.getDouble("lon"),
                            rs.getDouble("lat"));
                    SingletonReq8.getInstance().getReq8CircularBeans().add(bean);
                }
            }

            conn.commit();

            rs.close();
            stmt1.close();
            conn.close();

            System.out.println("Accesso Strutture e Contorni effettuato con successo");
            return true;

        } catch (Exception e) {
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
        System.out.println("Accesso Strutture e Contorni fallito.");
        return false;
    }
}
