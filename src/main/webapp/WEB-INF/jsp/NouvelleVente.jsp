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
	
		<form action="<%=request.getContextPath()%>/NouvelleVente"
			method="post" class="mt-5">
			
			<div class="form-group row mt justify-content-center">
			<div class="col col-lg-2">
				<label for="article" class="col-2 col-form-label ">Article :</label>
				</div>
				<div class="col col-lg-2">			
					<input class="form-control" id="article" name="article" required>
				</div>
				</div>
				
				<div class="form-group row mt-4 justify-content-center">
				<div class="col col-lg-2">
				<label for="description" class="col-2 col-form-label">Description :</label>		
				</div>
				<div class="col col-lg-2">
				<textarea class="form-control" id="description" name="description" required></textarea>
				</div>
				</div>
				
				<div class="form-group row mt-4 justify-content-center">
				<div class="col col-lg-2">
				<label for="categorie" class="col-2 col-form-label">Catégorie :</label>
				</div>
				<div class="col col-lg-2">
					<select id="categorie" class="form-control" name="categorie">
						<%for(Categorie categorie : CategorieManager.SelectAllCategories()) {%>
      					  <option value="<%=categorie.getNoCategorie() %>"><%=categorie.getLibelle() %></option>
    					  <%} %>  
   					   </select>
				</div>	
				</div>
				
				<div class="form-group row mt-4 justify-content-center">	
				<div class="col col-lg-2">
   					 <label for="upload" class="col-2 col-form-label">Photo de l'article :</label>
   					 </div>
   					 <div class="col col-lg-2">
 					 <input type="file" class="form-control-file" id="upload">
 				</div>
 				</div>
 				
				<div class="form-group row mt-4 justify-content-center">
 				<div class="col col-lg-2">
   			   <label class="col-2 col-form-label" for="miseAPrix">Prix de vente :</label>
   			   </div>
   			   <div class="col col-lg-2">
    			  <input class="form-control" type ="number" name="miseAPrix" id="miseAPrix" step="1" max="10000" required>
 			   </div>
 			 </div>
 			 
				<div class="form-group row mt-4 justify-content-center">
 			   <div class="col col-lg-2">
 				<label for="dated" class="col-2 col-form-label ">Début de l'enchère :</label>
				</div>
				<div class="col col-lg-2">
					<input type="date" class="form-control" id="dated" name="dated" required>
				</div>
				</div>
				
				<div class="form-group row mt-4 justify-content-center">
				<div class="col col-lg-2">
				<label for="datef" class="col-2 col-form-label ">Fin de l'enchère :</label>
				</div>
				<div class="col col-lg-2">
					<input type="date" class="form-control" id="datef" name="datef" required>
				</div>
				</div>
				
				
				<div class="form-group row mt-4 justify-content-center">
				<fieldset name="Retrait">Retrait
				
				<div class="form-group row mt-4 justify-content-center">
				<div class="col col-lg-2">
				<label for="rue" class="col-2 col-form-label ">Rue :</label>
				</div>
				<div class="col col-lg-2">
					<input class="form-control" id="rue" name="rue" required>
				</div>
				</div>
				<div class="form-group row mt-4 justify-content-center">
				<div class="col col-lg-2">
				<label for="codePostal" class="col-2 col-form-label ">Code Postal :</label>
				</div>
				<div class="col col-lg-2">
					<input class="form-control" id="codePostal" name="codePostal" required>
				</div>
				</div>
				<div class="form-group row mt-4 justify-content-center">
				<div class="col col-lg-2">
				<label for="ville" class="col-2 col-form-label ">Ville :</label>
				</div>
				<div class="col col-lg-2">
					<input class="form-control" id="ville" name="ville" required>
				</div>
				</div>
				</fieldset>
				
			</div>
			<div class="row justify-content-center mt-5">
  			<button type="submit" class="btn btn-outline-success col-2">Enregistrer</button>
  			<a href="<%=request.getContextPath()%>/accueil" class="col-2 offset-1 btn btn-outline-danger" role="button">Annuler</a>
  		</div>
		</form>
	</section>
	
	
	

</body>
</html>