/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pf_appweb_negocio_interfaces;

import java.util.List;
import pf_appweb_negocio_DTOS.ComentarioDTO;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public interface IControlComentario {

    public Boolean crearComentario(ComentarioDTO comentario);

    public Boolean editarComentario(ComentarioDTO comentario);

    public Boolean eliminarComentario(ComentarioDTO comentario);

    public List<ComentarioDTO> obtenerComentarios(ComentarioDTO comentarioDTO);

    public List<ComentarioDTO> obtenerComentariosPost(Long id);

    public ComentarioDTO obtenerComentarioId(Long id);
}
