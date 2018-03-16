
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
            for (i = 0; i < res.nb; i++) {
                disp = disp + "<span id=\"res" + res.resultats[i]['id'] + "\" idm=\"" + res.resultats[i]['id'] + "\" num=\"" + i + "\">" +
                        res.resultats[i]['titre'] + " " + res.resultats[i]['serie'] + " " + res.resultats[i]['cote'] +
                        " ";
                var temp = res.resultats[i]['auteurs'];
                for (j = 0; i<temp.nb; j++){
                    disp = disp + temp['auteur'] + " "
                }
                + "</span>" + "<br>";
                        
            }

            //attribution d'un lien a chaque element qui permet de récupérer l'id de la selection
            document.getElementById("recherche_resultat").innerHTML = disp;
            for (i = 0; i < res.nb; i++) {
                document.getElementById("res" + res.resultats[i]['id']).onclick = selection;
            }
        } else {
            document.getElementById("recherche_resultat").innerHTML = "";
        }
    }

    //formulaire envoyé en get à la servlet recherche
    var data = "titre=" + titre + "&" + "serie=" + serie + "&" + "auteur=" + auteur +"&" + "sujet=" + sujet + "&" + "all=" + all;
    xhttp.open("GET", "RechercheDocServlet?" + data, true);
    xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
    xhttp.send();

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
        xhttp.open("GET", "RechercheServlet?" + data, true);
        xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
        xhttp.send();




    }

}

