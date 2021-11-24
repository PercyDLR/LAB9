<%@ page import="Beans.BPais" %>
<%@ page import="Beans.BContinente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaContinentes" scope="request" type="java.util.ArrayList<Beans.BContinente>"/>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Crear País"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="paises"/>
            </jsp:include>

            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'>Agregar un nuevo País</h1>
                </div>
            </div>


                <div class="col-md-6 col-sm-4">
                    <form method="POST" action="<%=request.getContextPath()%>/paises?action=crear">

                        <div class="mb-3 form-group text-white">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required>
                        </div>
                        <div class="mb-3 form-group text-white">
                            <label for="continente" class="form-label">Continente</label>
                            <select class="form-select btn btn-outline-secondary" aria-label="Seleccionar Continente"
                                    id="continente" name="cont" required>
                                <option selected>---- Selecciona un Continente ----</option>
                                <%for (BContinente contP : listaContinentes) {%>
                                <option value="<%=contP.getIdContinente()%>"><%=contP.getNombreC()%>
                                </option>
                                <%}%>
                            </select>
                        </div>
                        <div class="mb-3 form-group text-white">
                            <label for="poblacion" class="form-label">Número de Población</label>
                            <input type="text" class="form-control" id="poblacion" name="poblacion" required>
                        </div>
                        <div class="mb-3 form-group text-white">
                            <label for="tam" class="form-label">Tamaño (Km^2)</label>
                            <input type="text" class="form-control" id="tam" name="tam" required>
                        </div>
                        <button type="submit" class="btn btn-success">
                            Agregar
                        </button>

                    </form>

                </div>

        </div>
    </body>
</html>
