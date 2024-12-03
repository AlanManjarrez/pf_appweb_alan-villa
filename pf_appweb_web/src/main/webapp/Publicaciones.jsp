

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=JetBrains+Mono&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="proyecto_publicaciones.css">
    <title>Publicaciones</title>
</head>

<body>
    <div class="main-container">
        <div class="content-background"></div>
        <span class="title">Publicaciones</span>
        <header class="header">
            <div class="logo"></div>
            <div class="title">TutosJavaDoc</div>
        </header>
        <div class="javadoc-section">
            <span class="section-title">JavaDoc</span>
            <div class="javadoc-description">
                <div class="description-background"></div>
                <span class="description-text">El JavaDoc es una herramienta poderosa utilizada en el desarrollo de
                    aplicaciones Java para documentar el código de manera estructurada y legible.</span>
            </div>
        </div>
        <div class="regex-section">
            <div class="regex-description">
                <div class="regex-background"></div>
                <span class="regex-text">Las expresiones regulares en Java se utilizan para buscar y validar patrones en
                    cadenas de texto. Puedes utilizar esta funcionalidad para buscar, validar y manipular cadenas de
                    texto de acuerdo con patrones específicos.<br><br>Ejemplo de validar un correo electrónico:</span>
            </div>
            <div class="code-block">
                <div class="code-background"></div>
                <span class="code-text">JavaScript

                    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                    const email = "ejemplo@correo.com";

                    if (emailRegex.test(email)) {
                    console.log("La dirección de correo es válida");
                    } else {
                    console.log("La dirección de correo es inválida");
                    }</span>
            </div>
            <span class="section-subtitle">Expresiones regulares en Java</span>
            <div class="divider"></div>
        </div>
        <div class="create-post-button">
            <div class="button-background"></div>
            <span class="button-text-publi">Crear Publicación</span>
        </div>
        <div class="edit-button">
            <div class="button-background-edit"></div>
            <span class="button-text">Editar</span>
        </div>
        <div class="edit-button-second">
            <div class="button-background-edit"></div>
            <span class="button-text">Editar</span>
        </div>
        <div class="sidebar">
            <div class="sidebar-background"></div>
            <div class="previous-topic">
                <div class="icon-background"></div>
                <span class="sidebar-text">Tema Anterior</span>
            </div>
            <div class="next-topic">
                <div class="icon-background"></div>
                <span class="sidebar-text">Tema Siguiente</span>
            </div>
            <span class="logout-text">Cerrar Sesión</span>
            <div class="user-info">
                <span class="username-text">Nombre de Usuario</span>
                <div class="user-icon"></div>
            </div>
        </div>
    </div>
</body>

</html>