<!-- PAGE D'UN OUVRAGE-->

<!-- Tags -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Club BD</title>

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


        <!-- STYLES -->
        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/index.css">
        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/base.css">
        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/ouvrage.css">

        <!-- SCRIPTS -->
        <script src="Scripts/navigation.js"></script>
        <script src="Scripts/index.js"></script>
        <script src="Scripts/deconnecter.js"></script>        
        <script src="Scripts/index_membre.js"></script>      
        <script src="Scripts/modif_infosperso.js"></script>  
        <script src="Scripts/event_listener.js"></script>
        <script src="Scripts/index_membre.js"></script>
        <script src="Scripts/ouvrage.js"></script>

    </head>
    <body onload="loadOuvrageUser(<c:out value="${idStatut}"/>,'<c:out value="${dispo}"/>', '<c:out value="${emprunt}"/>');disponibility('<c:out value="${dispo}"/>', '<c:out value="${emprunt}"/>');">

    <!-- CONTENU PRINCIPAL -->

    <div class="container">
        <!-- CHARGEMENT DES DONNEES LIEES A L'UTILISATEUR CONNECTE -->
        <div style="display:none;">
            <!-- Données personnelles-->
            <input type="hidden" id="idMembre" value="<c:out value="${id}"/>"/>
            <input type="hidden" id="nom" value="<c:out value="${nom}"/>"/> 
            <input type="hidden" id="prenom" value="<c:out value="${prenom}"/>"/> 
            <input type="hidden" id="email" value="<c:out value="${email}"/>"/> 
            <input type="hidden" id="idco" value="<c:out value="${idco}"/>"/>
            <input type="hidden" id="idStatut" value="<c:out value="${idStatut}"/>"/>
            <input type="hidden" id="iddoc" value="<c:out value="${iddoc}"/>"/>
        </div>
                
        <div class="left">
            <a class="back" href="#" onclick="goHomeMember()">Retour</a>
        </div>
                
        <!-- Bloc des récapitulatifs emprunts et réservations-->
        <div id ="ouvrage_content" class="bloc_home">
            <h3>Informations sur l'ouvrage</h3>
            <br/>
            <div id="display_ouvrage" class="bloc_home">
                <div class="row">
                    <div class="col-sm-4">
                        <div id="image_ouvrage">
                            <img class="img_border" src=<c:out value="${image}"/> alt="L'image n'a pas pu être affichée"/>
                        </div>
                    </div>
                    <div class="col-sm-8" id="infos_ouvrage">                          
                            <h2><c:out value="${titre}"/></h2>
                            <br/>
                            <div id="txt_infos">
                                <p><strong>Créateur(s) : </strong>
                                <c:forEach var="createur" items="${createurs}" varStatus="loop">
                                    <c:out value="${createur['nom']}"/> <c:out value="${createur['prenom']}"/> (<c:out value="${createur['poste']}"/>)<c:choose><c:when test="${!loop.last}">,</c:when><c:otherwise>.</c:otherwise></c:choose>
                                </c:forEach>
                                </p>
                                <p><strong>Série :</strong> <c:out value="${serie}"/></p>
                                <p><strong>N° :</strong> <c:out value="${numero}"/></p>
                                <p><strong>Cote :</strong> <c:out value="${cote}"/></p>
                                <p><strong>Genre(s) : </strong>
                                <c:forEach var="genre" items="${genres}" varStatus="loop">
                                     <c:out value="${genre}"/><c:if test="${!loop.last}">,</c:if>
                                </c:forEach>
                                </p>
                                <p><strong>Description :</strong> <c:out value="${description}"/></p>
                                <p><strong>Etat :</strong> <c:out value="${etat}"/></p>
                            </div>
                            <br/>
                            <div id="dispo"></div>
                            
                            <br/>
                            <center>
                                <button id="reserver_button" onclick="reserver('<c:out value="${idco}"/>', '<c:out value="${iddoc}"/>')" style="color:green; display:none;">Réserver</button>
                                <button id="valider_button" onclick="valider(<c:out value="${idStatut}"/>)" style="color:blue; display:none;">Valider</button>
                                <button id="retourner_button" onclick="retourner(<c:out value="${idStatut}"/>, '<c:out value="${idemp}"/>')" style="color:red; display:none;">Retourner</button>
                            </center>    
                    </div>
                </div>
            </div>
        </div>
    </div>
                            
    <!--POP-UP: validation de l'emprunt-->
    <div class="modal fade" id="valider-form" role="dialog">
        <div class="modal-dialog modal-sm" id="pop_form">
            <div class="modal-content modal_form">
                <!-- Croix de fermeture -->
                <button class="close" data-dismiss="modal" style="margin-right: 10px;">&times;</button>
                <!-- Titre -->
                    <p id= "subtitle" style="margin-top: 40px; text-align:center;">Veuillez indiquer la date de retour prévu :</p><br/> 
                    <p style="text-align:center;">Jour : <input type="text" id="jourInput" style="width:100px; margin-left:25px" value=""/></p>
                    <p style="text-align:center;">Mois : <input type="text" id="moisInput" style="width:100px; margin-left:20px" value=""/></p>
                    <p style="text-align:center;">Année : <input type="text" id="anneeInput" style="width:100px; margin-left:10px" value=""/></p>
                <center>       
                    <!--Confirmer la date et l'emprunt-->
                    <button id ="validation_button" type="button" onclick="confirmer(<c:out value="${idStatut}"/>,'<c:out value="${idco}"/>', '<c:out value="${idemp}"/>')" style="margin-top: 20px; color:blue; margin-bottom:20px;">Confirmer</button>
                </center>
                <br/>
            </div>
        </div>
    </div>                            
    
    <!--POP-UP: utilisateur non connecté ayant appuyé sur réserver-->
    <div class="modal fade" id="connect-form" role="dialog">
        <div class="modal-dialog modal-sm" id="pop_form">
            <div class="modal-content modal_form">
                <!-- Croix de fermeture -->
                <button class="close" data-dismiss="modal" style="margin-right: 10px;">&times;</button>
                <!-- Titre -->
                <p id= "subtitle" style="margin-top: 40px; text-align: center;">Vous n'êtes pas connecté</p> 
                <!-- Zone pour les messages d'erreur -->
                <p id="modification_error" class="error_message"></p>

                <center>
                    <!-- bouton renvoyant sur index_membre pour se connecter-->
                    <button id ="validation_button" type="button" onclick="goHome()" style="margin-top: 20px; color:green; margin-bottom:20px;margin-right:50px;">Se connecter</button>
                    <!-- bouton renvoyant vers la fenêtre d'inscription-->
                    <button id ="validation_button" type="button" onclick="getInscription()" style="margin-top: 20px; color:blue; margin-bottom:20px;">S'inscrire</button>
                </center>
                <br/>
            </div>
        </div>
    </div>

</body>
</html>