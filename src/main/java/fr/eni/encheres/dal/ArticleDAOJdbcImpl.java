package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;



import fr.eni.encheres.bo.Retrait;

import fr.eni.encheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String INSERT ="insert into Articles_Vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?);";

	private static final String SelectById = "Select * FROM Articles_Vendus WHERE no_article = ?";

	private static final String SELECT_ALL = "select * FROM RETRAITS r, CATEGORIES c, ARTICLES_VENDUS a, UTILISATEURS u WHERE a.no_article=r.no_article AND a.no_categorie=c.no_categorie AND a.no_utilisateur=u.no_utilisateur;";

	//private static final String INSERT = "INSERT INTO Articles_Vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, no_retrait) VALUES(?,?,?,?,?,?,?,?);";
	private static final String UPDATE_ARTICLE = "UPDATE Articles_Vendus SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=? WHERE no_article=?";

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


	@Override
	public void update(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();

 		try (Connection cnx = ConnectionProvider.getConnection()) {
 			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
 			pstmt.setString(1, article.getNomArticle());
 			pstmt.setString(2, article.getDescription());
 			pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getVendeur().getNoUtilisateur());
			pstmt.setInt(8, article.getCategorieArticle().getNoCategorie());
			
			pstmt.executeUpdate();
 			
 		}catch (Exception e) {
			e.printStackTrace();
			if (e instanceof BusinessException) {
				throw be;
			}
			throw new BusinessException(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
 		}
		}

	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//Retrait
				Retrait retrait = new Retrait(rs.getString(2), rs.getString(3), rs.getString(4));
				//Categorie
				Categorie categorie = Categorie.getCategorieIfExists(rs.getInt(5));
				if(categorie==null) {
					categorie = new Categorie(rs.getInt(5), rs.getString(6));
				}
				//ArticleVendu
				ArticleVendu articleVendu = ArticleVendu.getArticleIfExists(rs.getInt(7));
				if(articleVendu==null) {
					articleVendu = new ArticleVendu(rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDate(10).toLocalDate(), rs.getDate(11).toLocalDate(), rs.getInt(12), rs.getInt(13));
				}
				retrait.setArticle(articleVendu);
				articleVendu.setLieuRetrait(retrait);
				articleVendu.setCategorieArticle(categorie);
				
				if(!categorie.getArticles().contains(articleVendu)) {
					categorie.addArticle(articleVendu);
				}
				retrait.setArticle(articleVendu);
				
				Utilisateur vendeur = Utilisateur.getUtilisateurIfExists(rs.getInt(16));
				if(vendeur==null) {
					vendeur = new Utilisateur(rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getBytes(25), rs.getInt(26), rs.getBoolean(27));
				}
				if(!vendeur.getVentes().contains(articleVendu)) {
					vendeur.addVente(articleVendu);
				}
				articleVendu.setVendeur(vendeur);

				articles.add(articleVendu);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
		}
		return articles;

	}

}
