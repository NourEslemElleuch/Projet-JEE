<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire catégorie</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="module-page dashboard-page admin-page">
<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>
<div class="page-shell form-shell">
    <div class="form-module-card">
        <div class="form-header">
            <h1 class="form-title">
                <c:choose>
                    <c:when test="${not empty categorie.id}">
                        Modifier une catégorie
                    </c:when>
                    <c:otherwise>
                        Ajouter une catégorie
                    </c:otherwise>
                </c:choose>
            </h1>
            <p class="module-subtitle">
                Renseignez les informations de la catégorie puis enregistrez.
            </p>
        </div>

        <c:if test="${not empty erreur}">
            <div class="alert erreur">${erreur}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/categories" method="post" class="modern-form">
            <input type="hidden" name="id" value="${categorie.id}">

            <div class="form-group">
                <label for="libelle">Libellé</label>
                <input
                    type="text"
                    id="libelle"
                    name="libelle"
                    value="${categorie.libelle}"
                    placeholder="Ex : Ingénieur"
                    required
                >
            </div>

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/admin/categories" class="link-btn link-btn-light">
                    Annuler
                </a>
                <button type="submit" class="btn-success">
                    Enregistrer
                </button>
            </div>
        </form>
    </div>
</div>

</body>
</html>