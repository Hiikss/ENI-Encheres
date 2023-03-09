package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	public void insert(Retrait retrait) throws BusinessException;
	
	public Retrait SelectByArticle(ArticleVendu a) throws BusinessException;

}
