package Dao;

import Control.Controller;

import Bean.Req6_8SquareBean;
import Singletons.SingletonReq6;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;


public class Req6Dao {
    public static boolean req6(double percBrillanza, double elliptMin, double elliptMax) {
        Statement stmt1 = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            double contrast = (percBrillanza/100.0) + 1.0;

            String sql1 = String.format(Strings.strReq61, Double.toString(contrast), Double.toString(elliptMin),
                    Double.toString(elliptMax), Double.toString(contrast), Double.toString(elliptMin), Double.toString(elliptMax));
            String sql2 = String.format(Strings.strReq62, "herschel");
            String sql3 = String.format(Strings.strReq62, "spitzer");
            System.out.println(sql1);
            System.out.println(sql2);
            System.out.println(sql3);

            ResultSet rs[] = new ResultSet[3];

            rs[0]= stmt1.executeQuery(sql1);
            rs[1]= stmt2.executeQuery(sql2);
            rs[2]= stmt3.executeQuery(sql3);
            if (!rs[0].first() || !rs[1].first() || !rs[2].first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                ArrayList<Req6_8SquareBean> beans = new ArrayList<>();
                SingletonReq6.getInstance().setBeans(beans);

                Req6_8SquareBean bean1 = controller.createReq6_8Bean(rs[0].getInt("id"), rs[0].getString("name"),rs[0].getString("satellite"));
                SingletonReq6.getInstance().getBeans().add(bean1);

                while (rs[0].next()){
                    Req6_8SquareBean bean = controller.createReq6_8Bean(rs[0].getInt("id"), rs[0].getString("name"),rs[0].getString("satellite"));
                    SingletonReq6.getInstance().getBeans().add(bean);
                }

                int n1 = rs[1].getInt("count");
                int n2 = rs[2].getInt("count");

                SingletonReq6.getInstance().setTotaleStrutture(n1+n2);
            }

            conn.commit();

            rs[0].close();
            rs[1].close();
            rs[2].close();
            stmt1.close();
            stmt2.close();
            stmt3.close();
            conn.close();

            System.out.println("Accesso Strutture effettuato con successo");
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
                if (stmt2 != null)
                    stmt2.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (stmt3 != null)
                    stmt3.close();
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
