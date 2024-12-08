/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_persistencia_DAOS;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pf_appweb_persistencia_entity.Post;
import pf_appweb_persistencia_entity.Usuario;
import pf_appweb_persistencia_interfaces.IPostDAO;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class PostDAO implements IPostDAO {

    @Override
    public Boolean crearPost(Post post) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            if (post.getUsuario() == null || post.getUsuario().getCorreo() == null) {
                throw new IllegalArgumentException("El usuario asociado al post es nulo o no tiene un ID válido." + post.getUsuario().getId());
            }
            entityManager.getTransaction().begin();

            Usuario usuarioExistente = entityManager.createQuery("SELECT U FROM Usuario U WHERE U.correo = :correo", Usuario.class)
                    .setParameter("correo", post.getUsuario().getCorreo())
                    .getSingleResult();
            if (usuarioExistente == null) {
                throw new Exception("Usuario no encontrado");
            }
            post.setUsuario(usuarioExistente);
            entityManager.persist(post);
            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return false;
    }

    @Override
    public Boolean editarPost(Post post) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Post postEncontrado = entityManager.find(Post.class, post.getId());

            if (postEncontrado == null) {
                System.out.println("El post no fue encontrado");
            }

            // Actualizar los valores del post encontrado
            postEncontrado.setTitulo(post.getTitulo());
            postEncontrado.setContenido(post.getContenido());
            postEncontrado.setAnclado(post.getAnclado());

            entityManager.merge(postEncontrado);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return false;
    }

    @Override
    public Boolean eliminarPost(long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Post postEncontrado = entityManager.find(Post.class, id);

            if (postEncontrado == null) {
                System.out.println("El post no fue encontrado");
                return false;
            }

            entityManager.remove(postEncontrado);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return false;
    }

    @Override
    public List<Post> obtenerPost() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Post> posts = entityManager.createQuery("SELECT P FROM Post P", Post.class).getResultList();

            entityManager.getTransaction().commit();
            return posts;

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
    public List<Post> obtenerPostAnclados() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Post> posts = entityManager.createQuery("SELECT P FROM Post P WHERE P.anclado = true", Post.class).getResultList();

            entityManager.getTransaction().commit();
            return posts;

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
    public Post obtenerPostId(long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Post post = entityManager.createQuery("SELECT P FROM Post P WHERE P.id = :id", Post.class)
                    .setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            return post;

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

}
