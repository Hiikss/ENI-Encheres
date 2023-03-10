<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.encheres.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.format.FormatStyle"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>Accueil</title>
</head>
<body>
<%
String recherche = request.getParameter("recherche");
int categorie=0;
if(request.getParameter("categorie")!=null) {
	categorie = Integer.parseInt(request.getParameter("categorie"));
}
%>
<nav class="navbar navbar-expand-sm navbar-light bg-light">
	<a href="." class="navbar-brand">ENI-Encheres</a>
  	<div class="collapse navbar-collapse" id="navbarNav">
		<div class="navbar-nav ml-auto">
		<%
			if(session.getAttribute("utilisateur")==null)
			{
		%>
        	<a class="nav-link" href="<%=request.getContextPath()%>/login">Se connecter</a>
        	<a class="nav-link" href="<%=request.getContextPath()%>/signup">S'inscrire</a>
        <%
			}
			else {
        %>
        	<a class="nav-link" href=".">Enchères</a>
        	<a class="nav-link" href="<%=request.getContextPath()%>/NouvelleVente">Vendre un article</a>
        	<a class="nav-link" href="<%=request.getContextPath()%>/profil">Mon profil</a>
        	<a class="nav-link" href="<%=request.getContextPath()%>/logout">Déconnexion</a>
        <%
			}
        %>
        
        </div>
	</div>
</nav>
<header>
	<%
		if(session.getAttribute("utilisateur")==null)
		{
	%>
		<div class="row justify-content-around">
       		<a class="d-sm-none" href="<%=request.getContextPath()%>/login">Se connecter</a>
       		<a class="d-sm-none" href="<%=request.getContextPath()%>/signup">S'inscrire</a>
       	</div>
    <%
		}
		else {
    %>
   		<div class="row justify-content-around">
	       	<a class="d-sm-none" href=".">Vendre un article</a>
	       	<a class="d-sm-none" href="<%=request.getContextPath()%>/profil">Mon profil</a>
	       	<a class="d-sm-none" href="<%=request.getContextPath()%>/logout">Déconnexion</a>
	    </div>
    <%
		}
    %>
	<h1 class="mt-4 mb-5 text-center">Liste des enchères</h1>
</header>
<section class="container mt-4"><!-- d-flex justify-content-center justify-content-sm-start-->
	<form action="<%=request.getContextPath()%>/accueil" method="get">
		<div class="row">
			<article class="col-12 col-lg-6">
				<div class="row">
					<div class="col-12 order-1">
						<div class="row">
							<h5 class="col-10 offset-1 col-lg-12 offset-lg-0">Filtres :</h5>
						</div>
					</div>
					<div class="col-12 order-3 order-lg-2">
						<div class="row">
							<div class="col-10 mt-lg-2 offset-1 offset-lg-0 order-last order-lg-first pr-0">
								<div class="input-group">
									<div class="input-group-prepend">
						       			<i class="bi bi-search input-group-text"></i>
						    		</div> 
						      		<input type="text" class="form-control" id="recherche" name="recherche" placeholder="Le nom de l'article contient" <%if(recherche!=null&&!recherche.isBlank()){%>value="${recherche}"<%}%>>
					    		</div>
			    			</div>
			    			<div class="col-10 offset-1 col-lg-12 offset-lg-0 mt-3 order-first order-lg-last">
					    		<div class="form-group row">
					      			<label class="col-4 col-lg-3 d-flex align-items-center" for="categorie">Catégorie :</label>
					      			<select id="categorie" name="categorie" class="form-control col-8 col-lg-7">
					        			<option value="0" <%if(categorie==0){%>selected<%}%>>Toutes</option>
					        			<option value="1" <%if(categorie==1){%>selected<%}%>>Informatique</option>
					        			<option value="2" <%if(categorie==2){%>selected<%}%>>Ameublement</option>
					        			<option value="3" <%if(categorie==3){%>selected<%}%>>Vêtement</option>
					        			<option value="4" <%if(categorie==4){%>selected<%}%>>Sport &amp; loisirs</option>
					      			</select>
					    		</div>
				    		</div>
				    	</div>
				    </div>
		    		<%
						if(session.getAttribute("utilisateur")!=null)
						{
					%>
					<div class="col-12 order-2 order-lg-3">
						<div class="row">
							<div class="col-8 offset-2 col-lg-6 offset-lg-0 mt-2 mt-lg-0">
								<div class="form-check">
		  							<input class="form-check-input" type="radio" name="type" id="achats" value="achats" checked>
		  							<label class="form-check-label" for="achats">Achats</label>
								</div>
								<div class="form-check ml-4">
		  							<input class="form-check-input" type="checkbox" value="" id="encheresOuvertes">
		  							<label class="form-check-label" for="encheresOuvertes">enchères ouvertes</label>
								</div>
								<div class="form-check ml-4">
		  							<input class="form-check-input" type="checkbox" value="" id="mesEncheres">
		  							<label class="form-check-label" for="mesEncheres">mes enchères</label>
								</div>
								<div class="form-check ml-4">
		  							<input class="form-check-input" type="checkbox" value="" id="mesEncheresRemportees">
		  							<label class="form-check-label" for="mesEncheresRemportees">mes enchères remportées</label>
								</div>
							</div>
							<div class="col-8 offset-2 col-lg-6 offset-lg-0 mt-2 mt-lg-0">
								<div class="form-check">
		  							<input class="form-check-input" type="radio" name="type" id="ventes" value="ventes">
		  							<label class="form-check-label" for="ventes">Mes ventes</label>
								</div>
								<div class="form-check ml-4">
		  							<input class="form-check-input" type="checkbox" value="" id="ventesEnCours">
		  							<label class="form-check-label" for="ventesEnCours">mes ventes en cours</label>
								</div>
								<div class="form-check ml-4">
		  							<input class="form-check-input" type="checkbox" value="" id="ventesNonDebutees">
		  							<label class="form-check-label" for="ventesNonDebutees">ventes non débutées</label>
								</div>
								<div class="form-check ml-4">
		  							<input class="form-check-input" type="checkbox" value="" id="ventesTerminees">
		  							<label class="form-check-label" for="ventesTerminees">ventes terminées</label>
								</div>
							</div>
						</div>
					</div>
					<%
						}
	       			 %>
	       		</div>
			</article>
			<article class="col-12 col-lg-6 mt-3 mt-lg-0 d-flex align-items-center pr-0">
				<button type="submit" class="col-10 col-lg-6 offset-1 btn btn-outline-primary p-4">Rechercher</button>
			</article>
		</div>
	</form>
	<% List<ArticleVendu> articles = (List<ArticleVendu>) request.getAttribute("articles");
	if(articles!=null) {
		%>
		<div class="row">
		<%
		for(ArticleVendu article : articles) {
			if((recherche==null || recherche.isBlank() || article.getNomArticle().contains(recherche)) && (categorie==0 || categorie==article.getCategorieArticle().getNoCategorie())) {
			%>
				<article class="col-10 col-lg-5 offset-1 offset-lg-0 article mt-4">
					<%if(session.getAttribute("utilisateur")!=null) {%>
					<a href="<%=request.getContextPath()%>/DetailVente?=noArticle=<%=article.getNoArticle()%>">
					<%}%>
						<div><u><%=article.getNomArticle() %></u></div>
						<div>Prix : <%=article.getMiseAPrix()%> points</div>
						<div>Fin de l'enchère : <%=article.getDateFinEncheres().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))%></div>
						<div>Vendeur : <a class="profil" href="<%=request.getContextPath()%>/profil?id=<%=article.getVendeur().getNoUtilisateur()%>"><%=article.getVendeur().getPseudo()%></a></div>
					<%if(session.getAttribute("utilisateur")!=null) {%>
					</a>
					<%}%>
				</article>
				<div class="offset-lg-2"></div>
			<%
			}
		}
		%>
		</div>
		<%
	}
	%>
</section>
</body>
</html>