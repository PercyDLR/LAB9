package Daos;

import Beans.BAlumno;
import Beans.BContinente;
import Beans.BPais;
import Beans.BUniversidad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumnoDao extends BaseDao{

    public void crearAlumno(BAlumno alumno){
        String sql = "insert into alumno (idparticipante,codigo,promedio,iduniversidad)\n"+
                "values ("+alumno.getIdParticipante()+",'"+alumno.getCodigo()+"',"+alumno.getPromedio()+","+alumno.getUniversidad().getIdUniversidad()+")";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement()){

            stmt.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editarAlumno(BAlumno alumno){
        String sql = "update alumno set codigo="+alumno.getCodigo()+",promedio="+alumno.getPromedio()+",iduniversidad="+alumno.getUniversidad().getIdUniversidad()+
                "\nwhere idparticipante="+alumno.getIdParticipante();

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement()){

            stmt.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BAlumno infoAlumno(int idAlumno){

        BAlumno a = new BAlumno();
        a.setIdParticipante(idAlumno);

        String sql ="select * from alumno a\n" +
                "inner join participante p on (a.idparticipante = p.idparticipante)\n" +
                "inner join pais pa on (pa.idpais = p.idpais)\n"+
                "where a.idparticipante="+idAlumno;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql) ){

            if (rs.next()){
                a.setCodigo(rs.getString(2));
                a.setPromedio(rs.getFloat(3));
                a.setCondicion(rs.getBoolean(4));
                a.setUniversidad(new BUniversidad(rs.getInt(5)));
                a.setNombreP(rs.getString(7));
                a.setApellidP(rs.getString(8));
                a.setEdad(rs.getInt(9));
                a.setGenero(rs.getString(10));

                BPais p = new BPais();
                p.setIdPais(rs.getInt(11));
                p.setContinente(new BContinente(rs.getInt(13)));
                p.setNombre(rs.getString(14));
                p.setPoblacion(rs.getInt(15));
                p.setTamanio(rs.getInt(16));
                a.setPais(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public ArrayList<BAlumno> listarAlumnos(int opcion, int idUniversidad){

        ArrayList<BAlumno> alumnos = new ArrayList<>();
        String sql ="select * from alumno a\n" +
                "inner join participante p on (a.idparticipante = p.idparticipante)\n" +
                "inner join pais pa on (pa.idpais = p.idpais)\n";

        if(opcion==1){
            sql += "order by promedio;";
        } else if(opcion==2){
            sql += "where a.iduniversidad="+idUniversidad;
        }

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql) ){

            while(rs.next()){
                BAlumno a = new BAlumno();
                a.setIdParticipante(rs.getInt(1));
                a.setCodigo(rs.getString(2));
                a.setPromedio(rs.getFloat(3));
                a.setCondicion(rs.getBoolean(4));
                a.setUniversidad(new BUniversidad(rs.getInt(5)));
                a.setNombreP(rs.getString(7));
                a.setApellidP(rs.getString(8));
                a.setEdad(rs.getInt(9));
                a.setGenero(rs.getString(10));

                BPais p = new BPais();
                p.setIdPais(rs.getInt(11));
                p.setContinente(new BContinente(rs.getInt(13)));
                p.setNombre(rs.getString(14));
                p.setPoblacion(rs.getInt(15));
                p.setTamanio(rs.getInt(16));
                a.setPais(p);

                alumnos.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    public void eliminarAlumno(int idParticipante){
        String sql = "update alumno set condicion=0\n" +
                "where idparticipante="+idParticipante;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement()){

            stmt.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarAlumno(int idParticipante){
        String sql = "delete from alumno\n" +
                "where idparticipante="+idParticipante;

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement()){

            stmt.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
