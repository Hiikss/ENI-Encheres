<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<title>Erreur 500</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
	<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
</nav>
<section class="container">
	<div class="col col-md-8 offset-md-2 alert alert-danger text-center mt-4" role="alert">
		Erreur 500 - Problème interne au serveur
	</div>
	<div class="row justify-content-center mt-5">
		<a href="<%=request.getContextPath()%>/accueil" class="p-3 btn btn-outline-primary" role="button">Retour à l'accueil</a>
	</div>
</section>
</body>
</html>