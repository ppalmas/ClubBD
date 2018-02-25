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
function loadIndexUser(idStatut){
    if (idStatut==3){
        //L'utilisateur est seulement un centralien non cotisant
    } else if (idStatut==1){
        //L'utilisateur est un membre cotisant du club bd
        setVisible('gestion_emprunt');
    } else if (idStatut==2) {
        //L'utilisateur est un admin
        setVisible('gestion_emprunt');
        setVisible('gestion_inventaire');
        
    } else {
        //message d'erreur ?
    }
}
