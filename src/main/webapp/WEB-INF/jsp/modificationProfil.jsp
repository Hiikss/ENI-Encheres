<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> <%@page import="fr.eni.encheres.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification de profil</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-light bg-light">
	<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
	</nav>
	<section class="container">
	<h1 class="mt-5 text-center">Mon Profil</h1>
	<%@ include file="/WEB-INF/jsp/bandeauErreur.jspf" %>
		<form action="<%=request.getContextPath()%>/accueil"
			method="post" class="mt-5">
			<div class="form-group row mt-4">
				<label for="pseudo" class="col-2 offset-1 col-form-label ">Pseudo :</label>
				<div class="col-3">
					<input class="form-control" id="pseudo" name="pseudo" value="${utilisateur.pseudo}" required>
				</div>
				<label for="nom" class="col-2 col-form-label">Nom :</label>
				<div class="col-3">
					<input class="form-control" id="nom" name="nom" value="${utilisateur.nom}" required>
				</div>
			</div>
			<div class="form-group row mt-4">
				<label for="prenom" class="col-2 offset-1 col-form-label">Prénom :</label>
				<div class="col-3">
					<input class="form-control" id="prenom" name="prenom" value="${utilisateur.prenom}"required>
				</div>
				<label for="email" class="col-2 col-form-label">Email :</label>
				<div class="col-3">
					<input class="form-control" type="email" id="email" name="email" value="${utilisateur.email}"
						required>
				</div>
			</div>
			<div class="form-group row mt-4">
				<label for="telephone" class="col-2 offset-1 col-form-label">Téléphone :</label>
				<div class="col-3">
					<input class="form-control" type="tel" pattern="[0-9]{10}" id="telephone" name="telephone" value="${utilisateur.telephone}"required>
				</div>
				<label for="rue" class="col-2 col-form-label">Rue :</label>
				<div class="col-3">
					<input class="form-control" id="rue" name="rue" value="${utilisateur.rue}"required>
				</div>
			</div>
			<div class="form-group row mt-4">
				<label for="codePostal" class="col-2 offset-1 col-form-label">Code postal :</label>
				<div class="col-3">
					<input class="form-control" id="codePostal" name="codePostal" value="${utilisateur.codePostal}"required>
				</div>
				<label for="ville" class="col-2 col-form-label">Ville :</label>
				<div class="col-3">
				<input class="form-control" id="ville" name="ville" value="${utilisateur.ville}"required>
				</div>
				</div>
			<div class="form-group row mt-4">
				<label for="motDePasseActuel" class="col-2 offset-1 col-form-label">Mot de passe actuel :</label>
				<div class="col-3">
					<input class="form-control" type="password" id="motDePasseActuel" name="motDePasseActuel" required>
				</div>
			</div>
			<div class="form-group row mt-4">
				<label for="nouveauMotDePasse" class="col-2 offset-1 col-form-label">Nouveau mot de passe :</label>
				<div class="col-3">
					<input class="form-control" type="password" id="nouveauMotDePasse" name="nouveauMotDePasse" required>
				</div>
				<label for="confirmation" class="col-2 col-form-label">Confirmation :</label>
				<div class="col-3">
				<input class="form-control" type="password" id="confirmation" name="confirmation" required>
				</div>
				</div>
				<div class="form-group row mt-4">
				<label for="credit" class="col-2 offset-1 col-form-label">Crédit : ${utilisateur.credit}</label>
				</div>
		<div class="row justify-content-center mt-5">
  			<button type="submit" class="btn btn-outline-success col-2">Enregistrer</button>
  			<a href="<%=request.getContextPath()%>/" class="col-2 offset-1 btn btn-outline-danger" role="button">Supprimer mon compte</a>
  		</div>
  		</form>
	</section>
</body>||
</html>

