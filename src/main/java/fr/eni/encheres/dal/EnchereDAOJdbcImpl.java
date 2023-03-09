package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String SELECT_ALL = "select * FROM RETRAITS r, CATEGORIES c, ARTICLES_VENDUS a, UTILISATEURS v, UTILISATEURS en, ENCHERES e WHERE e.no_article=a.no_article AND a.no_article=r.no_article AND a.no_categorie=c.no_categorie AND e.no_utilisateur=en.no_utilisateur AND a.no_utilisateur=v.no_utilisateur;";

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> encheres = new ArrayList<>();
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
				
				Utilisateur encherisseur = Utilisateur.getUtilisateurIfExists(rs.getInt(28));
				if(encherisseur==null) {
					encherisseur = new Utilisateur(rs.getInt(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33), rs.getString(34), rs.getString(35), rs.getString(36), rs.getBytes(37), rs.getInt(38), rs.getBoolean(39));
				}
				
				//Enchere
				Enchere enchere = new Enchere(rs.getDate(42).toLocalDate(), rs.getInt(43), articleVendu, encherisseur);
				if(!articleVendu.getEncheres().contains(enchere)) {
					articleVendu.addEnchere(enchere);
				}
				if(!encherisseur.getEncheres().contains(enchere)) {
					encherisseur.addEnchere(enchere);
				}
				encheres.add(enchere);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
		}
		return encheres;
	}

}