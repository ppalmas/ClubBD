/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * 
 */
/**
 * Trigger pour valider le formulaire lorsqu'un des champs
 * input de celui-ci est sélectionné
 * @param {type} Liste liste de string : id des input
 * @returns {undefined}
 */
function load_listener(Liste) {
    for (i = 0; i < Liste.length; i++) {
        document.getElementById(Liste[i])
                .addEventListener("keyup", function (event) {
                    event.preventDefault();
                    if (event.keyCode === 13) {
                        document.getElementById("validation_button").click();
                    }
                });
    }
}

/**
 * Trigger pour valider le formulaire de recherche lorsqu'un des champs input
 * est sélectionné
 * @param Liste liste des champs input
 * @return Action comme si on cliquait sur le bouton recherche_button
 */
function load_listenerSearch(Liste) {
    for (i = 0; i < Liste.length; i++) {
        document.getElementById(Liste[i])
                .addEventListener("keyup", function (event) {
                    event.preventDefault();
                    if (event.keyCode === 13) {
                        document.getElementById("recherche_button").click();
                    }
                });
    }
}

/**
 * Trigger pour valider formulaire de proposition
 * @param {type} Liste
 * @returns {undefined}
 */
function load_listenerProposition(Liste){
    for (i = 0; i < Liste.length; i++) {
        document.getElementById(Liste[i])
                .addEventListener("keyup", function (event) {
                    event.preventDefault();
                    if (event.keyCode === 13) {
                        document.getElementById("validation_proposition").click();
                    }
                });
    }
}