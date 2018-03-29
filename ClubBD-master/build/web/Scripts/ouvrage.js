/**
 * Fonction permettant de gérer les
 * différentes fonctionnalités de la page ouvrage
 */

/**
 * Méthode pour afficher la disponibilité
 * @param {String} dispo
 * @param emprunt
 */
function disponibility(dispo, emprunt){
    var el = document.getElementById("dispo");
    if (dispo ===  "disponible"){
        setVisible('reserver_button');
        var s = '<div><p style="margin-left:225px; border-radius:50%; width:25px; height:25px; border:2px solid; background-color: green; text-indent: 3em;">Disponible</p></div>';
        el.innerHTML = s;
    } else {
        if (dispo === "nondisponible"){
            if (emprunt === "emprunte"){
                setInvisible('reserver_button');
                var s = '<p style="margin-left:225px; border-radius:50%; width:25px; height:25px; border:2px solid; background-color: red; text-indent: 3em;">Emprunté</p>';
                el.innerHTML = s;
            }
            else{
                if (emprunt === "reserve"){
                    setInvisible('reserver_button');
                    var s = '<p style="margin-left:225px; border-radius:50%; width:25px; height:25px; border:2px solid; background-color: orange; text-indent: 3em;">Réservé</p>';
                    el.innerHTML = s;                    
                }
            }
        }
    }
}

/**
 * Méthode permettant d'afficher la pop-up 
 * si l'utilisateur a oublié son mot de passe
 */
function pop_userNotConnect() {
    $('#connect-form').modal('show');
}

/**
 * Méthode pour reserver un ouvrage
 * @param {type} idco 
 * @param {type} iddoc 
 */
function reserver(idco, iddoc){
    if (idco==="0"){
        pop_userNotConnect();
    }
    else{
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;
                if (answer === "true") {
                    alert("Vous avez réservé le document");
                    window.location.reload();
                } else {
                    alert("Erreur lors de la réservation.");
                }
            }
        };
        var data = "idco="+idco+"&"+"iddoc="+iddoc;
        xhttp.open("POST", "ReserveServlet?"+data);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);
    }
}


/**
 * Méthode pour valider l'emprunt un ouvrage
 * @param idStatut 
 */
function valider(idStatut){
    if (idStatut===1 || idStatut===2){
        pop_userValidate();
    }
    else{
        alert("Vous n'avez pas les autorisations requises");
    }
}

/**
 * Méthode permettant d'afficher la pop-up 
 * de confirmation d'emprunt
 */
function pop_userValidate() {
    $('#valider-form').modal('show');
    getRetour();
}

/**
 * Méthode pour valider l'emprunt un ouvrage
 * @param idStatut
 * @param {type} idco
 * @param {type} idemp  
 */
function confirmer(idStatut, idco, idemp){
    if (idStatut===1 || idStatut===2){
        var jour = document.getElementById("jourInput").value;
        var mois = document.getElementById("moisInput").value;
        var annee = document.getElementById("anneeInput").value;
        var date_retour = annee+"-"+mois+"-"+jour;
        
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;
                if (answer === "true") {
                    alert("La réservation du document est validée.");
                    window.location.reload();
                } else {
                    alert("Erreur lors de la validation.");
                }
            }
        };
        var data = "idco="+idco+"&"+"idemp="+idemp+"&"+"date_retour="+date_retour;
        xhttp.open("POST", "ValiderServlet?"+data);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);
    }   
    else{
        alert("Vous n'avez pas les autorisations requises");
    }
}

/**
 * Fonctions pour retourner les emprunts
 * @param {type} idStatut
 * @param {type} idemp  
 * */
function retourner(idStatut, idemp){
    if (idStatut===1 || idStatut===2){
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;
                if (answer === "true") {
                    alert("Le document retourné est de nouveau disponible.");
                    window.location.reload();
                } else {
                    alert("Erreur lors du retour du document.");
                }
            }
        };
        var data = "idemp="+idemp;
        xhttp.open("POST", "RetourServlet?"+data);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhttp.send(data);
    }   
    else{
        alert("Vous n'avez pas les autorisations requises");
    }
}

/**
 * Charge les différents éléments de la page d'index
 * suivant le niveau d'accès utilisateur (idStatut)
 * @param idStatut identifiant du statut
 * @param {type} dispo 
 * @param {type} emprunt 
 * @returns {undefined}
 */
function loadOuvrageUser(idStatut, dispo, emprunt){
    if (idStatut===3 || idStatut===0){
        //L'utilisateur est seulement un centralien non cotisant
    } else if (idStatut===1 || idStatut===2){
        if (dispo === 'nondisponible'){
            //L'utilisateur est un membre cotisant du club bd ou un admin
            if (emprunt === 'emprunte'){
                setVisible('retourner_button');
                setInvisible('valider_button');
            }
            else{
                if (emprunt === 'reserve'){
                    setVisible('valider_button');
                    setInvisible('reserver_button');
                }
            }
        }
    } else {
        //message d'erreur ?
    }
}
    
/**
 * Renvoie la date actuelle + 2 semaines
 */
function getRetour(){
    var today = new Date();
    today.setDate(today.getDate() + 14);
    var dd = today.getMonth();
    if(dd<10){
        dd = '0'+dd;
    }
    var jour = document.getElementById("jourInput").value=dd;
    
    var mm = today.getMonth()+1; //January is 0!
    if(mm<10) {
        mm = '0'+mm;
    }
    var mois = document.getElementById("moisInput").value=mm;

    var aa = today.getFullYear(); 
    var annee = document.getElementById("anneeInput").value=aa;
}                            