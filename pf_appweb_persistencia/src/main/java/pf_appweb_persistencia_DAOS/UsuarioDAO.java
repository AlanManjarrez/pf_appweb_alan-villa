/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_persistencia_DAOS;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import pf_appweb_persistencia_entity.Usuario;
import pf_appweb_persistencia_interfaces.IUsuarioDAO;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class UsuarioDAO implements IUsuarioDAO{

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            
            String correo = usuario.getCorreo();
            Usuario usuarioExistente = null;
            try {
               usuarioExistente = entityManager.createQuery("SELECT U FROM Usuario U WHERE U.correo = :correo", Usuario.class)
                       .setParameter("correo", correo)
                       .getSingleResult();
                
            } catch (NoResultException e) {
            }
            
            if (usuarioExistente == null) {
                entityManager.persist(usuario);
                entityManager.getTransaction().commit();
                return usuario;
            }else{
                throw new SQLIntegrityConstraintViolationException("Este correo ya esta registrado");
            }
            
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return null;
    }

    @Override
    public Usuario editarUsuario(Usuario usuario) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Usuario usuarioEncontrado = entityManager.find(Usuario.class, usuario.getId());

            if (usuarioEncontrado == null) {
                System.out.println("El usuario no fue encontrado");
            }

            entityManager.persist(usuarioEncontrado);
            entityManager.getTransaction().commit();
            return usuarioEncontrado;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return null;
    }

    @Override
    public Usuario eliminarUsuario(Usuario usuario) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Usuario usuarioEncontrado = entityManager.find(Usuario.class, usuario.getId());

            if (usuarioEncontrado == null) {
                System.out.println("El usuario no fue encontrado");
            }

            entityManager.remove(usuarioEncontrado);
            entityManager.getTransaction().commit();
            return usuarioEncontrado;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return null;
    }
    
    
    public Usuario obtenerUsuarioCorreo(String correo) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            
            Usuario usuario = entityManager.createQuery("SELECT U FROM Usuario U WHERE U.correo = :correo", Usuario.class)
                    .setParameter("correo", correo)
                    .getSingleResult();
            return usuario;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return null;
    }
    
    
    @Override
    public Usuario iniciarSesion(String correo, String contrasena) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Usuario usuario = entityManager.createQuery("SELECT U FROM Usuario U WHERE U.correo = :correo AND U.contrasena = :contrasena ", Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("contrasena", contrasena)
                    .getSingleResult();
            return usuario;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return null;
    }
}
