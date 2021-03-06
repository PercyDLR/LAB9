<%@ page import="Beans.BParticipante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaparticipantes" scope="request" type="java.util.ArrayList<Beans.BParticipante>" class="java.util.ArrayList"/>
<jsp:useBean id="mensaje" scope="request" type="java.lang.String" class="java.lang.String"/>
<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="participantes"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="participantes"/>
    </jsp:include>
    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-md-8 col-sm-6">
            <h1 class='text-light'>Participantes</h1>
        </div>
        <a class="btn btn-success" href="<%=request.getContextPath()%>/participantes?action=crear">Añadir nuevo participante</a>
    </div>
    <%
        if(!mensaje.equals("")){
            String tipo = null;
            if(mensaje.equalsIgnoreCase("se edito de manera correcta")||mensaje.equalsIgnoreCase("se registro de manera correcta")||mensaje.equalsIgnoreCase("borrado exitoso")){
                tipo = "alert-success";
            }else{
                tipo= "alert-danger";
            }
            %>
    <div class="alert <%=tipo%> alert-dismissible fade show" role="alert">
        <%= mensaje%>
    </div>
    <%}%>
    <div class="tabla">
        <table class="table table-dark table-transparent table-hover">
            <thead>
            <th>#</th>
            <th>NOMBRE</th>
            <th>APELLIDO</th>
            <th>EDAD</th>
            <th>PAIS</th>
            <th>GENERO</th>
            <th colspan="2">OPCIONES</th>
            </thead>
            <%
                int i=0;
                for(BParticipante p : listaparticipantes) {
            %>
            <tr>
                <td><%=i+=1%></td>
                <td><%=p.getNombreP()%></td>
                <td><%=p.getApellidP()%></td>
                <td><%=p.getEdad()%></td>
                <td><%=p.getPais().getNombre()%></td>
                <td><%=p.getGenero()%></td>
                <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/participantes?action=editar&id=<%=p.getIdParticipante()%>">Editar</a>
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/participantes?action=eliminar&id=<%=p.getIdParticipante()%>">Eliminar</a></td>
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
