package Daos;

import Beans.BContinente;
import Beans.BPais;

import java.sql.*;
import java.util.ArrayList;

public class PaisDao extends BaseDao {


    public ArrayList<BPais> obtenerListaPaises(String filtro) {
        ArrayList<BPais> listaPaises = new ArrayList<>();


        String sql = "SELECT p.*, c.nombre as `continente` FROM lab9.pais p left join continente c on (p.idcontinente = c.idcontinente) order by p.nombre;";

        if (!filtro.equalsIgnoreCase("")) {
            sql = "SELECT p.*, c.nombre as `continente` FROM lab9.pais p left join continente c on (p.idcontinente = c.idcontinente) where p.idcontinente = " + filtro + " order by p.nombre;";
        }

        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while (rs.next()) {
                BPais pais = new BPais();

                pais.setIdPais(rs.getInt(1));
                pais.setContinente(new BContinente(rs.getInt(2), rs.getString(6)));

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

    public void eliminarPais(int idPais) {

        String sql = "DELETE FROM pais WHERE idpais = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idPais);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public BPais obtenerPaisPorId(int idPais) {

        BPais pais = null;


        String sql = "select * from pais where idpais = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setInt(1, idPais);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pais = new BPais();
                    pais.setIdPais(idPais);
                    pais.setContinente(new BContinente(rs.getInt(2), ""));
                    pais.setNombre(rs.getString(3));
                    pais.setPoblacion(rs.getInt(4));
                    pais.setTamanio(rs.getInt(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }
}
