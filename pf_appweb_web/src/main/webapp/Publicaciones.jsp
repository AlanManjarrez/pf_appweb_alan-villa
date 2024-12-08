<%@page import="pf_appweb_negocio_DTOS.TipoUsuarioDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pf_appweb_negocio_DTOS.UsuarioDTO"%>
<%@ page import="pf_appweb_negocio_DTOS.PostDTO"%>
<%@ page import="pf_appweb_negocio_DTOS.ComentarioDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<%
    UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuarioDTO");
    if (usuarioDTO == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    List<PostDTO> publicaciones = (List<PostDTO>) session.getAttribute("publicaciones");
    List<PostDTO> anclados = (List<PostDTO>) session.getAttribute("anclados");
    //System.out.println(publicaciones);
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/proyecto_publicaciones.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.NORMAL.toString())) {%>
                    <span class="username-text"><%= usuarioDTO.getNombreCompleto()%></span>
                    <% } %>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) {%>
                    <span class="username-text"><%= usuarioDTO.getNombreCompleto()%></span>
                    <% } %>

                </div>

                <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) {%>
                <a href="CrearPublicacion.jsp" class="sidebar-button">Crear Publicación Anclada</a>
                <% } %>
                <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.NORMAL.toString())) {%>
                <a href="CrearPublicacion.jsp" class="sidebar-button">Crear Publicación</a>
                <% } %>

                <a href="LogoutServlet" class="sidebar-button logout">Cerrar Sesión</a> 
            </aside>

            <!-- Contenido principal -->
            <main class="content">
                <h2 class="content-title">Publicaciones Ancladas</h2>

                <!-- Publicación anclada -->
                <section class="publication-pinned">
                    <% if (anclados != null && !anclados.isEmpty()) {
                            for (PostDTO postDTO : anclados) {%>
                    <section class="content-publication-pinned">
                        <h3 class="publication-title-pinned"><%= postDTO.getTitulo()%></h3>
                        <p class="publication-description-pinned"><%= postDTO.getContenido()%></p>
                    </section>
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
                    <section class="content-publication">
                        <a href="Comentarios.jsp?id=<%= postDTO.getId() %>" class="publication-title" id="publication-title" ><%= postDTO.getTitulo()%></a>
                        <p class="publication-description"><%= postDTO.getContenido()%></p>
                        <%
                            Calendar fechaHoraCreacion = postDTO.getFechaHoraCreacion();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String fechaFormateada = sdf.format(fechaHoraCreacion.getTime());
                        %>
                        <p class="publication-date"><%= fechaFormateada%></p>
                    </section>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) {%>
                    <button class="delete-button" onclick="eliminarPost(<%= postDTO.getId()%>)">Borrar</button>
                    <% } %>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.NORMAL.toString())) {%>
                    <button class="edit-button" onclick="editarPost(<%= postDTO.getId()%>)">Editar</button>
                    <% } %>
                    <% if (!hayPublicaciones) {%>
                    <p class="publication-title">No hay Publicaciones Recientes</p>
                    <% }%>  
                    <% }
                            }
                        }%>

                </section>
            </main>
        </div>
    </body>
    <script src="Scripts/Publicaciones.js"></script>
</html>