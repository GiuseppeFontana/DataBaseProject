package Dao;

import Control.Controller;
import Entity.Structure;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class Req6Dao {
    public static boolean req6(ArrayList<Structure> structures, String satellite, double percBrillanza, double elliptMin, double elliptMax, int[] sruttureTotali) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            double contrast = (percBrillanza/100.0) + 1.0;

            String sql = String.format(Strings.strReq61, satellite, Double.toString(contrast), Double.toString(elliptMin), Double.toString(elliptMax));
            String sql2 = String.format(Strings.strReq62,satellite);
            System.out.println(sql);
            System.out.println(sql2);
            ResultSet rs[] = new ResultSet[2];

            rs[0]= stmt.executeQuery(sql);
            rs[1]= stmt.executeQuery(sql2);
            if (!rs[0].first() || !rs[1].first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                Structure struttura1 = controller.createStructure(rs[0].getInt("id"), rs[0].getString("name"),
                        rs[0].getDouble("flux"), rs[0].getDouble("meandens"), rs[0].getDouble("meantemp"),
                        rs[0].getDouble("ellipt"), rs[0].getDouble("contrast"), rs[0].getString("satellite"),
                        rs[0].getString("instrument"));

                structures.add(struttura1);

                while (rs[0].next()){
                    Structure struttura = controller.createStructure(rs[0].getInt("id"), rs[0].getString("name"),
                            rs[0].getDouble("flux"), rs[0].getDouble("meandens"), rs[0].getDouble("meantemp"),
                            rs[0].getDouble("ellipt"), rs[0].getDouble("contrast"), rs[0].getString("satellite"),
                            rs[0].getString("instrument"));

                    structures.add(struttura);
                }

                sruttureTotali[0] = rs[1].getInt("count");
            }

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs[0].close();
            rs[1].close();
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
        System.out.println("Accesso Strutture effettuato con successo");
        return true;
    }
}
