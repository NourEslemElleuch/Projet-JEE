package com.projet.gestionprojets.dao;

import com.projet.gestionprojets.entity.Categorie;
import com.projet.gestionprojets.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategorieDAO {

    public List<Categorie> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categorie c ORDER BY c.id DESC", Categorie.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Categorie findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Categorie.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Categorie categorie) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categorie);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Categorie categorie) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(categorie);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Categorie categorie = em.find(Categorie.class, id);

            if (categorie != null) {
                em.getTransaction().begin();
                em.remove(categorie);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}