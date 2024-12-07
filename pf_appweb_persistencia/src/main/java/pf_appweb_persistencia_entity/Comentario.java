/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_persistencia_entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
@Entity(name = "Comentario")
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaHora;

    @Column(name = "contenido", nullable = false, length = 500)
    private String contenido;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Calendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario() {
    }

    public Comentario(Long id, Calendar fechaHora, String contenido, Post post, Usuario usuario) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.post = post;
        this.usuario = usuario;
    }

    public Comentario(Calendar fechaHora, String contenido, Post post, Usuario usuario) {
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.post = post;
        this.usuario = usuario;
    }
}
