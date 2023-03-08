<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification de profil</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
	</nav>
	<section class="container">
		<h1 class="mt-4 text-center">Mon Profil</h1>
		<%@ include file="/WEB-INF/jsp/bandeauErreur.jspf"%>
		<form action="<%=request.getContextPath()%>/modification"
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
					<input class="form-control" id="prenom" name="prenom" value="${utilisateur.prenom}" required>
				</div>
				<label for="email" class="col-2 col-form-label">Email :</label>
				<div class="col-3">
					<input class="form-control" type="email" id="email" name="email" value="${utilisateur.email}" required>
				</div>
			</div>
			<div class="form-group row mt-4">
				<label for="telephone" class="col-2 offset-1 col-form-label">Téléphone :</label>
				<div class="col-3">
					<input class="form-control" type="tel" pattern="[0-9]{10}"
						id="telephone" name="telephone" value="${utilisateur.telephone}" required>
				</div>
				<label for="rue" class="col-2 col-form-label">Rue :</label>
				<div class="col-3">
					<input class="form-control" id="rue" name="rue" value="${utilisateur.rue}" required>
				</div>
			</div>
			<div class="form-group row mt-4">
				<label for="codePostal" class="col-2 offset-1 col-form-label">Code postal :</label>
				<div class="col-3">
					<input class="form-control" id="codePostal" name="codePostal" value="${utilisateur.codePostal}" required>
				</div>
				<label for="ville" class="col-2 col-form-label">Ville :</label>
				<div class="col-3">
					<input class="form-control" id="ville" name="ville" value="${utilisateur.ville}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="motDePasseActuel" class="col-2 offset-1 col-form-label">Mot de passe actuel :</label>
				<div class="col-3">
					<input class="form-control" type="password" id="motDePasseActuel" name="motDePasseActuel" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="nouveauMotDePasse" class="col-2 offset-1 col-form-label">Nouveau mot de passe :</label>
				<div class="col-3">
					<input class="form-control" type="password" id="nouveauMotDePasse" name="nouveauMotDePasse" required>
				</div>
				<label for="confirmation" class="col-2 col-form-label">Confirmation :</label>
				<div class="col-3">
					<input class="form-control" type="password" id="confirmation" name="confirmation" required>
				</div>
			</div>
			<div class="form-group row ">
				<label for="credit" class="col-2 offset-1 col-form-label">Crédit : ${utilisateur.credit}</label>
			</div>
			<div class="row justify-content-center mt-3">
				<button type="submit" class="btn btn-outline-success col-2">Enregistrer</button>
				<button type="button" class="col-2 offset-1 btn btn-outline-danger" data-toggle="modal" data-target="#modalSuppr">Supprimer mon compte</button>
			</div>
		</form>
	</section>
</body>
<div class="modal fade" id="modalSuppr" tabindex="-1" aria-labelledby="modalSupprLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalSupprLabel">Attention</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">Voulez-vous vraiment supprimer votre compte ?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
					<a href="<%=request.getContextPath()%>/suppression" class="btn btn-danger" role="button">Supprimer</a>
				</div>
		</div>
	</div>
</div>
</html>

