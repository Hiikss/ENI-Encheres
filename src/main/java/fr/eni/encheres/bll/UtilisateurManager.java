package fr.eni.encheres.bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	
	public Utilisateur Insert(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, byte[] motDePasse, int credit, boolean administrateur) {
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
	
	
	
}
