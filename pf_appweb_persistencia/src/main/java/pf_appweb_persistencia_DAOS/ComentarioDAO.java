/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pf_appweb_persistencia_DAOS;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pf_appweb_persistencia_entity.Comentario;
import pf_appweb_persistencia_entity.Post;
import pf_appweb_persistencia_interfaces.IComentarioDAO;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class ComentarioDAO implements IComentarioDAO {

    @Override
    public boolean crearComentario(Comentario comentario) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            if (comentario.getPost().getId() == null) {
                throw new IllegalArgumentException("El ID del post no puede ser nulo."+comentario.getPost().getId());
            }
            entityManager.getTransaction().begin();
            Post postExistente = entityManager.find(Post.class, comentario.getPost().getId());
            if (postExistente == null) {
                throw new Exception("Post no encontrado");
            }

            entityManager.persist(comentario);
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
    public boolean editarComentario(Comentario comentario) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Comentario comentarioEncontrado = entityManager.find(Comentario.class, comentario.getId());

            if (comentarioEncontrado == null) {
                System.out.println("El comentario no fue encontrado");
            }

            entityManager.persist(comentarioEncontrado);
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
    public boolean eliminarComentario(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Comentario comentarioEncontrado = entityManager.find(Comentario.class, id);

            if (comentarioEncontrado == null) {
                System.out.println("El comentario no fue encontrado");
            }

            entityManager.remove(comentarioEncontrado);
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
    public List<Comentario> obtenerComentario() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Comentario> comentarios = entityManager.createQuery("SELECT C FROM Comentario C", Comentario.class).getResultList();

            entityManager.getTransaction().commit();
            return comentarios;

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
    public List<Comentario> obtenerComentariosPost(Long idPost) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Comentario> comentarios = entityManager.createQuery("SELECT C FROM Comentario C WHERE C.post.id = :idPost", Comentario.class)
                    .setParameter("idPost", idPost)
                    .getResultList();

            entityManager.getTransaction().commit();
            return comentarios;

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
    public Comentario obtenerComentarioId(long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Comentario comentario = entityManager.createQuery("SELECT C FROM Comentario C WHERE C.id = :id", Comentario.class)
                    .setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            return comentario;

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
