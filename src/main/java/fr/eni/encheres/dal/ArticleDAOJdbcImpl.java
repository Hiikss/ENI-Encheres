package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String INSERT ="insert into Articles_Vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?);";

	//private static final String INSERT = "INSERT INTO Articles_Vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, no_retrait) VALUES(?,?,?,?,?,?,?,?);";


	@Override
	public void insert(ArticleVendu article) throws BusinessException {
		if (article == null) {
			throw new BusinessException(CodesResultatDAL.INSERT_OBJET_NULL);
		}
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
				PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

				
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
				pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
				pstmt.setInt(5, article.getMiseAPrix());
				pstmt.setInt(6, article.getVendeur().getNoUtilisateur());
				pstmt.setInt(7, article.getCategorieArticle().getNoCategorie());
				//pstmt.setInt(8, article.getLieuRetrait().getNoRetrait());
				
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof BusinessException) {
				throw be;
			}
			throw new BusinessException(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
		}
		
		}

}
