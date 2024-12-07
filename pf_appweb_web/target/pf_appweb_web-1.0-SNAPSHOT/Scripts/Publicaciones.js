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

