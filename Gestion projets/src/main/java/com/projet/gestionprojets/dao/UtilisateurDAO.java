package com.projet.gestionprojets.dao;

import com.projet.gestionprojets.entity.Role;
import com.projet.gestionprojets.entity.Utilisateur;
import com.projet.gestionprojets.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UtilisateurDAO {

    public List<Utilisateur> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Utilisateur u LEFT JOIN FETCH u.categorie ORDER BY u.id DESC",
                    Utilisateur.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public Utilisateur findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Utilisateur> query = em.createQuery(
                    "SELECT u FROM Utilisateur u LEFT JOIN FETCH u.categorie WHERE u.id = :id",
                    Utilisateur.class
            );
            query.setParameter("id", id);
            List<Utilisateur> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    public List<Utilisateur> findEmployes() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Utilisateur> query = em.createQuery(
                    "SELECT u FROM Utilisateur u LEFT JOIN FETCH u.categorie WHERE u.role = :role ORDER BY u.nom, u.prenom",
                    Utilisateur.class
            );
            query.setParameter("role", Role.EMPLOYE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public Utilisateur findByEmailAndPassword(String email, String motDePasse) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Utilisateur> query = em.createQuery(
                    "SELECT u FROM Utilisateur u LEFT JOIN FETCH u.categorie " +
                            "WHERE u.email = :email AND u.motDePasse = :motDePasse",
                    Utilisateur.class
            );
            query.setParameter("email", email);
            query.setParameter("motDePasse", motDePasse);

            List<Utilisateur> resultats = query.getResultList();
            return resultats.isEmpty() ? null : resultats.get(0);
        } finally {
            em.close();
        }
    }
    public void save(Utilisateur utilisateur) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(utilisateur);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Utilisateur utilisateur) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(utilisateur);
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
            Utilisateur utilisateur = em.find(Utilisateur.class, id);
            if (utilisateur != null) {
                em.getTransaction().begin();
                em.remove(utilisateur);
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