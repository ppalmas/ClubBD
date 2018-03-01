/**
 * Récupération des sources qui correspondent à un type donné et affichage
 * @returns {Liste de sources}
 */
function recherche_doc() {
    //Nombre total de sources
    var cpt = document.getElementById("nbSources").value;
    //Type désiré
    var type = document.getElementById("select_source").value;
    //On récupère toutes les sources de ce type
    var sources = [];
    if (language == "fr" && type != chose_source_fr) {
        for (var i = 0; i < cpt; i++) {
            var s_type = document.getElementById("src_" + i + "_type").value;
            if (s_type == type) {
                var s = new Object();
                s.title = document.getElementById("src_" + i + "_title").value;
                s.index = i;
                s.score = 0;
                sources.push(s);
            }
        }
    }
    
    //Affichage
    displaySources(sources);
    return sources;
}