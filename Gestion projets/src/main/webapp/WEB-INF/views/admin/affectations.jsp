<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des affectations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="module-page dashboard-page admin-page">
<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>
<div class="page-shell narrow-shell">
    <div class="module-container">
        <div class="module-header">
            <div>
                <h1>Gestion des affectations</h1>
                <p class="module-subtitle">
                    Gérez les affectations des employés aux projets et suivez les périodes d’intervention.
                </p>
            </div>

            <div class="header-actions">
                <a class="link-btn link-btn-light" href="${pageContext.request.contextPath}/admin/dashboard">
                    Retour au dashboard
                </a>
                <a class="link-btn link-btn-primary quick-add-btn" href="${pageContext.request.contextPath}/admin/affectations?action=form" title="Nouvelle affectation" aria-label="Nouvelle affectation">
                    +
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
                                    <th>ID</th>
                                    <th>Employé</th>
                                    <th>Projet</th>
                                    <th>Date début</th>
                                    <th>Date fin</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="a" items="${affectations}">
                                    <tr>
                                        <td>
                                            <span class="id-badge">#${a.id}</span>
                                        </td>
                                        <td>
                                            <span class="cell-title">${a.employe.prenom} ${a.employe.nom}</span>
                                        </td>
                                        <td>${a.projet.nom}</td>
                                        <td>${a.dateDebut}</td>
                                        <td>${a.dateFin}</td>
                                        <td>
                                            <div class="table-actions table-actions--icons">
                                                <a class="link-btn link-btn-primary link-btn-sm icon-action-btn"
                                                   href="${pageContext.request.contextPath}/admin/affectations?action=edit&id=${a.id}"
                                                   title="Modifier"
                                                   aria-label="Modifier">
                                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.9" stroke="currentColor">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="M16.862 3.487a1.875 1.875 0 0 1 2.651 2.651L8.25 17.4l-3.75.938.938-3.75L16.862 3.487Z" />
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 4.5l3.75 3.75" />
                                                    </svg>
                                                </a>
                                                <a class="link-btn link-btn-danger link-btn-sm icon-action-btn"
                                                   href="${pageContext.request.contextPath}/admin/affectations?action=delete&id=${a.id}"
                                                   onclick="return confirm('Voulez-vous vraiment supprimer cette affectation ?');"
                                                   title="Supprimer"
                                                   aria-label="Supprimer">
                                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.9" stroke="currentColor">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 7.5h12m-9.75 0V6A1.5 1.5 0 0 1 9.75 4.5h4.5A1.5 1.5 0 0 1 15.75 6v1.5M9 10.5v6m6-6v6M7.5 7.5l.6 10.2A1.5 1.5 0 0 0 9.6 19.2h4.8a1.5 1.5 0 0 0 1.5-1.5l.6-10.2" />
                                                    </svg>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="empty-state">
                        <h3>Aucune affectation trouvée</h3>
                        <p>Ajoutez une première affectation pour commencer.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>