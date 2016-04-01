<%-- 
    Document   : lister
    Created on : 29 mars 2016, 08:03:25
    Author     : tom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Rejoindre Partie numéro ${partie.id} :</h1>
<div>
    <c:set var="url">
        <c:url value="/partie/rejoindre/${partie.id}"/>
    </c:set>
    <form:form modelAttribute="utilisateur" action="${url}" method="post">
        Nom d'utilisateur : <form:input path="nom"/>
        <input type="submit"/>
        <br>
    </form:form>
</div>