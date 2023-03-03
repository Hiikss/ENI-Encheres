package fr.eni.encheres.bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	
	
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur seConnecter(String identifiant, String mdp) throws BusinessException, NoSuchAlgorithmException {
		
		Utilisateur utilisateur = new Utilisateur();
		if(identifiant.contains("@"))
			utilisateur.setEmail(identifiant);
		else
			utilisateur.setPseudo(identifiant);
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		utilisateur.setMotDePasse(digest.digest(mdp.getBytes(StandardCharsets.UTF_8)));
		
		utilisateur = this.utilisateurDAO.seConnecter(utilisateur);
		
		System.out.println(utilisateur.toString());
		return utilisateur;
	}
	
	
	public Utilisateur Insert(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, byte[] motDePasse, int credit, boolean administrateur) throws BusinessException {
		BusinessException exception = new BusinessException();
		credit = 100;
		administrateur =false;
		Utilisateur utilisateur = null;
		this.validerPseudo(pseudo, exception);
		this.validerEmail(email, exception);
		this.validerMotDePasse(motDePasse, exception);
		
		if(!exception.hasErreurs()) {
			
			utilisateur = new Utilisateur();
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setVille(ville);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setCredit(credit);
			utilisateur.setAdministrateur(administrateur);
		
		this.utilisateurDAO.insert(utilisateur);
			}
		else
			{
				throw exception;
			}
		
		return utilisateur;
	}
	
	private void validerEmail(String email, BusinessException businessException) {
		boolean result;
	    // Vérifier si l'email est nul ou vide
	    if (email == null || email.isEmpty()) {
	        result= false;
	    }
	    
	    // Vérifier si l'email contient un "@" et un "."
	    if (!email.contains("@") || !email.contains(".")) {
	    	result= false;
	    }
	    
	    // Vérifier si l'email commence ou finit par un "."
	    if (email.startsWith(".") || email.endsWith(".")) {
	    	result= false;
	    }
	    
	    // Vérifier si l'email contient des caractères spéciaux invalides
	    String regex = "[^a-zA-Z0-9.@_-]";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);
	    if (matcher.find()) {
	    	result= false;
	    }
	    
	    if(result = false) {
	    	businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_ERREUR);
	    }
	}
	
	
	private void validerMotDePasse(byte[] motDePasse, BusinessException businessException) {
		
	}

	private void validerPseudo(String pseudo, BusinessException businessException) {
		boolean result;
	    // Vérifier si le pseudo est nul ou vide
	    if (pseudo == null || pseudo.isEmpty()) {
	        result= false;
	    }
	    
	    
	    // Vérifier si le pseudo contient des caractères spéciaux invalides
	    String regex = "[^a-zA-Z0-9.@_-]";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(pseudo);
	    if (matcher.find()) {
	    	result= false;
	    }
	    
	    if(result = false) {
	    	businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ERREUR);
	    }
	
}
	
}
