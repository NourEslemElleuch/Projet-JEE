package com.projet.gestionprojets.controller;

import com.projet.gestionprojets.dao.AffectationDAO;
import com.projet.gestionprojets.dao.ProjetDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/employe/employes-par-projet")
public class EmployesParProjetServlet extends HttpServlet {

    private final AffectationDAO affectationDAO = new AffectationDAO();
    private final ProjetDAO projetDAO = new ProjetDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || !"EMPLOYE".equals(String.valueOf(session.getAttribute("role")))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Long projetId = Long.parseLong(request.getParameter("id"));
        request.setAttribute("projet", projetDAO.findById(projetId));
        request.setAttribute("affectations", affectationDAO.findByProjetId(projetId));
        request.getRequestDispatcher("/WEB-INF/views/employe/employes-par-projet.jsp").forward(request, response);
    }
}