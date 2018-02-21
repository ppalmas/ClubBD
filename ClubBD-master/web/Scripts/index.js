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