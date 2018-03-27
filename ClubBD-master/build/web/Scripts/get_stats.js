var res;
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function get_stats()
{
    document.getElementById("stats_resultat").innerHTML="";
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {

            //Réponse de la servlet
            var answer = xhttp.responseText;
            res = JSON.parse(answer);
            //boucle daffichage
            var disp = "";
            if (res == null) {
                document.getElementById("stats_resultat").innerHTML = "Erreur.";
            }
            else {
                document.getElementById("stats_resultat").appendChild(makeULStats(res.resultats));
            }
        } else {
            document.getElementById("stats_resultat").innerHTML = "";
        }
    }

    xhttp.open("GET", "StatistiquesServlet", true);
    xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
    xhttp.send();

}

function makeULStats(array) {
    var div = document.createElement('div');
    var item = document.createElement('table');
    var ligne = document.createElement('tr');
    var colonne = document.createElement('th');
    console.log("mdr");
    colonne.appendChild(document.createTextNode("Série/Titre"));
    ligne.appendChild(colonne);
    var colonne = document.createElement('th');
    colonne.appendChild(document.createTextNode("Nombre de recherches"));
    ligne.appendChild(colonne);
    item.appendChild(ligne);
    for (var i = 0; i < array.length; i++) {
        var ligne = document.createElement('tr');
        var colonne=document.createElement('td');
        colonne.appendChild(document.createTextNode(array[i]['nomRech']));
        ligne.appendChild(colonne);
        var colonne=document.createElement('td');
        colonne.appendChild(document.createTextNode(array[i]['nbRech']));
        ligne.appendChild(colonne);

        item.appendChild(ligne);
    }

    div.appendChild(item);

    // Finally, return the constructed list:
    return div;
}