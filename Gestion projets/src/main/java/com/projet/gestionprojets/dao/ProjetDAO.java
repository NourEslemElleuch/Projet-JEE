package com.projet.gestionprojets.dao;

import com.projet.gestionprojets.entity.Projet;
import com.projet.gestionprojets.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProjetDAO {

    public List<Projet> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Projet p ORDER BY p.id DESC", Projet.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Projet findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Projet.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Projet projet) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(projet);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Projet projet) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(projet);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Projet projet = em.find(Projet.class, id);
            if (projet != null) {
                em.getTransaction().begin();
                em.remove(projet);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}