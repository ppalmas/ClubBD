
/**
 * Fonction qui retourne une suggestion de livres
 * @returns {undefined|Listedesources}
 */
function get_suggestion() {


    //On envoie les infos a la servlet pour requete
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            //Réponse de la servlet
            var answer = xhttp.responseText;
            
            //Initialisation des variables
            var auteurs1 = "";
            var auteurs2 = "";
            var titre1 = "";
            var titre2 = "";
            var id1 = "";
            var id2 = "";
            //Récupération des titres, auteurs, et disponibilité
            var answers = answer.split("*//*");
            var liste1 = parseDoc(answers[0]);
            var liste2 = parseDoc(answers[1]);
            var titre1 = liste1[0];
            var auteurs1 = liste1[1];
            var dispo1 = liste1[2];
            var titre2 = liste2[0];
            var auteurs2 = liste2[1];
            var dispo2 = liste2[2];
            var id1 = liste1[3];
            var id2 = liste2[3];
            //Suivant la disponibilité, on affiche en vert ou en orange
            if (dispo1=="true"){
                document.getElementById("dispo_color1").style.backgroundColor = "green";
            } else {
                document.getElementById("dispo_color1").style.backgroundColor = "orange";
            }
            if (dispo2=="true"){
                document.getElementById("dispo_color2").style.backgroundColor = "green";
            }else {
                document.getElementById("dispo_color2").style.backgroundColor = "orange";
            }
            //Actualisation des informations des documents de la page
            document.getElementById("iddoc1").innerHTML = id1;
            document.getElementById("iddoc2").innerHTML = id2;
            document.getElementById("sugg_titre1").innerHTML = titre1;
            document.getElementById("sugg_auteur1").innerHTML = auteurs1;
            document.getElementById("sugg_titre2").innerHTML = titre2;
            document.getElementById("sugg_auteur2").innerHTML = auteurs2;

        }
        ;
    }

    //formulaire envoyé en get à la servlet recherche

    xhttp.open("GET", "SuggestionServlet?", true);
    xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
    xhttp.send();
}


function parseDoc(liste) {
    var doc = liste.split("*/*");
    var titre = doc[0];
    var dispo = doc[2];
    var id = doc[3];
    var answers_auteurs = doc[1].split(";*");
    var n = answers_auteurs.length - 1;
    var auteurs = "";
    for (i = 0; i < n - 1; i++) {
        auteurs += answers_auteurs[i] + ", ";
    }
    auteurs += answers_auteurs[n - 1];
    var result = [];
    result[0] = titre;
    result[1] = auteurs;
    result[2] = dispo;
    result[3] = id;
    return result;
}


