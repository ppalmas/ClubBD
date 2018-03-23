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
        <title>${titre}</title>

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
    <body onload="loadOuvrageUser(<c:out value="${idStatut}"/>);disponibility('<c:out value="${dispo}"/>')">

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
                    <div class="column">
                        <div id="image_ouvrage">
                            <!--<img src="theprohecyistrue.jpg" alt="L'image n'a pas pu être affichée"/>-->
                            <img src=<c:out value="${image}"/> alt="L'image n'a pas pu être affichée"/>
                        </div>
                    </div>
                    <div class="column">
                        
                            <!--<h2>Jean va à la piscine</h2>
                            <br/>
                            <p><strong>Auteur :</strong> The Prophecy Is True</p>
                            <p><strong>Série :</strong> La vie de Jean
                                <strong>N° :</strong> PI</p>
                            <p><strong>Cote :</strong> 87188E82</p>
                            <p><strong>Genre :</strong> Fantastique</p>
                            <p><strong>Description :</strong> Jean se casse le pied en glissant à la piscine.
                                Jean se casse le pied en glissant à la piscine.
                                Jean se casse le pied en glissant à la piscine.
                                Jean se casse le pied en glissant à la piscine</p>
                            <p><strong>Etat :</strong> En décomposition</p>
                            <br/>
                            <div id="dispo"></div>-->
                            
                            <h2><c:out value="${titre}"/></h2>
                            <br/>
                            <c:forEach var="createur" items="${createurs}">
                                <c:if test="${createur.poste=='Auteur'}">
                                    <p><strong><c:out value="${createur['poste']}"/>(s) :</strong> <c:out value="${createur['nom']}"/> <c:out value="${createur['prenom']}"/></p>
                                </c:if>
                            </c:forEach>
                            <c:forEach var="createur" items="${createurs}">
                                <c:if test="${createur['poste']=='Illustrateur'}">
                                    <p><strong><c:out value="${createur['poste']}"/>(s) :</strong> <c:out value="${createur['nom']}"/> <c:out value="${createur['prenom']}"/></p>
                                </c:if>
                            </c:forEach>
                            <c:forEach var="createur" items="${createurs}">
                                <c:if test="${(createur['poste']!='Auteur')&&(createur['poste']!='Illustrateur')}">
                                    <p><strong><c:out value="${createur['poste']}"/> :</strong> <c:out value="${createur['nom']}"/> <c:out value="${createur['prenom']}"/></p>
                                </c:if>
                            </c:forEach>
                            <p><strong>Série :</strong> <c:out value="${serie}"/>
                                <strong>N° :</strong> <c:out value="${numero}"/></p>
                            <p><strong>Cote :</strong> <c:out value="${cote}"/></p>
                            <p><strong>Genre :</strong> <c:out value="${genre}"/></p>
                            <p><strong>Description :</strong> <c:out value="${description}"/></p>
                            <p><strong>Etat :</strong> <c:out value="${etat}"/></p>
                            <br/>
                            <div id="dispo"></div>
                            
                            <br/>
                            <center>
                                <button id="reserver_button" onclick="reserver(<c:out value="${idco}"/>)" style="color:green;">Réserver</button>
                                <button id="retourner_button" onclick="retourner(<c:out value="${idStatut}"/>)" style="margin-left: 100px; color:red; display:none;">Retourner</button>
                            </center>
                            
                            
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!--POP-UP: utilisateur non connecté ayant appuyé sur réserver-->
    <div class="modal fade" id="connect-form" role="dialog">
        <div class="modal-dialog modal-sm" id="pop_form">
            <div class="modal-content modal_form">
                <!-- Croix de fermeture -->
                <button class="close" data-dismiss="modal">&times;</button>
                <!-- Titre -->
                <p id= "subtitle" style="margin-top: 40px">Vous n'êtes pas connecté</p> 
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