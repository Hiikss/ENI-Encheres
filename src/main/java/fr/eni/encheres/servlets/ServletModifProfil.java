package fr.eni.encheres.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifProfil
 */
@WebServlet("/modification")
public class ServletModifProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des paramètres du formulaire
		HttpSession session = request.getSession();
		// Récupération des paramètres
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		// hashage du mot de passe
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] motDePasseActuel = digest
				.digest(request.getParameter("motDePasseActuel").getBytes(StandardCharsets.UTF_8));
		byte[] nouveauMotDePasse = digest
				.digest(request.getParameter("nouveauMotDePasse").getBytes(StandardCharsets.UTF_8));
		byte[] confirmation = digest.digest(request.getParameter("confirmation").getBytes(StandardCharsets.UTF_8));

		List<Integer> listeErreurs = new ArrayList<>();

		// Vérifie que les mots de passe sont égaux
		if (!Arrays.equals(nouveauMotDePasse, confirmation)) {
			listeErreurs.add(CodesResultatServlets.MDP_DIFFERENTS_ERREUR);
		}
		// S'il y a des erreurs, renvoie la page d'inscription avec les erreurs
		if (listeErreurs.size() > 0) {
			request.setAttribute("listeErreurs", listeErreurs);
			doGet(request, response);
		} else {
			UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			try {
				utilisateurManager.update(utilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
						motDePasseActuel, nouveauMotDePasse);
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect(request.getContextPath() + "/accueil");
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeErreurs", e.getListeCodesErreur());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}