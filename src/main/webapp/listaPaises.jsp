<%@ page import="Beans.BPais" %>
<%@ page import="Beans.BContinente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaPaises" scope="request" type="java.util.ArrayList<Beans.BPais>"/>
<jsp:useBean id="listaContinentes" scope="request" type="java.util.ArrayList<Beans.BContinente>"/>


<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Paises"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>
            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'>Países del Consorcio</h1>
                </div>
            </div>



            <div class="text-center" >



                <form method="POST" action="<%=request.getContextPath()%>/paises?action=listar">

                    <p>
                        <select class="form-select btn btn-outline-secondary " aria-label="Continentes" name="filter">
                            <option selected> -- Elige el continente --</option>
                            <option value="">Todos
                            </option>
                            <%for (BContinente contP : listaContinentes) {%>
                            <option value="<%=contP.getIdContinente()%>"><%=contP.getNombreC()%>
                            </option>
                            <%}%>
                        </select>
                        <button type="submit" class="btn btn-success">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
                            </svg>
                        </button>
                    </p>
                </form>

            </div>



            <div class="tabla ">
                <table class="table table-transparent table-hover text-center">
                    <thead class="text-white">
                        <tr>
                            <th><a class="btn btn-dark">NOMBRE</a></th>
                            <th><a class="btn btn-dark">CONTINENTE</a></th>
                            <th><a class="btn btn-dark">POBLACIÓN</a></th>
                            <th><a class="btn btn-dark">TAMAÑO</a></th>
                            <th colspan="2"><a class="btn btn-dark">OPCIONES</a></th>
                        </tr>
                    </thead>

                    <tbody>
                        <%for (BPais pais : listaPaises) {%>
                        <tr class="table-secondary">
                            <td><%=pais.getNombre()%>
                            </td>
                            <td><%=pais.getContinente().getNombreC()%>
                            </td>
                            <td><%=pais.getPoblacion()%>
                            </td>
                            <td><%=pais.getTamanio()%>
                            </td>
                            <td><a class="btn btn-primary"
                                   href="<%=request.getContextPath()%>/paises?action=editar&idP=<%=pais.getIdPais()%>">Editar</a>
                            </td>
                            <td><a class="btn btn-danger"
                                   href="<%=request.getContextPath()%>/universidades?action=eliminar&idP=<%=pais.getIdPais()%>">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>

                </table>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>