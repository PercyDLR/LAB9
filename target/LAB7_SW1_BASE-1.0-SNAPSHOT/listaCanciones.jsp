<%@ page import="Beans.Cancion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaCanciones" scope="request" type="java.util.ArrayList<Beans.Cancion>"/>
<jsp:useBean id="filtrado" scope="request" type="java.lang.Boolean"/>

<%String titulo = filtrado?"Lista de Canciones por Banda":"Lista de Canciones";%>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="<%=titulo%>"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="canciones"/>
            </jsp:include>
            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'><%=titulo%></h1>
                    <%if(filtrado){%>
                        <a class="btn btn-warning" href="<%=request.getContextPath()%>/listaCanciones">Mostrar todas las canciones</a>
                    <%}%>
                </div>
            </div>
            <div class="tabla">
                <table class="table table-dark table-transparent table-hover">
                    <thead>
                        <th>ID</th>
                        <th>CANCION</th>
                        <th>BANDA</th>
                    </thead>
                    <%
                        for (Cancion cancion : listaCanciones) {
                    %>
                    <tr>
                        <td><%=cancion.getIdCancion()%></td>
                        <td><%=cancion.getNombre()%></td>
                        <td><%=cancion.getBanda()%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>

