/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_controles.ControlUsuario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class PruebaControlUsuario {
    
    public PruebaControlUsuario() {
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
    void testIniciarSesion() {
        // Datos reales de la base de datos
        ControlUsuario controlUsuario = new ControlUsuario();
        String correo = "jose.manjarrez228982@potros.itson.edu.mx"; // Cambia a un correo válido en tu base de datos
        String contrasena = "123123123s"; // Cambia a una contraseña válida en tu base de datos

        // Ejecutar el método
        UsuarioDTO resultado = controlUsuario.iniciarSesion(correo, contrasena);

        // Validar el resultado
        assertNotNull(resultado, "El usuario no debería ser nulo");
        assertEquals(correo, resultado.getCorreo(), "El correo debería coincidir con el proporcionado");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
