<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Area - Administration des utilisateurs</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <%@ include file="/WEB-INF/views/Element/navbar.jsp" %>
        <header class="mb-5 pb-2 text-center">
            <h1 class="pt-3 display-4">Panel d'Administration</h1>
            <p class="lead">
                OMFG vous êtes Admin !!
            </p>
        </header>
        <div class="container">
            <h2>Edition du compte ${user.email}</h2>
            <hr>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                     ${errorMessage}
                 </div>
            </c:if>
            <form method="post">
                <div class="form-group">
                    <label for="inputEmail">Adresse Email</label>
                    <input name="email" type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="${user.email}" value="${user.email}">
                </div>
                <div class="form-group">
                    <label for="inputPassword">Mot de passe</label>
                    <input name="password" type="password" class="form-control" id="inputPassword" placeholder="${user.password}" value="${user.password}">
                </div>
                <c:if test="${username != user.email}">
                    <div class="form-check">
                        <label class="form-check-label">
                        <input name="banned" type="checkbox" class="form-check-input"<c:if test="${user.banned == true}"> checked</c:if>>
                            Bannir le compte
                        </label>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-success">Valider les modifications</button>
                <a href="/admin/users" class="btn btn-warning">Retour sans enregistrer</a>
            </form>
            <hr>
            <h3>Applets de ${user.email}</h3>
            <div class="card-columns">
                <c:forEach var="area" items="${areas}">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title text-center">${area.actionName} - ${area.reactionName}</h4>
                            <h6 class="card-subtitle mb-2 text-muted">Description</h6>
                            <p class="card-text">Arguments</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%@ include file="/WEB-INF/views/Element/footer.jsp" %>
        <%@ include file="/WEB-INF/views/Element/resources.jsp" %>
    </body>
</html>
