package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl {
	
	private static final String SELECT_ENCHERE = "SELECT * FROM Enchere WHERE no_article=?, no_utilisateur=?, date_encheres=?, montant_enchere=?";

	public List<Enchere> select() throws BusinessException {
		List<Enchere> liste = new ArrayList<Enchere>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Enchere article = new Enchere();
				article.setArticle(rs.getString("article"));
				article.setEncherisseur(rs.get("encherisseur"));
				article.setDateEnchere(rs.getDate("date_debut_encheres").toLocalDate());
				article.setMontantEchere(rs.getInt("montant_enchère"));
				liste.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.SELECT_ARTICLE_VENDU_ECHEC);
		}
		return liste;
}
}
