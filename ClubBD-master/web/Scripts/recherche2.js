
/**
 * Fonction qui retourne une liste de livres
 * à partir de mot-clés : recherche pour n'importe quel utilisateur
 * @returns {undefined|Listedesources}
 */
function recherche_doc() {
    //On récupère les infos
    var titre = document.getElementById("critere_titre").value;
    var auteur = document.getElementById("critere_auteur").value;
    var serie = document.getElementById("critere_serie").value;
    var sujet = document.getElementById("critere_sujet").value;


    //Si non vide
    if (titre != null || auteur != null || serie != null || sujet != null) {

        //On envoie les infos a la servlet pour requete
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;
                document.getElementById("recherche_resultat").innerHTML = answer;

            }
            ;
        }

        //formulaire envoyé en get à la servlet recherche
        var data = "titre=" + titre + "&" + "auteur=" + auteur;
        xhttp.open("GET", "RechercheDoc?" + data, true);
        xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
        xhttp.send();




    }

}

/**
 * Récupération des sources qui correspondent à un type donné et affichage
 * @returns {Liste de sources}
 */
function recherche2_doc() {
    //On récupère les infos
    var titre = document.getElementById("Titre").value;
    var serie = document.getElementById("Serie").value;
    var cote = document.getElementById("Cote").value;


    //Si non vide
    if (titre != null || serie != null || cote != null) {

        //On envoie les infos a la servlet pour requete
        xhttp = new XMLHttpRequest();



        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;


                document.getElementById("recherche_resultat").innerHTML = answer;


            }
            ;
        }

        //formulaire envoyé en get à la servlet recherche
        var data = "titre=" + titre + "&" + "serie=" + serie + "&" + "cote=" + cote;
        xhttp.open("GET", "Recherche?" + data, true);
        xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
        xhttp.send();




    }

}
