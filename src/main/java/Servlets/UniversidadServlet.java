package Servlets;

import Beans.BPais;
import Beans.BUniversidad;
import Daos.PaisDao;
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
        PaisDao pd = new PaisDao();

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        ArrayList<BPais> listaPaises = pd.obtenerListaPaises("");

        switch(action){
            case "listar":
                String filter = request.getParameter("filter") == null ? "ranking" : request.getParameter("filter");
                ArrayList<BUniversidad> listaUniversidades = ud.listarUniversidades(filter);

                request.setAttribute("resultado",request.getParameter("resultado"));
                request.setAttribute("listaUniversidades",listaUniversidades);
                RequestDispatcher view = request.getRequestDispatcher("listaUniversidades.jsp");
                view.forward(request,response);
                break;

            case "crear":
                request.setAttribute("listaPaises",listaPaises);
                RequestDispatcher view1 = request.getRequestDispatcher("crearUniversidad.jsp");
                view1.forward(request,response);
                break;

            case "editar":
                int idUniversidad = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("infoUniversidad",ud.infoUniversidad(idUniversidad));
                request.setAttribute("listaPaises",listaPaises);
                RequestDispatcher view2 = request.getRequestDispatcher("editarUniversidad.jsp");
                view2.forward(request,response);
                break;

            case "eliminar":
                int idUniversidad1 = Integer.parseInt(request.getParameter("id"));


                break;

            default:
                System.out.println("Universidad Servlet Get: Algo esta mal");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UniversidadDao ud = new UniversidadDao();
        BUniversidad uni = new BUniversidad();
        String action = request.getParameter("action");

        BPais pais = new BPais();
        pais.setIdPais(Integer.parseInt(request.getParameter("idPais")));
        uni.setPais(pais);

        uni.setNombre(request.getParameter("nombre"));
        uni.setRanking(Integer.parseInt(request.getParameter("ranking")));
        uni.setNumAlumnos(Integer.parseInt(request.getParameter("numAlumnos")));
        uni.setFoto(request.getParameter("foto"));

        String msg;

        switch(action){
            case "crear":
                msg = ud.crearUniversidad(uni) ? "re" : "rne";

                response.sendRedirect(request.getContextPath()+"/universidades?resultado="+msg);
                break;

            case "editar":
                uni.setIdUniversidad(Integer.parseInt(request.getParameter("id")));
                msg = ud.editarUniversidad(uni) ? "ee" : "ene";

                response.sendRedirect(request.getContextPath()+"/universidades?resultado="+msg);
                break;

            default:
                System.out.println("Universidad Servlet Post: Algo esta mal");
        }
    }
}
