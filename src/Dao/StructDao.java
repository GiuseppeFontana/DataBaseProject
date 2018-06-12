package Dao;

import Bean.BeanId;
import Control.Controller;
import Singletons.SingletonId;
import Singletons.SingletonStruct;
import Utils.Credenziali;
import Utils.Strings;
import java.sql.*;
import java.util.ArrayList;


public class StructDao {
    public static boolean searchStruct1(int id, String satellite) {
        Statement stmt1 = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = String.format(Strings.strShowStruct, satellite, Integer.toString(id));
            System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                SingletonStruct.getInstance().setStructure(controller.createStructure(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("flux"),
                        rs.getDouble("meandens"),
                        rs.getDouble("meantemp"),
                        rs.getDouble("ellipt"),
                        rs.getDouble("contrast"),
                        rs.getString("satellite"),
                        rs.getString("instrument")));
            }

            conn.commit();

            rs.close();
            stmt1.close();
            conn.close();
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
        System.out.println("Accesso Strutture fallito.");
        return false;
    }

    public static boolean showAllId(String sat){
        Statement stmt1 = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = String.format(Strings.strShowAllID, sat);
            System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                ArrayList<BeanId> idArray = new ArrayList<>();
                Controller controller = new Controller();
                SingletonId.getInstance().setBeans(idArray);

                SingletonId.getInstance().getBeans().add(
                        controller.createBeanId(
                                rs.getInt("id")));

                while (rs.next()){
                    SingletonId.getInstance().getBeans().add(
                            controller.createBeanId(
                                    rs.getInt("id")));
                }
            }

            conn.commit();

            rs.close();
            stmt1.close();
            conn.close();
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
        System.out.println("Accesso Strutture fallito.");
        return false;
    }
}
