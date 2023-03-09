package fr.eni.encheres.bo;

public class Retrait {
	private String rue;
	private String code_postal;
	private String ville;
	private ArticleVendu article;
	
	public Retrait() {

	}
	
	/**
	 * @param rue
	 * @param code_postal
	 * @param ville
	 */
	public Retrait(String rue, String code_postal, String ville) {
		this();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	/**
	 * @param article
	 * @param rue
	 * @param code_postal
	 * @param ville
	 */
	public Retrait(ArticleVendu article, String rue, String code_postal, String ville) {
		super();
		this.article = article;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	/**
	 * @param rue
	 * @param code_postal
	 * @param ville
	 * @param article
	 */
	public Retrait(String rue, String code_postal, String ville, ArticleVendu article) {
		this(rue, code_postal, ville);
		this.article = article;
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

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Retrait [article=" + article + ", rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville
				+ "]";
	}
}