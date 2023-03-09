package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Enchere {
	private LocalDate dateEnchere;
	private int montantEchere;
	private ArticleVendu article;
	private List<Utilisateur> encherisseur;
	
	
	
	
	/**
	 * 
	 */
	public Enchere() {
		encherisseur = new ArrayList<>();
		
	}
	
	
	
	
	/**
	 * @param dateEnchere
	 * @param montantEchere
	 * @param article
	 * @param encherisseur
	 */
	public Enchere(LocalDate dateEnchere, int montantEchere, ArticleVendu article, List<Utilisateur> encherisseur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEchere = montantEchere;
		this.article = article;
		this.encherisseur = encherisseur;
		encherisseur = new ArrayList<>();
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
	
	
	
	public List<Utilisateur> getEncherisseur() {
		return encherisseur;
	}




	public void setEncherisseur(List<Utilisateur> encherisseur) {
		this.encherisseur = encherisseur;
	}




	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEchere=" + montantEchere + ", article=" + article
				+ ", encherisseur=" + encherisseur + "]";
	}




	public void setArticle(String string) {
		// TODO Auto-generated method stub
		
	}




	
	
	
	
	
	
	

}
