package com.projet.gestionprojets.controller;

import com.projet.gestionprojets.dao.CategorieDAO;
import com.projet.gestionprojets.entity.Categorie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/categories")
public class CategorieServlet extends HttpServlet {

    private final CategorieDAO categorieDAO = new CategorieDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("role")))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        if (action == null || action.isBlank()) {
            listerCategories(request, response);
            return;
        }

        switch (action) {
            case "form":
                afficherFormulaireAjout(request, response);
                break;
            case "edit":
                afficherFormulaireModification(request, response);
                break;
            case "delete":
                supprimerCategorie(request, response);
                break;
            default:
                listerCategories(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("role")))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String libelle = request.getParameter("libelle");

        if (libelle == null || libelle.isBlank()) {
            request.setAttribute("erreur", "Le libellé est obligatoire.");
            request.setAttribute("categorie", new Categorie());
            request.getRequestDispatcher("/WEB-INF/views/admin/categorie-form.jsp").forward(request, response);
            return;
        }

        try {
            if (idParam == null || idParam.isBlank()) {
                Categorie categorie = new Categorie();
                categorie.setLibelle(libelle.trim());
                categorieDAO.save(categorie);

                response.sendRedirect(request.getContextPath() + "/admin/categories?success=added");
            } else {
                Long id = Long.parseLong(idParam);
                Categorie categorie = categorieDAO.findById(id);

                if (categorie == null) {
                    response.sendRedirect(request.getContextPath() + "/admin/categories?error=notfound");
                    return;
                }

                categorie.setLibelle(libelle.trim());
                categorieDAO.update(categorie);

                response.sendRedirect(request.getContextPath() + "/admin/categories?success=updated");
            }
        } catch (Exception e) {
            request.setAttribute("erreur", "Une erreur est survenue lors de l'enregistrement de la catégorie.");
            request.setAttribute("categorie", new Categorie(libelle));
            request.getRequestDispatcher("/WEB-INF/views/admin/categorie-form.jsp").forward(request, response);
        }
    }

    private void listerCategories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Categorie> categories = categorieDAO.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/views/admin/categories.jsp").forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("categorie", new Categorie());
        request.getRequestDispatcher("/WEB-INF/views/admin/categorie-form.jsp").forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/admin/categories?error=missingid");
            return;
        }

        Long id = Long.parseLong(idParam);
        Categorie categorie = categorieDAO.findById(id);

        if (categorie == null) {
            response.sendRedirect(request.getContextPath() + "/admin/categories?error=notfound");
            return;
        }

        request.setAttribute("categorie", categorie);
        request.getRequestDispatcher("/WEB-INF/views/admin/categorie-form.jsp").forward(request, response);
    }

    private void supprimerCategorie(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/admin/categories?error=missingid");
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            categorieDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/admin/categories?success=deleted");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/categories?error=delete");
        }
    }
}