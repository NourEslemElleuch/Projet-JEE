package com.projet.gestionprojets.controller;

import com.projet.gestionprojets.dao.CategorieDAO;
import com.projet.gestionprojets.dao.UtilisateurDAO;
import com.projet.gestionprojets.entity.Categorie;
import com.projet.gestionprojets.entity.Role;
import com.projet.gestionprojets.entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/utilisateurs")
public class UtilisateurServlet extends HttpServlet {

    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private final CategorieDAO categorieDAO = new CategorieDAO();

    private boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && "ADMIN".equals(String.valueOf(session.getAttribute("role")));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        if (action == null || action.isBlank()) {
            request.setAttribute("utilisateurs", utilisateurDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/views/admin/utilisateurs.jsp").forward(request, response);
            return;
        }

        if ("form".equals(action)) {
            request.setAttribute("utilisateur", new Utilisateur());
            request.setAttribute("categories", categorieDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/views/admin/utilisateur-form.jsp").forward(request, response);
            return;
        }

        if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("utilisateur", utilisateurDAO.findById(id));
            request.setAttribute("categories", categorieDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/views/admin/utilisateur-form.jsp").forward(request, response);
            return;
        }

        if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            utilisateurDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/admin/utilisateurs?success=deleted");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String roleParam = request.getParameter("role");
        String categorieIdParam = request.getParameter("categorieId");

        Role role = Role.valueOf(roleParam);

        Categorie categorie = null;
        if (role == Role.EMPLOYE && categorieIdParam != null && !categorieIdParam.isBlank()) {
            categorie = categorieDAO.findById(Long.parseLong(categorieIdParam));
        }

        Utilisateur utilisateur;
        if (idParam == null || idParam.isBlank()) {
            utilisateur = new Utilisateur();
        } else {
            utilisateur = utilisateurDAO.findById(Long.parseLong(idParam));
        }

        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        if (motDePasse != null && !motDePasse.isBlank()) {
            utilisateur.setMotDePasse(motDePasse);
        }
        utilisateur.setRole(role);
        utilisateur.setCategorie(role == Role.EMPLOYE ? categorie : null);

        if (idParam == null || idParam.isBlank()) {
            utilisateurDAO.save(utilisateur);
            response.sendRedirect(request.getContextPath() + "/admin/utilisateurs?success=added");
        } else {
            utilisateurDAO.update(utilisateur);
            response.sendRedirect(request.getContextPath() + "/admin/utilisateurs?success=updated");
        }
    }
}