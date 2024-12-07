/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pf_appweb_persistencia_main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import pf_appweb_persistencia_DAOS.ComentarioDAO;
import pf_appweb_persistencia_DAOS.PostDAO;
import pf_appweb_persistencia_DAOS.UsuarioDAO;
import pf_appweb_persistencia_entity.Comentario;
import pf_appweb_persistencia_entity.Post;
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
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PostDAO postDAO = new PostDAO();
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        Calendar fechaNacimiento = Calendar.getInstance();
        Calendar fechaCreacion = Calendar.getInstance();
        Calendar fechaEdicion = Calendar.getInstance();
        List<Comentario> comentarios = new ArrayList<>();
        fechaCreacion.set(3, Calendar.DECEMBER, 2024);
        fechaEdicion.set(6, Calendar.DECEMBER, 2024);
        fechaNacimiento.set(26, Calendar.OCTOBER, 2001);
        Usuario usuario = new Usuario("admin", "admin@gmail.com", "1234", "1234", "NONE", fechaNacimiento, "---", TipoUsuario.ADMOR, "ruta/default.png");
        Post post = new Post(fechaCreacion, "Titulo", "Contenido", fechaEdicion, Boolean.FALSE, usuarioDAO.obtenerUsuarioCorreo(usuario.getCorreo()), comentarios);
        Comentario comentario = new Comentario(fechaEdicion, "Contenido", post, usuario);
        
        

        //usuarioDAO.registrarUsuario(usuario);
        //System.out.println(usuarioDAO.obtenerUsuarioCorreo(usuario.getCorreo()));
        //System.out.println(usuarioDAO.iniciarSesion(usuario.getCorreo(), usuario.getContrasena()));
        //System.out.println(postDAO.crearPost(post));
        System.out.println(comentarioDAO.crearComentario(comentario));
    }
}
