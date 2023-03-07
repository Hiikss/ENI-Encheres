package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_PSEUDO = "SELECT * FROM Utilisateurs WHERE pseudo=?";
	private static final String SELECT_EMAIL = "SELECT * FROM Utilisateurs WHERE email=?";
	private static final String INSERT = "INSERT INTO Utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SE_CONNECTER = "SELECT * FROM Utilisateurs where (pseudo=? or email=?) and mot_de_passe=?";
	private static final String UPDATE_UTILISATEUR = "UPDATE Utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?";
	private static final String SELECT_BY_ID ="SELECT * FROM Utilisateurs where no_utilisateur =?";
	
	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			throw new BusinessException(CodesResultatDAL.INSERT_OBJET_NULL);
		}
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			// Test si le pseudo existe déjà
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_PSEUDO);
			pstmt.setString(1, utilisateur.getPseudo());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				be.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_PSEUDO_ECHEC);
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			// Test si l'email existe déjà
			pstmt = cnx.prepareStatement(SELECT_EMAIL);
			pstmt.setString(1, utilisateur.getEmail());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				be.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_EMAIL_ECHEC);
			}
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			// Si le pseudo et/ou l'email existent déjà, ça lance une erreur
			if (be.hasErreurs()) {
				throw be;
			}
			// Sinon on créer l'utilisateur
			else {
				pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setBytes(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getCredit());
				pstmt.setBoolean(11, utilisateur.isAdministrateur());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof BusinessException) {
				throw be;
			}
			throw new BusinessException(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
		}
	}

	@Override
	public void seConnecter(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			throw new BusinessException(CodesResultatDAL.UTILISATEUR_NULL);
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SE_CONNECTER);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getEmail());
			pstmt.setBytes(3, utilisateur.getMotDePasse());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				if (utilisateur.getPseudo() == null) {
					utilisateur.setPseudo(rs.getString("Pseudo"));
				} else {
					utilisateur.setEmail(rs.getString("Email"));
				}
				utilisateur.setNom(rs.getString("Nom"));
				utilisateur.setPrenom(rs.getString("Prenom"));
				utilisateur.setTelephone(rs.getString("Telephone"));
				utilisateur.setRue(rs.getString("Rue"));
				utilisateur.setCodePostal(rs.getString("Code_Postal"));
				utilisateur.setVille(rs.getString("Ville"));
				utilisateur.setCredit(rs.getInt("Credit"));
				utilisateur.setAdministrateur(rs.getBoolean("Administrateur"));

			} else {
				throw new BusinessException(CodesResultatDAL.SE_CONNECTER_UTILISATEUR_ECHEC);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.SE_CONNECTER_UTILISATEUR_ECHEC);
		}
	}

	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {
		PreparedStatement pstmt = null;
		boolean success = false;

		// Récupérer la connexion à la base de données
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()) {

			pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setBytes(9, utilisateur.getMotDePasse());

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
		}

	}

	@Override
	public void delete(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			throw new BusinessException(CodesResultatDAL.INSERT_OBJET_NULL);
		}
	}

	@Override
	public Utilisateur selectbyId(int id) throws BusinessException {
		Utilisateur utilisateur = null;
		Connection cnx;
		ResultSet rs;
	
		try {
			
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));
				utilisateur.setNoUtilisateur(id);
			}
			
			rs.close();
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(CodesResultatDAL.SELECT_USER_ECHEC);
		}

		return utilisateur;

	}

	@Override
	public void selectbyId(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
}
