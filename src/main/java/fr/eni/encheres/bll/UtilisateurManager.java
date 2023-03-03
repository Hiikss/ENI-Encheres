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
		
		this.utilisateurDAO.seConnecter(utilisateur);
		
		System.out.println(utilisateur.toString());
		return utilisateur;
	}
}
