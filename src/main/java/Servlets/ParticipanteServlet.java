package Servlets;

import Beans.BPais;
import Beans.BParticipante;
import Daos.PaisDao;
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
        PaisDao paisDao = new PaisDao();
        ArrayList<BPais> paises = paisDao.obtenerListaPaises("");
        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        String mensaje = request.getParameter("resultado");
        switch (action) {
            case "listar":
                ArrayList<BParticipante> listaparticipante = participanteDao.listarParticipante();
                request.setAttribute("mensaje",mensaje);
                request.setAttribute("listaparticipantes", listaparticipante);
                RequestDispatcher view = request.getRequestDispatcher("listaParticipantes.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("mensaje",mensaje);
                request.setAttribute("listaPaises", paises);
                view = request.getRequestDispatcher("crearParticipante.jsp");
                view.forward(request, response);
                break;
            case "editar":
                request.setAttribute("mensaje",mensaje);
                request.setAttribute("listaPaises", paises);
                int id_participante = Integer.parseInt(request.getParameter("id"));
                BParticipante bParticipante = participanteDao.infoParticipante(id_participante);
                request.setAttribute("participanteInfo", bParticipante);
                view = request.getRequestDispatcher("editarParticipante.jsp");
                view.forward(request, response);
                break;
            case "eliminar":
                int id_partel = Integer.parseInt(request.getParameter("id"));
                if(participanteDao.esEstudiante(id_partel)){
                    mensaje="Es un estudiante no puede ser borrado";
                }else{
                    participanteDao.eliminarParticipante(id_partel);
                    mensaje="borrado exitoso";
                }
                request.setAttribute("mensaje",mensaje);
                ArrayList<BParticipante> listaparticipanteb = participanteDao.listarParticipante();
                request.setAttribute("listaparticipantes", listaparticipanteb);
                view = request.getRequestDispatcher("listaParticipantes.jsp");
                view.forward(request, response);
                break;
            default:
                System.out.println("fallo algo");
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ParticipanteDao participanteDao = new ParticipanteDao();
    switch (request.getParameter("action")){
        case "crear":
            boolean todo_claro = true;
            String accion = "listar";
            String mensaje="se registro de manera correcta";
            String nombre =request.getParameter("nombre");
            String apellido =request.getParameter("apellido");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String genero = request.getParameter("genero");
            int id_pais = Integer.parseInt(request.getParameter("idPais"));
            //validaciones
            // solo letras
            if(!nombre.matches("^[a-zA-Z\\s]*$")){
                //System.out.println("el nombre no debe tener numeros");
                mensaje = "digito de manera incorrecta el nombre";
                todo_claro=false;
                accion = "crear";
            }
            if(!apellido.matches("^[a-zA-Z\\s]*$")){
                //System.out.println("el apellido no debe tener numeros");
                mensaje = "digito de manera incorrecta el apellido";
                todo_claro=false;
                accion = "crear";
            }
            //validacion edad
            if(edad<18){
                //System.out.println("no se puede registrar a menores de edad");
                mensaje = "debe tener como minimo 18 años el participante";
                todo_claro = false;
                accion = "crear";
            }
            if(todo_claro){
                BParticipante participante = new BParticipante();
                BPais pais = new BPais();
                pais.setIdPais(id_pais);
                participante.setNombreP(nombre);
                participante.setApellidP(apellido);
                participante.setPais(pais);
                participante.setGenero(genero);
                participante.setEdad(edad);
                participanteDao.crearParticipante(participante);
            }
            response.sendRedirect(request.getContextPath()+"/participantes?resultado="+mensaje+"&action="+accion);
            break;
        case "editar":
            boolean edicion_correcta = true;
            accion = "listar";
            String mensajeE="se editó de manera correcta";
            String nombreE =request.getParameter("nombre");
            String apellidoE =request.getParameter("apellido");
            int edadE = Integer.parseInt(request.getParameter("edad"));
            String generoE = request.getParameter("genero");
            int id_paisE = Integer.parseInt(request.getParameter("idPais"));
            if(!nombreE.matches("^[a-zA-Z\\s]*$")){
                //System.out.println("el nombre no debe tener numeros");
                mensajeE = "digito de manera incorrecta el nombre";
                edicion_correcta= false;
                accion="editar";
            }
            if(!apellidoE.matches("^[a-zA-Z\\s]*$")){
                //System.out.println("el apellido no debe tener numeros");
                mensajeE = "digito de manera incorrecta el apellido";
                edicion_correcta=false;
                accion="editar";
            }
            //validacion edad
            if(edadE<18){
                //System.out.println("no se puede registrar a menores de edad");
                mensajeE = "debe tener como minimo 18 años el participante";
                edicion_correcta=false;
                accion="editar";
            }
            if(edicion_correcta){
                BParticipante participante = new BParticipante();
                BPais pais = new BPais();
                pais.setIdPais(id_paisE);
                participante.setNombreP(nombreE);
                participante.setApellidP(apellidoE);
                participante.setPais(pais);
                participante.setGenero(generoE);
                participante.setEdad(edadE);
                participanteDao.editarParticipante(Integer.parseInt(request.getParameter("id")),participante);
            }
            response.sendRedirect(request.getContextPath()+"/participantes?resultado="+mensajeE+"&action="+accion+"&id="+request.getParameter("id"));
            break;
        default:
            System.out.println("Fallo en el sistema");
    }

    }
}
