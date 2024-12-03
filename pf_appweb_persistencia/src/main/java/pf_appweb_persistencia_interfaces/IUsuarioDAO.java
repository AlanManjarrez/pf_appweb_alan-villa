/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pf_appweb_persistencia_interfaces;

import pf_appweb_persistencia_entity.Usuario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public interface IUsuarioDAO {

    public Usuario registrarUsuario(Usuario usuario);

    public Usuario editarUsuario(Usuario usuario);

    public Usuario eliminarUsuario(Usuario usuario);

    public Usuario obtenerUsuario(long id);
}
