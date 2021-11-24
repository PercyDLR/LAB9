package Daos;

import Beans.BContinente;
import Beans.BPais;

import java.sql.*;
import java.util.ArrayList;

public class PaisDao extends BaseDao {


    public ArrayList<BPais> obtenerListaPaises() {
        ArrayList<BPais> listaPaises = new ArrayList<>();


        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT p.*, c.nombre as `continente` FROM lab9.pais p left join continente c on (p.idcontinente = c.idcontinente);");) {

            while (rs.next()) {
                BPais pais = new BPais();

                pais.setIdPais(rs.getInt(1));
                pais.setContinente(new BContinente(rs.getInt(2),rs.getString(6)));

                pais.setNombre(rs.getString(3));
                pais.setPoblacion(rs.getInt(4));
                pais.setTamanio(rs.getInt(5));

                listaPaises.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPaises;
    }

    public void actualizarPais(BPais pais) {

        String sql = "update pais set idcontinente = ?, nombre = ?, poblacion = ?, tamanio = ? where idpais = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, pais.getContinente().getIdContinente());
            pstmt.setString(2, pais.getNombre());
            pstmt.setInt(3, pais.getPoblacion());
            pstmt.setFloat(4, pais.getTamanio());
            pstmt.setInt(5, pais.getIdPais());


            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearPais(BPais pais) {

        String sql = "insert into pais (idcontinente, nombre, poblacion, tamanio) values (?,?,?,?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, pais.getContinente().getIdContinente());
            pstmt.setString(2, pais.getNombre());
            pstmt.setInt(3, pais.getPoblacion());
            pstmt.setFloat(4, pais.getTamanio());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
