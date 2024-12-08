/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_negocio_DTOS;

import java.util.Calendar;
import java.util.List;
import pf_appweb_persistencia_entity.Comentario;
import pf_appweb_persistencia_entity.TipoUsuario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class PostDTO {

    private Long id;
    private String titulo;
    private String contenido;
    private Calendar fechaHoraEdicion;
    private Calendar fechaHoraCreacion;
    private Boolean Anclado;
    private UsuarioDTO usuario;
    private List<ComentarioDTO> comentarios;

    public PostDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Calendar getFechaHoraEdicion() {
        return fechaHoraEdicion;
    }

    public void setFechaHoraEdicion(Calendar fechaHoraEdicion) {
        this.fechaHoraEdicion = fechaHoraEdicion;
    }

    public Calendar getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Calendar fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }
    public Boolean getAnclado() {
        return Anclado;
    }

    public void setAnclado(Boolean Anclado) {
        this.Anclado = Anclado;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "PostDTO{" + "id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + ", fechaHoraEdicion=" + fechaHoraEdicion + ", fechaHoraCreacion=" + fechaHoraCreacion + ", Anclado=" + Anclado + ", usuario=" + usuario + ", comentarios=" + comentarios + '}';
    }
    
}
