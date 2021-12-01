package aplicacao;



import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.XadrezExcecao;
import xadrez.XadrezPecas;
import xadrez.XadrezPosicao;


public class Programa {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<XadrezPecas> capturada = new ArrayList<>();
		
		while(!partidaXadrez.getCheckMate()) {
			try {
				UI.limparTela();
				UI.printPartida(partidaXadrez, capturada);
				System.out.println();
				System.out.println("Origem: ");
				XadrezPosicao origem = UI.leituraXadrezPosicao(sc);
				
				boolean[][] possivelMovimento = partidaXadrez.possivelMovimento(origem);
				UI.limparTela();
				UI.printTabuleiro(partidaXadrez.getPecas(), possivelMovimento);	  //para colocar cor nos possveis movimentos
				System.out.println();
				System.out.println("Destino: ");
				XadrezPosicao destino = UI.leituraXadrezPosicao(sc);
				
				XadrezPecas capturadaPeca = partidaXadrez.executarMovimentoXadrez(origem, destino);
				
				if(capturadaPeca != null) {
					capturada.add(capturadaPeca);
				}
				
				if(partidaXadrez.getPromocao() != null) {
					System.out.println("Entre com a peça para ser promovida (B/Q/C/T: ");
					String tipo = sc.nextLine().toUpperCase();
					while(!tipo.equals("B") && !tipo.equals("Q") && !tipo.equals("R")){
						System.out.println("Valor inválido da peça para ser promovida (B/Q/C/T: ");
						 tipo = sc.nextLine().toUpperCase();						
					}
				    partidaXadrez.substituirPeca(tipo);
				}
				
			   }
			catch (XadrezExcecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			    sc.nextLine();
			}
		} 
		UI.limparTela();
		UI.printPartida(partidaXadrez, capturada);
	}

}
