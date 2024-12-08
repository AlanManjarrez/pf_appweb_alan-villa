const form = document.getElementById("editpost-form");
const submitButton = document.getElementById("submit-edit-post");

submitButton.addEventListener("click", async () => {
    event.preventDefault();
    const formData = new FormData(form);
    const jsonBody = Object.fromEntries(formData.entries());

    try {
        const response = await fetch("EditarPublicacionServlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"// Especifica el tipo de contenido
            },
            body: JSON.stringify(jsonBody) // Convierte el objeto JSON a una cadena
        });

        const result = await response.json();

        if (response.ok && result.success) {
            alert("Publicación creada con éxito");
            window.location.href = "Publicaciones.jsp";
        } else {
            alert(`Error: ${result.message || "No se pudo crear la publicación."}`);
        }
    } catch (error) {
        console.error("Error al crear la publicación:", error);
        alert("Ocurrió un error inesperado.");
    }
});


