package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/Encherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEncherir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		int currentCredit = utilisateurConnecte.getCredit();
		
		int prixEnchere = Integer.parseInt(request.getParameter("miseAPrix"));
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		
		ArticleVendu article = null;
		article = ArticleVendu.getArticleIfExists(noArticle);

		if (article != null && prixEnchere > article.getMiseAPrix()
				&& utilisateurConnecte.getCredit() >= prixEnchere) {
			Enchere enchere = new Enchere(LocalDate.now(), prixEnchere, article, utilisateurConnecte);
			article.setPrixVente(prixEnchere);
			
			
			try {
				EnchereManager enchereManager = new EnchereManager();
				
				enchereManager.insert(enchere);
				request.setAttribute("mPrix", prixEnchere);
				request.setAttribute("meilleureEnchere", enchere);

				ArticleManager articleManager = new ArticleManager();
				articleManager.update(article);
				utilisateurConnecte.setCredit(currentCredit - prixEnchere);

				UtilisateurManager utilisateurManager = new UtilisateurManager();
				utilisateurManager.updateCredit(utilisateurConnecte);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/accueil");
		}
	}

}
