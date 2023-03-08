package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.RetraitDAOJdbcImpl;

public class RetraitManager {
	
	private static RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
	private static BusinessException be = new BusinessException();
	
	public RetraitManager() {
	}
	
	public static Retrait insertRetrait (Retrait retrait) throws BusinessException {
			
		validerAdresse(retrait, be);
		
		if(!be.hasErreurs()) {
			retraitDAO.insert(retrait);
		}
		else
		{
			throw be;
		}
		return retrait;
		
	}

	public static Retrait selectionnerRetraitById(int id) throws BusinessException {
		return retraitDAO.SelectById(id);
	}
	
	
	
	private static void validerAdresse(Retrait retrait, BusinessException be) {
		
		if(retrait.getRue() == null || retrait.getCode_postal() == null || retrait.getVille() == null 
				|| retrait.getRue().trim().equals("") ||  retrait.getCode_postal().trim().equals("") 
				|| retrait.getVille().trim().equals(""))
		{
			be.ajouterErreur(CodesResultatBLL.REGLE_RETRAITS_ADRESSE_ERREUR);
		}
	}

}
