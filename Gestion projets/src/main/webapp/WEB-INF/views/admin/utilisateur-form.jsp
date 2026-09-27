<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire utilisateur</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="module-page dashboard-page admin-page">
<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>
<div class="page-shell form-shell">
    <div class="form-module-card">
        <div class="form-header">
            <h1 class="form-title">
                ${not empty utilisateur.id ? 'Modifier un utilisateur' : 'Ajouter un utilisateur'}
            </h1>
            <p class="module-subtitle">
                Renseignez les informations du compte, le rôle et la catégorie.
            </p>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/utilisateurs" class="modern-form">
            <input type="hidden" name="id" value="${utilisateur.id}">

            <div class="form-grid">
                <div class="form-group">
                    <label for="nom">Nom</label>
                    <input type="text" id="nom" name="nom" value="${utilisateur.nom}" placeholder="Nom" required>
                </div>

                <div class="form-group">
                    <label for="prenom">Prénom</label>
                    <input type="text" id="prenom" name="prenom" value="${utilisateur.prenom}" placeholder="Prénom" required>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${utilisateur.email}" placeholder="email@exemple.com" required>
                </div>

                <div class="form-group">
                    <label for="motDePasse">Mot de passe</label>
                    <input type="text" id="motDePasse" name="motDePasse" value="" placeholder="Laisser vide pour ne pas modifier">
                </div>

                <div class="form-group">
                    <label for="role">Rôle</label>
                    <select name="role" id="role" required>
                        <option value="ADMIN" ${utilisateur.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                        <option value="EMPLOYE" ${utilisateur.role == 'EMPLOYE' ? 'selected' : ''}>EMPLOYE</option>
                    </select>
                </div>

                <div class="form-group form-group-full">
                    <label for="categorieId">Catégorie</label>
                    <select name="categorieId" id="categorieId">
                        <option value="">-- choisir --</option>
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.id}"
                                ${utilisateur.categorie != null && utilisateur.categorie.id == c.id ? 'selected' : ''}>
                                ${c.libelle}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/admin/utilisateurs" class="link-btn link-btn-light">
                    Annuler
                </a>
                <button type="submit" class="btn-success">Enregistrer</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>