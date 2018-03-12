/**
 * Fonctions pour la page index_membre
 */

/**
 * Méthode pour obtenir la page de gestion avec le statut admin
 * @returns {undefined}
 */
function getGestion(){
    // Formulaire pour obtenir la page admin
    var form = document.createElement('form');
    form.method = "GET";
    form.action = "admin.htm";
    // Le paramètre idco - identifiant de connexion - est envoyé avec la requête
    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    c1.value = document.getElementById("idco").value;
    form.appendChild(c1);
    document.body.appendChild(form);
    form.submit();
}

/**
 * Méthode pour obtenir la page de compte
 * @returns {undefined}
 */
function getMesEmprunts(){
    // Formulaire pour obtenir la page admin
    var form = document.createElement('form');
    form.method = "GET";
    form.action = "mesemprunts.htm";
    // Le paramètre idco - identifiant de connexion - est envoyé avec la requête
    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    c1.value = document.getElementById("idco").value;
    form.appendChild(c1);
    document.body.appendChild(form);
    form.submit();
}


/**
 * Méthode pour obtenir la page d'ouvrage
 * @returns {undefined}
 */
function goToOuvrage(){
    // Formulaire pour obtenir la page admin
    var form = document.createElement('form');
    form.method = "GET";
    form.action = "ouvrage.htm";
    // Le paramètre idco - identifiant de connexion - est envoyé avec la requête
    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    c1.value = document.getElementById("idco").value;
    form.appendChild(c1);
    document.body.appendChild(form);
    form.submit();
}
/**
 * Méthode permettant d'afficher la pop-up 
 * si l'utilisateur souhaite modifier ses informations personnelles
 */
function pop_info() {
    $('#modif_form').modal('show');
}

