<%@ page import="Beans.BAlumno" %>
<%@ page import="Beans.BParticipante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="infoUniversidad" scope="request" type="Beans.BUniversidad"/>
<jsp:useBean id="listaAlumnos" scope="request" type="java.util.ArrayList<Beans.BAlumno>"/>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Universidades"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="pb-5 pt-4 px-3 titlecolor">
                <h1 class='text-light'>Alumnos de <%=infoUniversidad.getNombre()%></h1>
                <a class="btn btn-success" href="<%=request.getContextPath()%>/alumnos?action=crear&idUniversidad=<%=infoUniversidad.getIdUniversidad()%>">Nuevo Alumno</a>
            </div>

            <div class="tabla">
                <table class="table table-transparent table-hover text-center">
                    <thead>
                        <th><a class="btn btn-dark" href="#">NOMBRE</a></th>
                        <th><a class="btn btn-dark" href="#">APELLIDO</a></th>
                        <th><a class="btn btn-dark" href="#">EDAD</a></th>
                        <th><a class="btn btn-dark" href="#">CÓDIGO</a></th>
                        <th><a class="btn btn-dark" href="#">PROMEDIO</a></th>
                        <th><a class="btn btn-dark" href="#">CONDICIÓN</a></th>
                        <th colspan="2"><a class="btn btn-dark" href="#">OPCIONES</a></th>
                    </thead>
                    <%
                        for (BAlumno a : listaAlumnos) {
                            String color = "text-white";
                            String condicion = "Normal";
                            if(!a.isCondicion()){
                                color = "text-danger";
                                condicion = "Eliminado";
                            }
                    %>
                        <tr class="<%=color%>">
                            <td class><%=a.getNombreP()%></td>
                            <td><%=a.getApellidP()%></td>
                            <td><%=a.getEdad()%></td>
                            <td><%=a.getCodigo()%></td>
                            <td><%=a.getPromedio()%></td>
                            <td><%=condicion%></td>
                            <th><a class="btn btn-primary" href="<%=request.getContextPath()%>/alumnos?action=editar&idUniversidad=<%=infoUniversidad.getIdUniversidad()%>&idAlumno=<%=a.getIdParticipante()%>">Editar</a></th>

                            <%if(a.isCondicion()){%>
                                <th><a class="btn btn-danger" href="<%=request.getContextPath()%>/alumnos?action=eliminar&idUniversidad=<%=infoUniversidad.getIdUniversidad()%>&idAlumno=<%=a.getIdParticipante()%>">Eliminar</a></th>
                            <%}else{%>
                                <th><a class="btn btn-danger" href="<%=request.getContextPath()%>/alumnos?action=borrar&idUniversidad=<%=infoUniversidad.getIdUniversidad()%>&idAlumno=<%=a.getIdParticipante()%>">Borrar</a></th>
                            <%}%>
                        </tr>
                    <%}%>
                </table>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
