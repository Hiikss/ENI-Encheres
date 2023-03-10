<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import=" fr.eni.encheres.bo.Categorie" %>
 <%@page import="java.util.List" %>
 <%@page import="fr.eni.encheres.bll.CategorieManager"%>
<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Nouvelle Vente</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
	<a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
	</nav>
	<section class="container">
	<h1 class="mt-5 text-center">Nouvelle Vente</h1>
	<%@ include file="/WEB-INF/jsp/bandeauErreur.jspf" %>
	<% Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");	%>    
	
		<form action="<%=request.getContextPath()%>/NouvelleVente"
			method="post" class="form-group mt-md-4 justify-content-center">
			
			<div class="form-group row justify-content-md-center">

				<label for="article" class="col-sm-2 col-form-label">Article :</label>
				<div class="col-sm-5">
					<input class="form-control" id="article" name="article" required>
				</div>
				</div>

				
				<div class="form-group row justify-content-md-center">

				<label for="description" class="col-sm-2 col-form-label">Description :</label>		
				<div class="col-sm-5">
				<textarea class="form-control" id="description" name="description" required></textarea>
				</div>
				</div>

				
				<div class="form-group row justify-content-md-center">

				<label for="categorie" class="col-sm-2 col-form-label">Catégorie :</label>

					<select id="categorie" class="form-control col-sm-5" name="categorie">
						<%for(Categorie categorie : CategorieManager.SelectAllCategories()) {%>
      					  <option value="<%=categorie.getNoCategorie() %>"><%=categorie.getLibelle() %></option>
    					  <%} %>  
   					   </select>
				</div>	


				
				<div class="form-group row justify-content-md-center">

   					 <label for="upload" class="col-sm-2 col-form-label">Photo de l'article :</label>
				<div class="col-sm-5">
 					 <input type="file" class="form-control-file col-sm" id="upload">
 				</div>
 				</div>


 				
				<div class="form-group row justify-content-md-center">

   			   <label class="col-sm-2 col-form-label" for="miseAPrix">Prix de vente :</label>
				<div class="col-sm-5">
    			  <input class="form-control" type ="number" name="miseAPrix" id="miseAPrix" step="1" max="10000" required>
 			   </div>
 			   </div>

 			 
				<div class="form-group row justify-content-md-center">

 				<label for="dated" class="col-sm-2 col-form-label ">Début de l'enchère :</label>
				<div class="col-sm-5">
					<input type="date" class="form-control" id="dated" name="dated" required>
				</div>
				</div>

				
				<div class="form-group row justify-content-md-center">

				<label for="datef" class="col-sm-2 col-form-label ">Fin de l'enchère :</label>
				<div class="col-sm-5">
					<input type="date" class="form-control" id="datef" name="datef" required>
				</div>
				</div>

				
		
				
				<fieldset name="Retrait" class="form-group justify-content-lg-center border p-3">
				<legend class="w-auto">Retrait</legend>
				
				<div class="form-group row justify-content-md-center">

				<label for="rue" class="col-sm-2 col-form-label ">Rue :</label>
				<div class="col-sm-5">
					<input class="form-control" id="rue" name="rue" value="<%=utilisateur.getRue() %>" required>
				</div>
				</div>

				<div class="form-group row justify-content-md-center">

				<label for="codePostal" class="col-sm-2 col-form-label ">Code Postal :</label>
				<div class="col-sm-5">
					<input class="form-control" id="codePostal" name="codePostal" value="<%=utilisateur.getCodePostal() %>" required>
				</div>
				</div>

				<div class="form-group row justify-content-md-center">

				<label for="ville" class="col-sm-2 col-form-label ">Ville :</label>
				<div class="col-sm-5">
					<input class="form-control" id="ville" name="ville" value ="<%=utilisateur.getVille() %>" required>
				</div>
				</div>

				</fieldset>
			
			
			<div class="row justify-content-center mt-5">
  			<button type="submit" class="btn btn-outline-success col-2">Enregistrer</button>
  			<a href="<%=request.getContextPath()%>/accueil" class="col-2 offset-1 btn btn-outline-danger" role="button">Annuler</a>
  		</div>
  		
		</form>
	</section>
	
	
	

</body>
</html>