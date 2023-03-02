package fr.eni.encheres.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String identifiant = null;
		String mdp = null;
		
		identifiant = request.getParameter("identifiant");
		
		mdp = request.getParameter("motDePasse");
		
		UtilisateurManager utilisateurManager = new UtilisateurManager(); 
		try {
			Utilisateur utilisateur = utilisateurManager.seConnecter(identifiant, mdp);
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect(request.getContextPath() + "/accueil");
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeErreurs", e.getListeCodesErreur());
			doGet(request, response);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
	}
}
