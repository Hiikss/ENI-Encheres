package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Retrait {
	private int noRetrait;
	private String rue;
	private String code_postal;
	private String ville;
	private List<ArticleVendu> article;
	
	
	public Retrait() {
		article = new ArrayList<>();
	}
	
	

	/**
	 * @param rue
	 * @param code_postal
	 * @param ville
	 */
	public Retrait(String rue, String code_postal, String ville) {
		super();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}



	/**
	 * @param noRetrait
	 * @param rue
	 * @param code_postal
	 * @param ville
	 * @param article
	 */
	public Retrait(int noRetrait, String rue, String code_postal, String ville, List<ArticleVendu> article) {
		super();
		this.noRetrait = noRetrait;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.article = article;
		article = new ArrayList<>();
	}



	/**
	 * @param rue
	 * @param code_postal
	 * @param ville
	 * @param article
	 */
	public Retrait(String rue, String code_postal, String ville, List<ArticleVendu> article) {
		super();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.article = article;
		article = new ArrayList<>();
	}



	public int getNoRetrait() {
		return noRetrait;
	}



	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}



	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	

	public List<ArticleVendu> getArticle() {
		return article;
	}



	public void setArticle(List<ArticleVendu> article) {
		this.article = article;
	}



	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + ", article=" + article
				+ "]";
	}



	
	
	
	

}
