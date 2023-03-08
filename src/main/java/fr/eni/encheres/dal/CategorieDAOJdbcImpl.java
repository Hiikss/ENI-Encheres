package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;


public class CategorieDAOJdbcImpl implements CategorieDAO{
	
	private static final String SELECTBYID ="Select * FROM Categories where no_categorie =?";

	@Override
	public Categorie SelectById(int id) throws BusinessException {
			Categorie categorie = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_ECHEC);
			throw be;
		}
		return categorie;
	}

}
