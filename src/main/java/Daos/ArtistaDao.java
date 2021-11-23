/*package Daos;

import Beans.Artista;

import java.sql.*;
import java.util.ArrayList;

public class ArtistaDao {

    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";

    public ArrayList<Artista> listarArtistas(boolean filter){
        ArrayList<Artista> listaArtistas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = null;

        if (filter) {
            sql = "SELECT a.idartista,a.nombre_artista,a.instrumento,a.banda, count(*) as 'cuenta' FROM artista a\n"+
                    "inner join banda b on (b.artista_lider=a.idartista)\n"+
                    "inner join cancion c on (a.banda=c.banda)\n" +
                    "group by a.idartista\n" +
                    "having cuenta>5;";
        } else {
            sql = "SELECT idartista,nombre_artista,instrumento,banda as 'cuenta' FROM artista;";
        }

        try (Connection conn = DriverManager.getConnection(url,user,pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                Artista art = new Artista(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                listaArtistas.add(art);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return listaArtistas;
    }
}*/
