/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_negocio_controles;

import java.util.ArrayList;
import java.util.List;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_convertidores.Convertidor;
import pf_appweb_negocio_interfaces.IControlPost;
import pf_appweb_persistencia_DAOS.PostDAO;
import pf_appweb_persistencia_entity.Post;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class ControlPost implements IControlPost{

    PostDAO postDAO;
    Convertidor convertidor;

    public ControlPost() {
        this.postDAO = new PostDAO();
        this.convertidor = new Convertidor();
    }

    @Override
    public Boolean crearPost(PostDTO post) {
        boolean postDTO = this.postDAO.crearPost(convertidor.dtoAPost(post));
        return postDTO;
    }

    @Override
    public Boolean editarPost(PostDTO post) {
        boolean postDTO = this.postDAO.editarPost(convertidor.dtoAPost(post));
        return postDTO;
    }

    @Override
    public Boolean eliminarPost(PostDTO post) {
        boolean postDTO = this.postDAO.eliminarPost(convertidor.dtoAPost(post));
        return postDTO;
    }

    @Override
    public List<PostDTO> obtenerPost() {
        List<Post> listaPosts = this.postDAO.obtenerPost();
        List<PostDTO> listaPostsDTO = new ArrayList<>();

        for (Post post : listaPosts) {
            listaPostsDTO.add(convertidor.postADTO(post));
        }

        return listaPostsDTO;
    }

    @Override
    public List<PostDTO> obtenerPostAnclados() {
        List<Post> listaPosts = this.postDAO.obtenerPostAnclados();
        List<PostDTO> listaPostsDTO = new ArrayList<>();

        for (Post post : listaPosts) {
            listaPostsDTO.add(convertidor.postADTO(post));
        }

        return listaPostsDTO;
    }

    @Override
    public PostDTO obtenerPostId(long id) {
        PostDTO postDTO = convertidor.postADTO(this.postDAO.obtenerPostId(id));
        return postDTO;
    }

}
