package com.projet.gestionprojets.controller;

import com.projet.gestionprojets.dao.ProjetDAO;
import com.projet.gestionprojets.entity.Projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/admin/projets")
public class ProjetServlet extends HttpServlet {

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
            request.setAttribute("projets", projetDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/views/admin/projets.jsp").forward(request, response);
            return;
        }

        if ("form".equals(action)) {
            request.setAttribute("projet", new Projet());
            request.getRequestDispatcher("/WEB-INF/views/admin/projet-form.jsp").forward(request, response);
            return;
        }

        if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("projet", projetDAO.findById(id));
            request.getRequestDispatcher("/WEB-INF/views/admin/projet-form.jsp").forward(request, response);
            return;
        }

        if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            projetDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/admin/projets?success=deleted");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idParam = request.getParameter("id");
        LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
        LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));

        if (dateFin.isBefore(dateDebut)) {
            request.setAttribute("erreur", "La date de fin doit être après la date de début.");
            request.getRequestDispatcher("/WEB-INF/views/admin/projet-form.jsp").forward(request, response);
            return;
        }

        Projet projet = (idParam == null || idParam.isBlank())
                ? new Projet()
                : projetDAO.findById(Long.parseLong(idParam));

        projet.setNom(request.getParameter("nom"));
        projet.setDescription(request.getParameter("description"));
        projet.setDateDebut(dateDebut);
        projet.setDateFin(dateFin);

        if (idParam == null || idParam.isBlank()) {
            projetDAO.save(projet);
            response.sendRedirect(request.getContextPath() + "/admin/projets?success=added");
        } else {
            projetDAO.update(projet);
            response.sendRedirect(request.getContextPath() + "/admin/projets?success=updated");
        }
    }
}