package Daos;

import Beans.BAlumno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumnoDao extends BaseDao{
    public ArrayList<BAlumno> listarAlumnos(int opcion){
        ArrayList<BAlumno> alumnos = new ArrayList<>();
        String sql ="select * from alumno";
        if(opcion==1){
            sql="select * from alumno order by promedio";
        }
        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql) ){
            while(rs.next()){
                alumnos.add(new BAlumno());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }
}
