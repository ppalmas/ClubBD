/**
 * Fonctions pour gérer la page d'index
 */

/**
 * Méthode permettant d'afficher la pop-up 
 * si l'utilisateur a oublié son mot de passe
 */
function pop_mdpoublie() {
    $('#oubli_form').modal('show');
}

/**
 * Méthode permettant d'afficher la page d'inscription
 * (Appel au controleur IndexController en méthode GET)
 */
function getInscription() {

    var form = document.createElement('form');
    form.method = "GET";
    form.action = "inscription.htm";
    document.body.appendChild(form);
    form.submit();

}

/**
 * Méthode pour obtenir la page d'ouvrage
 * (Appel au controleur IndexController en méthode GET)
 */
function goToOuvrageNotConnect(){
    // Formulaire pour obtenir la page admin
    var form = document.createElement('form');
    form.method = "GET";
    form.action = "ouvrage.htm";
    // Le paramètre idco - identifiant de connexion - est envoyé avec la requête
    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    c1.value = "0";
    form.appendChild(c1);
    document.body.appendChild(form);
    form.submit();
}