package Servlets;

import Beans.BContinente;
import Beans.BPais;
import Daos.ContinenteDao;
import Daos.PaisDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PaisesServlet", value = "/paises")
public class PaisesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String filter = request.getParameter("filter") != null ? request.getParameter("filter") : "";
        String action = request.getParameter("action") != null ? request.getParameter("action") : "listar";

        PaisDao paisDao = new PaisDao();
        ContinenteDao continenteDao = new ContinenteDao();

        RequestDispatcher view;
        switch (action) {
            case "listar":


                request.setAttribute("listaPaises", paisDao.obtenerListaPaises(filter));
                request.setAttribute("listaContinentes", continenteDao.obtenerListaContinentes());

                view = request.getRequestDispatcher("listaPaises.jsp");
                view.forward(request, response);

                break;


            case "crear":

                request.setAttribute("listaContinentes", continenteDao.obtenerListaContinentes());
                RequestDispatcher view2 = request.getRequestDispatcher("crearPais.jsp");
                view2.forward(request, response);
                break;

            case "editar":
                String idPStr = request.getParameter("idP")!= null? request.getParameter("idP"):"";
                int idP = Integer.parseInt(idPStr);
                BPais pais = paisDao.obtenerPaisPorId(idP);

                if (pais != null){
                    request.setAttribute("pais", pais);
                    request.setAttribute("listaContinentes",continenteDao.obtenerListaContinentes() );

                    view = request.getRequestDispatcher("editarPais.jsp");
                    view.forward(request, response);
                }else {
                    response.sendRedirect(request.getContextPath() + "/paises");
                }
                break;

            case "eliminar":
                String idBorrarStr = request.getParameter("idP")!= null? request.getParameter("idP"):"";
                int idBorrar = Integer.parseInt(idBorrarStr);

                BPais paisB = paisDao.obtenerPaisPorId(idBorrar);

                if (paisB!=null){
                    try {
                        paisDao.eliminarPais(idBorrar);
                        response.sendRedirect(request.getContextPath() + "/paises");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        String filter = request.getParameter("filter") != null ? request.getParameter("filter") : "";

        PaisDao paisDao = new PaisDao();

        RequestDispatcher view;

        switch (action) {
            case "listar":

                response.sendRedirect(request.getContextPath() + "/paises?filter=" + filter);
                break;

            case "editar":

                String idPEStr = request.getParameter("idPais")!= null? request.getParameter("idPais"):"";
                String nombrePE = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String idContinenteEStr = request.getParameter("cont") != null ? request.getParameter("cont") : "";
                String poblacionEStr = request.getParameter("poblacion") != null ? request.getParameter("poblacion") : "";
                String tamEStr = request.getParameter("tam") != null ? request.getParameter("tam") : "";

                int idPE = Integer.parseInt(idPEStr);
                int idContE = Integer.parseInt(idContinenteEStr);
                int poblacionE = Integer.parseInt(poblacionEStr);
                float tamE = Float.parseFloat(tamEStr);

                BContinente continenteE = new BContinente();
                continenteE.setIdContinente(idContE);

                BPais paisE = new BPais();
                paisE.setIdPais(idPE);
                paisE.setNombre(nombrePE);
                paisE.setContinente(continenteE);
                paisE.setPoblacion(poblacionE);
                paisE.setTamanio(tamE);

                paisDao.actualizarPais(paisE);

                response.sendRedirect(request.getContextPath() + "/paises");

                break;
            case "crear":
                System.out.println("entra en crear");

                String nombreP = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String idContinenteStr = request.getParameter("cont") != null ? request.getParameter("cont") : "";
                String poblacionStr = request.getParameter("poblacion") != null ? request.getParameter("poblacion") : "";
                String tamStr = request.getParameter("poblacion") != null ? request.getParameter("poblacion") : "";

                int idCont = Integer.parseInt(idContinenteStr);
                int poblacion = Integer.parseInt(poblacionStr);
                float tam = Float.parseFloat(tamStr);

                BContinente continente = new BContinente();
                continente.setIdContinente(idCont);

                BPais pais = new BPais();
                pais.setNombre(nombreP);
                pais.setContinente(continente);
                pais.setPoblacion(poblacion);
                pais.setTamanio(tam);

                paisDao.crearPais(pais);

                response.sendRedirect(request.getContextPath() + "/paises");

                break;
        }
    }
}

