package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String SELECT_ALL = "select * FROM ENCHERES e, UTILISATEURS u1, UTILISATEURS u2, ARTICLES_VENDUS a, RETRAITS r, CATEGORIES c WHERE e.no_article=a.no_article AND a.no_article=r.no_article AND a.no_categorie=c.no_categorie AND e.no_utilisateur=u1.no_utilisateur AND a.no_utilisateur=u2.no_utilisateur;";

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//Retrait
				Retrait retrait = Retrait.getRetraitIfExists(rs.getInt("noRetrait"));
				if(retrait==null) {
					retrait = new Retrait(rs.getInt("noRetrait"), rs.getString("rue"), rs.getString("cp"), rs.getString("ville"));
				}
				//Categorie
				Categorie categorie = Categorie.getCategorieIfExists(rs.getInt("noCat"));
				if(categorie==null) {
					categorie = new Categorie(rs.getInt("noCat"), rs.getString("libelle"));
				}
				//ArticleVendu
				ArticleVendu articleVendu = ArticleVendu.getArticleIfExists(rs.getInt("noArticle"));
				if(articleVendu==null) {
					articleVendu = new ArticleVendu(rs.getInt("noArticle"), rs.getString("nom"), rs.getString("desc"), rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate(), rs.getInt("prixInit"), rs.getInt("prixVente"));
				}
				articleVendu.setLieuRetrait(retrait);
				articleVendu.setCategorieArticle(categorie);
				
				if(!categorie.getArticles().contains(articleVendu)) {
					categorie.addArticle(articleVendu);
				}
				retrait.setArticle(articleVendu);
				
				Utilisateur vendeur = Utilisateur.getUtilisateurIfExists(rs.getInt("noUtil"));
				if(vendeur==null) {
					vendeur = new Utilisateur();
				}
				if(!vendeur.getVentes().contains(articleVendu)) {
					vendeur.addVente(articleVendu);
				}
				articleVendu.setVendeur(vendeur);
				
				Utilisateur encherisseur = new Utilisateur();
				
				//Enchere
				Enchere enchere = new Enchere(rs.getDate("date").toLocalDate(), rs.getInt("montant"), articleVendu, encherisseur);
				if(!articleVendu.getEncheres().contains(enchere)) {
					articleVendu.addEnchere(enchere);
				}
				if(!vendeur.getEncheres().contains(enchere)) {
					vendeur.addEnchere(enchere);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
		}
		return null;
	}

}
