package Servlets;

import Beans.Cancion;
import Daos.CancionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RecomendServlet", value = "/listaRecomendados")
public class RecomendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CancionDao cd = new CancionDao();
        ArrayList<Cancion> topCanciones =  cd.topCanciones();

        request.setAttribute("topCanciones",topCanciones);
        RequestDispatcher view = request.getRequestDispatcher("recomendados.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
