/*
package Servlets;

import Beans.Artista;
import Daos.ArtistaDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ArtistaServlet", value = "/listaArtistas")
public class ArtistaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean filtrado = request.getParameter("filter") != null && request.getParameter("filter").equals("on");

        ArtistaDao ad = new ArtistaDao();
        ArrayList<Artista> listaArtistas =  ad.listarArtistas(filtrado);

        System.out.println(listaArtistas.size());
        System.out.println(filtrado);

        request.setAttribute("filtrado",filtrado);
        request.setAttribute("listaArtistas",listaArtistas);
        RequestDispatcher view = request.getRequestDispatcher("artistas.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
*/
