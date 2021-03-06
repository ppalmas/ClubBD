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
        <title>Club BD - Accueil</title>

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


        <!-- STYLES -->
        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/index.css">
        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/base.css">

        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/recherche.css">

        <!-- SCRIPTS -->
        <script src="Scripts/navigation.js"></script>
        <script src="Scripts/index.js"></script>
        <script src="Scripts/connecter.js"></script>
        <script src="Scripts/recherche.js"></script>
        <script src="Scripts/event_listener.js"></script>
        <script src="Scripts/get_suggestion.js"></script>
        <script src="Scripts/ouvrage.js"></script>



    </head>
    <body onload="load_listener(['email', 'password']);
            load_listenerSearch(['recherche_doc', 'critere_titre', 'critere_auteur',
                'critere_serie', 'critere_sujet']);
            load_listenerProposition(['titre_proposition', 'commentaire_proposition']);">

        <div style="display:none;">
            <!-- Données personnelles-->
            <input type="hidden" id="idco" value="0"/>
            <input type="hidden" id="iddoc1" value=""/>
            <input type="hidden" id="iddoc2" value=""/>
        </div>

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
                    <a class="onglets" href="#" onclick="getNewContent('news_content', ['search_content', 'suggestions_content', 'infos_content'])">A la une</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('search_content', ['news_content', 'suggestions_content', 'infos_content'])">Rechercher...</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="get_suggestion(); getNewContent('suggestions_content', ['news_content', 'search_content', 'infos_content'])">Suggestions de lecture</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('infos_content', ['suggestions_content', 'news_content', 'search_content'])">Informations</a>

                </center>

            </div>
            <!-- Volet de droite -->
            <div id="right_div" class="col-md-8"> 
                <!-- Bloc des news-->
                <div id="news_content" class="bloc_home">
                    <div class="row">
                        <div id="selected_title" class="col-sm-10">
                            <div >A la une</div>
                            <hr/>
                        </div>

                    </div>
                    <div class="row">

                        <div class="el_news col-sm-10">
                            <div class="news_title">Une partie News sur le site</div>
                            <br/>
                            <div class="news_text">Via l'onglet "A la une" vous pourrez bientôt vous tenir au courant de l'actu du club BD !</div>
                        </div>
                        <div class="el_news col-sm-10">
                            <div class="news_title">Un nouveau site pour le club BD !</div>
                            <br/>
                            <div class="news_text">Le club BD s'offre un nouveau site en recrutant les braves informaticiens du PGROU6.</div>
                        </div>
                    </div>
                </div>
                <!--Bloc de recherche d'ouvrages-->
                <div id="search_content" class="bloc_home" style="display:none;">
                    <div class="col-md-8" id="row_content" style="width: 98%;">
                        <p style="text-align:left;">Recherche de documents
                            <input id="recherche_doc" style="width:500px; margin-left:30px;" placeholder="Titre, Auteur/Illustrateur, Serie"></p>
                        <div class="col-md-5" id="input_recherche" style="width:60%;">
                            <p style="text-align:left;">Rechercher par critère</a>
                            <div id="search_critere" style="text-align:left">
                                <input id="critere_titre" style="width:300px; margin:7px;" placeholder="Titre">
                                <br>
                                <input id="critere_auteur" style="width:300px; margin:7px;" placeholder="Auteur, Illustrateur">
                                <br>
                                <input id="critere_serie" style="width:300px; margin:7px;" placeholder="Serie">
                                <br>
                                <input id="critere_sujet" style="width:300px; margin:7px;" placeholder="Sujet">
                            </div>
                            <button id="recherche_button" style="margin-top:10px;" onclick="recherche_doc()">Rechercher</button>
                        </div>
                        <div class="col-md-3" id="info_recherche" style="width:40%;position:relative;display:inline;">

                            <p>IMPORTANT :<br><br> La recherche par critères prend en compte tous
                                les critères complétés.
                                <br>
                                <br>Pour chaque type de recherche, la recherche <b>cherchera exactement le mot-clef</b>
                                entré, dans les documents du club. Par exemple, "chat potté" donnera tous les documents dont
                                le titre, série, auteur ou sujet contiennent "chat potté" et non "chat" et "potté".
                            </p>
                        </div>

                    </div>
                    <br>
                    <div style="width:100%; position:static">
                        <p id="result_nothing"> </p>
                        <br>
                    </div>
                    <div id="recherche_resultat" style="position:static">

                    </div>
                    <div id="div_proposition" style="width:95%;display:none;position:relative;" >
                        <p>Vous ne trouvez pas ce que vous cherchez ? Proposez l'achat d'un document.</p>
                        <input id="titre_proposition" placeholder="Titre">
                        <input id="commentaire_proposition" placeholder="Commentaire">
                        <button id="validation_proposition" onclick="save_prop();">Envoyer</button>
                    </div>

                </div>

               <!--BLOC DE SUGGESTIONS de lecture-->
                <div id ="suggestions_content" class="bloc_home" style="display:none">
                    <div class="row suggestion">
                        <div class="col-sm-2">
                            <div id="image_ouvrage">
                                <img class="img_border" id="img_ouvrage1" width="90" src="" alt="L'image n'a pas pu être affichée"/>
                            </div>
                        </div>
                        <div class="col-sm-10">
                            <div id="sugg_titre1" class="row book_title" onclick="goToOuvrage2('iddoc1'.toString())"></div>
                            <div id="sugg_auteur1" class="row book_authors"></div>
                            <div class="row book_available">
                                <br>
                                <div class="col-sm-3"
                                     <div id="dispo_color1" style="height: 20px; width: 20px;  padding-right: 0px; padding-left: 0px; margin-left: 20%;"</div>
                                </div>
                                <div class="col-sm-5" href="#" onclick="reserver2('0','iddoc1')"><p>Réserver</p></div>
                            </div>
                        </div>
                    </div>
                    <div class="row suggestion">
                        <div class="col-sm-2">
                            <div id="image_ouvrage">
                                <img class="img_border" id="img_ouvrage2" width="90" src="" alt="L'image n'a pas pu être affichée"/>
                            </div>
                        </div>
                        <div class="col-sm-10">
                            <div id="sugg_titre2" class="row book_title" onclick="goToOuvrage2('iddoc2');"></div>
                            <div id="sugg_auteur2" class="row book_authors"></div>
                            <div class="row book_available">
                                <br>
                                <div class="col-sm-3"
                                     <div id="dispo_color2" style="height: 20px; width: 20px; padding-right: 0px; padding-left: 0px; margin-left: 20%;"</div>
                                </div>
                                <div class="col-sm-5" href="#" onclick="reserver2('0','iddoc2')"><p>Réserver</p></div>
                            </div>
                        </div>
                    </div>

                </div>

                <!--Bloc d'informations sur l'application-->
                <div class="bloc_home" id="infos_content" style="display:none;">
                    <p>Le site du club BD a été réalisé par des étudiants de l'option informatique en 2018
                        dans le cadre d'un projet de groupe. Le site propose aux utilisateurs de consulter l'inventaire 
                        du club, de se créer un compte pour réserver des documents et gérer ses emprunts. Les membres cotisants
                        du club peuvent s'en servir pour gérer les emprunts et retours des documents.
                        <br>
                        <br>
                        Vos données:
                        <br>
                        Veuillez noter que vos recherches,
                        lorsqu'elles sont infructueuses, seront enregistrées pour une utilisation purement statistique par le club.
                        Ces données sont anonymisées. Les emprunts que vous effectuez sont enregistrés également à des fins statistiques 
                        mais aussi pour permettre de pouvoir accéder à l'historique des documents. Ces données ne sont donc pas anonymisées.
                    </p>
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

