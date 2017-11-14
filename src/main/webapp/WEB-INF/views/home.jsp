<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Area - Home</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <%@ include file="/WEB-INF/views/Element/navbar.jsp" %>
        <header class="mb-5 pb-2 text-center">
            <h1 class="pt-3 display-4">Area</h1>
            <p class="lead">
                Le projet Area est un site permettant de créer des actions automatisées entre vos différents comptes sur des sites tiers.
            </p>
            <a href="/panel" class="btn btn-outline mb-3">Commencer</a>
        </header>
        <div class="container">
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                     ${errorMessage}
                 </div>
            </c:if>
            <h2 class="text-center">Exemples de Cards</h2>
            <hr>
            <div class="card-columns">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">Heure - Email</h4>
                        <h6 class="card-subtitle mb-2 text-muted">Le <kbd>01/01/{year} à 12:00</kbd>, envoyer un email à <kbd>email@example.com</kbd>.</h6>
                        <p class="card-text"><em>&#147; Bonne année {year} ! &#148;</em></p>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">Twitter - Twitter</h4>
                        <h6 class="card-subtitle mb-2 text-muted">Quand un Tweet contient le hashtag <kbd>#voyage</kbd>, retweeter le post.</h6>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">Facebook - Discord</h4>
                        <h6 class="card-subtitle mb-2 text-muted">Quand une invitation d'amitié Facebook est reçue, me prévenir sur Discord.</h6>
                    </div>
                </div>
            </div>
            <hr>
            <div class="media py-2">
                <img src="//www.overshootday.org/wp/wp-content/uploads/2017/08/globe_thumb.jpg" alt="Earth" class="mr-3 img-thumbnail">
                <div class="media-body">
                    <blockquote class="blockquote">
                      <p class="mb-0">Encore un site révolutionnaire créé par de véritables genies, je n'ai qu'à bien me tenir.</p>
                      <footer class="blockquote-footer">La Terre de <cite title="Source Title">La Vie Inc</cite></footer>
                    </blockquote>
                </div>
            </div>
            <div class="media py-2">
                <img src="//www.mes-etudes.com/uploads/media/school_logo/0001/02/thumb_1636_school_logo_retina.png" alt="Epitech" class="mr-3 img-thumbnail">
                <div class="media-body">
                    <blockquote class="blockquote">
                      <p class="mb-0">Un site responsive en une semaine seulement. Notre équipe de développeurs de l'intra n'en revient pas.</p>
                      <footer class="blockquote-footer">Le Bocal <cite title="Source Title">Epitech</cite></footer>
                    </blockquote>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/views/Element/footer.jsp" %>
        <%@ include file="/WEB-INF/views/Element/resources.jsp" %>
    </body>
</html>
