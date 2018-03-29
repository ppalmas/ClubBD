<!-- PAGE DE RECAP DES EMPRUNTS-->

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
        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/tableau.css">


        <!-- SCRIPTS -->
        <script src="Scripts/navigation.js"></script>
        <script src="Scripts/index.js"></script>
        <script src="Scripts/deconnecter.js"></script>        
        <script src="Scripts/index_membre.js"></script>      
        <script src="Scripts/modif_infosperso.js"></script>  
        <script src="Scripts/event_listener.js"></script>
        <script src="Scripts/gestionemprunts.js"></script>

    </head>
       
    <body>
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
        </div>
        
        <div class="left">
            <a class="back" href="#" onclick="goHomeMember()">Retour</a>
        </div>
       
        <!-- Bloc des récapitulatifs emprunts et réservations-->
        <div id ="recap_content" class="bloc_home">
            <center>
            <h3>Mes emprunts</h3>
            <hr>
            
            <br/>
            <fieldset>
              <div class="col-sm-6">
                <input type="checkbox" id="reservations_checkbox" onclick="setContrary('recap_reservations_content')" checked>
                <label for="reservations_checkbox">Afficher les réservations</label>
              </div>
              <div class="col-sm-6">
                <input type="checkbox" id="emprunts_checkbox" onclick="setContrary('recap_emprunts_content')" checked>
                <label for="emprunts_checkbox">Afficher les emprunts</label>
              </div>
            </fieldset>
            <br/>
            
            <p><i>Vous pouvez trier les tableaux en cliquant sur le titre d'une colonne.</i></p>
            
            <div id="recap_reservations_content" style="width: 95%;">
                <table id="table_reservations">
                    <caption>Récapitulatif des réservations</caption>
                    <tr>
                        <th onclick="sortTable(0,'table_reservations')">Titre</th>
                        <th onclick="sortTable(1,'table_reservations')">Cote</th>
                        <th onclick="sortTable(2,'table_reservations')">Date de réservation</th>
                    </tr>
                    <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td><a class="ouvrage_link" href="#" onclick="goToOuvrage(<c:out value="${reservation['iddoc']}"/>)" id="gestion_compte" style="color:blue;"><c:out value="${reservation['titre']}"/></a></td>
                        <td><c:out value="${reservation['cote']}"/></td>
                        <td><c:out value="${reservation['date_reserve']}"/></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>

            <div id="recap_emprunts_content" style="width: 95%;">
                <table id="table_emprunts">
                    <caption>Récapitulatif des emprunts</caption>
                    <tr>
                        <th onclick="sortTable(0, 'table_emprunts')">Titre</th>
                        <th onclick="sortTable(1, 'table_emprunts')">Cote</th>
                        <th onclick="sortTable(2, 'table_emprunts')">Date de réservation</th>
                        <th onclick="sortTable(3, 'table_emprunts')">Date d'emprunt</th>
                        <th onclick="sortTable(4, 'table_emprunts')">Date de retour</th>
                    </tr>
                    <c:forEach var="emprunt" items="${emprunts}">
                    <tr>
                        <td><a class="ouvrage_link" href="#" onclick="goToOuvrage(<c:out value="${emprunt['iddoc']}"/>)" id="gestion_compte" style="color:blue;"><c:out value="${emprunt['titre']}"/></a></td>
                        <td><c:out value="${emprunt['cote']}"/></td>
                        <td><c:out value="${emprunt['date_reserve']}"/></td>
                        <td><c:out value="${emprunt['date_emprunt']}"/></td>
                        <td><c:out value="${emprunt['date_retour']}"/></td>
                    </tr>
                    </c:forEach>
                </table>                
            </div>
            </center>
        </div>
    </div>

</body>
</html>
