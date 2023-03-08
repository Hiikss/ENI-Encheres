package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO{
	
	private static final String INSERT = "insert into RETRAITS (rue, code_postal, ville) values (?,?,?);";
	private static final String SELECTBYID ="Select * FROM Retraits where no_retrait =?";

	@Override
	public void insert(Retrait retrait) throws BusinessException {
		BusinessException be = new BusinessException();
		if(retrait == null) {
			
			be.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw be;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, retrait.getRue());
			stmt.setString(2, retrait.getCode_postal());
			stmt.setString(3, retrait.getVille());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
					if(rs.next()) {
						retrait.setNoRetrait(1);
					}
		}catch(Exception e) {
			e.printStackTrace();
			if(e instanceof BusinessException) {
				throw be;
			}
			throw new BusinessException(CodesResultatDAL.INSERT_RETRAIT_ECHEC);
		}
		
	}

	@Override
	public Retrait SelectById(int id) throws BusinessException {
		
		Retrait retrait = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				retrait = new Retrait();
				retrait.setNoRetrait(rs.getInt("no_retrait"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
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
