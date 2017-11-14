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
                Administration des utilisateurs.
            </p>
        </header>
        <div class="container">
            <a href="/admin" class="btn btn-info mb-3"><i class="fa fa-arrow-left" aria-hidden="true"></i> Retour</a>
            <div class="row">
                <div class="col"><h2>Utilisateurs</h2></div>
                <div class="col text-right"><a href="/admin/add-user" class="btn btn-info"><i class="fa fa-plus" aria-hidden="true"></i> Ajouter</a></div>
            </div>
            <hr>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                     ${errorMessage}
                 </div>
            </c:if>
            <table class="table table-striped table-bordered table-hover text-center">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Email</th>
                        <th scope="col">Banni</th>
                        <th scope="col">Tools</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <th scope="row">${user.id}</th>
                            <td>${user.email}</td>
                            <td>
                                <c:if test="${user.banned == false}">
                                    Non
                                </c:if>
                                <c:if test="${user.banned == true}">
                                    Oui
                                </c:if>
                            </td>
                            <td>
                                <a href="/admin/edit-user/${user.id}"><i class="fa fa-wrench" aria-hidden="true"></i></a>
                                <c:if test="${username != user.email}">
                                    <a href="#" class="deleteModalButton1" data-toggle="modal" data-target="#modal" data-url="/admin/delete-user/" data-id="${user.id}" data-name="le compte ${user.email}"><i class="fa fa-times" aria-hidden="true"></i></a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
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
