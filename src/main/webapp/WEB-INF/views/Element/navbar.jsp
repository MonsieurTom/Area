<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Area</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <c:if test="${username == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/signup">Inscription</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Connexion</a>
                </li>
            </c:if>
            <c:if test="${username != null}">
                <span class="navbar-text">
                    Connecté en tant que ${username}
                </span>
                <li class="nav-item">
                    <a class="nav-link" href="/panel">Panel</a>
                </li>
                <c:if test="${username == 'admin@admin.admin'}">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin">Administration</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Deconnexion</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
