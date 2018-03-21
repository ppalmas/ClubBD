
var res; //reponse json de resultats de recherche
var idm; //id de lelement selectionné
/**
 * Recherche de documents suiant des critères ou non
 * @returns {undefined|Listedesources}
 */
function recherche_doc() {
    //On récupère les infos
    var titre = document.getElementById("critere_titre").value;
    var auteur = document.getElementById("critere_auteur").value;
    var serie = document.getElementById("critere_serie").value;
    var sujet = document.getElementById("critere_sujet").value;
    var all = document.getElementById("recherche_doc").value;
    //On envoie les infos a la servlet pour requete
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {

            //Réponse de la servlet
            var answer = xhttp.responseText;

            res = JSON.parse(answer);
            //boucle daffichage
            var disp = "";
            if (res == null) {
                document.getElementById("recherche_resultat").innerHTML = "Erreur.";
            } else if (res.nb == "0") {
                document.getElementById("recherche_resultat").innerHTML = "Aucun résultat.";
            } else {
                document.getElementById("recherche_resultat").appendChild(makeUL(res.resultats));
            }
        } else {
            document.getElementById("recherche_resultat").innerHTML = "";
        }
    }

    //formulaire envoyé en get à la servlet recherche
    var data = "titre=" + titre + "&" + "serie=" + serie + "&" + "auteur=" + auteur + "&" + "sujet=" + sujet + "&" + "all=" + all;
    xhttp.open("GET", "RechercheDocServlet?" + data, true);
    xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
    xhttp.send();

}

/**
 * Fonction pour gérer dynamiquement l'affichage des résultats
 * de la recherche + définition d'id, des attributs onclick...
 * @param {type} array
 * @returns {Element|makeUL.div}
 */
function makeUL(array) {
    var div = document.createElement('div');
    //Définition de l'id de la div
    div.setAttribute("id", "result_temp");
    for (var i = 0; i < array.length; i++) {
        var item = document.createElement('table');
        var ligne = document.createElement('tr');

        //Titre
        var ligne = document.createElement('tr');
        //Défintion de l'id pour le titre, et de l'action à effectuer onclick
        ligne.setAttribute("id", "titre_result" + i);
        ligne.setAttribute("onclick", "goToOuvrage()");
        ligne.appendChild(document.createTextNode(array[i]['titre']));

        item.appendChild(ligne);

        //Auteur
        var ligne = document.createElement('tr');

        //Nombre d'auteurs pour chaque document
        var nb = array[i]['nbA'];
        if (nb > 0) {
            if (nb > 1) {
                for (var j = 0; j < nb-1; j++) {
                    ligne.appendChild(document.createTextNode(array[i]['auteurs']['auteur' + j] + ", "));
                }
            }
            ligne.appendChild(document.createTextNode(array[i]['auteurs']['auteur' + (nb-1)]));
            item.appendChild(ligne);
        } else {
            item.appendChild(document.createTextNode(""));
        }

        //Serie
        var serie = array[i]['serie'];
        if (serie != "#0*##") {//Document non hors série
            var ligne = document.createElement('tr');
            ligne.appendChild(document.createTextNode("Série : " + serie));
            item.appendChild(ligne);
        }

        div.appendChild(item);
    }

    // Finally, return the constructed list:
    return div;
}
