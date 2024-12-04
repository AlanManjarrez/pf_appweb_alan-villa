/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_negocio_convertidores;

import pf_appweb_negocio_DTOS.ComentarioDTO;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_interfaces.IConvertidor;
import pf_appweb_persistencia_entity.Comentario;
import pf_appweb_persistencia_entity.Post;
import pf_appweb_persistencia_entity.TipoUsuario;
import pf_appweb_persistencia_entity.Usuario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Convertidor implements IConvertidor {

    @Override
    public Usuario dtoAUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        if (usuario.getId() != null) {
            usuario.setId(usuarioDTO.getId());
        }
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setDomicilio(usuarioDTO.getDomicilio());
        usuario.setGenero(usuarioDTO.getGenero());
        usuario.setTipoUsuario(TipoUsuario.NORMAL);
        usuario.setAvatar(usuarioDTO.getAvatar());
        
        return usuario;
    }

    @Override
    public UsuarioDTO usuarioADTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if (usuario.getId() != null) {
            usuarioDTO.setId(usuario.getId());
        }
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setContrasena(usuario.getContrasena());
        usuarioDTO.setNombreCompleto(usuario.getNombreCompleto());
        usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setDomicilio(usuario.getDomicilio());
        usuarioDTO.setGenero(usuario.getGenero());
        usuarioDTO.setTipoUsuario(TipoUsuario.NORMAL);
        usuarioDTO.setAvatar(usuario.getAvatar());

        return usuarioDTO;
    }

    @Override
    public Post dtoAPost(PostDTO postDTO) {
        Post post = new Post();

        post.setId(postDTO.getId());
        post.setTitulo(postDTO.getTitulo());
        post.setContenido(postDTO.getContenido());
        post.setFechaHoraCreacion(postDTO.getFechaHoraCreacion());
        post.setFechaHoraEdicion(postDTO.getFechaHoraEdicion());
        post.setAnclado(postDTO.getAnclado());

        if (post.getUsuario() != null) {
            UsuarioDTO usuarioDTO = usuarioADTO(post.getUsuario());
            postDTO.setUsuario(usuarioDTO);
        }

        return post;
    }

    @Override
    public PostDTO postADTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitulo(post.getTitulo());
        postDTO.setContenido(post.getContenido());
        postDTO.setFechaHoraCreacion(post.getFechaHoraCreacion());
        postDTO.setFechaHoraEdicion(post.getFechaHoraEdicion());
        postDTO.setAnclado(post.getAnclado());

        if (postDTO.getUsuario() != null) {
            Usuario usuario = dtoAUsuario(postDTO.getUsuario());
            post.setUsuario(usuario);
        }

        return postDTO;
    }

    @Override
    public Comentario dtoAComentario(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();

        comentario.setId(comentarioDTO.getId());
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setFechaHora(comentarioDTO.getFechaHora());

        if (comentario.getUsuario() != null) {
            UsuarioDTO usuarioDTO = usuarioADTO(comentario.getUsuario());
            comentarioDTO.setUsuario(usuarioDTO);
        }

        return comentario;
    }

    @Override
    public ComentarioDTO comentarioADTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setContenido(comentario.getContenido());
        comentarioDTO.setFechaHora(comentario.getFechaHora());

        if (comentarioDTO.getUsuario() != null) {
            Usuario usuario = dtoAUsuario(comentarioDTO.getUsuario());
            comentario.setUsuario(usuario);
        }

        return comentarioDTO;
    }

}
