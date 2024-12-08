<%@page import="pf_appweb_negocio_DTOS.TipoUsuarioDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pf_appweb_negocio_DTOS.UsuarioDTO"%>
<%@ page import="pf_appweb_negocio_DTOS.PostDTO"%>

<%

    UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuarioDTO");
    if (usuarioDTO == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
    PostDTO postDTO = (PostDTO) session.getAttribute("postDTO");
    if (postDTO == null) {
        response.sendRedirect("Publicaciones.jsp");
        return;
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/proyecto_crear_publicacion.css"/>
        <title>Crear Publiación</title>
    </head>
    <body>
        <!-- Encabezado -->
        <header class="header">
            <div class="logo"></div>
            <div class="title">TutosJavaDoc</div>
        </header>

        <!-- Contenedor principal -->
        <main class="form-container">
            <!-- Título del formulario -->
            <h2>Editar Publiación</h2>

            <!-- Formulario -->
            <form id="editpost-form" action="EditarPublicacionServlet" method="POST">
                <!-- Grupo de campos: Titulo -->
                <div class="form-group">
                    <label for="titulo">Titulo:</label>
                    <input class="campo-create" type="text" id="titulo" name="titulo" value="<%= postDTO.getTitulo() %>" required>
                </div>

                <!-- Grupo de campos: Contraseña -->
                <div class="form-group">
                    <label for="contenido">Contenido:</label>
                    <textarea class="campo-create" id="contenido" name="contenido" rows="10" cols="51"><%= postDTO.getContenido()%></textarea>
                </div>

                <!-- Grupo de campos: Anclado -->
                <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) { %>
                <div class="form-group">
                    <label for="anclado">Anclado:</label>
                    <input class="campo-checkbox" type="checkbox" id="anclado" name="anclado" value="ON" />
                </div>
                <% }%> 

                <!-- Botón de enviar -->
                <button id="submit-edit-post" type="submit" class="form-button">Editar Publicación</button>

                <!-- Mensajes de error -->
                <div id="error-message" class="error-message" style="color: white; text-align: center; display: none;"></div>
            </form>
            <section class="return-boton-container">
                <div class="return-boton">
                    <a href="Publicaciones.jsp">Regresar a Publicaciones</a>
                </div>
            </section>
        </main>
    </body>
    <script src="Scripts/CrearPublicacion.js"></script>
</html>