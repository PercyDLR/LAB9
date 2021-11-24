package Daos;

import Beans.BContinente;
import Beans.BPais;
import Beans.BUniversidad;

import java.sql.*;
import java.util.ArrayList;

public class UniversidadDao extends BaseDao{

    public BUniversidad infoUniversidad(int id){
        BUniversidad u = new BUniversidad();

        String sql = "select u.iduniversidad, u.nombre, u.ranking, u.numAlumnos, u.foto, c.idcontinente, c.nombre, p.idpais, p.nombre, p.poblacion, p.tamanio from universidad u\n" +
                "inner join pais p on (u.idpais=p.idpais)\n" +
                "inner join continente c on (p.idcontinente=c.idcontinente)\n" +
                "where u.iduniversidad="+id;

        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            if(rs.next()){

                u.setIdUniversidad(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setRanking(rs.getInt(3));
                u.setNumAlumnos(rs.getInt(4));
                u.setFoto(rs.getString(5));

                BPais p = new BPais();
                p.setContinente(new BContinente(rs.getInt(6),rs.getString(7)));
                p.setIdPais(rs.getInt(8));
                p.setNombre(rs.getString(9));
                p.setPoblacion(rs.getInt(10));
                p.setTamanio(rs.getInt(11));
                u.setPais(p);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return u;
    }

    public ArrayList<BUniversidad> listarUniversidades(String filtro){

        ArrayList<BUniversidad> listaUniversidades = new ArrayList<>();

        String sql = "select u.iduniversidad, u.nombre, u.ranking, u.numAlumnos, u.foto, c.idcontinente, c.nombre, p.idpais, p.nombre, p.poblacion, p.tamanio from universidad u\n" +
                "inner join pais p on (u.idpais=p.idpais)\n" +
                "inner join continente c on (p.idcontinente=c.idcontinente)\n" +
                "order by "+filtro;

        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                BUniversidad u = new BUniversidad();

                u.setIdUniversidad(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setRanking(rs.getInt(3));
                u.setNumAlumnos(rs.getInt(4));
                u.setFoto(rs.getString(5));

                BPais p = new BPais();
                p.setContinente(new BContinente(rs.getInt(6),rs.getString(7)));
                p.setIdPais(rs.getInt(8));
                p.setNombre(rs.getString(9));
                p.setPoblacion(rs.getInt(10));
                p.setTamanio(rs.getInt(11));
                u.setPais(p);

                listaUniversidades.add(u);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaUniversidades;
    }

    public boolean crearUniversidad(BUniversidad u){

        String sql = "insert into universidad (nombre,idpais,ranking,numAlumnos,foto)\n" +
                "values (?,?,?,?,?);";

        try(Connection conn = this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,u.getNombre());
            pstmt.setInt(2,u.getPais().getIdPais());
            pstmt.setInt(3,u.getRanking());
            pstmt.setInt(4,u.getNumAlumnos());
            pstmt.setString(5,u.getFoto());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean editarUniversidad(BUniversidad u){

        String sql = "update universidad set nombre=?,idpais=?,ranking=?,numAlumnos=?,foto=?\n"+
                "where iduniversidad = ?;";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,u.getNombre());
            pstmt.setInt(2,u.getPais().getIdPais());
            pstmt.setInt(3,u.getRanking());
            pstmt.setInt(4,u.getNumAlumnos());
            pstmt.setString(5,u.getFoto());
            pstmt.setInt(6,u.getIdUniversidad());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean borrarUniversidad(BUniversidad u){

        String sql = "delete from universidad\n"+
                "where iduniversidad = ?";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,u.getIdUniversidad());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
