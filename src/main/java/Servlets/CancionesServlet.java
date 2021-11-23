/*package Servlets;

import Beans.Cancion;
import Daos.CancionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CancionesServlet", value = "/listaCanciones")
public class CancionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean filtrado = true;
        String id = request.getParameter("id");
        if(id==null) {
            id = "";
            filtrado = false;
        }

        CancionDao cd = new CancionDao();
        ArrayList<Cancion> listaCanciones =  cd.listarCanciones(id);

        request.setAttribute("filtrado",filtrado);
        request.setAttribute("listaCanciones",listaCanciones);
        RequestDispatcher view = request.getRequestDispatcher("listaCanciones.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}*/
