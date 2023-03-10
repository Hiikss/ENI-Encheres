<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"
	import="fr.eni.encheres.dal.*" import="fr.eni.encheres.bll.*"
	import="fr.eni.encheres.bo.*"
	import="java.time.format.DateTimeFormatter"
	import="java.time.format.FormatStyle"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail Vente</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
	</nav>
	<section class="container-fluid">
		<h1 class="mt-5 text-center">Détail Vente</h1>
		<%@ include file="/WEB-INF/jsp/bandeauErreur.jspf"%>
		<form action="<%=request.getContextPath()%>/DetailVente"%>

		<%-- Vérifier si l'utilisateur est le vendeur --%>
		<%
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		ArticleVendu article = (ArticleVendu) request.getAttribute("article");

		Enchere enchere = article.getMeilleureEnchere();

		Categorie categorie = article.getCategorieArticle();

		if (utilisateur.getNoUtilisateur() == article.getVendeur().getNoUtilisateur()) {
		%>
		<div class="form-group row mt-md-4 justify-content-center">
			<%=article.getNoArticle()%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<%=article.getDescription()%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="categorie"
				class="col-4 col-md-3 col-lg-2 col-form-label ">Catégorie :</label>
			<%=categorie.getLibelle()%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="miseAPrix"
				class="col-4 col-md-3 col-lg-2 col-form-label ">Mise à prix
				:</label>
			<%=article.getMiseAPrix()%>
			points
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="meilleureOffre"
				class="col-4 col-md-3 col-lg-2 col-form-label ">Meilleure
				offre :</label>
			<%=enchere.getMontantEchere()%>
			points
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="FinEnchere"
				class="col-4 col-md-3 col-lg-2 col-form-label ">Fin de
				l'enchère :</label>
			<%=article.getDateFinEncheres().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="Retrait" class="col-4 col-md-3 col-lg-2 col-form-label ">Retrait
				:</label>
			<%=article.getLieuRetrait()%>
		</div>

		<a href="<%=request.getContextPath()%>/NouvelleVente"
			class="col-2 offset-1 btn btn-outline-secondary" role="button">Modifier</a>
		<a href="<%=request.getContextPath()%>/accueil"
			class="col-2 offset-1 btn btn-outline-danger" role="button">Annuler</a>


		<%
		} else {
		%>

		<div class="form-group row mt-md-4 justify-content-center">
			<%=article.getNoArticle()%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<%=article.getDescription()%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="categorie"
				class="col-4 col-md-3 col-lg-2 col-form-label ">Catégorie :</label>
			<%=categorie.getLibelle()%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="miseAPrix"
				class="col-4 col-md-3 col-lg-2 col-form-label ">Mise à prix
				:</label>
			<%=article.getMiseAPrix()%>
			points
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="meilleureOffre"
				class="col-4 col-md-3 col-lg-2 col-form-label ">Meilleure
				offre :</label>
			<%=enchere.getMontantEchere()%>
			points
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="FinEnchere"
				class="col-4 col-md-3 col-lg-2 col-form-label ">Fin de
				l'enchère :</label>
			<%=article.getDateFinEncheres().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="Retrait" class="col-4 col-md-3 col-lg-2 col-form-label ">Retrait
				:</label>
			<%=article.getLieuRetrait()%>
		</div>

		<div class="form-group row mt-md-4 justify-content-center">
			<label for="Retrait" class="col-4 col-md-3 col-lg-2 col-form-label ">Vendeur
				:</label>
			<%=article.getVendeur().getPseudo()%>
		</div>

		<form method="POST" action="encherir.jsp">
			<input type="hidden" name="articleId"
				value="<%=article.getNomArticle()%>">
			<p>
				Ma proposition :
				<button type="submit" name="action" value="moins">-</button>
				<%=enchere.getMontantEchere()%>
				points
				<button type="submit" name="action" value="plus">+</button>
			</p>
		</form>
		<%
		}
		%>
	</section>
</body>
</html>