package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.CategorieDAOJdbcImpl;

public class CategorieManager {
	
	private static CategorieDAO categorieDAO = new CategorieDAOJdbcImpl();
	private static BusinessException businessException = new BusinessException();
	
	public CategorieManager() {

	}
	
	public static Categorie SelectCategorieById(int id ) throws BusinessException
	{
		return categorieDAO.SelectById(id);
	}

}
