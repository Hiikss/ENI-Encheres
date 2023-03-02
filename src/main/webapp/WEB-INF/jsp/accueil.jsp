<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<title>Accueil</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a href="." class="navbar-brand">ENI-Encheres</a>
  	<div class="collapse navbar-collapse" id="navbarNav">
		<div class="navbar-nav ml-auto">
        	<a class="nav-link" href="<%=request.getContextPath()%>/login">Se connecter</a>
        	<a class="nav-link" href="<%=request.getContextPath()%>/signup">S'inscrire</a>
        </div>
	</div>
</nav>
</body>
</html>