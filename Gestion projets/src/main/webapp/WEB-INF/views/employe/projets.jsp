<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mes projets</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="module-page dashboard-page admin-page">
<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>
<div class="page-shell narrow-shell">
    <div class="module-container">
        <div class="module-header">
            <div>
                <h1>Mes projets</h1>
                <p class="module-subtitle">
                    Bienvenue <strong>${sessionScope.userNomComplet}</strong>. Retrouvez ici les projets auxquels vous êtes affecté.
                </p>
            </div>
        </div>

        <div class="table-card">
            <c:choose>
                <c:when test="${not empty affectations}">
                    <div class="table-scroll">
                        <table class="modern-table">
                            <thead>
                                <tr>
                                    <th>Projet</th>
                                    <th>Description</th>
                                    <th>Date début</th>
                                    <th>Date fin</th>
                                    <th>&Eacute;quipe</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="a" items="${affectations}">
                                    <tr>
                                        <td>
                                            <span class="cell-title">${a.projet.nom}</span>
                                        </td>
                                        <td>
                                            <span class="description-cell">
                                                ${empty a.projet.description ? 'Aucune description' : a.projet.description}
                                            </span>
                                        </td>
                                        <td>${a.dateDebut}</td>
                                        <td>${a.dateFin}</td>
                                        <td>
                                            <a class="link-btn link-btn-primary link-btn-sm"
                                               href="${pageContext.request.contextPath}/employe/employes-par-projet?id=${a.projet.id}">
                                                Liste employés
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="empty-state">
                        <h3>Aucun projet trouvé</h3>
                        <p>Vous n’avez actuellement aucune affectation enregistrée.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>