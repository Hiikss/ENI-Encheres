<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail de l'enchère</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light bg-light">
	<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
	</nav>
<h1>Details de l'enchère</h1>
	<p>Nom de l'article : ${enchere.nom}</p>
	<p>Description : ${enchere.description}</p>
	<p>Meilleure offre actuelle : ${enchere.meilleureOffre}</p>
	<p>Mise à prix initiale: ${enchere.miseAPrix}</p>
	<p>Date de début : ${enchere.dateDebut}</p>
	<p>Date de fin : ${enchere.dateFin}</p>
	<p>Adresse de retrait : ${enchere.adresseRetrait}</p>
	<p>Nom du vendeur : ${enchere.vendeur}</p>
	
	<%-- Formulaire pour soumettre une enchère --%>
	<form action="SoumettreEnchereServlet" method="post">
		<label for="montant">Montant de l'enchère :</label>
		<input type="text" id="montant" name="montant">
		<input type="submit" value="Soumettre une enchère">
	</form>
</body>
</html>