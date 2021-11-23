package Servlets;

import Beans.BUniversidad;
import Daos.UniversidadDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UniversidadServlet", value = "/universidades")
public class UniversidadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UniversidadDao ud = new UniversidadDao();


        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");

        switch(action){
            case "listar":
                String filter = request.getParameter("filter") == null ? "ranking" : request.getParameter("filter");
                ArrayList<BUniversidad> listaUniversidades = ud.listarUniversidades(filter);

                request.setAttribute("listaUniversidades",listaUniversidades);
                RequestDispatcher view = request.getRequestDispatcher("listaUniversidades.jsp");
                view.forward(request,response);
                break;
            case "crear":
                break;
            case "editar":
                break;
            case "eliminar":
                break;
            default:
                System.out.println("Universidad Servlet : Algo esta mal");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
