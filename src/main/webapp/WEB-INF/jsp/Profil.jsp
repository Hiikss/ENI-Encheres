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
	<div class="container">
	
		<%Utilisateur utilisateur =(Utilisateur) request.getAttribute("utilisateur"); %>
		<div class="row justify-content-md-center">
			<div class="col col-lg-2">Pseudo : </div>
			<div class="col col-lg-2"><%=utilisateur.getPseudo() %> </div>
			</div>
			<div class="row justify-content-md-center">
			<div class="col col-lg-2">Nom : </div>
			<div class="col col-lg-2"><%=utilisateur.getNom() %></div>
			</div>
			<div class="row justify-content-md-center">
			<div class="col col-lg-2">Prénom : </div>
			<div class="col col-lg-2"><%=utilisateur.getPrenom() %></div>
			</div>
			<div class="row justify-content-md-center">
			<div class="col col-lg-2">Email : </div>
			<div class="col col-lg-2"><%=utilisateur.getEmail() %></div>
			</div>
			<div class="row justify-content-md-center">
			<div class="col col-lg-2">Telephone : </div>
			<div class="col col-lg-2"><%=utilisateur.getTelephone() %></div>
			</div>
			<div class="row justify-content-md-center">
			<div class="col col-lg-2">Rue : </div>
			<div class="col col-lg-2"><%=utilisateur.getRue() %></div>
			</div>
			<div class="row justify-content-md-center">
			<div class="col col-lg-2">Code Postal : </div>
			<div class="col col-lg-2"><%=utilisateur.getCodePostal() %></div>
			</div>
			<div class="row justify-content-md-center">
			<div class="col col-lg-2">Ville : </div>
			<div class="col col-lg-2"><%=utilisateur.getVille() %></div>
			</div>
			
		<% if(request.getAttribute("ok") != null){
				boolean ok = (boolean) request.getAttribute("ok");
				if(ok){%>
				
				<a href="<%=request.getContextPath()%>/modfierProfil" class="row justify-content-center mt-5">
				<button type="submit" value="modifier" name="modifier">Modifier</button>
				</a>
				
				<%}
				}%>
		
	
	</div>
</section>	



</body>
</html>