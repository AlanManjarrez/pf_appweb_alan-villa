<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pf_appweb_negocio_DTOS.UsuarioDTO"%>
<%

    UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuarioDTO");
    if (usuarioDTO == null) {
        response.sendRedirect("Login.jsp");
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
            <h2>Crear Publiación</h2>

            <!-- Formulario -->
            <form id="createpost-form" action="CrearPublicacionServlet" method="POST">
                <!-- Grupo de campos: Titulo -->
                <div class="form-group">
                    <label for="titulo">Titulo:</label>
                    <input class="campo-create" type="text" id="titulo" name="titulo" placeholder="Hola Mundo" required>
                </div>

                <!-- Grupo de campos: Contraseña -->
                <div class="form-group">
                    <label for="contenido">Contenido:</label>
                    <textarea class="campo-create" id="contenido" name="contenido" rows="10" cols="51" placeholder="Ingresa una descripción"></textarea>
                </div>

                <!-- Botón de enviar -->
                <button type="submit" class="form-button">Crear Publicación</button>

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
