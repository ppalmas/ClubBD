/**
 * ********************************************************************
 * Servlet Modif Infos Perso Servlet
 * --------------------------------------------------------------------
 * Servlet pour gérer la modification des informations personnelles
 * d'un utilisateur
 *********************************************************************
 */
package Servlets;

import Database.Connect;
import Database.Membre;
import Database.Statut;
import Managers.ConnectManager;
import Managers.ConnectManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet pour gérer la modification des informations personnelles
 * d'un utilisateur
 * @author Utilisateur
 */
@WebServlet(name = "ModifInfosPersoServlet", urlPatterns = {"/ModifInfosPersoServlet"})
public class ModifInfosPersoServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //On récupère les paramètres
        String email = request.getParameter("email");
        String idco = request.getParameter("idco");
        String name = request.getParameter("nom");
        String firstname = request.getParameter("prenom");
        String mdp = request.getParameter("mdp1");
        String mdp_old = request.getParameter("mdp_old");
        String email_old = request.getParameter("email_old");
        //Mise à jour des connexions
        ConnectManager cm = ConnectManagerImpl.getInstance();
        Connect c = cm.getByConnectId(idco);
        cm.updateConnection(c);
        cm.checkConnection();

        //On récupère la personne qui correspond à l'email entré
        MembreManager pm = MembreManagerImpl.getInstance();
        Membre m = pm.findMembreByEmail(email);

        //On récupère la personne qui correspond à l'identifiant de connexion
        Membre m1 = pm.findMembre(idco);

        //On teste si le nouvel email est déjà utilisé par un autre utilisateur ou non 
        //Si m est nul, aucun utilisateur n'a l'email
       
        //si m = m1 alors il s'agit de la même personne donc true
        
        Boolean b = (m == null) || (m.equals(m1));

        //Si le mail est disponible
        if (b) {
            b = cm.identifierValidation(email_old, mdp_old); // Vérification mot de passe correct
            // Update si le mail est disponible et si le mot de passe est correct
            if (b) {
                //Update du nom, prénom et email
                pm.updateInfos(m1, name, firstname, email);
                if (!(mdp.equals(""))) {
                    //Update du mot de passe
                    pm.updateMdp(m1, mdp);
                }
            }
        }

        //Envoi de la réponse : booléen vrai si l'email n'est pas celui d'un autre utilisateur
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(b + "");
    }

}
