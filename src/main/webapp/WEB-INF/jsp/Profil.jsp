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
<section class="container">
		<%Utilisateur utilisateur =(Utilisateur) request.getAttribute("utilisateur"); %>
		<div class="row mt-5">
			<div class="col-4 col-lg-2 offset-1 offset-lg-4">Pseudo : </div>
			<div class="col-6 col-lg-3"><%=utilisateur.getPseudo() %> </div>
		</div>
		<div class="row mt-2">
			<div class="col-4 col-lg-2 offset-1 offset-lg-4">Nom : </div>
			<div class="col-6 col-lg-3"><%=utilisateur.getNom() %></div>
		</div>
		<div class="row mt-2">
			<div class="col-4 col-lg-2 offset-1 offset-lg-4">Prénom : </div>
			<div class="col-6 col-lg-3"><%=utilisateur.getPrenom() %></div>
		</div>
		<div class="row mt-2">
			<div class="col-4 col-lg-2 offset-1 offset-lg-4">Email : </div>
			<div class="col-6 col-lg-3"><%=utilisateur.getEmail() %></div>
		</div>
		<div class="row mt-2">
			<div class="col-4 col-lg-2 offset-1 offset-lg-4">Telephone : </div>
			<div class="col-6 col-lg-3"><%=utilisateur.getTelephone() %></div>
		</div>
		<div class="row mt-2">
			<div class="col-4 col-lg-2 offset-1 offset-lg-4">Rue : </div>
			<div class="col-6 col-lg-3"><%=utilisateur.getRue() %></div>
		</div>
		<div class="row mt-2">
			<div class="col-4 col-lg-2 offset-1 offset-lg-4">Code Postal : </div>
			<div class="col-6 col-lg-3"><%=utilisateur.getCodePostal() %></div>
		</div>
		<div class="row mt-2">
			<div class="col-4 col-lg-2 offset-1 offset-lg-4">Ville : </div>
			<div class="col-6 col-lg-3"><%=utilisateur.getVille() %></div>
		</div>
			
		<% if(request.getAttribute("ok") != null){
		   boolean ok = (boolean) request.getAttribute("ok");
			if(ok){%>
				
			<a href="<%=request.getContextPath()%>/modification" class="col-4 col-lg-2 offset-4 offset-lg-5 btn btn-outline-primary mt-5 p-3" role="button">Modifier</a>
				
			<%}
			}%>
	
</section>	
</body>
</html>