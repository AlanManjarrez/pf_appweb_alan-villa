/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.util.ArrayList;
import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_controles.ControlPost;
import pf_appweb_persistencia_entity.TipoUsuario;

/**
 *
 * @author uirtis
 */
public class PruebaControlPost {

    public PruebaControlPost() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void testCrearPostConDatosReales() {
        // Crear un PostDTO con datos reales
        PostDTO postDTO = new PostDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setCorreo("admin@gmail.com");
        ControlPost controlPost = new ControlPost();
        postDTO.setTitulo("Título de integración");
        postDTO.setContenido("Este es un contenido real para pruebas de integración");
        postDTO.setFechaHoraCreacion(Calendar.getInstance());
        postDTO.setFechaHoraEdicion(Calendar.getInstance());
        postDTO.setAnclado(false);
        postDTO.setUsuario(usuarioDTO); // Supongamos que ya existe un UsuarioDTO válido
        postDTO.setComentarios(new ArrayList<>());

        // Ejecutar el método de servicio
        Boolean resultado = controlPost.crearPost(postDTO);

        // Verificar que el resultado sea verdadero
        assertTrue(resultado);

        // Opcional: Verificar que los datos se hayan guardado en la base de datos
        // Puedes realizar una consulta directa al DAO o repositorio para validar
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
