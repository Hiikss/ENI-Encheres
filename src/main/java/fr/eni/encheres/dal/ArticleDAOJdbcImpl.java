package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public class ArticleDAOJdbcImpl implements ArticleDAO {
//	public static final String INSERT ="insert into Articles_Vendus (no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUE(?,?,?,?,?,?,?,?);";

	private static final String INSERT = "INSERT INTO Articles_Vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, no_retrait) VALUES(?,?,?,?,?,?,?,?);";
	private static final String SELECT_ALL = "SELECT * FROM Articles_Vendus WHERE nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, no_utilisateur=?, no_retrait=?";

	@Override
	public void insert(ArticleVendu article) throws BusinessException {
		if (article == null) {
			throw new BusinessException(CodesResultatDAL.INSERT_OBJET_NULL);
		}
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = pstmt.executeQuery();

			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getVendeur().getNoUtilisateur());
			pstmt.setInt(7, article.getCategorieArticle().getNoCategorie());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
				;
			}

			pstmt.setInt(8, article.getLieuRetrait().getNoRetrait());

			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof BusinessException) {
				throw be;
			}
			throw new BusinessException(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
		}

	}

	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> liste = new ArrayList<ArticleVendu>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNomArticle(rs.getString("NomArticle"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setVendeur(rs.getString("vendeur"));
				article.setLieuRetrait(rs.getString("lieuRetrait"));
				liste.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.SELECT_ARTICLE_VENDU_ECHEC);
		}
		return liste;
	}
}
