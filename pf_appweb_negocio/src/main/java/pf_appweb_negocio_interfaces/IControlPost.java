/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pf_appweb_negocio_interfaces;

import java.util.List;
import pf_appweb_negocio_DTOS.PostDTO;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public interface IControlPost {

    public Boolean crearPost(PostDTO post);

    public Boolean editarPost(PostDTO post);

    public Boolean eliminarPost(long id);

    public List<PostDTO> obtenerPost();

    public List<PostDTO> obtenerPostAnclados();

    public PostDTO obtenerPostId(long id);
}
