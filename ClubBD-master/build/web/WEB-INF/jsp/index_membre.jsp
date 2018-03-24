<!-- PAGE D'ACCUEIL UNE FOIS CONNECTE -->

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
        <title>Accueil</title>

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
        <script src="Scripts/deconnecter.js"></script>        
        <script src="Scripts/index_membre.js"></script>      
        <script src="Scripts/modif_infosperso.js"></script>  
        <script src="Scripts/event_listener.js"></script>
        <script src="Scripts/recherche.js"></script>


    </head>
    <body onload="load_listener(['nom_modif', 'prenom_modif', 'email_modif', 'mdp_ancien_modif', 'mdp1_modif', 'mdp2_modif']);
            loadIndexUser(<c:out value="${idStatut}"/>);
            load_listenerSearch(['recherche_doc', 'critere_titre', 'critere_auteur',
                'critere_serie', 'critere_sujet']);
            load_listenerProposition(['titre_proposition', 'commentaire_proposition']);">
        <!-- CHARGEMENT DES DONNEES LIEES A L'UTILISATEUR CONNECTE /!\ indispensable
        pour la déconnexion notamment-->
        <div style="display:none;">
            <!-- Données personnelles-->
            <input type="hidden" id="idMembre" value="<c:out value="${id}"/>"/>
            <input type="hidden" id="nom" value="<c:out value="${nom}"/>"/> 
            <input type="hidden" id="prenom" value="<c:out value="${prenom}"/>"/> 
            <input type="hidden" id="email" value="<c:out value="${email}"/>"/> 
            <input type="hidden" id="idco" value="<c:out value="${idco}"/>"/> 
            <input type="hidden" id="idStatut" value="<c:out value="${idStatut}"/>"/> 
        </div>

        <!-- CONTENU PRINCIPAL -->
        <div class="container">              
            <!-- Volet de gauche -->
            <div id="left_div" class="col-md-4">
                <!--Informations du compte-->
                <center>
                    <p></p>
                    <!-- affichage nom prénom-->
                    <p class="info_perso" id="title" style="margin-top:5px;font-weight:bold;padding:1%;"><c:out value="${prenom}"/> <c:out value="${nom}"/></p>
                    <!-- affichage email-->
                    <p class="info_perso"id="info_email" ><c:out value="${email}"/></p>
                    <!-- lien de modification -->
                    <a id="modification_link" href="#" onclick="pop_info();">Modifier les informations personnelles</a>
                    <p><button style="margin-bottom:10px;margin-top:10px;" href="#" onclick="deconnect()">Se déconnecter</button></p>


                </center>
                <!-- Différents onglets -->
                <center><div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('news_content', ['search_content', 'suggestions_content', 'recap_content'])">A la une</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('search_content', ['news_content', 'suggestions_content', 'recap_content'])">Rechercher...</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('suggestions_content', ['news_content', 'search_content', 'recap_content'])">Suggestions de lecture</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getMesEmprunts()" id="gestion_compte">Mes Emprunts</a>
                    <div class="onglet_separator" id="sep_admin"></div>
                    <a class="onglets" href="#" style="display:none" onclick="getGestion()" id="gestion_inventaire">Gestion admin</a>
                    <div class="onglet_separator" id="sep_member"></div>
                    <a class="onglets" href="#" style="display:none" onclick="" id="gestion_emprunt">Gestion des emprunts</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('infos_content', ['suggestions_content', 'news_content', 'search_content'])">Informations</a>

                </center>

            </div>
            <!-- Volet de droite -->
            <div id="right_div" class="col-md-8"> 
                <!-- Bloc des news-->
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
                    <div id="div_proposition" style="width:100%;display:none;position:static;" >
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
                            <div class="thumbnail">image</div>
                        </div>
                        <div class="col-sm-10">
                            <div id="sugg_titre1" class="row book_title"></div>
                            <div id="sugg_auteur1" class="row book_authors"></div>
                            <div class="row book_available">
                                <br>
                                <div class="col-sm-3"
                                     <div id="dispo_color1" style="height: 20px; width: 20px;  padding-right: 0px; padding-left: 0px; margin-left: 20%;"</div>
                                </div>
                                <div class="col-sm-5"><p>Réserver</p></div>
                            </div>
                        </div>
                    </div>
                    <div class="row suggestion">
                        <div class="col-sm-2">
                            <div class="thumbnail">image</div>
                        </div>
                        <div class="col-sm-10">
                            <div id="sugg_titre2" class="row book_title"></div>
                            <div id="sugg_auteur2" class="row book_authors"></div>
                            <div class="row book_available">
                                <br>
                                <div class="col-sm-3"
                                     <div id="dispo_color2" style="height: 20px; width: 20px; padding-right: 0px; padding-left: 0px; margin-left: 20%;"</div>
                                </div>
                                <div class="col-sm-5"><p>Réserver</p></div>
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

        <!--POP-UP: modification des données personnelles-->
        <div class="modal fade" id="modif_form" role="dialog">
            <div class="modal-dialog modal-sm" id="pop_form">
                <div class="modal-content modal_form">
                    <!-- Croix de fermeture -->
                    <button class="close" data-dismiss="modal">&times;</button>
                    <!-- Titre -->
                    <p id= "subtitle" style="margin-top: 40px">Modification des informations personnelles</p> 
                    <!-- Zone pour les messages d'erreur -->
                    <p id="modification_error" class="error_message"></p>
                    <!-- Formulaire de modification des données -->
                    <!-- Champ pour l'email du compte associé-->
                    <div class="left_bloc">
                        <p class="left_p">Nom</p>
                        <input class="large_input" type="text" id="nom_modif" value="<c:out value="${nom}"/>">
                        <p class="left_p">Prénom</p>
                        <input class="large_input" type="text" id="prenom_modif" value="<c:out value="${prenom}"/>">
                        <p class="left_p">Email</p>
                        <input class="large_input" type="text" id="email_modif" value="<c:out value="${email}"/>">
                        <p class="left_p">Ancien mot de passe</p>
                        <input class="large_input" type="password" id="mdp_ancien_modif">
                        <p class="left_p">Nouveau mot de passe</p>
                        <input class="large_input" type="password" id="mdp1_modif">
                        <p class="left_p">Confirmation du mot de passe</p>
                        <input class="large_input" type="password" id="mdp2_modif">
                    </div>

                    <center>
                        <button id ="validation_button" type="button" onclick="modif_infos()" style="margin-top: 20px;">Ok</button>
                    </center>
                </div>
            </div>
        </div>

    </body>
</html>

