<%@ page import="Beans.Cancion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="topCanciones" scope="request" type="java.util.ArrayList<Beans.Cancion>"/>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Recomendados"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="recomendados"/>
            </jsp:include>
            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'>Lista de Canciones Recomendadas</h1>
                </div>
            </div>
            <div class="tabla">
                <table class="table table-dark table-transparent table-hover">
                    <thead>
                        <th>ID</th>
                        <th>CANCION</th>
                        <th>BANDA</th>
                        <th>Ver</th>
                    </thead>
                    <%
                        for (Cancion cancion : topCanciones) {
                    %>
                    <tr>
                        <td><%=cancion.getIdCancion()%></td>
                        <td><%=cancion.getNombre()%></td>
                        <td><%=cancion.getBanda()%></td>
                        <td><a class="btn btn-success" href="<%=request.getContextPath()%>/listaCanciones?id=<%=cancion.getBanda()%>">Mostrar todas las canciones</a></td>
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