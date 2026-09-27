<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire projet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="module-page dashboard-page admin-page">
<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>
<div class="page-shell form-shell">
    <div class="form-module-card">
        <div class="form-header">
            <h1 class="form-title">
                ${not empty projet.id ? 'Modifier un projet' : 'Ajouter un projet'}
            </h1>
            <p class="module-subtitle">
                Renseignez le nom, la description et la période du projet.
            </p>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/projets" class="modern-form">
            <input type="hidden" name="id" value="${projet.id}">

            <div class="form-group">
                <label for="nom">Nom du projet</label>
                <input type="text" id="nom" name="nom" value="${projet.nom}" placeholder="Ex : Refonte portail RH" required>
            </div>

            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" rows="5" placeholder="Décrivez brièvement le projet...">${projet.description}</textarea>
            </div>

            <div class="form-grid">
                <div class="form-group">
                    <label for="dateDebut">Date début</label>
                    <input type="date" id="dateDebut" name="dateDebut" value="${projet.dateDebut}" required>
                </div>

                <div class="form-group">
                    <label for="dateFin">Date fin</label>
                    <input type="date" id="dateFin" name="dateFin" value="${projet.dateFin}" required>
                </div>
            </div>

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/admin/projets" class="link-btn link-btn-light">
                    Annuler
                </a>
                <button type="submit" class="btn-success">Enregistrer</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>