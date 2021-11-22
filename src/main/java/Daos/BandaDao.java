package Daos;

import Beans.Banda;

import java.sql.*;
import java.util.ArrayList;

public class BandaDao {

    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";

    //En este caso se usa preparedStatement
    public ArrayList<Banda> obtenerListaBandas(String filter) {
        ArrayList<Banda> listabandas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select b.idbanda,b.nombre_banda,b.artista_lider from banda b\n" +
                "inner join artista a on (a.idartista=b.artista_lider)\n" +
                "where b.nombre_banda like ? or a.nombre_artista like ?;";

        try (Connection conn = DriverManager.getConnection(url,user,pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,"%"+filter);
            pstmt.setString(2,"%"+filter);

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Banda banda = new Banda(rs.getString(1),rs.getString(2),rs.getInt(3));
                    listabandas.add(banda);
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return listabandas;
    }


}
