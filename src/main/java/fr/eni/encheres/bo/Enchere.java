package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Enchere {
	private LocalDate dateEnchere;
	private int montantEchere;
	private ArticleVendu article;
	
	
	
	/**
	 * 
	 */
	public Enchere() {
		super();
	}
	/**
	 * @param dateEnchere
	 * @param montantEchere
	 * @param article
	 */
	public Enchere(LocalDate dateEnchere, int montantEchere, ArticleVendu article) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEchere = montantEchere;
		this.article = article;
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
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEchere=" + montantEchere + ", article=" + article
				+ "]";
	}
	
	
	
	
	
	

}
