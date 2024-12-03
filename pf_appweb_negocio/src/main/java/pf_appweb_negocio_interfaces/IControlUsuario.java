/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pf_appweb_negocio_interfaces;

import pf_appweb_negocio_DTOS.UsuarioDTO;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public interface IControlUsuario {

    public UsuarioDTO registrarUsuario(UsuarioDTO usuario);

    public UsuarioDTO editarUsuario(UsuarioDTO usuario);

    public UsuarioDTO eliminarUsuario(UsuarioDTO usuario);

    public UsuarioDTO obtenerUsuario(long id);
}
