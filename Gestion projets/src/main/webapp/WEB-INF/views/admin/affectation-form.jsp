<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire affectation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="module-page dashboard-page admin-page">
<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>
<div class="page-shell form-shell">
    <div class="form-module-card">
        <div class="form-header">
            <h1 class="form-title">
                <c:choose>
                    <c:when test="${not empty affectation.id}">
                        Modifier une affectation
                    </c:when>
                    <c:otherwise>
                        Nouvelle affectation
                    </c:otherwise>
                </c:choose>
            </h1>
            <p class="module-subtitle">
                Sélectionnez un employé, un projet, puis définissez la période d’affectation.
            </p>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/affectations" class="modern-form">
            <input type="hidden" name="id" value="${affectation.id}">

            <div class="form-grid">
                <div class="form-group">
                    <label for="employeId">Employé</label>
                    <select name="employeId" id="employeId" required>
                        <c:forEach var="e" items="${employes}">
                            <option value="${e.id}"
                                ${affectation.employe != null && affectation.employe.id == e.id ? 'selected' : ''}>
                                ${e.prenom} ${e.nom}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="projetId">Projet</label>
                    <select name="projetId" id="projetId" required>
                        <c:forEach var="p" items="${projets}">
                            <option value="${p.id}"
                                ${affectation.projet != null && affectation.projet.id == p.id ? 'selected' : ''}>
                                ${p.nom}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="dateDebut">Date début</label>
                    <input type="date" id="dateDebut" name="dateDebut" value="${affectation.dateDebut}" required>
                </div>

                <div class="form-group">
                    <label for="dateFin">Date fin</label>
                    <input type="date" id="dateFin" name="dateFin" value="${affectation.dateFin}" required>
                </div>
            </div>

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/admin/affectations" class="link-btn link-btn-light">
                    Annuler
                </a>
                <button type="submit" class="btn-success">Enregistrer</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>