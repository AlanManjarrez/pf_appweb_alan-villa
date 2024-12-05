/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pf_appweb_persistencia_main;

import java.util.Calendar;
import pf_appweb_persistencia_DAOS.UsuarioDAO;
import pf_appweb_persistencia_entity.TipoUsuario;
import pf_appweb_persistencia_entity.Usuario;

/**
 *
 * @author Jose Alan Manjarrez Ontiveros 228982
 * @author Jesus Eduardo Villanueva Godoy 235078
 */
public class Pf_appweb_persistencia {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.set(26, Calendar.OCTOBER, 2001);
        Usuario usuario = new Usuario("admin", "admin@gmail.com", "1234", "1234", "NONE", fechaNacimiento, "---", TipoUsuario.ADMOR, "ruta/default.png");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        //usuarioDAO.registrarUsuario(usuario);
        //System.out.println(usuarioDAO.obtenerUsuarioCorreo(usuario.getCorreo()));
        System.out.println(usuarioDAO.iniciarSesion(usuario.getCorreo(), usuario.getContrasena()));
    }
}
