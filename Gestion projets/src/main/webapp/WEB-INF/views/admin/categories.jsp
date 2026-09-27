<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des catégories</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="module-page dashboard-page admin-page">
<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>
<div class="page-shell narrow-shell">
    <div class="module-container">
        <div class="module-header">
            <div>
                <h1>Gestion des catégories</h1>
                <p class="module-subtitle">
                    Créez, modifiez et supprimez les catégories de votre application.
                </p>
            </div>

            <div class="header-actions">
                <a class="link-btn link-btn-light" href="${pageContext.request.contextPath}/admin/dashboard">
                    Retour au dashboard
                </a>
                <a class="link-btn link-btn-primary quick-add-btn" href="${pageContext.request.contextPath}/admin/categories?action=form" title="Nouvelle catégorie" aria-label="Nouvelle catégorie">
                    +
                </a>
            </div>
        </div>

        <c:if test="${param.success eq 'added'}">
            <div class="alert success">Catégorie ajoutée avec succès.</div>
        </c:if>

        <c:if test="${param.success eq 'updated'}">
            <div class="alert success">Catégorie modifiée avec succès.</div>
        </c:if>

        <c:if test="${param.success eq 'deleted'}">
            <div class="alert success">Catégorie supprimée avec succès.</div>
        </c:if>

        <c:if test="${param.error eq 'notfound'}">
            <div class="alert erreur">Catégorie introuvable.</div>
        </c:if>

        <c:if test="${param.error eq 'delete'}">
            <div class="alert erreur">Impossible de supprimer cette catégorie.</div>
        </c:if>

        <div class="table-card">
            <c:choose>
                <c:when test="${not empty categories}">
                    <div class="table-scroll">
                        <table class="modern-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Libellé</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="c" items="${categories}">
                                    <tr>
                                        <td>
                                            <span class="id-badge">#${c.id}</span>
                                        </td>
                                        <td>
                                            <span class="cell-title">${c.libelle}</span>
                                        </td>
                                        <td>
                                            <div class="table-actions table-actions--icons">
                                                <a class="link-btn link-btn-primary link-btn-sm icon-action-btn"
                                                   href="${pageContext.request.contextPath}/admin/categories?action=edit&id=${c.id}"
                                                   title="Modifier"
                                                   aria-label="Modifier">
                                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.9" stroke="currentColor">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="M16.862 3.487a1.875 1.875 0 0 1 2.651 2.651L8.25 17.4l-3.75.938.938-3.75L16.862 3.487Z" />
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 4.5l3.75 3.75" />
                                                    </svg>
                                                </a>

                                                <a class="link-btn link-btn-danger link-btn-sm icon-action-btn"
                                                   href="${pageContext.request.contextPath}/admin/categories?action=delete&id=${c.id}"
                                                   onclick="return confirm('Voulez-vous vraiment supprimer cette catégorie ?');"
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
                        <h3>Aucune catégorie trouvée</h3>
                        <p>Commencez par ajouter votre première catégorie.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>