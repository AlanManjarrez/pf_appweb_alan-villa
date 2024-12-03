/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_negocio_controles;

import java.util.ArrayList;
import java.util.List;
import pf_appweb_negocio_DTOS.ComentarioDTO;
import pf_appweb_negocio_convertidores.Convertidor;
import pf_appweb_negocio_interfaces.IControlComentario;
import pf_appweb_persistencia_DAOS.ComentarioDAO;
import pf_appweb_persistencia_entity.Comentario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class ControlComentario implements IControlComentario{

    ComentarioDAO comentarioDAO;
    Convertidor convertidor;

    public ControlComentario(ComentarioDAO comentarioDAO, Convertidor convertidor) {
        this.comentarioDAO = comentarioDAO;
        this.convertidor = convertidor;
    }

    @Override
    public Boolean crearComentario(ComentarioDTO comentario) {
        boolean comentarioDTO = this.comentarioDAO.crearComentario(convertidor.dtoAComentario(comentario));
        return comentarioDTO;
    }

    @Override
    public Boolean editarComentario(ComentarioDTO comentario) {
        boolean comentarioDTO = this.comentarioDAO.editarComentario(convertidor.dtoAComentario(comentario));
        return comentarioDTO;
    }

    @Override
    public Boolean eliminarComentario(ComentarioDTO comentario) {
        boolean comentarioDTO = this.comentarioDAO.eliminarComentario(convertidor.dtoAComentario(comentario));
        return comentarioDTO;
    }

    @Override
    public List<ComentarioDTO> obtenerComentarios(ComentarioDTO comentarioDTO) {
        List<Comentario> listaComentarios = this.comentarioDAO.obtenerComentario();
        List<ComentarioDTO> listaComentariosDTO = new ArrayList<>();

        for (Comentario comentario : listaComentarios) {
            listaComentariosDTO.add(convertidor.comentarioADTO(comentario));
        }

        return listaComentariosDTO;
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPost(Long id) {
        List<Comentario> listaComentarios = this.comentarioDAO.obtenerComentariosPost(id);
        List<ComentarioDTO> listaComentariosDTO = new ArrayList<>();

        for (Comentario comentario : listaComentarios) {
            listaComentariosDTO.add(convertidor.comentarioADTO(comentario));
        }

        return listaComentariosDTO;
    }

    @Override
    public ComentarioDTO obtenerComentarioId(Long id) {
        ComentarioDTO comentarioDTO = convertidor.comentarioADTO(this.comentarioDAO.obtenerComentarioId(id));
        return comentarioDTO;
    }
}
