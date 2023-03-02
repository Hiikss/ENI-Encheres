package fr.eni.encheres.bo;

import java.time.LocalDate;

public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	//etatVente en String ?
	private String etatVente;
	
	private Utilisateur vendeur;
	private Retrait lieuRetrait;
	private Categorie categorieArticle;

}
