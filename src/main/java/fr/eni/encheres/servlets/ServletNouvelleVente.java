package fr.eni.encheres.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNouvelleVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//Récupération des paramètres
		String nom = request.getParameter("article");
		String description = request.getParameter("description");
		LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("dated"));
		LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("datef"));
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		int categerieId = Integer.parseInt(request.getParameter("categorie"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		Retrait retrait = new Retrait();
			
		retrait = new Retrait();
		retrait.setRue(rue);
		retrait.setCode_postal(codePostal);
		retrait.setVille(ville);
		
		//S'il y a des erreurs, renvoie la page d'inscription avec les erreurs
		List<Integer> listeErreurs = new ArrayList<>();
		if(listeErreurs.size()>0) {
			request.setAttribute("listeErreurs", listeErreurs);
			doGet(request, response);
		}
		else {
			//Foiré pour l'instant il faut que je crée de nouvelle class comme RetraitDAOJdbcImpl etc... c'est mis en attente en attendant
			ArticleManager articleManager = new ArticleManager();
			ArticleVendu article = null;
			try {
				articleManager.insert(article);
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
