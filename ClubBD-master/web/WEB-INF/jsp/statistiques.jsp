<%-- 
    Document   : statistiques
    Created on : 22 mars 2018, 14:05:50
    Author     : centrale
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <th>titre</th>
                <th>date</th>
                <th>serie</th>
            </tr>
            
            <c:forEach var="i" items="${stats}">
                <tr>
                    <td><c:out value="${i['titre']}"/></td>
                    <td><c:out value="${i['dateRecherche']}"/></td>
                    <td><c:out value="${i['serie']}"/></td>
                </tr>
            </c:forEach> 
        </table>
    </body>
</html>
