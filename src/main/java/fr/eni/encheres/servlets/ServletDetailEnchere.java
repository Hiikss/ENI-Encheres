package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.EnchereDAO;

/**
 * Servlet implementation class ServletDetailEnchere
 */
@WebServlet("/ServletDetailEnchere")
public class ServletDetailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupérer l'id de l'enchère à afficher
		int idEnchere = Integer.parseInt(request.getParameter("idEnchere"));

		// Récupérer les détails de l'enchère depuis la base de données
		Enchere enchere = EnchereDAO.getEnchereById(idEnchere);

		// Ajouter les détails de l'enchère à la requête pour la JSP
		request.setAttribute("enchere", enchere);

		// Renvoyer la JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("detailsEnchere.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// Récupérer l'id de l'enchère à soumettre une enchère
			int idEnchere = Integer.parseInt(request.getParameter("idEnchere"));
			
			// Récupérer le montant de l'enchère soumise
			double montant = Double.parseDouble(request.getParameter("montant"));
			
			// Soumettre l'enchère à la base de données
			EnchereDAO.soumettreEnchere(idEnchere, montant);
			
			// Rediriger vers la page des détails de l'enchère mise à jour
			response.sendRedirect("/ServletDetailEnchere");

	}

}
