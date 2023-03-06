package fr.eni.encheres.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletSignIn
 */
@WebServlet("/signup")
public class ServletSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/creationCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//Récupération des paramètres
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		//hashage du mot de passe
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] motDePasse = digest.digest(request.getParameter("motDePasse").getBytes(StandardCharsets.UTF_8));
		byte[] confirmationMotDePasse = digest.digest(request.getParameter("confirmationMotDePasse").getBytes(StandardCharsets.UTF_8));
		
		List<Integer> listeErreurs = new ArrayList<>();
		
		//Vérifie que les mots de passe sont égaux
		if(!Arrays.equals(motDePasse, confirmationMotDePasse)) {
			listeErreurs.add(CodesResultatServlets.MDP_DIFFERENTS_ERREUR);
		}
		//S'il y a des erreurs, renvoie la page d'inscription avec les erreurs
		if(listeErreurs.size()>0) {
			request.setAttribute("listeErreurs", listeErreurs);
			doGet(request, response);
		}
		else {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur;
			try {
				utilisateur = utilisateurManager.insert(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect(request.getContextPath() + "/accueil");
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeErreurs", e.getListeCodesErreur());
				doGet(request, response);
			}
		}
	}

}
