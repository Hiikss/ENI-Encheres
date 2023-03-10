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

		Enchere enchere = null;
		if(article.getEncheres().size()>0){
			article.getMeilleureEnchere();
		}
		
		Categorie categorie = article.getCategorieArticle();
		%>
		<div class="form-group row mt-md-4 justify-content-center">
			<%=article.getNomArticle()%>
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
				<%if(enchere!=null){ %>
			<%=enchere.getMontantEchere()%>
			<%}else{ %>
			<%=article.getMiseAPrix() %>
			<%} %>
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
			<%=article.getLieuRetrait().getRue()%> <%=article.getLieuRetrait().getCode_postal()%> <%=article.getLieuRetrait().getVille()%>
		</div>
		<%if (utilisateur.getNoUtilisateur() == article.getVendeur().getNoUtilisateur()) {
		%>
		

		<a href="<%=request.getContextPath()%>/NouvelleVente"
			class="col-2 offset-1 btn btn-outline-secondary" role="button">Modifier</a>
		<a href="<%=request.getContextPath()%>/accueil"
			class="col-2 offset-1 btn btn-outline-danger" role="button">Annuler</a>


		<%
		} else {
		%>

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
				<input class="form-control" type ="number" name="miseAPrix" id="miseAPrix" step="1" max="10000" value=<%if(enchere!=null){ %>
			<%=enchere.getMontantEchere()%>
			<%}else{ %>
			<%=article.getMiseAPrix() %>
			<%} %>"" required>
				
				points
			</p>
			<button type="submit" class="btn btn-outline-success col-2">Enchérir</button>
		</form>
		<%
		}
		%>
	</section>
</body>
</html>