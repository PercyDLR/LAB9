<%@ page import="Beans.BPais" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaPaises" scope="request" type="java.util.ArrayList<Beans.BPais>"/>
<jsp:useBean id="mensaje" scope="request" type="java.lang.String" class="java.lang.String"/>
<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Agregar nuevo participante"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="participantes"/>
    </jsp:include>

    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-md-8 col-sm-6">
            <h1 class='text-light'>Agregar un nuevo participante</h1>
        </div>
    </div
    <%
        if(!mensaje.equals("")){
            String tipo = null;
            if(mensaje.equalsIgnoreCase("se edito de manera correcta")||mensaje.equalsIgnoreCase("se registro de manera correcta")||mensaje.equalsIgnoreCase("borrado exitoso")){
                tipo = "alert-success";
            }else{
                tipo= "alert-danger";
            }
    %>
    <div class="container">
    <div class="alert <%=tipo%> alert-dismissible fade show" role="alert">
        <%= mensaje%>
    </div>
    </div>
    <%}%>
    <div class="container">
    <div class="text-white">
        <form method="POST" action="<%=request.getContextPath()%>/participantes?action=crear">
            <div class="mb-3">
                <label for="pais" class="form-label">Pais</label>
                <select class="form-select" aria-label="Seleccionar Pais" id="pais" name="idPais" required>
                    <option selected>---- Seleccionar un Pais ----</option>
                    <%for(BPais pais : listaPaises){%>
                    <option value="<%=pais.getIdPais()%>"><%=pais.getNombre()%></option>
                    <%}%>
                </select>
            </div>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
            </div>
            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="apellido" name="apellido" required>
            </div>
            <div class="mb-3">
                <label for="edad" class="form-label">Edad</label>
                <input type="number" class="form-control" id="edad" name="edad" required>
            </div>
            <div class="mb-3">
                <label for= "genero" class="form-label">Genero</label>
                 <select class="form-select" aria-label="Seleccionar Genero" id="genero" name="genero" required>
                     <option selected>---- Seleccione su género ----</option>
                     <option value="hombre">Hombre</option>
                     <option value="mujer">Mujer</option>
                     <option value="otro">otro</option>
                 </select>
            </div>
            <div class="row">
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/participantes">Cancelar</a>
                <button type="submit" class="btn btn-success">Crear</button>
            </div>
        </form>
    </div>
    </div>
</div>
</body>
</html>