<%-- 
    Document   : lister
    Created on : 29 mars 2016, 08:03:25
    Author     : tom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Liste des parties</h1>
<!--<input type="button" value="Ajouter" onclick="chargeContenu('/film/ajouter')"/>-->
<c:forEach items="${parties}" var="partie">
    Partie numéro ${partie.id}, joueurs :
                                            <c:forEach items="${partie.listeUtilisateurs}" var="utilisateur">
                                                ${utilisateur.id} - ${utilisateur.nom}
                                           </c:forEach>
                                                
    <a href="<c:url value="/partie/rejoindre"></c:url>/${partie.id}">REJOINDRE</a>
    <br>
</c:forEach>
<br>    

