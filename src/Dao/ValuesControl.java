package Dao;

import Control.GraphicController;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ValuesControl {

    public static boolean max_min(String sat, int id){

        Statement stmt = null;
        Connection conn = null;

        try{

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            int min = 0;
            int max = 0;

            String minIdfil = String.format(Strings.strReq11_contrMin, sat);
            ResultSet controlMin = stmt.executeQuery(minIdfil);

            if (controlMin.next()) {

                min = controlMin.getInt(1);
            }

            String maxIdfil = String.format(Strings.strReq11_contrMax, sat);
            ResultSet controlMax = stmt.executeQuery(maxIdfil);

            if (controlMax.next()) {
                max = controlMax.getInt(1);
            }

            if (min > id || max < id) {

                GraphicController graphicController = new GraphicController();
                graphicController.alertError("Inserire un valore compreso tra:\n\n\t " + min + " e " + max);
                return false;

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return isIn(sat, id);
    }

    public static boolean isIn(String sat, int id){

        Statement stmt = null;
        Connection conn = null;

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String exist = String.format(Strings.control, sat, id);
            ResultSet controlExist = stmt.executeQuery(exist);

            if (!controlExist.next()) {

                GraphicController graphicController = new GraphicController();
                graphicController.alertError("Filamento non trovato");
                return false;
            }



        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

}
