/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pf_appweb_persistencia_interfaces;

import java.util.List;
import pf_appweb_persistencia_entity.Post;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public interface IPostDAO {

    public Boolean crearPost(Post post);

    public Boolean editarPost(Post post);

    public Boolean eliminarPost(Post post);

    public List<Post> obtenerPost();

    public List<Post> obtenerPostAnclados();

    public Post obtenerPostId(long id);
}
