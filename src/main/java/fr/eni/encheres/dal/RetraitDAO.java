package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	public void insert(Retrait retrait) throws BusinessException;
	
	public Retrait SelectById(int id) throws BusinessException;

}
