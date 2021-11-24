<%@ page import="Beans.BParticipante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="infoUniversidad" scope="request" type="Beans.BUniversidad"/>
<jsp:useBean id="infoAlumno" scope="request" type="Beans.BAlumno"/>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Editar Alumno"/>
    </jsp:include>

    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'>Editando Alumno: <%=infoAlumno.getNombreP()+" "+infoAlumno.getApellidP()%></h1>
                </div>
            </div>
            <div class="text-white">
                <form method="POST" action="<%=request.getContextPath()%>/alumnos?action=editar&idUniversidad=<%=infoUniversidad.getIdUniversidad()%>&idParticipante=<%=infoAlumno.getIdParticipante()%>">
                    <div class="mb-3">
                        <label for="participante" class="form-label">Pais</label>
                        <select class="form-select" aria-label="Seleccionar Pais" id="participante" name="idParticipante" disabled>
                            <option selected value="<%=infoAlumno.getIdParticipante()%>"><%=infoAlumno.getNombreP()%></option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="codigo" class="form-label">Código</label>
                        <input type="number" class="form-control" id="codigo" name="codigo" value="<%=infoAlumno.getCodigo()%>" required>
                    </div>
                    <div class="mb-3">
                        <label for="promedio" class="form-label">Promedio Ponderado</label>
                        <input type="number" step="0.01" max="20" min="0" class="form-control" id="promedio" name="promedio" value="<%=infoAlumno.getPromedio()%>" required>
                    </div>

                    <div class="row">
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/alumnos?idUniversidad=<%=infoUniversidad.getIdUniversidad()%>">Cancelar</a>
                        <button type="submit" class="btn btn-success">Guardar Cambios</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>