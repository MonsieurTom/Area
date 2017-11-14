<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Area - Modules</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <%@ include file="/WEB-INF/views/Element/navbar.jsp" %>
        <header class="mb-5 pb-2 text-center">
            <h1 class="pt-3 display-4">Panel</h1>
        </header>
        <div class="container">
            <a href="/panel" class="btn btn-info mb-3"><i class="fa fa-arrow-left" aria-hidden="true"></i> Retour</a>
            <a href="/panel/add-applet" class="btn btn-primary mb-3"><i class="fa fa-plus" aria-hidden="true"></i> Ajouter</a>
            <h2 class="mt-3">Modules Action</h2>
            <hr>
            <c:if test="${empty actions}">
                <p class="lead text-center">Aucun module Action trouvé.</p>
            </c:if>
            <div class="card-columns">
                <c:forEach var="action" items="${actions}">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title text-center">${action.name}</h4>
                            <ul class="list-triangle">
                                <c:forEach var="argument" items="${action.arguments}">
                                    <c:if test="${argument.name != 'invisible'}">
                                        <li>${argument.name}</li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                            <a href="/panel/add-applet?action=${action}" class="card-link">Activer</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <h2 class="mt-3">Modules Réaction</h2>
            <hr>
            <c:if test="${empty reactions}">
                <p class="lead text-center">Aucun module Réaction trouvé.</p>
            </c:if>
            <div class="card-columns">
                <c:forEach var="reaction" items="${reactions}">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title text-center">${reaction.name}</h4>
                            <ul class="list-triangle">
                                <c:forEach var="argument" items="${reaction.arguments}">
                                    <c:if test="${argument.name != 'invisible'}">
                                        <li>${argument.name}</li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                            <a href="/panel/add-applet?action=${action}" class="card-link">Activer</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%@ include file="/WEB-INF/views/Element/footer.jsp" %>
        <%@ include file="/WEB-INF/views/Element/resources.jsp" %>
    </body>
</html>
