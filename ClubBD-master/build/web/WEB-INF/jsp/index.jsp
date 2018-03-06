<!-- PAGE D'ACCUEIL -->

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

        <!-- SCRIPTS -->
        <script src="Scripts/navigation.js"></script>
        <script src="Scripts/index.js"></script>
        <script src="Scripts/connecter.js"></script>
        <script src="Scripts/recherche2.js"></script>
        <script src="Scripts/event_listener.js"></script>



    </head>
    <body onload="load_listener(['email','password']);">


        <!-- CONTENU PRINCIPAL -->

        <div class="container">            
            <!-- Volet de gauche -->
            <div id="left_div" class="col-md-4">
                <p id="title">Bienvenue sur le site du club BD</p>
                <!-- Formulaire de connexion -->
                <center>
                    <!-- Champ pour l'email -->
                    <input type="text" name ="email" id="email" placeholder="Email">    
                    <!-- Champ pour le mot de passe -->
                    <input  type="password" name="password" id="password"  style="margin-left:5px; margin-bottom:7px" placeholder="Mot de passe">
                    <!-- Bouton pour soumettre le formulaire de connexion -->
                    <button id ="validation_button" type="button" class="button small_button" onclick="connect()">Ok</button>

                    <!-- si erreur à la connexion -->
                    <p id="error_connect"></p>
                    <!-- Lien pour réinitialiser son mot de passe -->
                    <br>
                    <div style="margin-bottom:10px;">
                        <a id="mdp_oublie" href="#" onclick="pop_mdpoublie()"><i>Mot de passe oublié ?</i></a>
                        <a id="inscription" href="#" onclick="getInscription()"><i>S'inscrire ?</i></a>
                    </div>
                </center>
                <!-- Différents onglets -->
                <center>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('news_content', ['search_content', 'suggestions_content'])">A la une</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('search_content', ['news_content', 'suggestions_content'])">Rechercher...</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('suggestions_content', ['news_content', 'search_content'])">Suggestions de lecture</a>
                </center>

            </div>
            <!-- Volet de droite -->
            <div id="right_div" class="col-md-8"> 
                <!-- Bloc des news-->
                <div id="news_content" class="bloc_home">
                    <p> News</p>
                </div>
                <!--Bloc de recherche d'ouvrages-->
                <div id="search_content" class="bloc_home" style="display:none;">
                    <div class="row_content" style="width: 95%;">
                        <p style="text-align:left;">Recherche de documents
                            <input id="recherche_doc" style="width:500px; margin-left:30px;" placeholder="Titre, Auteur/Illustrateur, Serie"></p>
                        <p style="text-align:left;" id="critere" style="margin-top:10px;" onclick="setVisible('search_critere')">Rechercher par critère</p>
                        <div id="search_critere" style="display:none; text-align:left">
                            <input id="search_titre" style="width:300px; margin:10px;" placeholder="Titre">
                            <br>
                            <input id="critere_auteur" style="width:300px; margin:10px;" placeholder="Auteur, Illustrateur">
                            <br>
                            <input id="search_serie" style="width:300px; margin:10px;" placeholder="Serie">
                            <br>
                            <input id="search_sujet" style="width:300px; margin:10px;" placeholder="Sujet">
                        </div>
                        <button style="margin-top:10px;" onclick="recherche_doc()">Rechercher</button>

                    </div>
                    <div id="recherche_resultat">

                    </div>

                </div>
                <!-- Bloc des suggestions-->
                <div id ="suggestions_content" class="bloc_home" style="display:none">
                    <p>Suggestion</p>
                </div>
            </div>

        </div>

        <!--POPUP : mot de passe oublié-->
        <div class="modal fade" id="oubli_form" role="dialog">
            <div class="modal-dialog modal-sm medium_modal">
                <div class="modal-content modal_form">
                    <!-- Croix de fermeture -->
                    <button class="close" data-dismiss="modal">&times;</button>
                    <!-- Titre -->
                    <p class= "title"  style="margin-top: 40px">Mot de passe oublié ?</p> 
                    <!-- Zone pour les messages d'erreur -->
                    <p id="inscription_error" class="error_message"></p>
                    <!-- Formulaire -->
                    <div class="modal-body">  
                        <!-- Champ pour l'email du compte associé-->
                        <left><p>Email du compte :</p></left>
                        <input  type="text" name="name" id="email_password_forgotten">
                        <center>
                            <button id ="valid_password_forgotten" type="button" onclick="" style="margin-top: 20px;">Ok</button>
                        </center>
                    </div>
                </div>
            </div>
        </div>
        
        

    </body>
</html>

