package Servlets;

import Beans.BParticipante;
import Daos.ParticipanteDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ParticipanteServlet", value = "/participantes")
public class ParticipanteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ParticipanteDao participanteDao = new ParticipanteDao();
        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        switch (action){
            case"listar":
                ArrayList<BParticipante> listaparticipante = participanteDao.listarParticipante();
                request.setAttribute("listaparticipantes",listaparticipante);
                RequestDispatcher view = request.getRequestDispatcher("listaParticipantes.jsp");
                view.forward(request,response);
                break;
            case "crear":
                break;
            case "editar":
                break;
            case "eliminar":
                break;
            default:
                System.out.println("fallo algo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
