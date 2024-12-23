package pf_appweb_persistencia_entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jose Alan Manjarrez Ontiveros 228982
 * @author Jesus Eduardo Villanueva Godoy 235078
 */
@Entity(name = "Post")
public class Post implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaHoraCreacion;

    @Column(name = "Titulo", nullable = false, length = 300)
    private String titulo;

    @Column(name = "Contenido", nullable = false, length = 500)
    private String contenido;

    @Column(name = "fechaHoraEdicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaHoraEdicion;

    @Column(name = "Anclado", nullable = false)
    private Boolean anclado;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Calendar fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getAnclado() {
        return anclado;
    }

    public void setAnclado(Boolean anclado) {
        this.anclado = anclado;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Post() {
    }

    public Post(Long id, Calendar fechaHoraCreacion, String titulo, String contenido, Calendar fechaHoraEdicion, Boolean anclado, Usuario usuario, List<Comentario> comentarios) {
        this.id = id;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaHoraEdicion = fechaHoraEdicion;
        this.anclado = anclado;
        this.usuario = usuario;
        this.comentarios = comentarios;
    }

    public Post(Calendar fechaHoraCreacion, String titulo, String contenido, Calendar fechaHoraEdicion, Boolean anclado, Usuario usuario, List<Comentario> comentarios) {
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaHoraEdicion = fechaHoraEdicion;
        this.anclado = anclado;
        this.usuario = usuario;
        this.comentarios = comentarios;
    }

}
