<%@ page import="Beans.BPais" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaPaises" scope="request" type="java.util.ArrayList<Beans.BPais>"/>
<jsp:useBean id="participanteInfo" scope="request" type="Beans.BParticipante"/>
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
            <h1 class='text-light'>Editar un participante</h1>
        </div>
    </div>
    <div class="text-white">
        <form method="POST" action="<%=request.getContextPath()%>/participantes?action=editar">
            <div class="mb-3">
                <label for="pais" class="form-label">Pais</label>
                <select class="form-select" aria-label="Seleccionar Pais" id="pais" name="idPais" required>
                    <%for(BPais pais : listaPaises){
                        if(pais.getIdPais()==participanteInfo.getPais().getIdPais()){%>
                    <option selected value="<%=pais.getIdPais()%>"><%=pais.getNombre()%></option>
                    <%}else{%>
                    <option value="<%=pais.getIdPais()%>"><%=pais.getNombre()%></option>
                    <%}}%>
                </select>
            </div>
            <div class="mb-3">
                <input type="hidden" name="id" value="<%=participanteInfo.getIdParticipante()%>">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<%=participanteInfo.getNombreP()%>" required>
            </div>
            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="apellido" name="apellido" value="<%=participanteInfo.getApellidP()%>" required>
            </div>
            <div class="mb-3">
                <label for="edad" class="form-label">Edad</label>
                <input type="number" class="form-control" id="edad" name="edad" value="<%=participanteInfo.getEdad()%>" required>
            </div>
            <div class="mb-3">
                <label for= "genero" class="form-label">Genero</label>
                <select class="form-select" aria-label="Seleccionar Genero" id="genero" name="genero" required>
                    <option selected value="<%=participanteInfo.getGenero()%>"><%=participanteInfo.getGenero()%></option>
                    <option value="hombre">Hombre</option>
                    <option value="mujer">Mujer</option>
                    <option value="otro">otro</option>
                </select>
            </div>
            <div class="row">
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/participantes">Cancelar</a>
                <button type="submit" class="btn btn-success">Editar</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>