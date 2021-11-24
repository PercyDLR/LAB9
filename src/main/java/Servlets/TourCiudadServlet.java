/*package Servlets;

import Beans.TPC;
import Daos.TourDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TourCiudadServlet", value = "/listaToursporCiudad")
public class TourCiudadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TourDao td = new TourDao();
        ArrayList<TPC> TPC = td.listaToursCiudad();

        request.setAttribute("listaToursCiudad",TPC);
        RequestDispatcher view = request.getRequestDispatcher("toursPorCiudad.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}*/
