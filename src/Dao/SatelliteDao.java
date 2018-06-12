package Dao;

import Utils.Credenziali;
import Utils.Strings;
import java.sql.*;
import java.time.LocalDate;

public class SatelliteDao {
    public static boolean insertSatellite(String satellite, LocalDate beginact, String endact, String agency) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);


            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = null;
            if (!endact.equals("NULL")){
                sql = String.format(Strings.strInsertSatellite,satellite, beginact, endact, agency);
            }
            else {
                sql = String.format(Strings.strInsertSatellite2,satellite, beginact, endact, agency);
            }
            System.out.println(sql);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int rs = stmt.executeUpdate(sql);

            if (rs != 1) {
                System.out.println("Inserimento Satellite fallito.");
                return false;
            }

            // STEP 6: Clean-up dell'ambiente
            stmt.close();
            conn.close();

            System.out.println("Inserimento Satellite effettuato con successo");
            return true;

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
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
        System.out.println("Inserimento Satellite fallito.");
        return false;
    }

    public static boolean insertInstrument(String instrument, String sat) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);


            // STEP 4: creazione ed esecuzione della query
            String sql = String.format(Strings.strInsertInstrument,instrument,sat);
            System.out.println(sql);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int rs = stmt.executeUpdate(sql);

            if (rs != 1) {
                System.out.println("Inserimento Strumento fallito");
                return false;
            }

            // STEP 6: Clean-up dell'ambiente
            stmt.close();
            conn.close();

            System.out.println("Inserimento effettuato con successo");
            return true;

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
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
        System.out.println("Inserimento Strumento fallito");
        return false;
    }

    public static boolean insertStrip(String instrument, Double strip) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Statement stmt2 = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);
            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            String sql2 = String.format(Strings.strPickSatellite, instrument);
            System.out.println(sql2);


            stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //before stmt2
            ResultSet rs1 = stmt2.executeQuery(sql2);
            if (!rs1.first()) {
                System.out.println("Ricerca Satellite fallita");
                return false;
            }

            String sql = String.format(Strings.strInsertStrip, strip, instrument, rs1.getString(1));
            System.out.println(sql);
            int rs = stmt.executeUpdate(sql);
            if (rs != 1) {
                System.out.println("Inserimento banda fallito");
                return false;
            }

            // STEP 6: Clean-up dell'ambiente
            conn.commit();
            stmt2.close();
            stmt.close();
            conn.close();

            System.out.println("Inserimento banda effettuato con successo");
            return true;

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
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
        System.out.println("Inserimento banda fallito");
        return false;
    }

}
