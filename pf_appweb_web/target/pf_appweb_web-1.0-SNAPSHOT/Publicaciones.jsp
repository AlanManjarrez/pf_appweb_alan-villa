<%@page import="pf_appweb_negocio_DTOS.TipoUsuarioDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pf_appweb_negocio_DTOS.UsuarioDTO"%>
<%@ page import="pf_appweb_negocio_DTOS.PostDTO"%>
<%@ page import="pf_appweb_negocio_DTOS.ComentarioDTO"%>
<%@ page import="java.util.List"%>

<%
    UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuarioDTO");
    if (usuarioDTO == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    List<PostDTO> publicaciones = (List<PostDTO>) request.getAttribute("publicaciones");
    List<PostDTO> anclados = (List<PostDTO>) request.getAttribute("anclados");
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/proyecto_publicaciones.css">
        <title>Publicaciones</title>
    </head>

    <body>
        <!-- Contenedor principal -->
        <div class="main-container">

            <!-- Encabezado -->
            <header class="header">
                <div class="logo"></div>
                <h1 class="title">TutosJavaDoc</h1>
            </header>

            <!-- Barra lateral -->
            <aside class="sidebar">
                <div class="user-info">
                    <img src="<%= usuarioDTO.getAvatar() != null ? usuarioDTO.getAvatar() : "ruta/default.png"%>" alt="Avatar Usuario" class="avatar" />
                    <br>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase("NORMAL")) {%>
                    <span class="username-text"><%= usuarioDTO.getNombreCompleto()%></span>
                    <% } %>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase("ADMOR")) {%>
                    <span class="username-text"><%= usuarioDTO.getNombreCompleto()%></span>
                    <% } %>

                </div>
                    
                <button class="sidebar-button">Crear Publicación</button>
                <% if(usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) {%>
                <button class="sidebar-button">Crear Publicación Anclada</button>
                <% } %>
                <button class="sidebar-button">Editar Perfil</button>

                <a href="LogoutServlet" class="sidebar-button logout">Cerrar Sesión</a> 
            </aside>

            <!-- Contenido principal -->
            <main class="content">
                <h2 class="content-title">Publicaciones</h2>

                <!-- Publicación anclada -->
                <section class="publication-pinned">
                    <% if(anclados != null && !anclados.isEmpty()) {
                        for (PostDTO postDTO : anclados) {%>
                        <h3 class="publication-title"><%= postDTO.getTitulo() %></h3>
                    <p class="publication-description">
                        Descripción de la publicación... Aquí puedes agregar texto descriptivo de lo que se está compartiendo en esta publicación.
                    </p>
                    <% }} %>
                    <!-- Bloque flexible para código, imagen o contenido adicional -->
                    <div class="publication-content">
                        <!-- Opcional: Código -->
                        <pre class="code-block">
                        <code></code>
                        </pre>

                        <!-- Opcional: Imagen -->
                        <div class="image-container">
                            <img src="" alt="Imagen de ejemplo" class="publication-image" />
                        </div>
                    </div>

                    <!-- Botones de acción -->
                    <button class="edit-button">Editar</button>
                </section>

                <!-- Publicación extra (puedes duplicar este bloque) -->
                <section class="publication">
                    <h3 class="publication-title">Otra Publicación</h3>
                    <p class="publication-description">
                        Aquí puedes agregar contenido adicional para una nueva publicación. Por ejemplo, un fragmento de texto, una imagen o cualquier información relevante.
                    </p>

                    <div class="publication-content">
                        <!-- Ejemplo: Imagen -->
                        <div class="image-container">
                            <img src="ruta-a-tu-imagen.jpg" alt="Imagen de ejemplo" class="publication-image" />
                        </div>
                    </div>

                    <button class="edit-button">Editar</button>
                </section>
            </main>
        </div>
    </body>

</html>