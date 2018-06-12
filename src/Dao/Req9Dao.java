package Dao;

import Control.Controller;
import Entity.Bound;
import Entity.Star;
import Singletons.SingletonReq9;
import Utils.Credenziali;
import Utils.Strings;
import java.sql.*;
import java.util.ArrayList;

public class Req9Dao {
    public static boolean getStars() {
        Statement stmt1 = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = Strings.strReq91;
            System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Star> array = new ArrayList<>();
                SingletonReq9.getInstance().setStars(array);

                Star star1 = controller.createStar(rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("lon"), rs.getDouble("lat"),
                        rs.getDouble("flux"), rs.getString("type"));
                SingletonReq9.getInstance().getStars().add(star1);

                while (rs.next()){
                    Star star = controller.createStar(rs.getInt("id"), rs.getString("name"),
                            rs.getDouble("lon"), rs.getDouble("lat"),
                            rs.getDouble("flux"), rs.getString("type"));
                    SingletonReq9.getInstance().getStars().add(star);
                }
            }

            conn.commit();

            rs.close();
            stmt1.close();
            conn.close();

            System.out.println("Accesso Stelle effettuato con successo");
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
        System.out.println("Accesso Stelle fallito.");
        return false;
    }

    public static boolean getBounds(int id, String satellite) {
        Statement stmt1 = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = String.format(Strings.strReq92, satellite, Integer.toString(id));
            System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Bound> array = new ArrayList<>();
                SingletonReq9.getInstance().setStructureBounds(array);

                Bound bound1 = controller.createBound(rs.getInt("id"),
                        rs.getDouble("lon"),
                        rs.getDouble("lat"));
                SingletonReq9.getInstance().getStructureBounds().add(bound1);

                while (rs.next()){
                    Bound bound = controller.createBound(rs.getInt("id"),
                            rs.getDouble("lon"),
                            rs.getDouble("lat"));
                    SingletonReq9.getInstance().getStructureBounds().add(bound);
                }
            }

            conn.commit();

            rs.close();
            stmt1.close();
            conn.close();

            System.out.println("Accesso Contorni effettuato con successo");
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
        System.out.println("Accesso Contorni fallito.");
        return false;
    }
}
