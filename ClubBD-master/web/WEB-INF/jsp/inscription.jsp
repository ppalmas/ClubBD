<!-- PAGE D'INSCRIPTION -->

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


        <!-- SCRIPTS -->
        <script src="Scripts/navigation.js"></script>
        <script src="Scripts/index.js"></script>


    </head>



    <!-- CONTENU PRINCIPAL -->

    <div class="container">    
        <div  class="row content"> 
            <center>
            <p style="margin-top:30px">Inscription</p>
            <p><input id="email_inscri" placeholder="Email"></p>
            <p><input id="mdp_inscri" placeholder="Mot de passe"></p>
            <p><input id="mdp_confirm_inscri" placeholder="Confirmation de Mot de passe"></p>
            <p><input id="nom_inscri" placeholder="Nom"></p>
            <p><input id="prenom_inscri" placeholder="Prénom"></p>
            <button >Valider</button>
            </center>
        </div>
    </div>

</body>
</html>

