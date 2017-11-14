<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Area - Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <%@ include file="/WEB-INF/views/Element/navbar.jsp" %>
        <header class="mb-5 pb-2 text-center">
            <h1 class="pt-3 display-4">Connexion</h1>
        </header>
        <div class="container">
            <p class="lead text-center">
                Vous n'avez pas encore de compte ? <a href="/signup">Inscrivez-vous</a>.
            </p>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                     ${errorMessage}
                 </div>
            </c:if>
            <form method="post">
                <div class="form-group">
                    <label for="inputEmail">Adresse Email</label>
                    <input name="email" type="email" class="form-control" id="inputEmail" placeholder="Adresse email">
                </div>
                <div class="form-group">
                    <label for="inputPassword">Mot de passe</label>
                    <input name="password" type="password" class="form-control" id="inputPassword" placeholder="Mot de passe">
                </div>
                <button type="submit" class="btn btn-primary">Connexion</button>
            </form>
        </div>
        <%@ include file="/WEB-INF/views/Element/footer.jsp" %>
        <%@ include file="/WEB-INF/views/Element/resources.jsp" %>
    </body>
</html>
