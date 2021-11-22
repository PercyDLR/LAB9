package Daos;

import Beans.Cancion;

import java.sql.*;
import java.util.ArrayList;

public class CancionDao {

    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";

    public ArrayList<Cancion> topCanciones(){
        ArrayList<Cancion> top =new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url,user,pass);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select c.idcancion,c.nombre_cancion,c.banda,count(*) as 'cantidad' from cancion c\n" +
                        "inner join reproduccion r on (c.idcancion=r.cancion_idcancion)\n" +
                        "group by c.idcancion\n" +
                        "having cantidad>2\n" +
                        "order by cantidad desc;")) {
            while(rs.next()){
                Cancion can = new Cancion();
                can.setIdCancion(rs.getInt(1));
                can.setNombre(rs.getString(2));
                can.setBanda(rs.getString(3));
                top.add(can);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return top;
    }

    public ArrayList<Cancion> listarCanciones(String id){
        ArrayList<Cancion> lista =new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url,user,pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select c.idcancion,c.nombre_cancion,c.banda as 'cantidad' from cancion c\n" +
                     "where c.banda like '"+id+"%'\n"+
                     "order by c.banda, c.nombre_cancion;")) {
            while(rs.next()){
                Cancion can = new Cancion();
                can.setIdCancion(rs.getInt(1));
                can.setNombre(rs.getString(2));
                can.setBanda(rs.getString(3));
                lista.add(can);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
