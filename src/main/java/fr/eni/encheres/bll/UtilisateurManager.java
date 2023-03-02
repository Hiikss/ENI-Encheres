package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	
	
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur seConnecter(String identifiant, String mdp) {
		
		Utilisateur utilisateur = new Utilisateur();
		if(identifiant.contains("@"))
			utilisateur.setEmail(identifiant);
		else
			utilisateur.setPseudo(identifiant);
			
		utilisateur.setMotDePasse(mdp);
		
		this.utilisateurDAO.seConnecter(utilisateur);
	}
}
