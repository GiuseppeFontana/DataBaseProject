package Dao;

import Control.Controller;
import Entity.SkeletonPoint;
import Singletons.SingletonReq12;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class Req12Dao {


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

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
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
