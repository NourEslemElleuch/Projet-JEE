package com.projet.gestionprojets.controller;

import com.projet.gestionprojets.dao.AffectationDAO;
import com.projet.gestionprojets.dao.ProjetDAO;
import com.projet.gestionprojets.dao.UtilisateurDAO;
import com.projet.gestionprojets.entity.Affectation;
import com.projet.gestionprojets.entity.Projet;
import com.projet.gestionprojets.entity.Role;
import com.projet.gestionprojets.entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/admin/affectations")
public class AffectationServlet extends HttpServlet {

    private final AffectationDAO affectationDAO = new AffectationDAO();
    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private final ProjetDAO projetDAO = new ProjetDAO();

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
            request.setAttribute("affectations", affectationDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/views/admin/affectations.jsp").forward(request, response);
            return;
        }

        if ("form".equals(action)) {
            request.setAttribute("affectation", new Affectation());
            request.setAttribute("employes", utilisateurDAO.findEmployes());
            request.setAttribute("projets", projetDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/views/admin/affectation-form.jsp").forward(request, response);
            return;
        }

        if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("affectation", affectationDAO.findById(id));
            request.setAttribute("employes", utilisateurDAO.findEmployes());
            request.setAttribute("projets", projetDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/views/admin/affectation-form.jsp").forward(request, response);
            return;
        }

        if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            affectationDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/admin/affectations?success=deleted");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idParam = request.getParameter("id");
        Long employeId = Long.parseLong(request.getParameter("employeId"));
        Long projetId = Long.parseLong(request.getParameter("projetId"));
        LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
        LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));

        if (dateFin.isBefore(dateDebut)) {
            response.sendRedirect(request.getContextPath() + "/admin/affectations?error=date");
            return;
        }

        Utilisateur employe = utilisateurDAO.findById(employeId);
        Projet projet = projetDAO.findById(projetId);

        if (employe == null || employe.getRole() != Role.EMPLOYE || projet == null) {
            response.sendRedirect(request.getContextPath() + "/admin/affectations?error=data");
            return;
        }

        Affectation affectation = (idParam == null || idParam.isBlank())
                ? new Affectation()
                : affectationDAO.findById(Long.parseLong(idParam));

        affectation.setEmploye(employe);
        affectation.setProjet(projet);
        affectation.setDateDebut(dateDebut);
        affectation.setDateFin(dateFin);

        if (idParam == null || idParam.isBlank()) {
            affectationDAO.save(affectation);
            response.sendRedirect(request.getContextPath() + "/admin/affectations?success=added");
        } else {
            affectationDAO.update(affectation);
            response.sendRedirect(request.getContextPath() + "/admin/affectations?success=updated");
        }
    }
}