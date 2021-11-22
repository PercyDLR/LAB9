<%@ page import="Beans.Artista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaArtistas" scope="request" type="java.util.ArrayList<Beans.Artista>"/>
<jsp:useBean id="filtrado" scope="request" type="java.lang.Boolean"/>

<%String titulo = filtrado?"Lista de Filtrada de Artistas":"Lista de Artistas";%>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Artistas"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="artistas"/>
            </jsp:include>
            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'><%=titulo%></h1>
                    <%if(!filtrado){%>
                        <a class="btn btn-warning" href="<%=request.getContextPath()%>/listaArtistas?filter=on">Solo más de 5 canciones</a>
                    <%}%>
                </div>
            </div>
            <div class="tabla">
                <table class="table table-dark table-transparent table-hover">
                    <thead>
                        <th>ID</th>
                        <th>NOMBRE</th>
                        <th>BANDA</th>
                        <th>INSTRUMENTO</th>
                    </thead>
                    <%
                        String clase = null;
                        for (Artista artista : listaArtistas) {
                            switch(artista.getIdinstrumento()){
                                case "GUI":
                                    clase="fila-red";
                                    break;
                                case "BSS":
                                    clase="fila-purple";
                                    break;
                                case "DRM":
                                    clase="fila-blue";
                                    break;
                                case "VOC":
                                    clase="fila-yellow";
                            }
                    %>
                    <tr class="<%=clase%>">
                        <td><%=artista.getIdArtista()%></td>
                        <td><%=artista.getNombre_artista()%></td>
                        <td><%=artista.getIdbanda()%></td>
                        <td><%=artista.getIdinstrumento()%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
