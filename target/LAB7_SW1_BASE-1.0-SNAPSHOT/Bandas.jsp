<%@ page import="Beans.Banda" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaBandas" scope="request" type="java.util.ArrayList<Beans.Banda>"/>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Bandas"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="bandas"/>
            </jsp:include>
            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'>Lista de Bandas</h1>
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
                        for (Banda banda : listaBandas) {
                    %>
                    <tr>
                        <td><%=banda.getIdBanda()%></td>
                        <td><%=banda.getNombre_banda()%></td>
                        <td><%=banda.getIdlider()%></td>
                        <td><a class="btn btn-success" href="<%=request.getContextPath()%>/listaCanciones?id=<%=banda.getIdBanda()%>">Mostrar todas las canciones</a></td>
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