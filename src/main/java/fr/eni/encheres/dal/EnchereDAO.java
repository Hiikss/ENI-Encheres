package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Enchere;

public class EnchereDAO {

	public static Enchere getEnchereById(int idEnchere) {
		return getEnchereById(idEnchere);
	}

	public static void soumettreEnchere(int idEnchere, double montant) {
		
	}
=======
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	List<Enchere> selectAll() throws BusinessException;

}
