package challenge;

public class Quote {

	private int id;
	private String actor;
	private String quote;

	public Quote() {
	}

	public Quote(int id, String actor, String quote) {
		this.id = id;
		this.actor = actor;
		this.quote = quote;
	}

	public Integer getId() {
		return this.id;
	}

	public String getActor() {
		return this.actor;
	}

	public String getQuote() {
		return this.quote;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

}
