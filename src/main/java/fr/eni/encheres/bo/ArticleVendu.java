package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	//etatVente en String ? On pourrait faire une enum ?
	private String etatVente;
	
	private Utilisateur vendeur;
	private Retrait lieuRetrait;
	private Categorie categorieArticle;
	private List<Enchere> encheres;
	
	public static List<ArticleVendu> instances = new ArrayList<>();
	
	/**
	 * 
	 */
	public ArticleVendu() {
		encheres = new ArrayList<>();
		instances.add(this);
	}
		
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, int prixVente) {
		this();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
	}
	
	/**
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param vendeur
	 * @param lieuRetrait
	 * @param categorieArticle
	 * @param enchere
	 */
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, String etatVente, Utilisateur vendeur, Retrait lieuRetrait,
			Categorie categorieArticle, List<Enchere> encheres) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.lieuRetrait = lieuRetrait;
		this.categorieArticle = categorieArticle;
		this.encheres = encheres;
	}
	
	/**
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param vendeur
	 * @param lieuRetrait
	 * @param categorieArticle
	 * @param enchere
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Utilisateur vendeur,
			Retrait lieuRetrait, Categorie categorieArticle, List<Enchere> encheres) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, etatVente, vendeur, lieuRetrait, categorieArticle, encheres);
		this.noArticle = noArticle;
	}
	
	public static ArticleVendu getArticleIfExists(int no) {
		ArticleVendu articleVendu = null;
		for(ArticleVendu article : instances) {
			if(article.getNoArticle()==no) {
				articleVendu = article;
			}
		}
		return articleVendu;
	}

	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public String getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}
	public Utilisateur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}
	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}
	public Categorie getCategorieArticle() {
		return categorieArticle;
	}
	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}
	
	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}
	
	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void addEnchere(Enchere enchere) {
		if(!this.encheres.contains(enchere)) {
			this.encheres.add(enchere);
		}
	}
	
	public Enchere getMeilleureEnchere() {
		Enchere meilleureEnchere = null;
		for(Enchere enchere : encheres) {
			if(enchere.getMontantEchere()>meilleureEnchere.getMontantEchere() || meilleureEnchere==null) {
				meilleureEnchere = enchere;
			}
		}
		return meilleureEnchere;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente
				+ ", lieuRetrait=" + lieuRetrait + ", categorieArticle=" + categorieArticle + ", enchere=" + encheres
				+ "]";
	}
}