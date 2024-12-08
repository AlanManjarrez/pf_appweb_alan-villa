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

    PostDTO postDTO = (PostDTO) session.getAttribute("postDTO");
    if (postDTO == null) {
        response.sendRedirect("Publicaciones.jsp");
        return;
    }

    List<ComentarioDTO> comentarios = (List<ComentarioDTO>) session.getAttribute("comentarios");
    //System.out.println(publicaciones);
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/proyecto_comentarios.css">
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

                <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.NORMAL.toString())) {%>
                <a href="CrearComentario.jsp" class="sidebar-button">Crear Comentario</a>
                <% }%>

                <a href="LogoutServlet" class="sidebar-button logout">Cerrar Sesión</a> 
            </aside>

            <!-- Contenido principal -->
            <main class="content">
                <h2 class="content-title"><%= postDTO.getTitulo()%> </h2>

                <!-- Publicación anclada para comentar -->
                <section class="publication-pinned">
                    <p class="publication-description"><%= postDTO.getContenido()%></p>
                </section>

                <section class="create-comment">
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.NORMAL.toString())) {%>
                    <form action="CrearComentarioServlet" method="POST">
                    <input class="text-comment" type="text" id="contenido" name="contenido"   placeholder="Hola" required>
                    <input type="submit" value="botton-send" />
                    </form>
                    <% } %>
                </section>


                <!-- Publicación extra (puedes duplicar este bloque) -->
                <h2 class="content-title">Comentarios</h2>
                <section class="publication">
                    <% if (comentarios != null && !comentarios.isEmpty()) {
                            for (ComentarioDTO comentarioDTO : comentarios) {
                    %>
                    <section class="content-publication">
                        <p class="publication-description"><%= comentarioDTO.getContenido()%></p>
                        <%
                            Calendar fechaHoraCreacion = comentarioDTO.getFechaHora();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String fechaFormateada = sdf.format(fechaHoraCreacion.getTime());
                        %>
                        <p class="publication-date"><%= fechaFormateada%></p>
                    </section>
                    <% if (usuarioDTO.getTipoUsuario().toString().equalsIgnoreCase(TipoUsuarioDTO.ADMOR.toString())) {%>
                    <button class="delete-button" onclick="eliminarComentario(<%= comentarioDTO.getId()%>)">Borrar</button>
                    <% } %>
                    <% if (comentarios == null) {%>
                    <p class="publication-title">No hay Comentarios</p>
                    <% }%>  
                    <% }
                        }%>

                </section>
            </main>
        </div>
    </body>
    <script src="Scripts/Comentarios.js"></script>
</html>