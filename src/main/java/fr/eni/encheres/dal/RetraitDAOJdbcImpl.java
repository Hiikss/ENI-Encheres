package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO{
	

	private static final String INSERT = "insert into RETRAITS (no_article, rue, code_postal, ville) values (?,?,?,?);";
	private static final String SELECTBYARTICLE ="Select * FROM Retraits where no_article =?";


	@Override
	public void insert(Retrait retrait) throws BusinessException {
		BusinessException be = new BusinessException();
		if(retrait == null) {
			
			be.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw be;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(INSERT);
			stmt.setInt(1, retrait.getArticle().getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCode_postal());
			stmt.setString(4, retrait.getVille());
			
			stmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			if(e instanceof BusinessException) {
				throw be;
			}
			throw new BusinessException(CodesResultatDAL.INSERT_RETRAIT_ECHEC);
		}
		
	}

	@Override
	public Retrait SelectByArticle(ArticleVendu a) throws BusinessException {
		
		Retrait retrait = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(SELECTBYARTICLE);
			stmt.setInt(1, a.getNoArticle());
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				retrait = new Retrait(a, rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_ECHEC);
			throw be;
		}
		return retrait;
	}

}
