/**
 * ********************************************************************
 * Class utilities
 * Fonction return_page simple
 *********************************************************************
 */
package Utilities;

import Controllers.InscriptionController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Fonction return_page simple
 * @author Paola
 */
public class utilities {

    /**
     *
     * @param page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected static void return_page(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            dispatcher.include(request, response);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
