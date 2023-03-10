package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	void select(ArticleVendu article) throws BusinessException;
	public void insert(Enchere enchere) throws BusinessException;

}

