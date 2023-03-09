package fr.eni.encheres.bo;

import java.time.LocalDate;


public class Enchere {
	private LocalDate dateEnchere;
	private int montantEchere;
	private ArticleVendu article;
	private Utilisateur encherisseur;
	
	
	
	
	/**
	 * 
	 */
	public Enchere() {

		
	}
	
	
	
	
	/**
	 * @param dateEnchere
	 * @param montantEchere
	 * @param article
	 * @param encherisseur
	 */
	public Enchere(LocalDate dateEnchere, int montantEchere, ArticleVendu article, Utilisateur encherisseur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEchere = montantEchere;
		this.article = article;
		this.encherisseur = encherisseur;

	}






	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEchere() {
		return montantEchere;
	}
	public void setMontantEchere(int montantEchere) {
		this.montantEchere = montantEchere;
	}
	public ArticleVendu getArticle() {
		return article;
	}
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	
	
	
	public Utilisateur getEncherisseur() {
		return encherisseur;
	}




	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}




	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEchere=" + montantEchere + ", article=" + article
				+ ", encherisseur=" + encherisseur + "]";
	}




	
	
	
	
	
	
	

}
