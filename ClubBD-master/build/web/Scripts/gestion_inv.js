/**
 * Récupération des sources qui correspondent à un type donné et affichage
 * @returns {Liste de sources}
 */
var res; //reponse json de resultats de recherche
var idm; //id de lelement selectionné
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

                res = JSON.parse(answer);


                //boucle daffichage

                var disp = "";
                for (i = 0; i < res.nb; i++) {

                    disp = disp + "<span id=\"res" + res.resultats[i]['id'] + "\" idm=\"" + res.resultats[i]['id'] + "\" num=\"" + i + "\">" + res.resultats[i]['titre'] + " " + res.resultats[i]['serie'] + " " + res.resultats[i]['cote'] + "</span>" + "<br>";

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
        var data = "titre=" + titre + "&" + "serie=" + serie + "&" + "cote=" + cote;
        xhttp.open("GET", "RechercheServlet?" + data, true);
        xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
        xhttp.send();




    }

setVisible('recherche_resultat');
}

function ajouter() {
    var titrea = document.getElementById("titrea").value;
    var seriea = document.getElementById("seriea").value;
    var numeroa = document.getElementById("numeroa").value;
    var descriptiona = document.getElementById("descriptiona").value;

    var etata = $('input[name=etat]:checked').val(); 
    
    var commentairea = document.getElementById("commentairea").value;
    var imagea = document.getElementById("imagea").value;
    var cotea = document.getElementById("cotea").value;



    


    //Si la saisie est valide
    if (titrea !== "" && cotea !== "" && etata !== "") {

    
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;

                if (answer == "true") {


                    alert("Document ajouté !");
                    
                     
                } else {
                    alert("Erreur lors de l'ajout.");
                }

            } 
        };
        var data = "titre=" + titrea + "&" + "cote=" + cotea + "&" + "serie=" + seriea + "&" + "numero=" + numeroa+ "&" + "description=" + descriptiona + "&" + "etat=" + etata + "&" + "commentaire="+commentairea+"&"+"image="+imagea;
        xhttp.open("POST", "AjoutServlet?");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhttp.send(data);
        
        
        




    }
    else{
        alert("Veuillez remplir tous les champs obligatoires (*)");
    }

}

function modifier() {
    var titrea = document.getElementById("titrem").value;
    var seriea = document.getElementById("seriem").value;
    var numeroa = document.getElementById("numerom").value;
    var descriptiona = document.getElementById("descriptionm").value;
    var etata = $('input[name=etatm]:checked').val(); 
    var commentairea = document.getElementById("commentairem").value;
    var imagea = document.getElementById("imagem").value;
    var cotea = document.getElementById("cotem").value;



    


    //Si la saisie est valide
    if (titrea !== "" && cotea !== "" && etata !== "") {

        
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;

                if (answer == "true") {
                    
                    alert("Document modifié !");
                    
                    
                    //Si l'addresse email est déjà prise   
                } else {
                    alert("Erreur lors de la modification.");
                }
                
            } 
        };
        
        var data = "idm="+idm+"&"+"titre=" + titrea + "&" + "cote=" + cotea + "&" + "serie=" + seriea + "&" + "numero=" + numeroa+ "&" + "description=" + descriptiona + "&" + "etat=" + etata + "&" + "commentaire="+commentairea+"&"+"image="+imagea;
        xhttp.open("POST", "ModifierServlet?");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhttp.send(data);
        
        
        
        



    }
    else{
        alert("Veuillez remplir tous les champs obligatoires (*)");
    }


}

function selection() {
    idm = this.getAttribute("idm");
    num = this.getAttribute("num");
    document.getElementById("titrem").value = res.resultats[num]['titre'];
    document.getElementById("seriem").value = res.resultats[num]['serie'];
    document.getElementById("cotem").value = res.resultats[num]['cote'];
    document.getElementById("descriptionm").value = res.resultats[num]['description'];
    document.getElementById("commentairem").value = res.resultats[num]['commentaire'];
    
    document.getElementById("numerom").value = res.resultats[num]['numero'];
    document.getElementById("imagem").value = res.resultats[num]['image'];

    document.getElementById("idm").value = idm;
    
    
    // pour letat
    
    elts=document.getElementsByName("etatm");
    
    for(i=1;i<6;i++){
        if (elts[i].value==res.resultats[num]['etat']){
            elts[i].checked=true;
        }else{elts[i].checked=false;
    }
    
    
    
    setVisible('modif');



    }
}