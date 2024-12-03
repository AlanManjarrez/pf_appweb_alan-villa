/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_negocio_controles;

import java.sql.SQLIntegrityConstraintViolationException;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_convertidores.Convertidor;
import pf_appweb_negocio_interfaces.IControlUsuario;
import pf_appweb_persistencia_DAOS.UsuarioDAO;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class ControlUsuario implements IControlUsuario {

    UsuarioDAO usuarioDAO;
    Convertidor convertidor;

    public ControlUsuario() {
        this.usuarioDAO = new UsuarioDAO();
        this.convertidor =  new Convertidor();
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuario) {
        UsuarioDTO usuarioDTO = convertidor.usuarioADTO(this.usuarioDAO.registrarUsuario(convertidor.dtoAUsuario(usuario)));
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO editarUsuario(UsuarioDTO usuario) {
        UsuarioDTO usuarioDTO = convertidor.usuarioADTO(this.usuarioDAO.editarUsuario(convertidor.dtoAUsuario(usuario)));
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO eliminarUsuario(UsuarioDTO usuario) {
        UsuarioDTO usuarioDTO = convertidor.usuarioADTO(this.usuarioDAO.eliminarUsuario(convertidor.dtoAUsuario(usuario)));
        return usuarioDTO;
    }
    /*
    @Override
    public UsuarioDTO obtenerUsuario(String correo) {
        UsuarioDTO usuarioDTO = convertidor.usuarioADTO(this.usuarioDAO.obtenerUsuario(correo));
        return usuarioDTO;
    }
    */

    @Override
    public UsuarioDTO iniciarSesion(String correo, String contrasena) {
        UsuarioDTO usuarioDTO = convertidor.usuarioADTO(this.usuarioDAO.iniciarSesion(correo, contrasena));
        return usuarioDTO;
    }
}
