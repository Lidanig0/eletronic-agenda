package agendadecontatos;

public class Telefone {
	public String telefone;
	public String ddd;
	public String Countrycode;
	
	public Telefone(String telefone, String ddd, String Countrycode) {
		this.telefone = telefone;
		this.ddd = " (" + ddd + ") ";
		this.Countrycode = "+" + Countrycode;
	}
}
