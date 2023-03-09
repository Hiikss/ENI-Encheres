package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private byte[] motDePasse;
	private int credit;
	
	private List<ArticleVendu> ventes;
	private List<Enchere> encheres;
	
	private boolean administrateur;
	
	public static List<Utilisateur> instances = new ArrayList<>();
	
	public Utilisateur() {
		ventes = new ArrayList<>();
		encheres = new ArrayList<>();
		instances.add(this);
	}
	
	/**
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville) {
		this();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	/**
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, byte[] motDePasse) {
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
		this.motDePasse = motDePasse;
	}

	/**
	 * @param noUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param administrateur
	 */
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, byte[] motDePasse, int credit, boolean administrateur) {
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
		this.noUtilisateur = noUtilisateur;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	/**
	 * @param noUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param vente
	 * @param encherit
	 */
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, byte[] motDePasse, int credit, boolean administrateur, List<ArticleVendu> vente, List<Enchere> encherit) {
		this(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		this.ventes = vente;
		this.encheres = encherit;
		vente = new ArrayList<>();
		encherit = new ArrayList<>();
	}

	/**
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param vente
	 * @param encherit
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, byte[] motDePasse, int credit, List<ArticleVendu> vente,
			List<Enchere> encherit) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.ventes = vente;
		this.encheres = encherit;
		vente = new ArrayList<>();
		encherit = new ArrayList<>();
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public byte[] getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(byte[] motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public List<ArticleVendu> getVentes() {
		return ventes;
	}

	public void addVente(ArticleVendu vente) {
		this.ventes.add(vente);
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void addEnchere(Enchere enchere) {
		this.encheres.add(enchere);
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit + ", vente="
				+ ventes + ", encherit=" + encheres + ", administrateur=" + administrateur + "]";
	}
}