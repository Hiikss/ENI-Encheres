package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	
	private EnchereDAO enchererDAO;
	private static EnchereManager instance;

	public EnchereManager() {
		enchererDAO = DAOFactory.getEnchereDAO();
	}

	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}

	public void select(ArticleVendu article) throws BusinessException {
		this.enchererDAO.select(article);
	}
	
	public Enchere insert(Enchere enchere) throws BusinessException{
			BusinessException exception = new BusinessException();
		
		
		this.validerEnchere(enchere, exception);
		
			if(!exception.hasErreurs()) {
				
		
			this.enchererDAO.insert(enchere);
		}
			else
			{
				throw exception;
			}
		System.out.println(enchere.toString());
		
		return enchere;
	}
	
	
	
public void validerEnchere(Enchere enchere,BusinessException businessException) throws BusinessException{
		
		if(enchere.getMontantEchere()== 0){
            businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_MONTANT_ERREUR);
        }
		
		
		if(enchere.getDateEnchere() == null || enchere.getDateEnchere().isBefore(LocalDate.now())) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_DATE_ERREUR);
		}
		
	
		
	}

}
