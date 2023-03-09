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
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
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
		ArticleVendu article = new ArticleVendu();
		
		try {
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
		
		
		Categorie categorie = CategorieManager.SelectCategorieById(categerieId);
		
			
		

		
		//S'il y a des erreurs, renvoie la page de vente avec les erreurs
		List<Integer> listeErreurs = new ArrayList<>();
		if(listeErreurs.size()>0) {
			request.setAttribute("listeErreurs", listeErreurs);
			doGet(request, response);
		}
		else {
			try {
				article.setNomArticle(nom);
				article.setDescription(description);
				article.setDateDebutEncheres(dateDebutEnchere);
				article.setDateFinEncheres(dateFinEnchere);
				article.setMiseAPrix(miseAPrix);
				article.setCategorieArticle(categorie);
				
				article.setVendeur(utilisateur);
				
				ArticleManager articleManager = new ArticleManager();
				articleManager.insert(article);
				
				retrait = new Retrait();
				retrait.setRue(rue);
				retrait.setCode_postal(codePostal);
				retrait.setVille(ville);
				retrait.setArticle(article);
				
					RetraitManager.insertRetrait(retrait);
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect(request.getContextPath() + "/accueil");
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeErreurs", e.getListeCodesErreur());
				doGet(request, response);
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
}
