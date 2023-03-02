package fr.eni.encheres.dal;


public abstract class DAOFactory {
	
	public static UtilisateurDAO getAvisDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
}