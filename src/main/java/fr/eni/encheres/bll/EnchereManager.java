package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
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

	public List<Enchere> selectAll() throws BusinessException {
		return this.enchererDAO.selectAll();
	}
}
