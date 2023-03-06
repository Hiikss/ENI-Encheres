<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création de compte</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-light bg-light">
	<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
	</nav>
	<h1 class="mt-5 text-center">Inscription</h1>
	<section class="container">
		<form action="<%=request.getContextPath()%>/signup"
			method="post" class="mt-5">
			<div class="form-group row mt-4 justify-content-center">
				<label for="pseudo" class="col-2 col-form-label ">Pseudo :</label>
				<div class="col-3">
					<input class="form-control" id="pseudo" name="pseudo" required>
				</div>
				<label for="nom" class="col-2 col-form-label">Nom :</label>
				<div class="col-3">
					<input class="form-control" id="nom" name="nom" required>
				</div>
			</div>
			<div class="form-group row mt-4 justify-content-center">
				<label for="prenom" class="col-2 col-form-label">Prénom :</label>
				<div class="col-3">
					<input class="form-control" id="prenom" name="prenom" required>
				</div>
				<label for="email" class="col-2 col-form-label">Email :</label>
				<div class="col-3">
					<input class="form-control" type="email" id="email" name="email"
						required>
				</div>
			</div>
			<div class="form-group row mt-4 justify-content-center">
				<label for="telephone" class="col-2 col-form-label">Téléphone :</label>
				<div class="col-3">
					<input class="form-control" type="tel" pattern="[0-9]{10}" id="telephone" name="telephone" required>
				</div>
				<label for="rue" class="col-2 col-form-label">Rue :</label>
				<div class="col-3">
					<input class="form-control" id="rue" name="rue" required>
				</div>
			</div>
			<div class="form-group row mt-4 justify-content-center">
				<label for="codePostal" class="col-2 col-form-label">Code postal :</label>
				<div class="col-3">
					<input class="form-control" id="codePostal" name="codePostal" required>
				</div>
				<label for="ville" class="col-2 col-form-label">Ville :</label>
				<div class="col-3">
				<input class="form-control" id="ville" name="ville" required>
				</div>
				</div>
			<div class="form-group row mt-4 justify-content-center">
				<label for="motDePasse" class="col-2 col-form-label">Mot de passe :</label>
				<div class="col-3">
					<input class="form-control" type="password" id="motDePasse" name="motDePasse" required>
				</div>
				<label for="confirmation" class="col-2 col-form-label">Confirmation : </label>
				<div class="col-3">
				<input class="form-control" type="password" id="confirmationMotDePasse" name="confirmation" required>
				</div>
			</div>
		<div class="row justify-content-center mt-5">
  			<button type="submit" class="btn btn-outline-success col-2">Créer</button>
  			<a href="<%=request.getContextPath()%>/accueil" class="col-2 offset-1 btn btn-outline-danger" role="button">Annuler</a>
  		</div>
  		</form>
	</section>
</body>
</html>