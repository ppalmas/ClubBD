/**
 * Fonctions javascript pour la pop-up de modification des données personnelles
 */
/**
 * Fonction permettant de valider la modification d'informations personnelles
 * @returns {undefined}
 */
function modif_infos() {
// Récupération des données saisies dans la page html
    var email = document.getElementById("email_modif").value;
    var email_old = document.getElementById("email").value;
    var nom = document.getElementById("nom_modif").value;
    var prenom = document.getElementById("prenom_modif").value;
    var mdp1 = document.getElementById("mdp1_modif").value;
    var mdp2 = document.getElementById("mdp2_modif").value;
    var mdp_old = document.getElementById("mdp_ancien_modif").value;
    //Si l'ancien mot de passe n'est pas renseigné, un message d'erreur 
    //est affiché pour le demander à l'utilisateur
    if ((mdp_old != "") && (mdp_old != null)) {
        //On vérifie que les deux mots de passe entrés sont valides et identiques
        if (valid_password(mdp1, mdp2)) {
//Envoi des modifications à la servlet
            xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    //Réponse de la servlet
                    var answer = xhttp.responseText;

                    //Si le nouvel email n'est pas utilisé par qqn d'autre, la mise à jour s'est effectuée
                    //On met à jout l'affichage et on ferme la pop-up
                    if (answer == "true") {

                        //Mise à jour de l'affichage
                        document.getElementById("modification_error").innerHTML = "";
                        document.getElementById("email").value = email;
                        document.getElementById("nom").value = nom;
                        document.getElementById("prenom").value = prenom;
                        document.getElementById("title").innerHTML = prenom + " " + nom;
                        document.getElementById("info_email").innerHTML = email;
                        //On efface la zone pour les messages d'erreur

                        //On ferme la pop-up
                        $('#modif_form').modal('hide');

                    } else {
                        //Message d'erreur
                        document.getElementById("modification_error").innerHTML = "Erreur, l'email est déjà utilisé ou le mot de passe est incorrect.";
                    }
                }
            };
            var data = "email_old=" + email_old + "&" + "email=" + email + "&" + "idco=" + document.getElementById("idco").value + "&" + "nom=" + nom +
                    "&" + "prenom=" + prenom + "&" + "mdp1=" + mdp1 + "&" + "mdp_old=" + mdp_old;
            ;
            xhttp.open("GET", "ModifInfosPersoServlet?" + data, true);
            xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
            xhttp.send();
        }
    } else {
        document.getElementById("modification_error").innerHTML = "Veuillez entrez votre actuel mot de passe.";
    }
}

/**
 * Vérification que le mot de passe contient plus de 6 caractères et que les 2 mdp sont égaux
 * @param {String} mdp1
 * @param {String} mdp2
 * @returns {Boolean}
 */
function valid_password(mdp1, mdp2) {
    //Si un des mots de passe est non nul, c'est que l'utilisateur souhaite le changer
    if ((mdp1 != "")&&(mdp1!=null)) {
        if (mdp1.length < 6) {
            //Message d'erreur
            document.getElementById("modification_error").innerHTML = "Veuillez entrer un mot de passe de plus de 6 caractères.";
            return false
        } else {
            if (mdp1 != mdp2) {
                //Message d'erreur
                document.getElementById("modification_error").innerHTML = "Les deux mots de passe ne sont pas identiques.";
                return false;
            } else {
                return true;
            }
        }
    } else {
        return true;
    }

}

