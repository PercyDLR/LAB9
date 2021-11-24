<%@ page import="Beans.BParticipante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaParticipantes" scope="request" type="java.util.ArrayList<Beans.BParticipante>"/>
<jsp:useBean id="infoUniversidad" scope="request" type="Beans.BUniversidad"/>

<html>
    <head>
        <!-- Para la busqueda de participante -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    </head>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Agregar Alumno"/>
    </jsp:include>

    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'>Agregar un nuevo Alumno a: <%=infoUniversidad.getNombre()%></h1>
                </div>
            </div>
            <div class="text-white">
                <form method="POST" action="<%=request.getContextPath()%>/alumnos?action=crear&idUniversidad=<%=infoUniversidad.getIdUniversidad()%>">
                    <div class="mb-3">
                        <label for="participante" class="form-label">Pais</label>
                        <select class="form-select" aria-label="Seleccionar Pais" id="participante" name="idParticipante" required>
                            <option selected>---- Seleccionar un Participante ----</option>

                            <%for(BParticipante p : listaParticipantes){%>
                                <option value="<%=p.getIdParticipante()%>"><%=p.getNombreP()%></option>
                            <%}%>

                        </select>
                        <script>
                            $(document).ready(function() {
                                $('.form-select').select2();
                            });
                        </script>
                    </div>

                    <div class="mb-3">
                        <label for="codigo" class="form-label">Código</label>
                        <input type="number" class="form-control" id="codigo" name="codigo" required>
                    </div>
                    <div class="mb-3">
                        <label for="promedio" class="form-label">Promedio Ponderado</label>
                        <input type="number" step="0.01" max="20" min="0" class="form-control" id="promedio" name="promedio" required>
                    </div>

                    <div class="row">
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/alumnos?idUniversidad=<%=infoUniversidad.getIdUniversidad()%>">Cancelar</a>
                        <button type="submit" class="btn btn-success">Crear</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
