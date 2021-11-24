package Servlets;

import Beans.BAlumno;
import Beans.BUniversidad;
import Daos.AlumnoDao;
import Daos.ParticipanteDao;
import Daos.UniversidadDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlumnoServlet", value = "/alumnos")
public class AlumnoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher view;

        ParticipanteDao pd = new ParticipanteDao();
        UniversidadDao ud = new UniversidadDao();
        AlumnoDao ad = new AlumnoDao();

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        int idUniversidad = Integer.parseInt(request.getParameter("idUniversidad"));
        BUniversidad infoUniversidad = ud.infoUniversidad(idUniversidad);

        switch(action){
            case "listar":
                request.setAttribute("listaAlumnos",ad.listarAlumnos(2,idUniversidad));
                request.setAttribute("infoUniversidad",infoUniversidad);

                view = request.getRequestDispatcher("listaAlumnos.jsp");
                view.forward(request,response);
                break;

            case "crear":
                int idPais = infoUniversidad.getPais().getIdPais();
                request.setAttribute("listaParticipantes",pd.participantesSinUniversidad(idPais));
                request.setAttribute("infoUniversidad",infoUniversidad);

                view = request.getRequestDispatcher("crearAlumno.jsp");
                view.forward(request,response);
                break;

            case "editar":
                int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));

                request.setAttribute("infoUniversidad",ud.infoUniversidad(idUniversidad));
                request.setAttribute("infoAlumno",ad.infoAlumno(idAlumno));

                view = request.getRequestDispatcher("editarAlumno.jsp");
                view.forward(request,response);

                break;

            case "eliminar":
                int idAlumno1 = Integer.parseInt(request.getParameter("idAlumno"));
                ad.eliminarAlumno(idAlumno1);

                request.setAttribute("listaAlumnos",ad.listarAlumnos(2,idUniversidad));
                request.setAttribute("infoUniversidad",ud.infoUniversidad(idUniversidad));

                view = request.getRequestDispatcher("listaAlumnos.jsp");
                view.forward(request,response);
                break;
            case "borrar":
                int idAlumno2 = Integer.parseInt(request.getParameter("idAlumno"));
                ad.borrarAlumno(idAlumno2);
                ud.cambiarNumEstudiantes(idUniversidad,-1);

                request.setAttribute("listaAlumnos",ad.listarAlumnos(2,idUniversidad));
                request.setAttribute("infoUniversidad",ud.infoUniversidad(idUniversidad));

                view = request.getRequestDispatcher("listaAlumnos.jsp");
                view.forward(request,response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        UniversidadDao ud = new UniversidadDao();
        AlumnoDao ad = new AlumnoDao();

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        int idUniversidad = Integer.parseInt(request.getParameter("idUniversidad"));
        int idParticipante = Integer.parseInt(request.getParameter("idParticipante"));

        BAlumno alumno = new BAlumno();
        alumno.setIdParticipante(idParticipante);

        switch(action) {
            case "crear":

                alumno.setCodigo(request.getParameter("codigo"));
                alumno.setPromedio(Float.parseFloat(request.getParameter("promedio")));
                alumno.setUniversidad(new BUniversidad(idUniversidad));

                ad.crearAlumno(alumno);
                ud.cambiarNumEstudiantes(idUniversidad,1);

                response.sendRedirect(request.getContextPath() + "/alumnos?idUniversidad=" + idUniversidad);
                break;

            case "editar":

                alumno.setCodigo(request.getParameter("codigo"));
                alumno.setPromedio(Float.parseFloat(request.getParameter("promedio")));
                alumno.setUniversidad(new BUniversidad(idUniversidad));

                ad.editarAlumno(alumno);

                response.sendRedirect(request.getContextPath() + "/alumnos?idUniversidad=" + idUniversidad);
                break;

        }
    }
}
