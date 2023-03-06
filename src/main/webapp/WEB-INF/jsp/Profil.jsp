<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="fr.eni.encheres.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<title>Profil</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
	<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
	</nav>
	
<section>
	<div class="profil">
	
		<%Utilisateur utilisateur =(Utilisateur) request.getAttribute("utilisateur"); %>
		
			<p>Pseudo : <%=utilisateur.getPseudo() %> </p>
			<p>Nom : <%=utilisateur.getNom() %></p>
			<p>Prénom : <%=utilisateur.getPrenom() %></p>
			<p>Email : <%=utilisateur.getEmail() %></p>
			<p>Telephone : <%=utilisateur.getTelephone() %> </p>
			<p>Rue : <%=utilisateur.getRue() %></p>
			<p>Code Postal : <%=utilisateur.getCodePostal() %></p>
			<p>Ville : <%=utilisateur.getVille() %></p>
			
		<% if(request.getAttribute("ok") != null){
				boolean ok = (boolean) request.getAttribute("ok");
				if(ok){%>
				--! Verifier le lien--
				<a href="<%=request.getContextPath()%>/modfierProfil">
				<button type="submit" value="modifier" name="modifier">Modifier</button>
				</a>
				<%}
				}%>
		
	
	</div>
</section>	



</body>
</html>