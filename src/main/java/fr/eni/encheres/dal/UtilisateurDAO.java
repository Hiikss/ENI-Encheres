package fr.eni.encheres.dal;



import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insert (Utilisateur utilisateur) throws BusinessException;
	
	public void seConnecter (Utilisateur utilisateur)throws BusinessException;

	void update(Utilisateur utilisateur) throws BusinessException;

	void delete(Utilisateur utilisateur) throws BusinessException;

	void selectbyId(Utilisateur utilisateur) throws BusinessException;
}
