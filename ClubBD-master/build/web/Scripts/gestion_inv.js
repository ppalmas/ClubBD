/**
 * Récupération des sources qui correspondent à un type donné et affichage
 * @returns {Liste de sources}
 */
var res; //reponse json de resultats de recherche
var idm; //id de lelement selectionné
var nbcrea = 0; // pour +/- crea
var nbcream = 0;


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

                    disp = disp + "<span class=\"res\" id=\"res" + res.resultats[i]['id'] + "\" idm=\"" + res.resultats[i]['id'] + "\" num=\"" + i + "\">" + res.resultats[i]['titre'] + " " + res.resultats[i]['serie'] + " " + res.resultats[i]['cote'] + "</span>" + "<br>";

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

    var cnp0 = document.getElementById("createura0").value;
    var cnp1 = document.getElementById("createura1").value;
    var cnp2 = document.getElementById("createura2").value;
    var cnp3 = document.getElementById("createura3").value;
    var cnp4 = document.getElementById("createura4").value;

    var genre = document.getElementById("genrea").value;
    
    
    







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
        var data = "genre=" + genre + "&cnp0=" + cnp0 + "&cnp1=" + cnp1 + "&cnp2=" + cnp2 + "&cnp3=" + cnp3 + "&cnp4=" + cnp4 + "&titre=" + titrea + "&" + "cote=" + cotea + "&" + "serie=" + seriea + "&" + "numero=" + numeroa + "&" + "description=" + descriptiona + "&" + "etat=" + etata + "&" + "commentaire=" + commentairea + "&" + "image=" + imagea + "&seriename=&seriedesc=&type=0&nomcrea=&prenomcrea=";
        xhttp.open("POST", "AjoutServlet?");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);







    } else {
        alert("Veuillez remplir tous les champs obligatoires (*)");
    }

}

function ajouterserie() {
    var seriename = document.getElementById("seriename").value;
    var seriedesc = document.getElementById("seriedesc").value;


    //Si la saisie est valide
    if (seriename !== "") {


        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;

                if (answer == "true") {


                    alert("Série ajoutée !");

                } else {
                    alert("Erreur lors de l'ajout.");
                }

            }
        };
        var data = "cnp0=&cnp1=&cnp2=&cnp3=&cnp4=&titre=&cote=&serie=&numero=&description=&etat=&commentaire=&image=&" + "seriename=" + seriename + "&" + "seriedesc=" + seriedesc + "&type=1";
        xhttp.open("POST", "AjoutServlet?");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);







    } else {
        alert("Veuillez remplir tous les champs obligatoires (*)");
    }

}


function ajoutercreateur() {
    var nomcrea = document.getElementById("nomcrea").value;
    var prenomcrea = document.getElementById("prenomcrea").value;


    //Si la saisie est valide
    if (nomcrea !== "" && prenomcrea !== "") {


        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;

                if (answer == "true") {


                    alert("Créateur ajouté !");

                } else {
                    alert("Erreur lors de l'ajout.");
                }

            }
        };
        var data = "cnp0=&cnp1=&cnp2=&cnp3=&cnp4=&titre=&cote=&serie=&numero=&description=&etat=&commentaire=&image=&seriename=&seriedesc=&type=2" + "&nomcrea=" + nomcrea + "&prenomcrea=" + prenomcrea;
        xhttp.open("POST", "AjoutServlet?");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);







    } else {
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

    var cnp0 = document.getElementById("createurm0").value;
    var cnp1 = document.getElementById("createurm1").value;
    var cnp2 = document.getElementById("createurm2").value;
    var cnp3 = document.getElementById("createurm3").value;
    var cnp4 = document.getElementById("createurm4").value;

    var genre = document.getElementById("genrem").value;
    var chg="false";
    
    console.log(document.getElementById("cotem").value+" "+res.resultats[num]['cote']);
    var a = document.getElementById("cotem").value;
    var b = res.resultats[num]['cote'];
    
    if (a!==b){chg="true";}





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

        var data = "chg="+chg+"&genre=" + genre + "&cnp0=" + cnp0 + "&cnp1=" + cnp1 + "&cnp2=" + cnp2 + "&cnp3=" + cnp3 + "&cnp4=" + cnp4 + "&idm=" + document.getElementById("idm").value + "&" + "titre=" + titrea + "&" + "cote=" + cotea + "&" + "serie=" + seriea + "&" + "numero=" + numeroa + "&" + "description=" + descriptiona + "&" + "etat=" + etata + "&" + "commentaire=" + commentairea + "&" + "image=" + imagea + "&type=0&seriename=&seriedesc=&complet=&idserie=";
        xhttp.open("POST", "ModifierServlet?");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);







    } else {
        alert("Veuillez remplir tous les champs obligatoires (*)");
    }


}

function modifierserie() {
    var seriename = document.getElementById("serienamem").value;
    var seriedesc = document.getElementById("seriedescm").value;
    var complet = 0;
    var chg="false";
    if (document.getElementById("serienamem")!=res.resultats[num]['serie']){chg="true"}

    //pour complet
    if (document.getElementById('completm').checked == true) {
        complet = 1;
    }

    //Si la saisie est valide
    if (seriename !== "") {


        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;

                if (answer == "true") {

                    alert("Série modifiée !");

                    //Si l'addresse email est déjà prise   
                } else {
                    alert("Erreur lors de la modification.");
                }

            }
        };

        var data = "chg="+chg+"&cnp0=&cnp1=&cnp2=&cnp3=&cnp4=&idm=&titre=&cote=&serie=&numero=&description=&etat=&commentaire=&image=&" + "seriename=" + seriename + "&seriedesc=" + seriedesc + "&complet=" + complet + "&type=1&idserie=" + document.getElementById("idms").value;
        xhttp.open("POST", "ModifierServlet?");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);







    } else {
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

    document.getElementById("createurm0").value = res.resultats[num]['crea0'];
    document.getElementById("createurm1").value = res.resultats[num]['crea1'];
    document.getElementById("createurm2").value = res.resultats[num]['crea2'];
    document.getElementById("createurm3").value = res.resultats[num]['crea3'];
    document.getElementById("createurm4").value = res.resultats[num]['crea4'];

    document.getElementById("genrem").value = res.resultats[num]['genres'];



    // pour letat

    listelem = document.getElementsByName("etatm");

    for (i = 0; i < 4; i++) {
        if (listelem[i].value == res.resultats[num]['etat']) {
            listelem[i].checked = true;
        } else {
            listelem[i].checked = false;
        }
    }



    setVisible('modif');




}

function selectionserie() {
    idm = this.getAttribute("idm");
    num = this.getAttribute("num");
    document.getElementById("serienamem").value = res.resultats[num]['seriename'];
    document.getElementById("seriedescm").value = res.resultats[num]['seriedesc'];


    document.getElementById("idms").value = idm;


    // pour complet

    if (res.resultats[num]['complet'] == true) {
        document.getElementById('completm').checked = true;
    } else {
        document.getElementById('completm').checked = false;
    }

    setVisible('modif');




}

function updatemembre(){
     var idstat =$('input[name=idmradio]:checked').val();
   

   

        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;

                if (answer == "true") {

                    alert("Statut modifié !");

                       
                } else {
                    alert("Erreur lors de la modification.");
                }

            }
        };

        var data = "type=4&"+"idmembre=" + document.getElementById("idmm").value +"&idstat="+idstat;
        xhttp.open("POST", "ModifierServlet?");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);







    


}

function selectionmembre() {
    idm = this.getAttribute("idm");
    num = this.getAttribute("num");
    document.getElementById("nommembrem").value = res.resultats[num]['nom'];
    document.getElementById("prenommembrem").value = res.resultats[num]['prenom'];


    document.getElementById("idmm").value = idm;


    // pour statut
    
        if (res.resultats[num]['idstat']==3){
            document.getElementById("membre").checked=true;
        }
        if (res.resultats[num]['idstat']==1){
            document.getElementById("staff").checked=true;
        }
        if (res.resultats[num]['idstat']==2){
            document.getElementById("admin").checked=true;
        }
    

    

    setVisible('modifm');




}

function refresh() {
    
    window.location.replace("admin.htm?idco=" + document.getElementById("idco").value);
    

}

function recherche2_serie() {
    //On récupère les infos
    var seriename = document.getElementById("nomseries").value;



    //Si non vide
    if (seriename != "") {
        console.log(seriename);
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

                    disp = disp + "<span class=\"res\" id=\"res" + res.resultats[i]['id'] + "\" idm=\"" + res.resultats[i]['id'] + "\" num=\"" + i + "\">" + res.resultats[i]['seriename'] + "</span>" + "<br>";

                }


                //attribution d'un lien a chaque element qui permet de récupérer l'id de la selection
                document.getElementById("recherche_resultat").innerHTML = disp;
                for (i = 0; i < res.nb; i++) {


                    document.getElementById("res" + res.resultats[i]['id']).onclick = selectionserie;


                }
            } else {
                document.getElementById("recherche_resultat").innerHTML = "";
            }
        }

        //formulaire envoyé en get à la servlet recherche
        var data = "seriename=" + seriename;
        xhttp.open("GET", "RechercheSerieServlet?" + data, true);
        xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
        xhttp.send();




    }

    setVisible('recherche_resultat');
}

function recherche2_membre() {
    //On récupère les infos
    var prenom = document.getElementById("prenommembre").value;
    var nom = document.getElementById("nommembre").value;




    //Si non vide
    if (prenom != "" || nom != "") {
        
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

                    disp = disp + "<span class=\"res\" id=\"res" + res.resultats[i]['id'] + "\" idm=\"" + res.resultats[i]['id'] + "\" num=\"" + i + "\">" + res.resultats[i]['prenom'] +" "+ res.resultats[i]['nom'] + "</span>" + "<br>";

                }


                //attribution d'un lien a chaque element qui permet de récupérer l'id de la selection
                document.getElementById("recherche_resultatm").innerHTML = disp;
                for (i = 0; i < res.nb; i++) {


                    document.getElementById("res" + res.resultats[i]['id']).onclick = selectionmembre;


                }
            } else {
                document.getElementById("recherche_resultatm").innerHTML = "";
            }
        }

        //formulaire envoyé en get à la servlet recherche
        var data = "prenom=" + prenom+"&nom="+nom;
        xhttp.open("GET", "RechercheMembreServlet?" + data, true);
        xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
        xhttp.send();




    }

    document.getElementById("recherche_resultatm").innerHTML = "";
}

function addcrea() {
    if (nbcrea == 0) {
        setVisible('crea1');
        nbcrea += 1;
    } else if (nbcrea == 1) {
        setVisible('crea2');
        nbcrea += 1;
    } else if (nbcrea == 2) {
        setVisible('crea3');
        nbcrea += 1;
    } else if (nbcrea == 3) {
        setVisible('crea4');
        nbcrea += 1;
    }

}

function rmvcrea() {
    if (nbcrea == 2) {
        setInvisible('crea2');
        document.getElementById("createura2").value = "";
        nbcrea -= 1;
    } else if (nbcrea == 1) {
        setInvisible('crea1');
        document.getElementById("createura1").value = "";
        nbcrea -= 1;
    } else if (nbcrea == 0) {

        document.getElementById("createura0").value = "";
    } else if (nbcrea == 3) {
        setInvisible('crea3');
        nbcrea -= 1;
        document.getElementById("createura3").value = "";
    } else if (nbcrea == 4) {
        setInvisible('crea4');
        nbcrea -= 1;
        document.getElementById("createura4").value = "";
    }


}

