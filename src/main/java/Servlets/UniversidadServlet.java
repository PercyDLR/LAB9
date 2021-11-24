package Servlets;

import Beans.BAlumno;
import Beans.BPais;
import Beans.BParticipante;
import Beans.BUniversidad;
import Daos.AlumnoDao;
import Daos.PaisDao;
import Daos.ParticipanteDao;
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
        response.setCharacterEncoding("UTF-8");

        UniversidadDao ud = new UniversidadDao();
        PaisDao pd = new PaisDao();
        RequestDispatcher view;

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        ArrayList<BPais> listaPaises = pd.obtenerListaPaises("");

        switch(action){
            case "listar":
                String filter = request.getParameter("filter") == null ? "ranking" : request.getParameter("filter");
                ArrayList<BUniversidad> listaUniversidades = ud.listarUniversidades(filter);

                String resultado = request.getParameter("resultado")==null ? "" : request.getParameter("resultado");
                request.setAttribute("resultado",resultado);
                request.setAttribute("listaUniversidades",listaUniversidades);
                view = request.getRequestDispatcher("listaUniversidades.jsp");
                view.forward(request,response);
                break;

            case "crear":
                request.setAttribute("listaPaises",listaPaises);
                view = request.getRequestDispatcher("crearUniversidad.jsp");
                view.forward(request,response);
                break;

            case "editar":
                // Obtiene el id de la universidad que va a editar
                int idUniversidad = Integer.parseInt(request.getParameter("id"));
                // Manda la información de la Universidad y de los Países disponibles para elegir
                request.setAttribute("infoUniversidad",ud.infoUniversidad(idUniversidad));
                request.setAttribute("listaPaises",listaPaises);
                view = request.getRequestDispatcher("editarUniversidad.jsp");
                view.forward(request,response);
                break;

            case "eliminar":
                AlumnoDao ad = new AlumnoDao();
                ParticipanteDao pad = new ParticipanteDao();

                int idUniversidad1 = Integer.parseInt(request.getParameter("id"));
                ArrayList<BAlumno> alumnosEnUniversidad = ad.listarAlumnos(2,idUniversidad1);

                // Elimina a todos los alumnos de la Universidad
                for(BAlumno alumno : alumnosEnUniversidad){
                    ad.borrarAlumno(alumno.getIdParticipante());
                }
                int idPais = ud.infoUniversidad(idUniversidad1).getPais().getIdPais();

                // Si el pais se queda sin universidades se borra también
                if(ud.universidadesEnPais()==1){


                    // 1. Obtiene los participantes en el país
                    ArrayList<BParticipante> participantesEnPais = pad.listarParticipante(idPais);
                    // 2. Los elimina a todos de la DB
                    for(BParticipante pa : participantesEnPais){
                        pad.eliminarParticipante(pa.getIdParticipante());
                    }
                    // 3. ELimina la universidad
                    if(ud.borrarUniversidad(idUniversidad1)){
                        request.setAttribute("resultado","be");
                    }else{
                        request.setAttribute("resultado","bne");
                    }
                    // Elimina el pais
                    pd.eliminarPais(idPais);

                // Sa aún le quedan universidades, solo se borra la universidad
                }else if(ud.borrarUniversidad(idUniversidad1)){
                    request.setAttribute("resultado","be");
                }else{
                    request.setAttribute("resultado","bne");
                }
                // Lo redirige a la vista del listado
                request.setAttribute("listaUniversidades",ud.listarUniversidades("ranking"));
                view = request.getRequestDispatcher("listaUniversidades.jsp");
                view.forward(request,response);
                break;

            default:
                System.out.println("Universidad Servlet Get: Algo esta mal");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        UniversidadDao ud = new UniversidadDao();
        AlumnoDao ad = new AlumnoDao();

        BUniversidad uni = new BUniversidad();
        uni.setNombre(request.getParameter("nombre"));
        uni.setRanking(Integer.parseInt(request.getParameter("ranking")));
        uni.setFoto(request.getParameter("foto"));

        BPais pais = new BPais();
        pais.setIdPais(Integer.parseInt(request.getParameter("idPais")));
        uni.setPais(pais);


        String msg;
        switch(request.getParameter("action")){
            case "crear":
                uni.setNumAlumnos(0);
                msg = ud.crearUniversidad(uni) ? "re" : "rne";

                response.sendRedirect(request.getContextPath()+"/universidades?resultado="+msg);
                break;

            case "editar":
                int idUniversidad = Integer.parseInt(request.getParameter("id"));

                uni.setIdUniversidad(idUniversidad);
                uni.setNumAlumnos(ad.listarAlumnos(2,idUniversidad).size());

                msg = ud.editarUniversidad(uni) ? "ee" : "ene";

                response.sendRedirect(request.getContextPath()+"/universidades?resultado="+msg);
                break;

            default:
                System.out.println("Universidad Servlet Post: Algo esta mal");
        }
    }
}
