const eliminarComentario = async (comentarioId) => {
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
                const response = await fetch(`BorrarComentarioServlet?comentarioId=${encodeURIComponent(comentarioId)}`, {
                    method: "GET"
                });

                if (response.ok) {
                    Swal.fire("Eliminado!", "El comentario ha sido eliminado.", "success").then(() => {
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        title: "Error",
                        text: "No se pudo eliminar el comentario. Intenta nuevamente.",
                        icon: "error"
                    });
                }
            } catch (error) {
                Swal.fire({
                    title: "Error",
                    text: "Ocurrió un problema al eliminar el comentario.",
                    icon: "error"
                });
                console.error(error);
            }
        }
    });
};
