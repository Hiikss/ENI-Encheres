package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {
	
	public Categorie SelectById(int id) throws BusinessException;

}
