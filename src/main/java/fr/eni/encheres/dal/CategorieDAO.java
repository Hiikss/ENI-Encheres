package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {
	
	public Categorie SelectById(int id) throws BusinessException;
	
	public List<Categorie> SelectAll() throws BusinessException;

}
