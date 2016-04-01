<%-- 
    Document   : lister
    Created on : 29 mars 2016, 08:03:25
    Author     : tom
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Partie numéro ${partie.id}</h1>
<h3> côté du joueur : ${utilisateur.nom}
<br><br>

<a href="<c:url value="/partie/jouer"></c:url>/${partie.id}">CARTE SUIVANTE</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="/partie/totem"></c:url>/${partie.id}">TOTEM</a>
<br><br>

<c:forEach items="${listeUtilisateurs}" var="utilisateur">
    - ${utilisateur.nom} 
    Nombre de cartes restantes : ${utilisateur.nbrCartes} 
<br>

<div style="width:500px;height:100px;border:1px solid #000;background-color: ${utilisateur.couleurCarteActuelle}">
    Carte
</div>

</c:forEach>

<br>

