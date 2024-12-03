/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pf_appweb_negocio_interfaces;

import pf_appweb_negocio_DTOS.ComentarioDTO;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_persistencia_entity.Comentario;
import pf_appweb_persistencia_entity.Post;
import pf_appweb_persistencia_entity.Usuario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public interface IConvertidor {

    public Usuario dtoAUsuario(UsuarioDTO usuarioDTO);

    public UsuarioDTO usuarioADTO(Usuario usuario);

    public Post dtoAPost(PostDTO postDTO);

    public PostDTO postADTO(Post post);

    public Comentario dtoAComentario(ComentarioDTO comentarioDTO);

    public ComentarioDTO comentarioADTO(Comentario comentario);
}
