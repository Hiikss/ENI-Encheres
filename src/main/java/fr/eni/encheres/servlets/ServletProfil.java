package fr.eni.encheres.servlets;

import java.io.IOException;
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
 * Servlet implementation class ServletProfil
 */
@WebServlet("/profil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		boolean uSession = true;
		if (request.getParameter("id") != null){
			int id = Integer.parseInt(request.getParameter("id"));			
			if(utilisateur==null || id!=utilisateur.getNoUtilisateur()) {
				uSession = false;
				UtilisateurManager um = UtilisateurManager.getInstance();
				try {
					utilisateur = um.selectUtilisateur(id);
				} catch (BusinessException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		
		request.setAttribute("ok", uSession);
		request.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("/WEB-INF/jsp/Profil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
