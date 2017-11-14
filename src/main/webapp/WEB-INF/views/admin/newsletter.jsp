<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Area - Newsletter</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <%@ include file="/WEB-INF/views/Element/navbar.jsp" %>
        <header class="mb-5 pb-2 text-center">
            <h1 class="pt-3 display-4">Newsletter</h1>
        </header>
        <div class="container">
            <a href="/admin" class="btn btn-info mb-3"><i class="fa fa-arrow-left" aria-hidden="true"></i> Retour</a>
            <h2>Envoi d'un email de Newsletter</h2>
            <hr>
            <p class="lead">
                Via ce formulaire, vous pouvez envoyer un email de newsletter à tous vos utilisateurs.<br>
                Prenez soin de ne pas en abuser pour éviter de finir en spam ou de perdre des membres.
            </p>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                     ${errorMessage}
                 </div>
            </c:if>
            <c:if test="${successMessage != null}">
                <div class="alert alert-success" role="alert">
                     ${successMessage}
                 </div>
            </c:if>
            <form method="post">
                <div class="form-group">
                    <label for="inputObject">Objet</label>
                    <input name="object" type="text" class="form-control" id="inputObject">
                </div>
                <div class="form-group">
                    <label for="inputMessage">Message</label>
                    <textarea name="message" class="form-control" id="inputMessage" rows="10"></textarea>
                </div>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal">Envoyer</button>
                <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalLabel">Confirmation d'envoi</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Attention, l'envoi d'une newsletter à tous vos utilisateurs n'est pas une action à prendre à la légère. Pensez à vous relire avant de valider l'envoi du message.<br>
                                Après confirmation d'envoi, il est impossible de revenir en arrière.
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">Confirmer l'envoi du message</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <%@ include file="/WEB-INF/views/Element/footer.jsp" %>
        <%@ include file="/WEB-INF/views/Element/resources.jsp" %>
    </body>
</html>
