/**
 * Fonction permettant de gérer les
 * différentes fonctionnalités de la page ouvrage
 */

/**
 * Méthode pour afficher la disponibilité
 * @param {String} dispo
 */
function disponibility(dispo){
    var el = document.getElementById("dispo");
    if (dispo ===  "disponible"){
        var s = '<div><p style="margin-left:225px; border-radius:50%; width:25px; height:25px; border:2px solid; background-color: green; text-indent: 3em;">Disponible</p></div>';
        el.innerHTML = s;
    } else {
        if (dispo === "nondisponible"){
            var s = '<p style="margin-left:225px; border-radius:50%; width:25px; height:25px; border:2px solid; background-color: red; text-indent: 3em;">Non&nbsp;disponible</p>';
            el.innerHTML = s;
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
 */
function reserver(idco){
    if (idco===0){
        pop_userNotConnect();
    }
    else{
        //Reserver
    }
}

/**
 * Fonctions pour retourner les emprunts
 * @param {type} idStatut 
 * */
function retourner(idStatut){
}

/**
 * Charge les différents éléments de la page d'index
 * suivant le niveau d'accès utilisateur (idStatut)
 * @param idStatut identifiant du statut
 * @returns {undefined}
 */
function loadOuvrageUser(idStatut){
    if (idStatut===3){
        //L'utilisateur est seulement un centralien non cotisant
    } else if (idStatut===1 || idStatut===2){
        //L'utilisateur est un membre cotisant du club bd ou un admin
        setVisible('retourner_button');
    } else {
        //message d'erreur ?
    }
}
