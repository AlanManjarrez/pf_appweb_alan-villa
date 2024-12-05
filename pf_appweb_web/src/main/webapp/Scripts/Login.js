const loginForm = document.getElementById("login-form");
const errorMessage = document.getElementById("error-message");

loginForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Evitar envío directo del formulario

    // Extraer datos del formulario
    
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    try {
        // Realizar la solicitud con fetch
        const response = await fetch("LoginServlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({email, password})
        });

        // Verificar si la respuesta es válida
        const result = await response.json();

        if (response.ok && result.success) {
            // Redirigir si el inicio de sesión es exitoso
            window.location.href = result.url_redirect;
        } else {
            // Mostrar mensaje de error del servidor o mensaje genérico
            showError(result.error || "Error al iniciar sesión.");
        }
    } catch (error) {
        console.error("Error:", error);
        showError("Ocurrió un error inesperado. Intenta de nuevo más tarde.");
    }
});

/**
 * Mostrar el mensaje de error.
 * @param {string} message - Mensaje a mostrar.
 */
function showError(message) {
    errorMessage.style.display = "block";
    errorMessage.textContent = message;
}


