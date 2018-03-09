<!-- PAGE ADMIN -->

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
        <script src="Scripts/deconnecter.js"></script>
        <script src="Scripts/gestion_inv.js"></script>


    </head>
    <body>
        <!-- CHARGEMENT DES DONNEES LIEES A L'UTILISATEUR CONNECTE -->
        <div style="display:none;">
            <!-- Données personnelles-->
            <input type="hidden" id="idMembre" value="<c:out value="${id}"/>"/>
            <input type="hidden" id="nom" value="<c:out value="${nom}"/>"/> 
            <input type="hidden" id="prenom" value="<c:out value="${prenom}"/>"/> 
            <input type="hidden" id="email" value="<c:out value="${email}"/>"/> 
            <input type="hidden" id="idco" value="<c:out value="${idco}"/>"/> 
        </div>



        <!-- CONTENU PRINCIPAL -->
        <div class="container">             
            <!-- Volet de gauche -->
            <div id="left_div" class="col-md-4">
                <!--Informations du compte-->
                <center>
                    <div class="left">
                        <a href="#" onclick="goHomeMember()">Retour</a>
                    </div>
                    <br>
                    <!-- affichage nom prénom-->
                    <p class="info_perso" id="title" style="margin-top:5px;font-weight:bold;padding:1%;"><c:out value="${prenom}"/> <c:out value="${nom}"/></p>
                    <!-- affichage email-->
                    <p class="info_perso"id="info_email" >Admin</p>
                    <p><button style="margin-bottom:10px;" href="#" onclick="deconnect()">Se déconnecter</button></p>
                </center>
                <!-- Différents onglets -->
                <center><div class="onglet_separator"></div>

                    <a class="onglets" href="#" onclick="getMonCompte()" id="gestion_compte">Mon compte</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('propositions_content', ['stats_content', 'membres_content', 'gestion_inv_content', 'retour_emprunt_content'])">Propositions d'achat</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('gestion_inv_content', ['propositions_content', 'stats_content', 'membres_content', 'retour_emprunt_content'])">Gestion inventaire</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('membres_content', ['propositions_content', 'stats_content', 'gestion_inv_content', 'retour_emprunt_content'])">Gestion membres</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('stats_content', ['propositions_content', 'membres_content', 'gestion_inv_content', 'retour_emprunt_content'])">Statistiques</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('retour_emprunt_content', ['stats_content', 'propositions_content', 'news_content', 'search_content', 'membres_content', 'gestion_inv_content'])">Retour emprunts</a>
                </center>
            </div>
            <!-- Volet de droite -->
            <div id="right_div" class="col-md-8"> 
                <!--BLOC ADMIN-->
                <!--Bloc retourner un emprunt-->
                <div id ="retour_emprunt_content" class="bloc_home" style="display:none">
                    <p>Retourner un emprunt</p>
                </div>
                <div id ="propositions_content" class="bloc_home" style="display:none">
                    <p>Propositions d'achat</p>
                </div>

                <!-- Bloc gestion inventaire-->
                <div id ="gestion_inv_content" class="bloc_home" style="display:none">
                    <h1>Gestion de l'inventaire</h1>
                    <div id="ajout">
                        <h2>Ajout</h2>
                        <label for="titrea">Titre</label>
                        <input id="titrea" type="text"/><br>
                        <label for="cotea">Côte</label>
                        <input id="cotea" type="text"/><br>
                        <label for="seriea">Série</label>
                        <input id="seriea" type="text"/><br>
                        <label for="numeroa">Numéro</label>
                        <input id="numeroa" type="text"/><br>
                        <label for="descriptiona">Description</label>
                        <input id="descriptiona" type="text"/><br>
                        <label for="etata">Etat</label>
                        <input id="etata" type="text"/><br>
                        <label for="imagea">Chemin image</label>
                        <input id="imagea" type="text"/><br>
                        <label for="commentairea">Commentaire</label>
                        <input id="commentairea" type="text"/><br>
                        
                                    
                        
                        <input type="submit" value="Ajouter" onclick="ajouter()"/>
                        
                    </div>
                    <div id="selection">
                        <h2>Sélection</h2>
                        <label for="Titre">Titre</label>
                        <input type="text" id="Titre" name="Titre"/><br>
                        <label for="Serie">Série</label>
                        <input type="text" id="Serie" name="Serie"/><br>
                        <label for="Cote">Côte</label>
                        <input type="text" id="Cote" name="Cote"/><br>
                        <button onclick="recherche2_doc()">Rechercher</button>
                    </div>
                    <h2>Résultats</h2>
                    <div id="recherche_resultat">

                        
                    </div>
                    <div id="modif">
                       <h2>Modification</h2>  
                       <label for="titrem">Titre</label>
                       <input type="text" id="titrem" value=""/><br>
                       <label for="cotem">Côte</label>
                       <input type="text" id="cotem" value=""/><br>
                       <label for="seriem">Série</label>
                       <input type="text" id="seriem" value=""/><br>
                    </div>
                   
                    

                </div>

                <!-- Bloc gestion membres-->
                <div id ="membres_content" class="bloc_home" style="display:none">
                    <p>Gestion des membres</p>
                </div>

                <!-- Bloc des stats-->
                <div id ="stats_content" class="bloc_home" style="display:none">
                    <p>Statistiques</p>
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
                    <!-- Formulaire d'inscription -->
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

