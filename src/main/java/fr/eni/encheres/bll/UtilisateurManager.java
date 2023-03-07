package fr.eni.encheres.bll;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.catalina.tribes.util.Arrays;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private static final int CREDIT = 100;
	private static final boolean ADMIN = false;

	private UtilisateurDAO utilisateurDAO;
	private static UtilisateurManager instance;

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	public Utilisateur seConnecter(String identifiant, byte[] mdp) throws BusinessException {

		Utilisateur utilisateur = new Utilisateur();
		if (identifiant.contains("@"))
			utilisateur.setEmail(identifiant);
		else
			utilisateur.setPseudo(identifiant);

		utilisateur.setMotDePasse(mdp);

		this.utilisateurDAO.seConnecter(utilisateur);

		System.out.println(utilisateur.toString());
		return utilisateur;
	}

	public Utilisateur insert(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, byte[] motDePasse) throws BusinessException {
		BusinessException exception = new BusinessException();
		Utilisateur utilisateur = null;
		this.validerPseudo(pseudo, exception);
		this.validerEmail(email, exception);
		this.validerCoordonne(nom, prenom, telephone, rue, codePostal, ville, exception);

		if (!exception.hasErreurs()) {

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
			utilisateur.setCredit(CREDIT);
			utilisateur.setAdministrateur(ADMIN);

			this.utilisateurDAO.insert(utilisateur);
		} else {
			throw exception;
		}
		System.out.println(utilisateur.toString());
		return utilisateur;
	}

	public void update(Utilisateur utilisateur, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, byte[] motDePasseActuel,
			byte[] nouveauMotDePasse) throws BusinessException {
		BusinessException exception = new BusinessException();
		this.validerPseudo(pseudo, exception);
		this.validerEmail(email, exception);
		this.validerCoordonne(nom, prenom, telephone, rue, codePostal, ville, exception);
		this.validerComparaisonMdp(utilisateur.getMotDePasse(), motDePasseActuel, exception);

		if (!exception.hasErreurs()) {

			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setVille(ville);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setMotDePasse(nouveauMotDePasse);

			this.utilisateurDAO.update(utilisateur);
		} else {
			throw exception;
		}
		System.out.println(utilisateur.toString());

	}

	private void validerComparaisonMdp(byte[] mdpActuel, byte[] mdpSaisi, BusinessException businessException) {
		if (!Arrays.equals(mdpActuel, mdpSaisi)) {
			businessException.ajouterErreur(CodesResultatBLL.MDP_COMPARAISON_ERREUR);
		}

	}
	
	public void delete(Utilisateur utilisateur) throws BusinessException {
		this.utilisateurDAO.delete(utilisateur);
	}

	private void validerEmail(String email, BusinessException businessException) {
		boolean result = true;
		// Vérifier si l'email est nul ou vide
		if (email == null || email.isEmpty()) {
			result = false;
		}

		// Vérifier si l'email contient un "@" et un "."
		if (!email.contains("@") || !email.contains(".")) {
			result = false;
		}

		// Vérifier si l'email commence ou finit par un "."
		if (email.startsWith(".") || email.endsWith(".")) {
			result = false;
		}

		// Vérifier si l'email contient des caractères spéciaux invalides
		String regex = "[^a-zA-Z0-9.@_-]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.find()) {
			result = false;
		}

		if (result == false) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_ERREUR);
		}
	}

	private void validerPseudo(String pseudo, BusinessException businessException) {
		boolean result = true;
		// Vérifier si le pseudo est nul ou vide
		if (pseudo.isBlank()) {
			result = false;
		}

		if (!pseudo.matches("^[a-zA-Z0-9]+$")) {
			result = false;
		}

		if (result == false) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ERREUR);
		}

	}

	private void validerCoordonne(String nom, String prenom, String telephone, String rue, String codePostal,
			String ville, BusinessException businessException) {
		if (nom.isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_USER_NOM_ERREUR);
		}

		if (prenom.isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_USER_PRENOM_ERREUR);
		}

		if (telephone.isBlank() || telephone.length() != 10) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_USER_RUE_ERREUR);
		}

		if (rue.isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_USER_RUE_ERREUR);
		}

		if (codePostal.isBlank() || codePostal.length() != 5) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_USER_CODEPOSTAL_ERREUR);
		}

		if (ville.isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_USER_VILLE_ERREUR);
		}
	}

	public Utilisateur selectUtilisateur(int id) throws BusinessException {
		return this.utilisateurDAO.selectbyId(id);
	}
}