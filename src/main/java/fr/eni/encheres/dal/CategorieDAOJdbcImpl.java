package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;


public class CategorieDAOJdbcImpl implements CategorieDAO{
	
	private static final String SELECTBYID ="Select * FROM Categories where no_categorie =?";
	private static final String SELECTALL = "select * from CATEGORIES";

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
	
	
	@Override
	public List<Categorie> SelectAll() throws BusinessException {
		
		List<Categorie> categories = new ArrayList<Categorie>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(SELECTALL);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				categories.add(categorie);
			}
					

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.LECTURE_ALLCATEGORIES_ECHEC);
			throw be;

		}
		return categories;
	}

}
