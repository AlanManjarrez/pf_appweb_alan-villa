function previewAvatar(event) {
    const reader = new FileReader();
    reader.onload = function () {
        const preview = document.getElementById('avatar-preview');
        preview.src = reader.result;
    };
    if (event.target.files[0]) {
        reader.readAsDataURL(event.target.files[0]);
    } else {
        // Si no hay archivo seleccionado, volver a mostrar la imagen predeterminada
        document.getElementById('avatar-preview').src = 'ruta/default.png';
    }
}


const registroForm = document.getElementById("login-form");

registroForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Evitar el envío directo del formulario

    const formData = new FormData(registroForm);

    try {
        const response = await fetch('RegistrarUsuarioServlet', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const data = await response.json();

        if (data.success) {
            Swal.fire({
                icon: 'success',
                title: 'Registro exitoso',
                text: data.message
            }).then(() => {
                window.location.href = data.url_redirect;
            });
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: data.message
            });
        }
    } catch (error) {
        console.error('Error:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Ocurrió un problema al registrar el usuario. Inténtalo nuevamente.'
        });
    }
});
