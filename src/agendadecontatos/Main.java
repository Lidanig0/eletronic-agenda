package agendadecontatos;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

public class Main {

	public static List<Contato> AGENDA = new LinkedList<Contato>();
	public static Contato NOVO_CONTATO;
	
	public static void main(String[] args) {
		JanelaMain frame = new JanelaMain();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
