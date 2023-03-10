package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String SELECT = "SELECT * FROM utilisateurs u, encheres e, articles_vendus a WHERE e.no_article=a.no_article AND a.no_article=? AND e.no_utilisateur=u.no_utilisateur";
	private static final String INSERT ="insert into Encheres (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(?,?,?,?);";
	
	@Override
	public void select(ArticleVendu article) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT);
			pstmt.setInt(1, article.getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Utilisateur encherisseur = Utilisateur.getUtilisateurIfExists(rs.getInt(1));
				if(encherisseur==null) {
					encherisseur = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBytes(10), rs.getInt(11), rs.getBoolean(12));
				}
				Enchere enchere = Enchere.getEnchereIfExists(rs.getDate(15).toLocalDate(), rs.getInt(16), article, encherisseur);
				if(enchere==null) {
					enchere = new Enchere(rs.getDate(15).toLocalDate(), rs.getInt(16), article, encherisseur);
				}
				
				encherisseur.addEnchere(enchere);
				article.addEnchere(enchere);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
		}
	}


	@Override
	public void insert(Enchere enchere) throws BusinessException {
		if (enchere == null) {
			throw new BusinessException(CodesResultatDAL.INSERT_OBJET_NULL);
		}
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
				PreparedStatement pstmt = cnx.prepareStatement(INSERT);

				
				pstmt.setInt(1, enchere.getEncherisseur().getNoUtilisateur());
				pstmt.setInt(2, enchere.getArticle().getNoArticle());
				pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere()));
				pstmt.setInt(4, enchere.getMontantEchere());
				
				pstmt.executeUpdate();
				
			
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof BusinessException) {
				throw be;
			}
			throw new BusinessException(CodesResultatDAL.INSERT_ENCHERE_ECHEC);
		}
		
	}

}