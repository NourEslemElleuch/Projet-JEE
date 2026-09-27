<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=20">
</head>
<body class="dashboard-page admin-page">

<%@ include file="/WEB-INF/views/includes/app-header.jspf" %>

<main class="page-main">
    <div class="page-shell">
        <div class="dashboard-container dashboard-panel">
            <section class="dashboard-hero">
                <div class="dashboard-hero-copy">
                    <h1>Dashboard Admin</h1>
                    <p class="dashboard-subtitle">
                        Gérez facilement les catégories, utilisateurs, projets et affectations
                        depuis une interface plus claire, moderne et rapide à utiliser.
                    </p>

                    <div class="hero-tags">
                        <span class="hero-tag">4 modules</span>
                        <span class="hero-tag">Administration</span>
                        <span class="hero-tag">Suivi centralisé</span>
                    </div>
                </div>

            </section>

            <ul class="dashboard-nav enhanced-nav">
                <li class="nav-card">
                    <a href="${pageContext.request.contextPath}/admin/categories">
                        <span class="card-badge">Organisation</span>
                        <span class="icon-wrap">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.8" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M9.568 3H5.25A2.25 2.25 0 0 0 3 5.25v4.318c0 .597.237 1.17.659 1.591l9.581 9.581c.699.699 1.78.872 2.607.33a18.095 18.095 0 0 0 5.223-5.223c.542-.827.369-1.908-.33-2.607L11.16 3.66A2.25 2.25 0 0 0 9.568 3Z" />
                                <path stroke-linecap="round" stroke-linejoin="round" d="M6 6h.008v.008H6V6Z" />
                            </svg>
                        </span>
                        <span class="card-title">Gestion des catégories</span>
                        <span class="card-desc">Créer, modifier et organiser les catégories de projets.</span>
                        <span class="card-link">Accéder</span>
                    </a>
                </li>

                <li class="nav-card">
                    <a href="${pageContext.request.contextPath}/admin/utilisateurs">
                        <span class="card-badge">Comptes</span>
                        <span class="icon-wrap">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.8" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M15 19.128a9.38 9.38 0 0 0 2.625.372 9.337 9.337 0 0 0 4.121-2.308M15 19.128v-3.86a2.25 2.25 0 0 1 2.25-2.25h3.5A2.25 2.25 0 0 1 23 15.25v3.86M15 19.128a9.37 9.37 0 0 1-2.625.372A9.337 9.337 0 0 1 8.25 17.25v-3.86a2.25 2.25 0 0 0-2.25-2.25h-3.5A2.25 2.25 0 0 0 0 15.25v3.86m0 0a9.37 9.37 0 0 0 2.625.372M8.25 17.25a9.37 9.37 0 0 1-2.625.372M3.75 19.128a9.37 9.37 0 0 1-2.625.372M10.5 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0Zm12.75 0a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0Z" />
                            </svg>
                        </span>
                        <span class="card-title">Gestion des utilisateurs</span>
                        <span class="card-desc">Administrer les comptes, rôles et accès des utilisateurs.</span>
                        <span class="card-link">Accéder</span>
                    </a>
                </li>

                <li class="nav-card">
                    <a href="${pageContext.request.contextPath}/admin/projets">
                        <span class="card-badge">Pilotage</span>
                        <span class="icon-wrap">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.8" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 21h16.5M4.5 3h15M5.25 3v18m13.5-18v18M9 6.75h6M9 11.25h6M9 15.75h6" />
                            </svg>
                        </span>
                        <span class="card-title">Gestion des projets</span>
                        <span class="card-desc">Suivre, mettre à jour et structurer les projets de l’application.</span>
                        <span class="card-link">Accéder</span>
                    </a>
                </li>

                <li class="nav-card">
                    <a href="${pageContext.request.contextPath}/admin/affectations">
                        <span class="card-badge">Ressources</span>
                        <span class="icon-wrap">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.8" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M9 12h3.75M9 15h3.75M9 18h3.75m3 .75H18a2.25 2.25 0 0 0 2.25-2.25V6.108c0-1.135-.845-2.098-1.976-2.192a48.424 48.424 0 0 0-1.123-.08m-5.801 0c-.065.21-.1.433-.1.664 0 .414.336.75.75.75h4.5a.75.75 0 0 0 .75-.75c0-.231-.035-.454-.1-.664M6.75 7.5h4.5v.75h-4.5v-.75Z" />
                            </svg>
                        </span>
                        <span class="card-title">Gestion des affectations</span>
                        <span class="card-desc">Affecter les ressources et suivre la répartition des tâches.</span>
                        <span class="card-link">Accéder</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</main>

</body>
</html>