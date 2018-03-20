/**
 * Fonction permettant de gérer les
 * différentes fonctionnalités de la barre de navigation
 */

/**
 * Afficher le contenu d'un nouvel onglet et cacher l'actuel
 * @param {type} thingShow to Show
 * @param {type} thingHide to Hide (list)
 * @returns {undefined}
 */


function getNewContent(thingShow, thingHide) {
    setVisible(thingShow);
    for (i = 0; i < thingHide.length; i++) {
        setInvisible(thingHide[i]);

    }
}

/**
 * Méthode rendant visible un élément d'une page HTM
 * @param {type} thingId
 * @returns {undefined}
 */
function setVisible(thingId) {
    var targetElement;
    targetElement = document.getElementById(thingId);
    targetElement.style.display = "";
}

/**
 * Méthode rendant invisibl un élément d'une page HTM
 * @param {type} thingId
 * @returns {undefined}
 */
function setInvisible(thingId) {
    var targetElement;
    targetElement = document.getElementById(thingId);
    targetElement.style.display = "none";
}

/**
 * Charge les différents éléments de la page d'index
 * suivant le niveau d'accès utilisateur (idStatut)
 * @param idStatut identifiant du statut
 * @returns {undefined}
 */
function loadIndexUser(idStatut) {
    if (idStatut == 3) {
        //L'utilisateur est seulement un centralien non cotisant
    } else if (idStatut == 1) {
        //L'utilisateur est un membre cotisant du club bd
        setVisible('gestion_emprunt');
    } else if (idStatut == 2) {
        //L'utilisateur est un admin
        setVisible('gestion_emprunt');
        setVisible('gestion_inventaire');

    } else {
        //message d'erreur ?
    }
}

/**
 * Retourner à la page d'accueil
 * @returns {undefined}
 */
function goHome() {
    var form = document.createElement('form');
    form.method = "GET";
    form.action = "index.htm";

    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    c1.value = 0;
    form.appendChild(c1);

    document.body.appendChild(form);
    form.submit();
}

/**
 * Retourner à la page d'accueil d'utilisateur connecté
 * @returns {undefined}
 */
function goHomeMember() {
    var form = document.createElement('form');
    form.method = "GET";
    form.action = "index_membre.htm";

    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    c1.value = document.getElementById("idco").value;
    form.appendChild(c1);

    document.body.appendChild(form);
    form.submit();
}

/**
 * Méthode pour obtenir la page d'ouvrage (à partir d'un utilisateur connecté)
 * @returns {undefined}
 */
function goToOuvrage() {
    // Formulaire pour obtenir la page de visualisation d'un ouvrage
    var form = document.createElement('form');
    form.method = "GET";
    form.action = "ouvrage.htm";
    // Le paramètre idco - identifiant de connexion - est envoyé avec la requête
    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    try {
        c1.value = document.getElementById("idco").value;
    } catch (Exception) {
        c1.value = 0;
    }
    form.appendChild(c1);
    document.body.appendChild(form);
    form.submit();
}
