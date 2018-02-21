package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!-- PAGE D'ACCUEIL -->\r\n");
      out.write("\r\n");
      out.write("<!-- Tags -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n");
      out.write("    \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Club BD</title>\r\n");
      out.write("\r\n");
      out.write("        <!-- BOOTSTRAP -->\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\r\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!-- STYLES -->\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"Stylesheets/index.css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!-- SCRIPTS -->\r\n");
      out.write("        <script src=\"Scripts/navigation.js\"></script>\r\n");
      out.write("        <script src=\"Scripts/inscription.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- CONTENU PRINCIPAL -->\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container\">    \r\n");
      out.write("        <div  class=\"row content\">          \r\n");
      out.write("            <!-- Volet de gauche -->\r\n");
      out.write("            <div id=\"left_div\" class=\"col-md-4\">\r\n");
      out.write("                <p id=\"title\">Bienvenue sur le site du club BD</p>\r\n");
      out.write("                <!-- Formulaire de connexion -->\r\n");
      out.write("                <div class=\"modal-body\" width=\"100%\">  \r\n");
      out.write("                    <center>\r\n");
      out.write("                        <!-- Champ pour l'email -->\r\n");
      out.write("                        <input type=\"text\" name =\"email\" id=\"email\" placeholder=\"Email\">    \r\n");
      out.write("                        <!-- Champ pour le mot de passe -->\r\n");
      out.write("                        <input  type=\"password\" name=\"password\" id=\"password\"  style=\"margin-left:5px; margin-bottom:7px\" placeholder=\"Mot de passe\">\r\n");
      out.write("                        <!-- Bouton pour soumettre le formulaire de connexion -->\r\n");
      out.write("                        <button id =\"valid_connexion\" type=\"button\" class=\"button small_button\" onclick=\"\">Ok</button>\r\n");
      out.write("                        <!-- Lien pour réinitialiser son mot de passe -->\r\n");
      out.write("\r\n");
      out.write("                        <p id=\"mdp_oublie\" onclick=\"\"><i>Mot de passe oublié ?</i>\r\n");
      out.write("                            <a id=\"inscription\" href=\"#\" onclick=\"pop_inscription()\"><i>S'inscrire ?</i></a>\r\n");
      out.write("                            <br>\r\n");
      out.write("                    </center>\r\n");
      out.write("                    <!-- Différents onglets -->\r\n");
      out.write("                    <center><div class=\"onglet_separator\"></div>\r\n");
      out.write("                        <a class=\"onglets\" href=\"#\" onclick=\"getNewContent('news_content', 'search_content', 'suggestions_content')\">A la une</a>\r\n");
      out.write("                        <div class=\"onglet_separator\"></div>\r\n");
      out.write("                        <a class=\"onglets\" href=\"#\" onclick=\"getNewContent('search_content', 'news_content', 'suggestions_content')\">Rechercher...</a>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"onglet_separator\"></div>\r\n");
      out.write("                        <a class=\"onglets\" href=\"#\" onclick=\"getNewContent('suggestions_content', 'news_content', 'search_content')\">Suggestions de lecture</a>\r\n");
      out.write("                    </center>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <!-- Volet de droite -->\r\n");
      out.write("            <div id=\"right_div\" class=\"col-md-8\"> \r\n");
      out.write("                <!-- Bloc des news-->\r\n");
      out.write("                <div id=\"news_content\" class=\"bloc_home\">\r\n");
      out.write("                    <p> News</p>\r\n");
      out.write("                </div>\r\n");
      out.write("                <!--Bloc de recherche d'ouvrages-->\r\n");
      out.write("                <div id=\"search_content\" class=\"bloc_home\" style=\"display:none;\">\r\n");
      out.write("                    <div class=\"row\">\r\n");
      out.write("                        <p>Recherche</p>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("                <!-- Bloc des suggestions-->\r\n");
      out.write("                <div id =\"suggestions_content\" class=\"bloc_home\" style=\"display:none\">\r\n");
      out.write("                    <p>Suggestion</p>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!--POPUP : inscription-->\r\n");
      out.write("    <div class=\"modal fade\" id=\"inscription_form\" role=\"dialog\">\r\n");
      out.write("        <div class=\"modal-dialog modal-sm medium_modal\">\r\n");
      out.write("            <div class=\"modal-content modal_form\">\r\n");
      out.write("                <!-- Croix de fermeture -->\r\n");
      out.write("                <button class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
      out.write("                <!-- Titre -->\r\n");
      out.write("                <p class= \"title\"  style=\"margin-top: 40px\">Inscription</p> \r\n");
      out.write("                <!-- Zone pour les messages d'erreur -->\r\n");
      out.write("                <p id=\"inscription_error\" class=\"error_message\"></p>\r\n");
      out.write("                <!-- Formulaire d'inscription -->\r\n");
      out.write("                <div class=\"modal-body\">  \r\n");
      out.write("                    <!-- Champ pour le nom-->\r\n");
      out.write("                    \r\n");
      out.write("                    <input  type=\"text\" name=\"name\" id=\"inscription_name\">\r\n");
      out.write("                    <!-- Champ pour le prénom-->\r\n");
      out.write("                    <input  type=\"text\" name=\"firstname\" id=\"inscription_firstname\">\r\n");
      out.write("                    <!-- Champ pour l'email -->\r\n");
      out.write("                    <input type=\"text\" name =\"email\" id=\"inscription_email\">  \r\n");
      out.write("                    <!-- Champ pour le mot de passe-->\r\n");
      out.write("                    <input  type=\"password\" name=\"password\" id=\"inscription_password\">\r\n");
      out.write("                    <!-- Champ pour la confirmation du mot de passe-->\r\n");
      out.write("                    <input  type=\"password\" name=\"password\" id=\"inscription_password2\" style=\"margin-bottom:7px\">\r\n");
      out.write("                    <!-- Message d'information pour les champs obligatoires-->\r\n");
      out.write("                    <!-- Bouton pour soumettre le formulaire d'inscription-->\r\n");
      out.write("                    <center>\r\n");
      out.write("                        <button id =\"valid_inscription\" type=\"button\" class=\"button small_button\" onclick=\"inscription()\" style=\"margin-top: 20px\"></button>\r\n");
      out.write("                    </center>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
