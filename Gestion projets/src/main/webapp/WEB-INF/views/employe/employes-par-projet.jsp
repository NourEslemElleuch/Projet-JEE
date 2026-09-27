<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Employés du projet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="module-page dashboard-page admin-page">
<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>
<div class="page-shell narrow-shell">
    <div class="module-container">
        <div class="module-header">
            <div>
                <h1>Employés du projet</h1>
                <p class="module-subtitle">
                    Projet : <strong>${projet.nom}</strong>
                </p>
            </div>

            <div class="header-actions">
                <a class="link-btn link-btn-light" href="${pageContext.request.contextPath}/employe/projets">
                    Retour à mes projets
                </a>
            </div>
        </div>

        <div class="table-card">
            <c:choose>
                <c:when test="${not empty affectations}">
                    <div class="table-scroll">
                        <table class="modern-table">
                            <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Catégorie</th>
                                    <th>Date début</th>
                                    <th>Date fin</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="a" items="${affectations}">
                                    <tr>
                                        <td>${a.employe.nom}</td>
                                        <td>
                                            <span class="cell-title">${a.employe.prenom}</span>
                                        </td>
                                        <td>
                                            ${a.employe.categorie != null ? a.employe.categorie.libelle : '-'}
                                        </td>
                                        <td>${a.dateDebut}</td>
                                        <td>${a.dateFin}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="empty-state">
                        <h3>Aucun employé trouvé</h3>
                        <p>Aucune affectation n’est liée à ce projet pour le moment.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>