<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Configuración del documento -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Hoja de estilos -->
        <link rel="stylesheet" href="css/proyecto_iniciar_sesion.css">

        <!-- Título de la página -->
        <title>Iniciar Sesión</title>
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
            <h2>Iniciar Sesión</h2>

            <!-- Formulario -->
            <form id="login-form" action="LoginServlet" method="POST">
                <!-- Grupo de campos: Email -->
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input class="campo-login" type="email" id="email" name="email" placeholder="ejemplo@correo.com" title="Introduce un correo válido como ejemplo@correo.com" required>
                </div>

                <!-- Grupo de campos: Contraseña -->
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input class="campo-login" type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required>
                </div>

                <!-- Botón de enviar -->
                <button type="submit" class="form-button">Iniciar Sesión</button>

                <!-- Mensajes de error -->
                <div id="error-message" class="error-message" style="color: white; text-align: center; display: none;"></div>
            </form>
            <section class="sign-boton-container">
                <div class="sign-boton">
                    <a href="RegistroUsuario.jsp">¿No te has registrado? Registrate</a>
                </div>
            </section>
        </main>
    </body>
    <script src="Scripts/Login.js"></script>
</html>
