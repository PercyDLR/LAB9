<%@ page import="Beans.BPais" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaPaises" scope="request" type="java.util.ArrayList<Beans.BPais>"/>
<jsp:useBean id="infoUniversidad" scope="request" type="Beans.BUniversidad"/>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Editar Universidad"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-md-8 col-sm-6">
                    <h1 class='text-light'>Editando a: <%=infoUniversidad.getNombre()%></h1>
                </div>
            </div>

            <div class="text-white">
                <form method="POST" action="<%=request.getContextPath()%>/universidades?action=editar&id=<%=infoUniversidad.getIdUniversidad()%>">
                    <div class="mb-3">
                        <label for="pais" class="form-label">Pais</label>
                        <select class="form-select" aria-label="Seleccionar Pais" id="pais" name="idPais" required>
                            <%for(BPais pais : listaPaises){
                                if(pais.getIdPais()==infoUniversidad.getPais().getIdPais()){%>
                                    <option selected value="<%=pais.getIdPais()%>"><%=pais.getNombre()%></option>
                                <%}else{%>
                                    <option value="<%=pais.getIdPais()%>"><%=pais.getNombre()%></option>
                            <%}}%>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="<%=infoUniversidad.getNombre()%>" required>
                    </div>
                    <div class="mb-3">
                        <label for="ranking" class="form-label">Ranking</label>
                        <input type="number" class="form-control" id="ranking" name="ranking" value="<%=infoUniversidad.getRanking()%>" required>
                    </div>
                    <div class="mb-3">
                        <label for="foto" class="form-label">URL de la Foto</label>
                        <input type="text" class="form-control" id="foto" name="foto" value="<%=infoUniversidad.getFoto()%>" required>
                    </div>

                    <div class="row">
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/universidades">Cancelar</a>
                        <button type="submit" class="btn btn-success">Guardar Cambios</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
