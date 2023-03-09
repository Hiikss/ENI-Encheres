package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleDAO {
	

	public void insert(ArticleVendu article) throws BusinessException;
	
	public ArticleVendu selectById(int id) throws BusinessException;
	
	public void update(ArticleVendu article) throws BusinessException;


	List<ArticleVendu> selectAll() throws BusinessException;

}
