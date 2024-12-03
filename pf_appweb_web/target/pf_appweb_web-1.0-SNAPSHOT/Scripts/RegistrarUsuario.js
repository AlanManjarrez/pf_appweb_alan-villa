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

