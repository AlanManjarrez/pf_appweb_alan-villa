/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_persistencia_entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author JESUS
 */
@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column()
    private Calendar fechaHora;
    private String contenido;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Comentario comentarioPadre;

    @OneToMany(mappedBy = "comentarioPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> respuestas;

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

    public Comentario getComentarioPadre() {
        return comentarioPadre;
    }

    public void setComentarioPadre(Comentario comentarioPadre) {
        this.comentarioPadre = comentarioPadre;
    }

    public List<Comentario> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Comentario> respuestas) {
        this.respuestas = respuestas;
    }

    public Comentario() {
    }

    public Comentario(Long id, Calendar fechaHora, String contenido, Post post, Usuario usuario, Comentario comentarioPadre, List<Comentario> respuestas) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.post = post;
        this.usuario = usuario;
        this.comentarioPadre = comentarioPadre;
        this.respuestas = respuestas;
    }

    public Comentario(Calendar fechaHora, String contenido, Post post, Usuario usuario, Comentario comentarioPadre, List<Comentario> respuestas) {
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.post = post;
        this.usuario = usuario;
        this.comentarioPadre = comentarioPadre;
        this.respuestas = respuestas;
    }
    
    
    
}
