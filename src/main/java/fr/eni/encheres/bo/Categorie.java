package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> article;
	
	
	public Categorie() {
		article = new ArrayList<>();
	}
	
	


	/**
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		article = new ArrayList<>();
	}




	/**
	 * @param noCategorie
	 * @param libelle
	 * @param article
	 */
	public Categorie(int noCategorie, String libelle, List<ArticleVendu> article) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.article = article;
		article = new ArrayList<>();
	}





	public int getNoCategorie() {
		return noCategorie;
	}


	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	


	public List<ArticleVendu> getArticle() {
		return article;
	}




	public void setArticle(List<ArticleVendu> article) {
		this.article = article;
	}




	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", article=" + article + "]";
	}




	
	
	
	

}
