package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> articles;
	
	public static List<Categorie> instances = new ArrayList<>();
	
	public Categorie() {
		articles = new ArrayList<>();
		instances.add(this);
	}

	/**
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(int noCategorie, String libelle) {
		this();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	/**
	 * 
	 * @param noCategorie
	 * @param libelle
	 * @param article
	 */
	public Categorie(int noCategorie, String libelle, ArticleVendu article) {
		this(noCategorie, libelle);
		addArticle(article);
	}

	public static Categorie getCategorieIfExists(int no) {
		Categorie categorie = null;
		for(Categorie cat : instances) {
			if(cat.getNoCategorie()==no) {
				categorie = cat;
			}
		}
		return categorie;
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

	public List<ArticleVendu> getArticles() {
		return articles;
	}

	public void addArticle(ArticleVendu article) {
		if(articles==null) {
			articles = new ArrayList<>();
		}
		if(!articles.contains(article)) {
		articles.add(article);
		}
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", article=" + articles + "]";
	}

}
