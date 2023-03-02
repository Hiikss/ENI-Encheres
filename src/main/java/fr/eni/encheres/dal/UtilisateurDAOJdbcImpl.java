package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT = "INSERT INTO Utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,hashbytes('sha2_256',?),?,?);";
	private static final String SE_CONNECTER = "SELECT * FROM Utilisateurs where (pseudo=? or email=?) and mot_de_passe=hashbytes('sha2_256',?)";
	
	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.getAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void seConnecter(Utilisateur utilisateur) throws BusinessException{
		if(utilisateur==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SE_CONNECTER_UTILISATEUR_ECHEC);
			throw businessException;
		}	
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SE_CONNECTER);
			pstmt.setString(1, Utilisateur.getPseudo());
			pstmt.setString(2, Utilisateur.getEmail());
			pstmt.setString(3, Utilisateur.getMotDePasse());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("No_Administrateur"));
				if(utilisateur.getPseudo()==null)
				{
					utilisateur.setPseudo(rs.getString("Pseudo"));
				}else utilisateur.setEmail(rs.getString("Email"));
				utilisateur.setNom(rs.getString("Nom"));
				utilisateur.setPrenom(rs.getString("Prenom"));
				utilisateur.setTelephone(rs.getString("Telephone"));
				utilisateur.setRue(rs.getString("Rue"));
				utilisateur.setCodePostal(rs.getString("Code_Postal"));
				utilisateur.setVille(rs.getString("Ville"));
				utilisateur.setCredit(rs.getInt("Credit"));
				utilisateur.setAdministrateur(rs.getBoolean("Administrateur"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SE_CONNECTER_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}
	
	@Override
	public void update(Utilisateur utilisateur) throws BusinessException{
			if(utilisateur==null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
				throw businessException;
			}
	}

	@Override
	public void delete(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
	}

	@Override
	public void selectbyId(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

	}
}
