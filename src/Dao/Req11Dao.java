package Dao;

import Bean.Req11_Bean;
import Control.Controller;
import Singletons.SingletonReq11;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class Req11Dao {

    //---------trovo il numero di segmenti per il filamento inserito----------//

    //-----------------------------------e------------------------------------//

    //-----------------------------Trovo idbranch dei segmenti per il filamento scelto----------------------------//



    public static boolean numeroSegmenti(String sat, int id){

        SingletonReq11.getInstance().setId(id);
        SingletonReq11.getInstance().setSat(sat);

        Statement stmt = null;
        Connection conn = null;
        int nSeg = 0;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = String.format(Strings.strReq52, sat, id);  //numero segmenti
            ResultSet nSegmenti = stmt.executeQuery(query);



            ArrayList<Req11_Bean> beans = new ArrayList<>();
            SingletonReq11.getInstance().setBeans(beans);
            Controller controller = new Controller();

            if (nSegmenti.next()){
                nSeg = nSegmenti.getInt(1);

                System.out.println("Numero segmenti= " + nSeg);

                String query2 = String.format(Strings.strReq11_1, sat, id);
                ResultSet segmenti = stmt.executeQuery(query2);

                    while (segmenti.next()){
                        System.out.println("Segmento:" + " " + segmenti.getInt(1));
                        SingletonReq11.getInstance().getBeans().add(controller.createReq11_Bean(nSeg, segmenti.getInt(1)));

                    }
                }


        }catch (SQLException | ClassNotFoundException e){   //TODO MATTIA perchè classnotfound?
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //-----------------Trovo le coordinate dei vertici del segmento selezionato--------------------------------//

    public static Optional coordinate(int Segmento){

        Statement stmt = null;
        Connection conn = null;


        Optional<Double> distanzaMinima = null;

        String sat = SingletonReq11.getInstance().getSat();
        int id = SingletonReq11.getInstance().getId();

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query3 = String.format(Strings.strReq11_3, sat, Segmento);  //Trovo n massimo
            ResultSet rs = stmt.executeQuery(query3);
            ArrayList<Integer> k = new ArrayList<Integer>();

            if (rs.next()) {
                k.add(1);
                k.add(rs.getInt(1));
            }

            ArrayList<Double> LonVertex = new ArrayList<>();
            ArrayList<Double> LatVertex = new ArrayList<>();

            ResultSet resultSet[] = new ResultSet[2];


            for (int i = 0; i < k.size(); i++) {
                String query4_lon = String.format(Strings.strReq11_2, sat, Segmento, k.get(i));

                resultSet[i] = stmt.executeQuery(query4_lon);

                if (resultSet[i].next()) {
                    LonVertex.add(resultSet[i].getDouble(1));
                }
            }

            ResultSet resultSet1[] = new ResultSet[2];

            for (int i = 0; i < k.size(); i++) {

                String query4_lat = String.format(Strings.strReq11_2_1, sat, Segmento, k.get(i));
                resultSet1[i] = stmt.executeQuery(query4_lat);

                if (resultSet1[i].next()) {
                    LatVertex.add(resultSet1[i].getDouble(1));
                }

            }


            //-----------------Trovo le coordinate dei contorni del filamento-----------------------------------//

            String query5 = String.format(Strings.strReq11_4, sat, id);
            String query6 = String.format(Strings.strReq11_4, sat, id);

            ResultSet contorniFil_lon = stmt.executeQuery(query5);
            ArrayList<Double> lonContorniFilamento = new ArrayList<>();
            ArrayList<Double> latContorniFilamento = new ArrayList<>();


            while (contorniFil_lon.next()) {
                lonContorniFilamento.add(contorniFil_lon.getDouble(1));
                //latContorniFilamento.add(contorniFil.getDouble(2));
            }

            ResultSet contorniFil_lat = stmt.executeQuery(query6);


            while (contorniFil_lat.next()){
                latContorniFilamento.add(contorniFil_lat.getDouble(1));
            }

            //----------------Calcolo la distanza dei vertici del segmento selezionato dal contorno
            //----------------e le metto in array--------------------------------------------------------------//

            ArrayList<Double> distanze = new ArrayList<>();

            for (int i = 0; i<lonContorniFilamento.size(); i++){

                for (int s = 0; s<LatVertex.size(); s++){

                    Double prova = ((LonVertex.get(s) - lonContorniFilamento.get(s))*(LonVertex.get(s) - lonContorniFilamento.get(s))-(LatVertex.get(s)) - (latContorniFilamento.get(s))*(LatVertex.get(s) - latContorniFilamento.get(s)));
                    Double minima = Math.sqrt(prova);
                    distanze.add(minima);
                }

            }

            System.out.println("FINE");

            distanzaMinima= distanze.stream().reduce(Double::min);

            System.out.println("Distanza minima: " + distanzaMinima);

        }catch (SQLException  | ClassNotFoundException e){
            e.printStackTrace();
        }
        return distanzaMinima;
    }

}
