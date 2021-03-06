package Dao;

import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;

public class RegisterDao {
    public static boolean req32(String name, String surname, String username, String pass1, String email, boolean type) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);


            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = String.format(Strings.strRegister, name, surname, username, pass1, email, type);
            System.out.println("query:\n"+sql);

            /*
            se non metto questo la seguente riga mi da l'errore:
            org.postgresql.util.PSQLException: L'operazione richiete un «ResultSet» scorribile mentre questo è «FORWARD_ONLY».
             */
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int rs = stmt.executeUpdate(sql);

            if (rs != 1) {
                System.out.println("Errore nella Query.");
                return false;
            }

            // STEP 6: Clean-up dell'ambiente
            stmt.close();
            conn.close();

            System.out.println("Registrazione effettuata con successo");
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
        System.out.println("Query fallita");
        return false;
    }
}
