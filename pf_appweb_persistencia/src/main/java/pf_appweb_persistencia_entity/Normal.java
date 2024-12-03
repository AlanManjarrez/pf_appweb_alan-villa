/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_persistencia_entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author JESUS
 */
@Entity(name = "Normal")
public class Normal extends Usuario{

    public Normal() {
    }

    public Normal(Long id, String nombreCompleto, String correo, String contrasena, String telefono, String ciudad, Calendar fechaNacimiento, String genero) {
        super(id, nombreCompleto, correo, contrasena, telefono, ciudad, fechaNacimiento, genero);
    }

    public Normal(String nombreCompleto, String correo, String contrasena, String telefono, String ciudad, Calendar fechaNacimiento, String genero) {
        super(nombreCompleto, correo, contrasena, telefono, ciudad, fechaNacimiento, genero);
    }
    
    
    
}
