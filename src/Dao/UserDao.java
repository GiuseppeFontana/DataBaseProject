package Dao;

import Control.Controller;
import Utils.Credenziali;
import Utils.Strings;
import Control.GraphicController;

import java.sql.*;

public class UserDao {
    public static boolean daoLogin(String u, String p){
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);


            stmt = conn.createStatement();
            String sql = String.format(Strings.strLogin, u, p);
            System.out.println("query:\n"+sql);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()){
                System.out.println("empty resultset;");
                GraphicController graphicController = new GraphicController();
                String message = "Username o password incorretti";
                graphicController.alertError(message);
                return false;
            }

            String name = rs.getString("name");
            String email = rs.getString("email");
            String surname = rs.getString("surname");
            Boolean admin = rs.getBoolean("admin");

            Controller controller = new Controller();
            controller.createUser(u, p, email, name, surname, admin);

            if(!admin){
                GraphicController graphicController = new GraphicController();
                graphicController.homeUser();

            }

            if (admin){
                GraphicController graphicController = new GraphicController();
                graphicController.homeAdmin();
            }

            rs.close();
            stmt.close();

            conn.close();
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
        return true;
    }
}