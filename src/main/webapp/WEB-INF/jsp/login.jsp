<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<title>Se connecter</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
	<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
</nav>
<section class="container">
	<form action="<%=request.getContextPath()%>/login" method="post" class="mt-5">
  		<div class="form-group row justify-content-center">
    		<label for="identifiant" class="col-2 col-form-label ">Identifiant :</label>
    		<div class="col-3">
    			<input class="form-control" id="identifiant" name="identifiant" required>
    		</div>
  		</div>
 		 <div class="form-group row justify-content-center">
    		<label for="motDePasse" class="col-2 col-form-label">Mot de passe :</label>
    		<div class="col-3">
   				<input type="password" class="form-control" id="motDePasse" name="motDePasse" required>
   			</div>
  		</div>
  		<div class="row justify-content-center mt-4">
  			<button type="submit" class="btn btn-outline-success">Connexion</button>
  			<div class="col-3">
  				<div class="row justify-content-center">
  					<div class="form-group form-check">
    					<input type="checkbox" class="form-check-input" id="seSouvenir">
    					<label class="form-check-label" for="seSouvenir">Se souvenir de moi</label>
  					</div>
  				</div>
  				<div class="row justify-content-center">
  					<a href="#" >Mot de passe oublié</a>
  				</div>
  			</div>
  		</div>
	</form>
	<div class="row justify-content-center mt-5">
		<a href="<%=request.getContextPath()%>/signup" class="col-3 btn btn-outline-primary" role="button">Créer un compte</a>
	</div>
</section>
</body>
</html>