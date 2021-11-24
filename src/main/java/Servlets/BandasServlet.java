/*
package Servlets;

import Beans.Banda;
import Daos.BandaDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BandasServlet", value = "/listaBandas")
public class BandasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filter = request.getParameter("filter") == null? "":request.getParameter("filter");
        BandaDao bd = new BandaDao();
        ArrayList<Banda> listaBandas= bd.obtenerListaBandas(filter);

        request.setAttribute("listaBandas",listaBandas);
        RequestDispatcher view = request.getRequestDispatcher("Bandas.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
*/
