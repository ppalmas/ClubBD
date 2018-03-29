    var res;
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function get_propositions()
{
    document.getElementById("propositions_resultat").innerHTML="";
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {

            //Réponse de la servlet
            var answer = xhttp.responseText;
            res = JSON.parse(answer);
            console.log(res);
            //boucle daffichage
            var disp = "";
            if (res == null) {
                document.getElementById("propositions_resultat").innerHTML = "Erreur.";
            }
            else {
                document.getElementById("propositions_resultat").appendChild(makeULProp(res.resultats));
            }
        } else {
            document.getElementById("propositions_resultat").innerHTML = "";
        }
    }

    xhttp.open("GET", "PropositionServlet", true);
    xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
    xhttp.send();

}

function makeULProp(array) {
    var div = document.createElement('div');
    var item = document.createElement('table');
    console.log(array)
    console.log("lol");
    var ligne = document.createElement('tr');
    var colonne = document.createElement('th');
    colonne.appendChild(document.createTextNode("Nom de la proposition"));
    ligne.appendChild(colonne);
    var colonne = document.createElement('th');
    colonne.appendChild(document.createTextNode("Commentaire"));
    ligne.appendChild(colonne);
    item.appendChild(ligne);
    for (var i = 0; i < array.length; i++) {
        var ligne = document.createElement('tr');
        var colonne=document.createElement('td');
        colonne.appendChild(document.createTextNode(array[i]['nomProp']));
        ligne.appendChild(colonne);
        var colonne=document.createElement('td');
        colonne.appendChild(document.createTextNode(array[i]['comProp']));
        ligne.appendChild(colonne);

        item.appendChild(ligne);
    }

    div.appendChild(item);

    // Finally, return the constructed list:
    return div;
}