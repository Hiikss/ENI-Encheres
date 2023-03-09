package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;


public interface ArticleDAO {
	
	public void insert(ArticleVendu article) throws BusinessException;

	public List<ArticleVendu> selectAll() throws BusinessException;
}
