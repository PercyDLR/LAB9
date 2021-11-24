package Daos;

import Beans.BContinente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinenteDao extends BaseDao{

    public ArrayList<BContinente> obtenerListaContinentes() {
        ArrayList<BContinente> listaContinentes = new ArrayList<>();


        String sql = "SELECT * FROM continente order by nombre;";

        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while (rs.next()) {
                BContinente continente = new BContinente(rs.getInt(1),rs.getString(2));

                listaContinentes.add(continente);
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }

        return listaContinentes;
    }

}
