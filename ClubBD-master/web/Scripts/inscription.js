/**
 * Fonctions pour gérer l'inscription d'un utilisateur 
 * */



/**
 * Inscription d'un utilisateur 
 * Se fait grâce à la Servlet ControlInscriptionServlet, et renvoie la page index_membre si l'inscription réussit
 */
function inscription() {

    //On récupère les infos
    var name = document.getElementById("nom_inscri").value;
    var firstname = document.getElementById("prenom_inscri").value;
    var email = document.getElementById("email_inscri").value;
    var mdp = document.getElementById("mdp_inscri").value;
    var mdp2 = document.getElementById("mdp_confirm_inscri").value;

    //Si la saisie est valide
    if (valid_form(name, firstname, mdp, mdp2, email)) {

        //On envoie le mail à une servlet pour voir si celui-ci est déjà utilisé ou non
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                //Réponse de la servlet
                var answer = xhttp.responseText;

                //Si l'adresse email n'est pas déjà prise, l'inscription a été effectuée. 
                //Reste à connecter la personne
                if (answer == "true") {

                    //Appel du controller pour effuectuer la connexion via un formulaire (en Post)
                    var form = document.createElement('form');
                    form.method = "POST";
                    form.action = "index_membre.htm";

                    var c1 = document.createElement('input');
                    c1.type = "hidden";
                    c1.name = "email";
                    c1.value = email;
                    form.appendChild(c1);

                    var c2 = document.createElement('input');
                    c2.type = "hidden";
                    c2.name = "mdp";
                    c2.value = mdp;
                    form.appendChild(c2);

                    var c3 = document.createElement('input');
                    c3.type = "hidden";
                    c3.name = "up";
                    c3.value = 0;
                    form.appendChild(c3);

                    document.body.appendChild(form);
                    form.submit();
                    alert("Votre inscription a bien été prise en compte.")
                    
                    //Si l'addresse email est déjà prise   
                } else {
                    //Message d'erreur
                    document.getElementById("inscription_error").innerHTML = "L'email entré est déjà utilisé";
                }

            } else {
                document.getElementById("inscription_error").innerHTML = "";
            }
        };
        var data = "email=" + email + "&" + "mdp=" + mdp + "&" + "name=" + name + "&" + "firstname=" + firstname;
        xhttp.open("GET", "ControlInscriptionServlet?" + data, true);
        xhttp.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
        xhttp.send();

    }


}

/**
 * Vérifier qu'une adresse email est syntaxiquement valide
 * @param {String} email
 * @returns {Boolean} 
 */
function valid_email(email) {
    var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');

    if (reg.test(email)) {
        return true;
    } else {
        //Message d'erreur
        document.getElementById("inscription_error").innerHTML = "L'email n'est pas valide.";
        return false;
    }
}

/**
 * Retourne vrai si word est non vide. Retourne un message d'erreur sinon
 * @param {String} email
 * @returns {Boolean} 
 */
function valid_word(word) {
    if (word == "") {
        //Message d'erreur
        document.getElementById("inscription_error").innerHTML = "Veuillez remplir tous les champs.";
        return false;
    } else {
        return true;
    }
}

/**
 * Vérification que le mot de passe contient plus de 6 caractères et que les 2 mdp sont égaux
 * @param {String} mdp1
 * @param {String} mdp2
 * @returns {Boolean}
 */
function valid_password(mdp1, mdp2) {
    if (mdp1.length < 6) {
        //Message d'erreur
        document.getElementById("inscription_error").innerHTML = "Veuillez entrer un mot de passe de plus de 6 caractères.";
        return false
    } else {
        if (mdp1 != mdp2) {
            //Message d'erreur
            document.getElementById("inscription_error").innerHTML = "Les deux mots de passe ne sont pas identiques.";
            return false;
        } else {
            return true;
        }
    }
}

/**
 * Validation du formulaire
 * @param {String} name
 * @param {String} firstname
 * @param {String} mdp
 * @param {String} mdp2
 * @param {String} email
 * @returns {Boolean}
 */
function valid_form(name, firstname, mdp, mdp2, email) {
    return valid_email(email) && valid_password(mdp, mdp2) && valid_word(name) && valid_word(firstname)
}
