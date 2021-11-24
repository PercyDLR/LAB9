package Servlets;

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
        String filter = request.getParameter("filter") != null ?request.getParameter("filter"): "";
        String action = request.getParameter("action") != null ?request.getParameter("action"): "listar";

        PaisDao paisDao = new PaisDao();


        RequestDispatcher view;
        switch (action) {
            case "listar":
                ContinenteDao continenteDao = new ContinenteDao();

                request.setAttribute("listaPaises",paisDao.obtenerListaPaises(filter));
                request.setAttribute("listaContinentes",continenteDao.obtenerListaContinentes());

                view = request.getRequestDispatcher("listaPaises.jsp");
                view.forward(request, response);

                break;

/*
            case "crear":

                request.setAttribute("listaBandas",bandaDao.obtenerListaBandas() );
                RequestDispatcher view2 = request.getRequestDispatcher("crearArtista.jsp");
                view2.forward(request, response);
                break;

            case "editar":
                String idAStr = request.getParameter("idA")!= null? request.getParameter("idA"):"";
                int idA = Integer.parseInt(idAStr);
                Artista artista = artistaDao.obtenerArtistaPorId(idA);

                if (artista != null){
                    request.setAttribute("artista", artista);
                    request.setAttribute("listaBandas",bandaDao.obtenerListaBandas() );

                    view = request.getRequestDispatcher("editarArtista.jsp");
                    view.forward(request, response);
                }else {
                    response.sendRedirect(request.getContextPath() + "/artistas");
                }
                break;
            case "eliminar":
                String idBorrarStr = request.getParameter("idA")!= null? request.getParameter("idA"):"";
                int idBorrar = Integer.parseInt(idBorrarStr);

                Artista artistaB = artistaDao.obtenerArtistaPorId(idBorrar);

                if (artistaB!=null){
                    try {
                        artistaDao.eliminarArtista(idBorrar);
                        response.sendRedirect(request.getContextPath() + "/artistas");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                break;*/
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        String filter = request.getParameter("filter") != null ?request.getParameter("filter"): "";

        PaisDao paisDao = new PaisDao();

        RequestDispatcher view;

        switch (action){
            case "listar":

                response.sendRedirect(request.getContextPath() + "/paises?filter="+filter);
                break;
                /*
            case "update":

                String nombreArtista = request.getParameter("nombreArtista") != null ? request.getParameter("nombreArtista") : "";
                String idBandaStr = request.getParameter("idBanda") != null ? request.getParameter("idBanda") : "";
                int idBanda = Integer.parseInt(idBandaStr);
                int idArtista = Integer.parseInt(idArtistaStr);
                artistaDao.actualizarArtista(idArtista,nombreArtista, idBanda);
                response.sendRedirect(request.getContextPath() + "/artistas");

                break;
            case "crear":

                String nombreArtistaC = request.getParameter("nombreArtista") != null ? request.getParameter("nombreArtista") : "";
                String idBandaCStr = request.getParameter("idBanda") != null ? request.getParameter("idBanda") : "";
                int idBandaC = Integer.parseInt(idBandaCStr);
                artistaDao.crearArtista(nombreArtistaC, idBandaC);
                response.sendRedirect(request.getContextPath() + "/artistas");

                break;*/
        }
    }
}

