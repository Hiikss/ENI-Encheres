package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

import fr.eni.encheres.bo.Utilisateur;

public abstract class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String INSERT ="insert into Articles_Vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?);";
	private static final String SelectById = "Select * FROM Articlesè=_Vendus WHERE no-article = ?";
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


	@Override
	public ArticleVendu selectById(int id) throws BusinessException {
		ArticleVendu art = null;
		Connection cnx;
		ResultSet rs;
	
		try {
			
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SelectById);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				art = articleBuilder(rs);
			}
			
			rs.close();
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.SELECT_USER_ECHEC);
		}
		return art;
	}
	
	public ArticleVendu articleBuilder(ResultSet rs) throws BusinessException, SQLException {

		Utilisateur vendeur = this.getVendeur(rs.getInt("no_utilisateur"));
		Categorie categorie = this.getCategorie(rs.getInt("no_categorie"));


		ArticleVendu article = new ArticleVendu();

		article.setNoArticle(rs.getInt("no_article"));
		article.setNomArticle(rs.getString("nom_article"));
		article.setDescription(rs.getString("description"));
		article.setDateDebutEncheres((rs.getDate("date_debut_encheres").toLocalDate()));
		article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
		article.setMiseAPrix(rs.getInt("prix_initial"));
		article.setPrixVente(rs.getInt("prix_vente"));
		article.setVendeur(vendeur);
		article.setCategorieArticle(categorie);


		return article;

	}
	
	private Utilisateur getVendeur(int Id) {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		Utilisateur vArticle = null;
		try {
			vArticle = utilisateurDAO.selectbyId(Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vArticle;
	}

	private Categorie getCategorie(int Id) {
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		Categorie cArticle = null;
		try {
			cArticle = categorieDAO.SelectById(Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cArticle;
	}

}
