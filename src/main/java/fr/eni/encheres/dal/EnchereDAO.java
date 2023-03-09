package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public interface EnchereDAO {

	void select(ArticleVendu article) throws BusinessException;

}
