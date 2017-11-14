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
            <h1 class="pt-3 display-4">Panel</h1>
        </header>
        <div class="container">
            <div class="row">
                <div class="col"><h2>Vos Applets</h2></div>
                <div class="col text-right"><a href="/panel/modules" class="btn btn-info"><i class="fa fa-plus" aria-hidden="true"></i> Ajouter</a></div>
            </div>
            <hr>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                     ${errorMessage}
                 </div>
            </c:if>
            <div class="card-columns">
                <c:forEach var="area" items="${areas}">
                    <div class="card">
                        <div class="card-body">
                            <a href="#" class="deleteModalButton1" data-toggle="modal" data-target="#modal" data-url="/panel/delete-applet/" data-id="${area.id}" data-name="l'applet ${area.actionName}-${area.reactionName}"><i class="fa fa-times" aria-hidden="true"></i></a>
                            <h4 class="card-title text-center">${area.actionName} - ${area.reactionName}</h4>
                            <h6 class="card-subtitle mb-2 text-muted">Description</h6>
                            <p class="card-text">Arguments</p>
                            <a href="/panel/edit-applet/${area.id}"><i class="fa fa-wrench" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modalLabel">Confirmation de suppression</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <a id="deleteModalButton2" href="#" class="btn btn-primary">Confirmer la suppression</a>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/views/Element/footer.jsp" %>
        <%@ include file="/WEB-INF/views/Element/resources.jsp" %>
    </body>
</html>
