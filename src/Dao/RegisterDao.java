package Dao;

import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;

public class RegisterDao {
    public static boolean req32(String name, String surname, String username, String pass1, String email, boolean type) {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            stmt = conn.createStatement();
            String sql = String.format(Strings.strRegister, name, surname, username, pass1, email, type);
            System.out.println("query:\n"+sql);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int rs = stmt.executeUpdate(sql);

            if (rs != 1) {
                System.out.println("Errore nella Query.");
                return false;
            }

            stmt.close();
            conn.close();

            System.out.println("Registrazione effettuata con successo");
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
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
        System.out.println("Query fallita");
        return false;
    }
}
