<!-- PAGE ADMIN -->

<!-- Tags -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


        <!-- STYLES -->
        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/index.css">
        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/base.css">

        <link rel="stylesheet" type="text/css" media="screen" href="Stylesheets/tableau.css">

        
        <style>
            .container {
                text-align: left;
            }
            
            label {
                width: 250px;
            }
            
            .radio {
                
                display: unset;
            }
            
            h2{
               cursor: pointer; 
            }
            
            .res{
                cursor: pointer;
            }
        </style>


        <!-- SCRIPTS -->
        <script src="Scripts/navigation.js"></script>

        <script src="Scripts/index.js"></script>
        <script src="Scripts/deconnecter.js"></script>
        <script src="Scripts/gestion_inv.js"></script>
        <script src="Scripts/get_propositions.js"></script>
        <script src="Scripts/get_stats.js"></script>



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
                    <!-- affichage nom prénom-->
                    <p class="info_perso" id="title" style="margin-top:5px;font-weight:bold;padding:1%;"><c:out value="${prenom}"/> <c:out value="${nom}"/></p>
                    <!-- affichage email-->
                    <p class="info_perso"id="info_email" >Admin</p>
                    <p><button style="margin-bottom:10px;" href="#" onclick="deconnect()">Se déconnecter</button></p>
                </center>
                <!-- Différents onglets -->
                <center><div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="goHomeMember()" id="gestion_compte">Accueil</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="get_propositions();getNewContent('propositions_content', ['stats_content', 'membres_content', 'gestion_inv_content', 'retour_emprunt_content'])">Propositions d'achat</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('gestion_inv_content', ['propositions_content', 'stats_content', 'membres_content', 'retour_emprunt_content'])">Gestion inventaire</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('membres_content', ['propositions_content', 'stats_content', 'gestion_inv_content', 'retour_emprunt_content'])">Gestion membres</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="get_stats();getNewContent('stats_content', ['propositions_content', 'membres_content', 'gestion_inv_content', 'retour_emprunt_content'])">Statistiques</a>
                    <div class="onglet_separator"></div>
                    <a class="onglets" href="#" onclick="getNewContent('retour_emprunt_content', ['stats_content', 'propositions_content', 'membres_content', 'gestion_inv_content'])">Retour emprunts</a>
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
                    <h1>Propositions d'achat</h1>
                    <div id="propositions_resultat" style="position:static">
                    </div>
                </div>

                <!-- Bloc gestion inventaire-->
                <div id ="gestion_inv_content" class="bloc_home" style="display:none">
                    <h1>Gestion de l'inventaire</h1>
                    <input type="button" id="refresh" value="Rafraîchir la BDD" onclick="refresh()"/>
                    <span onclick="getNewContent('ajout', ['selection', 'modif', 'recherche_resultat'])"><h2>Ajout</h2></span>

                    <div id="ajout" style="display:none">



                        <div><h3>Ajout d'un document</h3>
                            <label for="titrea">Titre*</label>
                            <input name="titrea" id="titrea" type="text" value=""/><br>
                            <label for="cotea">Côte*</label>
                            <input name="cotea" id="cotea" type="text" value=""/><br>
                            <label for="seriea">Série</label>
                            <input type="text" list="seriecombo" id="seriea" name="seriea">
                            <datalist id="seriecombo" >
                                <c:forEach items="${lserie}" var="serie">

                                    <option><c:out value="${serie}" />

                                    </c:forEach>
                            </datalist>
                            <br>
                            <label for="numeroa">Numéro</label>
                            <input name="numeroa" id="numeroa" type="text" value=""/><br>

                            <label for="genrea">Genres (séparer par une virgule)</label>
                            <input id="genrea" name="genrea" type="text" value=""/><br>
                            <label for="createura">Créateurs,poste (sans espace)</label>

                            <input type="text" list="createurcombo" id="createura0" name="createura0">
                            <input type="button" id="addcrea" name="addcrea" onclick="addcrea()" value="+"/>
                            <input type="button" id="rmvcrea" name="rmvcrea" onclick="rmvcrea()" value="-"/><br>
                            <div id="crea1" style="display:none"><input type="text" list="createurcombo" id="createura1" name="createura1" ><br></div>
                            <div id="crea2" style="display:none"><input type="text" list="createurcombo" id="createura2" name="createura2" ><br></div>

                            <div id="crea3" style="display:none"><input type="text" list="createurcombo" id="createura3" name="createura3" ><br></div>
                            <div id="crea4" style="display:none"><input type="text" list="createurcombo" id="createura4" name="createura4" ><br></div>
                            <datalist id="createurcombo" >
                                <c:forEach items="${lcrea}" var="crea">

                                    <option><c:out value="${crea}" />

                                    </c:forEach>
                            </datalist>


                            <label for="descriptiona">Description</label>
                            <input name="descriptiona" id="descriptiona" type="text" value=""/><br>

                            <label class="radio" for="neuf">Neuf</label>
                            <input type="radio" id="neuf" name="etat" value="1" checked/>
                            <label class="radio" for="tbon">Très bon</label>
                            <input type="radio" id="tbon" name="etat" value="2"/>
                            <label class="radio" for="bon">Bon</label>
                            <input type="radio" id="bon" name="etat" value="3"/>
                            <label class="radio" for="abime">Abimé</label>
                            <input type="radio" id="abime" name="etat" value="4"/>
                            <label class="radio" for="tabime">Très abimé</label>
                            <input type="radio" id="tabime" name="etat" value="5"/>
                            <br>

                            <label for="imagea">Chemin image</label>
                            <input name="imagea" id="imagea" type="text" value=""/><br>
                            <label for="commentairea">Commentaire</label>
                            <input name="commentairea" id="commentairea" type="text" value=""/><br>



                            <input type="submit" value="Ajouter" onclick="ajouter()"/><br><br></div>

                        <div><h3>Ajout d'une série</h3>
                            <label for="seriename">Nom de la série*</label>
                            <input type="text" id="seriename" name="seriename" value=""/><br>
                            <label for="seriedesc">Description</label>
                            <input type="text" id="seriedesc" name="seriedesc" value=""/><br>
                            <input type="submit" value="Ajouter" onclick="ajouterserie()"/><br><br></div>

                        <div><h3>Ajout d'un créateur</h3>
                            <label for="nomcrea">Nom créateur*</label>
                            <input type="text" id="nomcrea" name="nomcrea" value=""/><br>
                            <label for="prenomcrea">Prénom créateur*</label>
                            <input type="text" id="prenomcrea" name="prenomcrea" value=""/><br>
                            <input type="submit" value="Ajouter créateur" onclick="ajoutercreateur()"/><br><br></div>







                    </div>
                    <span onclick="getNewContent('selection', ['ajout'])"><h2>Sélection</h2></span>

                    <div id="selection" style="display:none">
                        <div> <h3>Sélection document</h3><br>
                            <label for="Titre">Titre</label>
                            <input type="text" id="Titre" name="Titre"/><br>

                            <label for="Serie">Série</label>

                            <input type="text" id="Serie" name="Serie">
                            <br>

                            <label for="Cote">Côte</label>
                            <input type="text" id="Cote" name="Cote"/><br>
                            <button onclick="recherche2_doc()">Rechercher document</button><br><br></div>


                        <div><h3>Sélection série</h3><br>
                            <label for="nomseries">Nom série</label>
                            <input type="text" id="nomseries" name="nomseries"/><br>




                            <button onclick="recherche2_serie()">Rechercher série</button></div>


                    </div>
                    <h2>Résultats</h2>
                    <div id="recherche_resultat">


                    </div>
                    <h2>Modification document</h2> 
                    <div id="modif" style="display:none">
                        <h3>Modification document</h3>
                        <label>Id document</label>
                        <input id="idm" type="text" readonly="readonly" value=""><br>

                        <label for="titrem">Titre*</label>
                        <input name="titrem" id="titrem" type="text" value=""/><br>
                        <label for="cotem">Côte*</label>
                        <input name="cotem" id="cotem" type="text" value=""/><br>
                        <label for="seriem">Série</label>

                        <!-- comboboc serie -->
                        <input type="text" list="seriecombo" id="seriem" name="seriem">



                        <br>
                        <label for="numerom">Numéro</label>
                        <input name="numerom" id="numerom" type="text" value=""/><br>

                        <label for="genrem">Genres (séparer avec une virgule)</label>
                        <input name="genrem" id="genrem" type="text" value=""/><br>

                        <label for="createurm">Créateurs,poste (sans espace)</label>

                        <input type="text" list="createurcombo" id="createurm0" name="createurm0">
                        <br>
                        <input type="text" list="createurcombo" id="createurm1" name="createurm1" ><br>
                        <input type="text" list="createurcombo" id="createurm2" name="createurm2" >
                        <br>

                        <input type="text" list="createurcombo" id="createurm3" name="createurm3" ><br>
                        <input type="text" list="createurcombo" id="createurm4" name="createurm4" >
                        <br>



                        <label for="descriptionm">Description</label>
                        <input name="descriptionm" id="descriptionm" type="text" value=""/><br>

                        <label class="radio" for="neufm">Neuf</label>
                        <input type="radio" id="neufm" name="etatm" value="1" checked/>
                        <label class="radio" for="tbonm">Très bon</label>
                        <input type="radio" id="tbonm" name="etatm" value="2"/>
                        <label class="radio" for="bonmf">Bon</label>
                        <input type="radio" id="bonm" name="etatm" value="3"/>
                        <label class="radio" for="abimem">Abimé</label>
                        <input type="radio" id="abimem" name="etatm" value="4"/>
                        <label class="radio" for="tabimem">Très abimé</label>
                        <input type="radio" id="tabimem" name="etatm" value="5"/>
                        <br>
                        <label for="imagem">Chemin image</label>
                        <input name="imagem" id="imagem" type="text" value=""/><br>
                        <label for="commentairem">Commentaire</label>
                        <input name="commentairem" id="commentairem" type="text" value=""/><br>



                        <input type="submit" value="Modifier document" onclick="modifier()"/>





                        <h3>Modification Série</h3> 
                        <label>ID série</label>
                        <input id="idms" type="text" readonly="readonly" value=""><br>

                        <label for="serienamem">Titre*</label>
                        <input name="serienamem" id="serienamem" type="text" value=""/><br>
                        <label for="seriedescm">Description</label>
                        <input name="seriedescm" id="seriedescm" type="text" value=""/><br>
                        <label for="completm">Complet</label>
                        <input name="completm" id="completm" type="checkbox"/><br>

                        <input type="submit" value="Modifier série" onclick="modifierserie()"/>
                    </div>

                </div>



                <!-- Bloc gestion membres-->
                <div id ="membres_content" class="bloc_home" style="display:none">
                    <h1>Gestion des membres</h1>
                    <label for="nommembre">Nom</label>
                    <input type="text" id="nommembre" value=""><br>
                    <label for="prenommembre">Prénom</label>
                    <input type="text" id="prenommembre" value=""><br>
                    <input type="submit" value="Rechercher" onclick="recherche2_membre()"><br>

                    <div id="recherche_resultatm"></div>

                    <br>
                    <div id="modifm" style="display:none">
                        <h2>Modification statut</h2>
                        
                        <label for="idmm">Id membre</label>
                        <input type="text" id="idmm" readonly="true" value=""><br>
                        <label for="nommembrem">Nom</label>
                        <input type="text" id="nommembrem" readonly="true" value=""><br>
                        <label for="prenommembrem">Prénom</label>
                        <input type="text" id="prenommembrem" readonly="true" value=""><br>
                        <label class="radio" for="membre">Membre</label>
                            <input type="radio" id="membre" name="idmradio" value="1"/>
                            <label class="radio" for="staff">Admin</label>
                            <input type="radio" id="staff" name="idmradio" value="2"/>
                            <label class="radio" for="admin">Cotisant</label>
                            <input type="radio" id="admin" name="idmradio" value="3"/><br>
                            
                        <input type="submit" value="Modifier" onclick="updatemembre()"><br>


                    </div>

                </div>

                <!-- Bloc des stats-->
                <div id ="stats_content" class="bloc_home" style="display:none">
                    <h1>Statistiques sur les dernières recherches non abouties</h1>
                    <div id="stats_resultat" style="position:static">
                    </div>
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

