<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Area - Panel</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <%@ include file="/WEB-INF/views/Element/navbar.jsp" %>
        <header class="mb-5 pb-2 text-center">
            <h1 class="pt-3 display-4">Applet</h1>
        </header>
        <div class="container">
            <a href="/panel/modules" class="btn btn-info mb-3"><i class="fa fa-arrow-left" aria-hidden="true"></i> Retour</a>
            <h2>Modification d'un Applet</h2>
            <hr>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                     ${errorMessage}
                 </div>
            </c:if>
            <form method="post">
                <div id="actionForm" class="form-group">
                    <label for="selectAction">Action</label>
                    <select name="actionName" class="form-control" id="selectAction">
                        <c:forEach var="action" items="${actions}">
                            <option value="${action.name}" data-nbArgs="${action.arguments.size()}"
                                <c:set var="count" value="${0}" />
                                <c:forEach var="argument" items="${action.arguments}">
                                    data-arg<c:out value="${count}" />Name="${argument.name}"
                                    data-arg<c:out value="${count}" />Placeholder="${argument.placeholder}"
                                    <c:set var="count" value="${count + 1}" />
                                </c:forEach>
                                >
                                ${action.name}
                            </option>
                        </c:forEach>
                    </select>
                    <div id="actionArguments" class="mt-4"></div>
                </div>
                <div id="reactionForm" class="form-group">
                    <label for="selectReaction">Reaction</label>
                    <select name="reactionName" class="form-control" id="selectAction">
                        <c:forEach var="reaction" items="${reactions}">
                            <option value="${reaction.name}" data-nbArgs="${reaction.arguments.size()}"
                                <c:set var="count" value="${0}" />
                                <c:forEach var="argument" items="${reaction.arguments}">
                                    data-arg<c:out value="${count}" />Name="${argument.name}"
                                    data-arg<c:out value="${count}" />Placeholder="${argument.placeholder}"
                                    <c:set var="count" value="${count + 1}" />
                                </c:forEach>
                                >
                                ${reaction.name}
                            </option>
                        </c:forEach>
                    </select>
                    <div id="reactionArguments" class="mt-4"></div>
                </div>
                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <a href="/panel/modules" class="btn btn-warning">Retour sans enregistrer</a>
            </form>
        </div>
        <%@ include file="/WEB-INF/views/Element/footer.jsp" %>
        <%@ include file="/WEB-INF/views/Element/resources.jsp" %>
    </body>
</html>
