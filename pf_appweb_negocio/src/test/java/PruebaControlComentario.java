/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pf_appweb_negocio_DTOS.ComentarioDTO;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_controles.ControlComentario;
import pf_appweb_negocio_controles.ControlUsuario;
import pf_appweb_persistencia_entity.Comentario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class PruebaControlComentario {

    public PruebaControlComentario() {
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
    void testCrearComentario() {
        // Crear datos de prueba para ComentarioDTO
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setContenido("Comentario de prueba");
        comentarioDTO.setFechaHora(Calendar.getInstance());
        ControlUsuario controlUsuario = new ControlUsuario();

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setCorreo("admin1@gmail.com");
        comentarioDTO.setUsuario(usuario);

        List<ComentarioDTO> comentarios = new ArrayList<>();

        Calendar fechaCreacion = Calendar.getInstance();
        Calendar fechaEdicion = Calendar.getInstance();

        fechaCreacion.set(2024, Calendar.DECEMBER, 3);
        fechaEdicion.set(2024, Calendar.DECEMBER, 6);

        PostDTO post = new PostDTO();
        post.setId(1L);
        post.setAnclado(Boolean.FALSE);
        post.setTitulo("Titulo");
        post.setContenido("Contenido");
        post.setFechaHoraCreacion(fechaCreacion);
        post.setFechaHoraEdicion(fechaEdicion);
        post.setUsuario(usuario);
        post.setComentarios(comentarios);
        
        comentarioDTO.setPost(post);

        // Probar el método de negocio
        ControlComentario controlComentario = new ControlComentario();
        boolean resultado = controlComentario.crearComentario(comentarioDTO);

        // Validar que el comentario fue creado correctamente
        assertTrue(resultado, "El comentario deberia haberse creado correctamente.");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
