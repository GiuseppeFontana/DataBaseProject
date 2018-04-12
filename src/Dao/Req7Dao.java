package Dao;

import Control.Controller;
import Entity.Structure;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class Req7Dao {
//    public static boolean req7(ArrayList<Structure> structures, String satellite, int min, int max) {
//        // STEP 1: dichiarazioni
//        Statement stmt = null;
//        Connection conn = null;
//        try {
//            // STEP 2: loading dinamico del driver
//            Class.forName("org.postgresql.Driver");
//
//            // STEP 3: apertura connessione
//            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);
//
//            conn.setAutoCommit(false);
//
//
//            // STEP 4: creazione ed esecuzione della query
//            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//            String sql = String.format(Strings.strReq61, satellite, Double.toString(contrast), Double.toString(elliptMin), Double.toString(elliptMax));
//            System.out.println(sql);
//
//            ResultSet rs = stmt.executeQuery(sql);
//            if (!rs.first()){
//                System.out.println("Resultset vuoto.");
//                return false;
//            }
//            else {
//                Controller controller = new Controller();
//                Structure struttura1 = controller.createStructure(rs.getInt("id"), rs.getString("name"),
//                        rs.getDouble("flux"), rs.getDouble("meandens"), rs.getDouble("meantemp"),
//                        rs.getDouble("ellipt"), rs.getDouble("contrast"), rs.getString("satellite"),
//                        rs.getString("instrument"));
//
//                structures.add(struttura1);
//
//                while (rs.next()){
//                    Structure struttura = controller.createStructure(rs.getInt("id"), rs.getString("name"),
//                            rs.getDouble("flux"), rs.getDouble("meandens"), rs.getDouble("meantemp"),
//                            rs.getDouble("ellipt"), rs.getDouble("contrast"), rs.getString("satellite"),
//                            rs.getString("instrument"));
//
//                    structures.add(struttura);
//                }
//            }
//
//            conn.commit();
//
//            // STEP 6: Clean-up dell'ambiente
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (Exception e) {
//            // Errore nel loading del driver
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null)
//                    stmt.close();
//            } catch (SQLException se2) {
//                se2.printStackTrace();
//            }
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        System.out.println("Accesso Strutture effettuato con successo");
//        return true;
//    }
//    }
}
