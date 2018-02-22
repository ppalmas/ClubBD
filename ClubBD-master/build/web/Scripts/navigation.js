/**
 * Fonction permettant de gérer les
 * différentes fonctionnalités de la barre de navigation
 */

/**
 * Afficher le contenu d'un nouvel onglet et cacher l'actuel
 * @param {type} thingShow to Show
 * @param {type} thingHide to Hide
 * @param {type} thingHide2 to Hide
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