package agendadecontatos;

public class Address {
	public String rua;
	public String numero;
	public String bairro;
	public String complemento;
	public String cidade;
	public String estado;
	public String cep;
	public String pais;
	
	public Address(String rua, String numero, String bairro, String complemento, 
			String cidade, String estado, String cep, String pais) {
		
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.pais = pais;
	}
}
