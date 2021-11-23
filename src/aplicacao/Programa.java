package aplicacao;



import java.util.InputMismatchException;
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
		
		while(true) {
			try {
				UI.limparTela();
				UI.printTabuleiro(partidaXadrez.getPecas());
				System.out.println();
				System.out.println("Origem: ");
				XadrezPosicao origem = UI.leituraXadrezPosicao(sc);
				
				System.out.println();
				System.out.println("Destino: ");
				XadrezPosicao destino = UI.leituraXadrezPosicao(sc);
				
				XadrezPecas capturadaPeca = partidaXadrez.executarMovimentoXadrez(origem, destino);
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

	}

}
