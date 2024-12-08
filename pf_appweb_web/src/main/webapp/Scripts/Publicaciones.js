const eliminarPost = async (postId) => {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "No podrás deshacer esta acción.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminar!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            try {
                const response = await fetch(`BorrarPublicacionServlet?postId=${encodeURIComponent(postId)}`, {
                    method: "GET"
                });

                if (response.ok) {
                    Swal.fire("Eliminado!", "El post ha sido eliminado.", "success").then(() => {
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        title: "Error",
                        text: "No se pudo eliminar el post. Intenta nuevamente.",
                        icon: "error"
                    });
                }
            } catch (error) {
                Swal.fire({
                    title: "Error",
                    text: "Ocurrió un problema al eliminar el post.",
                    icon: "error"
                });
                console.error(error);
            }
        }
    });
};


const editarPost = (postId) => {
    Swal.fire({
        title: "Editar post",
        text: "¿Deseas editar el post?",
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, editar!"
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = `EditarPublicacionServlet?postId=${encodeURIComponent(postId)}`;
        }
    });
};

//const viewCommentsButtons = document.querySelectorAll(".view-comments-button");
//
//viewCommentsButtons.forEach((button) => {
//    button.addEventListener("click", async (event) => {
//        event.preventDefault(); // Evitar comportamiento por defecto del enlace
//
//        // Obtener el ID de la publicación
//        const postId = button.getAttribute("publication-title");
//
//        try {
//            // Realizar la solicitud con fetch
//            const response = await fetch("ComentariosServlet", {
//                method: "POST",
//                headers: {
//                    "Content-Type": "application/json"
//                },
//                body: JSON.stringify({ postId })
//            });
//
//            // Verificar si la respuesta es válida
//            const result = await response.json();
//
//            if (response.ok && result.success) {
//                // Redirigir a la página de comentarios si la solicitud es exitosa
//                window.location.href = result.url_redirect;
//            } else {
//                // Mostrar mensaje de error si ocurre un problema
//                showError(result.error || "Error al cargar los comentarios.");
//            }
//        } catch (error) {
//            console.error("Error:", error);
//            showError("Ocurrió un error inesperado. Intenta de nuevo más tarde.");
//        }
//    });
//});
//
//function showError(message) {
//    const errorMessage = document.getElementById("error-message");
//    errorMessage.style.display = "block";
//    errorMessage.textContent = message;
//}
