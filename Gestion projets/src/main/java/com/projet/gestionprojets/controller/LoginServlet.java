package com.projet.gestionprojets.controller;

import com.projet.gestionprojets.dao.UtilisateurDAO;
import com.projet.gestionprojets.entity.Role;
import com.projet.gestionprojets.entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("role") != null) {
            String role = session.getAttribute("role").toString();

            if ("ADMIN".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                return;
            }

            if ("EMPLOYE".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/employe/projets");
                return;
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        if (email == null || email.isBlank() || motDePasse == null || motDePasse.isBlank()) {
            request.setAttribute("erreur", "Email et mot de passe sont obligatoires.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        Utilisateur utilisateur = utilisateurDAO.findByEmailAndPassword(email.trim(), motDePasse.trim());

        if (utilisateur == null) {
            request.setAttribute("erreur", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("userId", utilisateur.getId());
        session.setAttribute("userNomComplet", utilisateur.getPrenom() + " " + utilisateur.getNom());
        session.setAttribute("role", utilisateur.getRole().name());

        if (utilisateur.getRole() == Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/employe/projets");
        }
    }
}