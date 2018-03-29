/**
 * ********************************************************************
 * Servlet Recherche
 * --------------------------------------------------------------------
 * Gestion des recherches
 *********************************************************************
 */
package Servlets;

import Database.Createurdocument;
import Database.Document;
import Database.Genredocument;
import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gestion des recherches
 * @author centrale
 */
@WebServlet(name = "RerchercheServlet", urlPatterns = {"/RechercheServlet"})
public class RechercheServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des paramètres via le javascript recherche2.js
        String titre = request.getParameter("titre");
        String cote = request.getParameter("cote");
        String serie = request.getParameter("serie");

        //transformation en liste critères
        ArrayList<String> criteres = new ArrayList();
        criteres.add(null);
        criteres.add(null);
        criteres.add(null);
        criteres.set(0, titre);
        criteres.set(1, serie);
        criteres.set(2, cote);
        List<Document> l = null;

        //Création d'une entité Document
        DocumentManager dm = DocumentManagerImpl.getInstance();

        JsonObject json = new JsonObject();
        JsonArray resultats = new JsonArray();

        try {
            l = dm.findDocumentCr(criteres);
            //creation d'un json pour exploiter les reponses dans le js
            System.out.println(criteres);
            for (int i = 0; i < l.size(); i++) {
                JsonObject temp = new JsonObject();
                temp.addProperty("titre", l.get(i).getTitre());

                try {
                    temp.addProperty("serie", l.get(i).getIdSerie().getNomSerie());

                } catch (Exception e) {
                    temp.addProperty("serie", "");

                }

                try {
                    temp.addProperty("numero", l.get(i).getNumero().toString());
                } catch (Exception e) {
                    temp.addProperty("numero", "");
                }
                //pour les créateurs

                List<Createurdocument> lcrea = new ArrayList(l.get(i).getCreateurdocumentCollection());

                try {
                    temp.addProperty("crea0", lcrea.get(0).getIdCreateur().getNomCreateur() + " " + lcrea.get(0).getIdCreateur().getPrenomCreateur() + "," + lcrea.get(0).getPoste());
                } catch (Exception e) {
                    temp.addProperty("crea0", "");
                }
                try {
                    temp.addProperty("crea1", lcrea.get(1).getIdCreateur().getNomCreateur() + " " + lcrea.get(1).getIdCreateur().getPrenomCreateur() + "," + lcrea.get(1).getPoste());
                } catch (Exception e) {
                    temp.addProperty("crea1", "");
                }
                try {
                    temp.addProperty("crea2", lcrea.get(2).getIdCreateur().getNomCreateur() + " " + lcrea.get(2).getIdCreateur().getPrenomCreateur() + "," + lcrea.get(2).getPoste());
                } catch (Exception e) {
                    temp.addProperty("crea2", "");
                }
                try {
                    temp.addProperty("crea3", lcrea.get(3).getIdCreateur().getNomCreateur() + " " + lcrea.get(3).getIdCreateur().getPrenomCreateur() + "," + lcrea.get(3).getPoste());
                } catch (Exception e) {
                    temp.addProperty("crea3", "");
                }
                try {
                    temp.addProperty("crea4", lcrea.get(4).getIdCreateur().getNomCreateur() + " " + lcrea.get(4).getIdCreateur().getPrenomCreateur() + "," + lcrea.get(4).getPoste());
                } catch (Exception e) {
                    temp.addProperty("crea4", "");
                }

                //genres
                try {
                    List<Genredocument> lgenre = new ArrayList(l.get(i).getGenredocumentCollection());
                    String genres = "";
                    for (int j = 0; j < lgenre.size(); j++) {
                        genres = genres + lgenre.get(j).getIdGenre().getNomGenre() + ",";

                    }
                    genres = genres.substring(0, genres.length() - 1);

                    temp.addProperty("genres", genres);
                } catch (Exception e) {
                    temp.addProperty("genres", "");

                }

                temp.addProperty("description", l.get(i).getDescription());
                temp.addProperty("commentaire", l.get(i).getCommentaire());
                temp.addProperty("image", l.get(i).getImageDocument());
                temp.addProperty("cote", l.get(i).getCote());
                temp.addProperty("id", l.get(i).getIdDocument().toString());
                temp.addProperty("etat", l.get(i).getIdEtat().getIdEtat().toString());
                resultats.add(temp);

            }

            json.add("resultats", resultats);
            json.addProperty("nb", l.size());

        } catch (Exception e) {

        }
        
        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(new Gson().toJson(json)); // Réponse : resultats

    }

}
