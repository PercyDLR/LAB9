package Daos;

import Beans.BPais;
import Beans.BParticipante;

import java.sql.*;
import java.util.ArrayList;

public class ParticipanteDao extends BaseDao{

    public void crearParticipante(BParticipante participante){
        String sql = "insert into participante (nombre, apellido, edad, genero, idpais) values(?,?,?,?,?)";

        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setString(1,participante.getNombreP());
            pstmt.setString(2,participante.getApellidP());
            pstmt.setInt(3,participante.getEdad());
            pstmt.setString(4,participante.getGenero());
            pstmt.setInt(5,participante.getPais().getIdPais());
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void eliminarParticipante(BParticipante participante){
        String sql= "delete from participante where idparticipante= values(?) ";

        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setInt(1,participante.getIdParticipante());
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<BParticipante> listarParticipante(){
        ArrayList<BParticipante> participantes = new ArrayList<>();
        String sql="select p.idparticipante,p.nombre,p.apellido,p.edad,p.genero,c.nombre from participante p inner join pais c on (c.idpais=p.idpais)";
        try(Connection connection = this.getConnection();
            Statement  stmt = connection.createStatement();
            ResultSet rs  = stmt.executeQuery(sql)){
                while(rs.next()){
                    BPais pais = new BPais();
                    pais.setNombre(rs.getString(6));
                    participantes.add(new BParticipante(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),pais));
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return participantes;
    }
    public void editarParticipante(BParticipante participante){
        String sql="update participante set nombre=?,apellido=?,edad=?,genero=?,idpais=?";

        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

                pstmt.setString(1,participante.getNombreP());
                pstmt.setString(2,participante.getApellidP());
                pstmt.setInt(3,participante.getEdad());
                pstmt.setString(4,participante.getGenero());
                pstmt.setInt(5,participante.getPais().getIdPais());
                pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
