
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
            //On récupère les infos
            var answers = answer.split("*/*");
            var titre=answers[0];
            var answers_auteurs = answers[1].split(";*");
            //document.getElementById("sugg_auteur").innerHTML = answers_auteurs[0];
            var n = answers_auteurs.length-1;
            var auteurs = "";
            for (i=0;i<n-1;i++){
                auteurs += answers_auteurs[i] + ",";
            }
            auteurs += answers_auteurs[n-1];
            document.getElementById("sugg_titre").innerHTML = titre;
            document.getElementById("sugg_auteur").innerHTML = auteurs;

        }
        ;
    }

    //formulaire envoyé en get à la servlet recherche
    
    xhttp.open("GET", "SuggestionServlet?", true);
    xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
    xhttp.send();




}
