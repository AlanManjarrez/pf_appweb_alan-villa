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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JESUS
 */
@Entity(name="Usuario")
public abstract class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombreCompleto",nullable = false,length = 250)
    private String nombreCompleto;
    
    @Column(name="correo",nullable = false, length = 250)
    private String correo;
    
    @Column(name="contasena",nullable = false,length = 50)
    private String contrasena;
    
    @Column(name = "telefono",nullable = false,length = 15)
    private String telefono;
    
    @Column(name="ciudad",nullable = false,length = 150)
    private String ciudad;
    
    @Column(name = "fechaNacimiento",nullable = false)
    @Temporal(TemporalType.TIME)
    private Calendar fechaNacimiento;
    
    @Column(name="genero", nullable = false, length = 30)
    private String genero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Usuario() {
    }
    
    public Usuario(Long id, String nombreCompleto, String correo, String contrasena, String telefono, String ciudad, Calendar fechaNacimiento, String genero) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public Usuario(String nombreCompleto, String correo, String contrasena, String telefono, String ciudad, Calendar fechaNacimiento, String genero) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    
}
