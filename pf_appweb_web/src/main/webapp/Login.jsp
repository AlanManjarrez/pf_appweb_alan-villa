<%-- 
    Document   : Login
    Created on : Dec 2, 2024, 5:22:18 PM
    Author     : uirtis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/proyecto_iniciar_sesion.css">
    <title>Iniciar Sesión</title>
</head>
<body>
    <div class="header">
        <div class="logo"></div>
        <div class="title">TutosJavaDoc</div>
    </div>
    <div class="form-container">
        <h2>Iniciar Sesión</h2>
        <form>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="button" class="form-button">Iniciar Sesión</button>
            
        </form>
    </div>
</body>
</html>
