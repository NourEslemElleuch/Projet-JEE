<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Acces</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=10">
</head>
<body class="auth-page">

<div class="auth-wrapper">
    <div class="auth-layout single-pane">
        <div class="auth-brand">
            <div>
                <span class="eyebrow">Gestion des projets</span>
                <h1>Bienvenue</h1>
                <p class="brand-text">
                    Connectez-vous pour accéder à votre espace d’administration
                    et gérer efficacement les utilisateurs, projets et affectations.
                </p>
            </div>

            <c:if test="${not empty erreur}">
                <div class="alert erreur">${erreur}</div>
            </c:if>

            <c:if test="${param.logout eq '1'}">
                <div class="alert info">Vous êtes déconnecté.</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login" method="post" class="auth-form">
                <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" id="email" name="email" placeholder="Votre email" required autocomplete="email">
                </div>

                <div class="form-group">
                    <label for="motDePasse">Mot de passe</label>
                    <input type="password" id="motDePasse" name="motDePasse" placeholder="Votre mot de passe" required autocomplete="current-password">
                </div>

                <button type="submit">Se connecter</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>