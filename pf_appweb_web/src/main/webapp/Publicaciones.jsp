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
                <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) {%>
                <button class="sidebar-button">Crear Publicación Anclada</button>
                <% } %>
                <button class="sidebar-button">Editar Perfil</button>

                <a href="LogoutServlet" class="sidebar-button logout">Cerrar Sesión</a> 
            </aside>

            <!-- Contenido principal -->
            <main class="content">
                <h2 class="content-title">Publicaciones Ancladas</h2>

                <!-- Publicación anclada -->
                <section class="publication-pinned">
                    <% if (anclados != null && !anclados.isEmpty()) {
                            for (PostDTO postDTO : anclados) {%>
                    <p class="publication-title"><%= postDTO.getTitulo()%></p>
                    <p class="publication-description"><%= postDTO.getContenido()%></p>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) {%>
                    <button class="delete-button" onclick="eliminarPost(<%= postDTO.getId()%>)">Eliminar</button>

                    <% } %>
                    <% }
                    } else {%>
                    <p class="publication-title">No hay Publicaciones Ancladas</p>
                    <% } %>
                    <!-- Bloque flexible para código, imagen o contenido adicional -->
                    <div class="publication-content">
                    </div>

                    <!-- Botones de acción -->
                    <button class="edit-button">Editar</button>
                </section>

                <!-- Publicación extra (puedes duplicar este bloque) -->
                <h2 class="content-title">Publicaciones Recientes</h2>
                <section class="publication">
                    <% if (publicaciones != null && !publicaciones.isEmpty()) {
                            boolean hayPublicaciones = false;
                            for (PostDTO postDTO : publicaciones) {
                                if (!postDTO.getAnclado()) {
                                            hayPublicaciones = true;
                    %>
                    <p class="publication-title"><%= postDTO.getTitulo() %></p>
                    <p class="publication-description"><%= postDTO.getContenido()%></p>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) {%>
                    <button class="delete-button" onclick="eliminarPost(<%= postDTO.getId() %>)">Borrar</button>
                    <% } %>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.NORMAL.toString())) {%>
                    <button class="edit-button" onclick="editarPost(<%= postDTO.getId() %>)">Editar</button>
                    <% } %>
                    
                    <% } } } %>
                    
                </section>
            </main>
        </div>
    </body>

</html>