<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/proyecto_registro.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <title>Registro</title>
    </head>

    <body>
        <!-- Header -->
        <header class="header">
            <div class="logo"></div>
            <div class="title">TutosJavaDoc</div>
        </header>

        <!-- Main Content -->
        <main class="registro">
            <!-- Form Section -->
            <section class="form">
                <div class="backgroundform">
                    <h2 class="titulo-registro">Registro</h2>

                    <!-- Form -->
                    <form id="login-form" class="form-content" action="RegistrarUsuarioServlet" method="post" enctype="multipart/form-data">

                        <!-- Success/Error Message -->
                        <c:if test="${not empty mensaje}">
                            <div class="${tipoMensaje == 'success' ? 'alert-success' : 'alert-error'}">
                                ${mensaje}
                            </div>
                        </c:if>

                        <!-- Input Fields -->
                        <label class="label" for="nombre">Nombre Completo:</label>
                        <input class="campo-registro" type="text" id="nombre" name="nombre" pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+" title="Solo se permiten letras y espacios" placeholder="Juan Perez" required>

                        <label class="label" for="email">Email:</label>
                        <input class="campo-registro" type="email" id="email" name="email" placeholder="ejemplo@correo.com" title="Introduce un correo válido como ejemplo@correo.com" required>

                        <label class="label" for="password">Contraseña:</label>
                        <input class="campo-registro" type="password" id="password" name="password" minlength="8" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" title="Debe tener al menos 8 caracteres" required placeholder="Ingresa tu contraseña">

                        <label class="label" for="telefono">Teléfono:</label>
                        <input class="campo-registro" type="text" id="telefono" name="telefono" pattern="^\+?[0-9]{10,15}$" title="Solo se permiten números" placeholder="+13175550116" required>

                        <!-- Avatar Section -->
                        <label>Avatar: (Opcional)</label>
                        <label class="label" for="avatar">
                            <div class="custom-file-upload">
                                <input class="campo-registro" type="file" id="avatar" name="avatar" accept=".png, .jpg, .jpeg" onchange="previewAvatar(event)">
                                <img class="icono-avatar" src="https://icons.veryicon.com/png/o/internet--web/prejudice/user-128.png" alt="Icono de avatar">
                            </div>
                        </label>

                        <!-- Avatar Preview Section -->
                        <label>Vista previa del avatar:</label>
                        <div class="avatar-preview-container">
                            <img width="45px" height="45px" id="avatar-preview" src="ruta/default.png" alt="Vista previa del avatar" class="avatar-preview">
                        </div>

                        <!-- Domicilio and Date Fields -->
                        <label class="label" for="domicilio">Domicilio:</label>
                        <input class="campo-registro" type="text" id="domicilio" name="domicilio" placeholder="Calle 1234" required>

                        <label class="label" for="fecha-nacimiento">Fecha de Nacimiento:</label>
                        <input class="campo-registro" type="date" id="fecha-nacimiento" name="fecha-nacimiento" required>

                        <!-- Gender Select -->
                        <label class="label" for="genero">Género:</label>
                        <select class="campo-registro" id="genero" name="genero">
                            <option value="" disabled selected></option>
                            <option value="masculino">Masculino</option>
                            <option value="femenino">Femenino</option>
                            <option value="otro">Otro</option>
                        </select>

                        <!-- Submit Button -->
                        <button type="submit" class="boton-registrarse">Registrarse</button>
                    </form>
                </div> 
                
                <!-- Login Section -->
                <section class="login-boton-container">
                    <div class="login-boton">
                        <a href="Login.jsp">¿Tienes cuenta? Inicia sesión</a>
                    </div>
                </section>
            </section>
        </main>

        <!-- JS Script -->
        <script src="Scripts/RegistrarUsuario.js"></script>
    </body>

</html>
