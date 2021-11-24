<%@ page import="Beans.BUniversidad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaUniversidades" scope="request" type="java.util.ArrayList<Beans.BUniversidad>"/>
<jsp:useBean id="resultado" scope="request" type="java.lang.String"/>

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
        <h1 class='text-light'>Universidades Miembro</h1>
        <a class="btn btn-success" href="<%=request.getContextPath()%>/universidades?action=crear">Nueva Universidad</a>
      </div>

      <% // Mensajes informativos
        String alertClass = null;
        String alertMssg = null;
        if (!resultado.equals("")) {
          switch (resultado) {
            case "re":
              alertClass = "alert-success";
              alertMssg = "Universidad registrada exitosamente";
              break;
            case "rne":
              alertClass = "alert-danger";
              alertMssg = "Hubo un problema con el registro de la Universidad";
              break;
            case "ee":
              alertClass = "alert-success";
              alertMssg = "Se guardaron los cambios a la Universidad";
              break;
            case "ene":
              alertClass = "alert-danger";
              alertMssg = "No se pudieron actualizar los datos de la Universidad";
              break;
            case "be":
              alertClass = "alert-success";
              alertMssg = "Universidad eliminada exitosamente";
              break;
            case "bne":
              alertClass = "alert-danger";
              alertMssg = "Hubo un problema al eliminar la universidad seleccionada";
              break;
          }
      %>
      <div class="alert <%=alertClass%> alert-dismissible fade show" role="alert">
        <%=alertMssg%>
      </div>
      <%}%>
      <div class="tabla">
        <table class="table table-transparent table-hover text-center">
          <thead>
            <th><a class="btn btn-dark" href="<%=request.getContextPath()%>/universidades?filter=u.nombre">NOMBRE</a></th>
            <th><a class="btn btn-dark" href="<%=request.getContextPath()%>/universidades?filter=p.nombre">PAIS</a></th>
            <th><a class="btn btn-dark" href="<%=request.getContextPath()%>/universidades?filter=u.ranking">RANKING</a></th>
            <th><a class="btn btn-dark" href="<%=request.getContextPath()%>/universidades?filter=u.numAlumnos">#ALUMNOS</a></th>
            <th><a class="btn btn-dark" href="#">FOTO</a></th>
            <th colspan="2"><a class="btn btn-dark" href="#">OPCIONES</a></th>
          </thead>
          <%
            for (BUniversidad u : listaUniversidades) {
              String color = "";
              switch(u.getPais().getContinente().getIdContinente()){
                case 1:
                  color = "primary";
                  break;
                case 2:
                  color = "secondary";
                  break;
                case 3:
                  color = "success";
                  break;
                case 4:
                  color = "warning";
                  break;
                case 5:
                  color = "alert";
                  break;
                case 6:
                  color = "light";
                  break;
              }
          %>
          <tr class="table-<%=color%>">
            <td class><%=u.getNombre()%></td>
            <td><%=u.getPais().getNombre()%></td>
            <td># <%=u.getRanking()%></td>
            <td><%=u.getNumAlumnos()%></td>
            <td><img src="<%=u.getFoto()%>" alt="Foto de la Universidad" width="100%" height="100px"/></td>
            <th><a class="btn btn-primary" href="<%=request.getContextPath()%>/universidades?action=editar&id=<%=u.getIdUniversidad()%>">Editar</a></th>
            <th><a class="btn btn-danger" href="<%=request.getContextPath()%>/universidades?action=eliminar&id=<%=u.getIdUniversidad()%>">Eliminar</a></th>
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