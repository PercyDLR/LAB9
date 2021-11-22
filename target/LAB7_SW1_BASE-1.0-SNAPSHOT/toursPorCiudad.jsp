<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaToursCiudad" scope="request" type="java.util.ArrayList<Beans.TPC>"/>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Tours Por Ciudad"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="tpc"/>
            </jsp:include>
            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'>Lista de Tours por Ciudad</h1>
                </div>
            </div>
            <div class="tabla">
                <table class="table table-dark table-transparent table-hover">
                    <thead>
                        <th>#</th>
                        <th>TOUR</th>
                        <th>CIUDAD</th>
                        <th>FECHA</th>
                    </thead>
                    <%
                        for (int ii=0;ii<listaToursCiudad.size();ii++) {
                    %>
                    <tr>
                        <td><%=ii+1%></td>
                        <td><%=listaToursCiudad.get(ii).getNombre_tour()%></td>
                        <td><%=listaToursCiudad.get(ii).getNombre_ciudad()%></td>
                        <td><%=listaToursCiudad.get(ii).getFecha()%></td>
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
