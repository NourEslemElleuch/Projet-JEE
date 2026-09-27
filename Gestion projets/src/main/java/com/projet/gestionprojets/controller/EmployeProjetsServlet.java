package com.projet.gestionprojets.controller;

import com.projet.gestionprojets.dao.AffectationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/employe/projets")
public class EmployeProjetsServlet extends HttpServlet {

    private final AffectationDAO affectationDAO = new AffectationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || !"EMPLOYE".equals(String.valueOf(session.getAttribute("role")))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Long userId = (Long) session.getAttribute("userId");
        request.setAttribute("affectations", affectationDAO.findByEmployeId(userId));
        request.getRequestDispatcher("/WEB-INF/views/employe/projets.jsp").forward(request, response);
    }
}