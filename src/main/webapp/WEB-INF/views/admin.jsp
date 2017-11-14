<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Area - Administration</title>
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
        <div class="container text-center">
            <p class="lead">
                Bienvenue sur votre Panel d'Administration.<br>
                A partir de cette page, vous pouvez effectuer des modifications sur les comptes utilisateur et sur les modules.
            </p>
            <a href="/admin/users" class="btn btn-info">Utilisateurs</a>
            <a href="/admin/modules" class="btn btn-info">Modules</a>
            <a href="/admin/newsletter" class="btn btn-info">Newsletter</a>
            <br>
            <img src="//wiki.gadz.org/uploads/Accueil/ADMIN.gif" alt="Admin" class="my-3 img-thumbnail">
        </div>
        <%@ include file="/WEB-INF/views/Element/footer.jsp" %>
        <%@ include file="/WEB-INF/views/Element/resources.jsp" %>
    </body>
</html>
