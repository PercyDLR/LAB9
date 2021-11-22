package Daos;

import Beans.TPC;
import Beans.Tour;

import java.sql.*;
import java.util.ArrayList;

public class TourDao {

    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";


    public ArrayList<Tour> obtenerListaTours(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Tour> listaTours = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from tour where nombre_tour like '%world%'")) {

            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String banda = rs.getString(3);

                listaTours.add(new Tour(id,nombre,banda));
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        return listaTours;
    }

    public ArrayList<TPC> listaToursCiudad(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<TPC> listaTours = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select t.nombre_tour,c.nombre_ciudad,tc.fecha from tours_por_ciudad tc\n" +
                     "inner join tour t on (tc.tour=t.idtour)\n" +
                     "inner join ciudad c on (tc.ciudad=c.idciudad);")) {

            while (rs.next()) {
                TPC tour = new TPC(rs.getString(3),rs.getString(1),rs.getString(2));
                listaTours.add(tour);
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        return listaTours;
    }



}
