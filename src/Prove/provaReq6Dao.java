package Prove;

import Prove.Req6Bean;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class provaReq6Dao {
    public static ArrayList<Req6Bean> req6(double percBrillanza, double elliptMin, double elliptMax) {
        // STEP 1: dichiarazioni
        Statement stmt1 = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        Connection conn = null;

        ArrayList<Req6Bean> beans = new ArrayList<Req6Bean>();

        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            double contrast = (percBrillanza/100.0) + 1.0;

            String sql1 = String.format(Strings.strReq61, Double.toString(contrast), Double.toString(elliptMin), Double.toString(elliptMax), Double.toString(contrast), Double.toString(elliptMin), Double.toString(elliptMax));
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
                return null;
            }
            else {
                int total1 = rs[1].getInt("count");
                int total2 = rs[2].getInt("count");


                while (rs[0].next()){
                    beans.add(new Req6Bean(rs[0].getInt("id"), rs[0].getString("name"), rs[0].getString("satellite"), total1+total2));
                }
            }

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs[0].close();
            rs[1].close();
            rs[2].close();
            stmt1.close();
            stmt2.close();
            stmt3.close();
            conn.close();
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
        System.out.println("Accesso Strutture effettuato con successo");
        return beans;
    }
}
