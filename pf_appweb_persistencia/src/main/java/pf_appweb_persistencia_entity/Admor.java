/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_persistencia_entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

/**
 *
 * @author JESUS
 */
@Entity(name="Admor")
public class Admor extends Usuario{

    public Admor() {
    }

    public Admor(Long id, String nombreCompleto, String correo, String contrasena, String telefono, String ciudad, Calendar fechaNacimiento, String genero) {
        super(id, nombreCompleto, correo, contrasena, telefono, ciudad, fechaNacimiento, genero);
    }

    public Admor(String nombreCompleto, String correo, String contrasena, String telefono, String ciudad, Calendar fechaNacimiento, String genero) {
        super(nombreCompleto, correo, contrasena, telefono, ciudad, fechaNacimiento, genero);
    }
    
    
}
