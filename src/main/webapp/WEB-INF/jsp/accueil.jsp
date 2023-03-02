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
<nav class="navbar navbar-light bg-light">
  <a href="." class="navbar-brand">ENI-Encheres</a>
  <span class="navbar-text">
  	<a href="<%=request.getContextPath()%>/login">Se connecter</a>
  	|
  	<a href="<%=request.getContextPath()%>/signup">S'inscrire</a>
  </span>
</nav>
</body>
</html>