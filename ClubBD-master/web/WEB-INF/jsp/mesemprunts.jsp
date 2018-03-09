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
        <script src="Scripts/index_membre.js"></script>


    </head>
    <body>



    <!-- CONTENU PRINCIPAL -->

    <div class="container">
        <div class="left">
            <a class="back" href="#" onclick="goHome()">Retour</a>
        </div>
        
        <!-- Bloc des récapitulatifs emprunts et réservations-->
        <div id ="recap_content" class="bloc_home">
            <center>
            <h3>Mes emprunts</h3>
            <div class="recap_reservations_content" style="width: 95%;">
                <table>
                    <caption>Récapitulatif des réservations</caption>
                    <tr>
                        <th>Titre</th>
                        <th>Cote</th>
                        <th>Date de réservation</th>
                    </tr>
                    <tr>
                        <td>Jacques</td>
                        <td>810</td>
                        <td>03/01</td>
                    </tr>
                <!--<c:forEach var="item" items="${itemsList}">
                    <tr>
                        <td><c:out value="${item['titre']}"/></td>
                        <td><c:out value="${item['cote']}"/></td>
                        <td><c:out value="${item['date_reserve']}"/></td>
                    </tr>
                </c:forEach>-->
                </table>
            </div>

            <div class="recap_emprunts_content" style="width: 95%;">
                <table>
                    <caption>Récapitulatif des emprunts</caption>
                    <tr>
                        <th>Titre</th>
                        <th>Cote</th>
                        <th>Date de réservation</th>
                        <th>Date d'emprunt</th>
                        <th>Date de retour</th>
                    </tr>
                    <tr>
                        <td>Jean</td>
                        <td>S8W</td>
                        <td>20/12</td>
                        <td>21/12</td>
                        <td>01/01</td>
                    </tr>
                <!--<c:forEach var="item" items="${itemsList}">
                     <tr>
                        <td><c:out value="${item['titre']}"/></td>
                        <td><c:out value="${item['cote']}"/></td>
                        <td><c:out value="${item['date_reserve']}"/></td>
                        <td><c:out value="${item['date_emprunt']}"/></td>
                        <td><c:out value="${item['date_retour']}"/></td>
                    </tr>
                </c:forEach>-->
                </table>                
            </div>
            </center>
        </div>
    </div>

</body>
</html>
