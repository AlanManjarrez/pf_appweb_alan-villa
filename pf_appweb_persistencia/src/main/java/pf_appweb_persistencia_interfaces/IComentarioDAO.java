/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pf_appweb_persistencia_interfaces;

import java.util.List;
import pf_appweb_persistencia_entity.Comentario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public interface IComentarioDAO {

    public boolean crearComentario(Comentario comentario);

    public boolean editarComentario(Comentario comentario);

    public boolean eliminarComentario(Comentario comentario);

    public List<Comentario> obtenerComentario();

    public List<Comentario> obtenerComentariosPost(Long idPost);

    public Comentario obtenerComentarioId(long id);
}
