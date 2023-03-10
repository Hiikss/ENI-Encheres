package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;


public class ArticleManager {
	
	private static ArticleDAO articleDAO;
	private static ArticleManager instance;
	
	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public static ArticleManager getInstance() {
		if(instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	public ArticleVendu insert(ArticleVendu article) throws BusinessException {
		BusinessException exception = new BusinessException();
		
		
		this.validerArticle(article, exception);
		
			if(!exception.hasErreurs()) {
				if(article.getLieuRetrait() == null) {
					Retrait r = new Retrait(article.getVendeur().getRue(), article.getVendeur().getVille(), article.getVendeur().getCodePostal());
					article.setLieuRetrait(r);
				}
				
		
			this.articleDAO.insert(article);
		}
			else
			{
				throw exception;
			}
		System.out.println(article.toString());
		
		return article;
	}
	
	public List<ArticleVendu> selectAll() throws BusinessException {
		return this.articleDAO.selectAll();
	}
	
	public void validerArticle(ArticleVendu article,BusinessException businessException) throws BusinessException{
		
		if(article.getNomArticle().isBlank()){
            businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
        }
		
		if(article.getDescription().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_ERREUR);
		}
		
		if(article.getDateDebutEncheres() == null || article.getDateFinEncheres() == null || article.getDateDebutEncheres().isBefore(LocalDate.now())|| article.getDateFinEncheres().isBefore(article.getDateDebutEncheres())) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_ERREUR);
		}
		
	
		
	}
	
	public static ArticleVendu selectById(int id)throws BusinessException{
		return articleDAO.selectById(id);
		
	}
	
	public void update(ArticleVendu article) throws BusinessException{
		BusinessException be = new BusinessException();
		this.validerArticle(article, be);
		
		if (!be.hasErreurs()) {
			this.articleDAO.update(article);
		} else {
			throw be;
		}
		System.out.println(article.toString());
	}
	

}
