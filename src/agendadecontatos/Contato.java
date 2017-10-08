package agendadecontatos;

import java.util.LinkedList;
import java.util.List;

public class Contato {
	public String nome;
	public String sobrenome;
	public String idade;
	public String email;
	public List<Telefone> telefones;
	public List<Address> address;
	
	public Contato(String novoNome, String novoSobrenome, String idade, String email) {
		telefones = new LinkedList<Telefone>();
		address = new LinkedList<Address>();
		this.nome = novoNome;
		this.sobrenome = novoSobrenome;
		this.idade = idade;
		this.email = email;
	}
}
