package com.projet.gestionprojets.dao;

import com.projet.gestionprojets.entity.Affectation;
import com.projet.gestionprojets.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AffectationDAO {

    public List<Affectation> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT a FROM Affectation a " +
                            "JOIN FETCH a.employe e " +
                            "JOIN FETCH a.projet p " +
                            "LEFT JOIN FETCH e.categorie " +
                            "ORDER BY a.id DESC",
                    Affectation.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public Affectation findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Affectation> query = em.createQuery(
                    "SELECT a FROM Affectation a " +
                            "JOIN FETCH a.employe e " +
                            "JOIN FETCH a.projet p " +
                            "LEFT JOIN FETCH e.categorie " +
                            "WHERE a.id = :id",
                    Affectation.class
            );
            query.setParameter("id", id);
            List<Affectation> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    public List<Affectation> findByEmployeId(Long employeId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Affectation> query = em.createQuery(
                    "SELECT a FROM Affectation a " +
                            "JOIN FETCH a.projet " +
                            "JOIN FETCH a.employe " +
                            "WHERE a.employe.id = :id ORDER BY a.dateDebut DESC",
                    Affectation.class
            );
            query.setParameter("id", employeId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Affectation> findByProjetId(Long projetId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Affectation> query = em.createQuery(
                    "SELECT a FROM Affectation a " +
                            "JOIN FETCH a.projet " +
                            "JOIN FETCH a.employe e " +
                            "LEFT JOIN FETCH e.categorie " +
                            "WHERE a.projet.id = :id ORDER BY e.nom, e.prenom",
                    Affectation.class
            );
            query.setParameter("id", projetId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void save(Affectation affectation) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(affectation);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Affectation affectation) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(affectation);
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
            Affectation affectation = em.find(Affectation.class, id);
            if (affectation != null) {
                em.getTransaction().begin();
                em.remove(affectation);
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