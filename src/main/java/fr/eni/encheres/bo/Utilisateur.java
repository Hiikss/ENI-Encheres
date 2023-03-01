package fr.eni.encheres.bo;

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
	private String motDePasse;
	private int credit;
	
	private List<ArticleVendu> vente;
	private Enchere encherit;
	
	private int administrateur;

}
