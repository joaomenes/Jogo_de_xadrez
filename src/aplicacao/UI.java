package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.XadrezPecas;
import xadrez.XadrezPosicao;

public class UI {  //UI = user interface
	
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";   //Cores do TEXTO
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";  //Cores do FUNDO
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

 // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void limparTela() {
           System.out.print("\033[H\033[2J");
           System.out.flush();
        }
    
    public static XadrezPosicao leituraXadrezPosicao(Scanner sc) {
    	try {
    	    String s = sc.nextLine();
    	    char coluna = s.charAt(0);
    	    int fileira = Integer.parseInt(s.substring(1));
    	    return new XadrezPosicao(coluna, fileira );
    	}
    	catch(InputMismatchException e) {
    		throw new InputMismatchException ("Erro para a posi??o de xadrez. Os valores v?lidos s?o de a1 a h8");
    	    }
    	}
    
    public static void printPartida(PartidaXadrez partidaXadrez, List<XadrezPecas> capturada) {
    	printTabuleiro(partidaXadrez.getPecas());
    	System.out.println();
    	printPecaCapturada(capturada);
    	System.out.println();
    	System.out.print("Turno: " +  partidaXadrez.getTurno());
    	if(!partidaXadrez.getCheckMate()) {
    	System.out.print("Aguardando o Jogador: "+ partidaXadrez.getAtualJogador());
    	if(partidaXadrez.getCheck()) {
    		System.out.println("CHECK!");
    	}
    }
    	else {
    		System.out.println("CHECKMATE");
    		System.out.println("vencedor: " + partidaXadrez.getAtualJogador());
    	  }
    	}
	
	public static void printTabuleiro(XadrezPecas[][] pecas) {
	  for (int i=0; i<pecas.length; i++) {
		  System.out.print((8-i) + " ");
		  for(int j=0; j<pecas.length; j++) {
			  printPeca(pecas[i][j], false);
		  }
		  System.out.println();
	  }
		System.out.println("  a b c d e f g h");
   }
	
	public static void printTabuleiro(XadrezPecas[][] pecas, boolean[][] possivelMovimento) {  //para colocar cor nos possveis movimentos
		  for (int i=0; i<pecas.length; i++) {
			  System.out.print((8-i) + " ");
			  for(int j=0; j<pecas.length; j++) {
				  printPeca(pecas[i][j], possivelMovimento[i][j]);
			  }
			  System.out.println();
		  }
			System.out.println("  a b c d e f g h");
	   }
	
	private static void printPeca(XadrezPecas peca, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
	
    	if (peca == null) {
            System.out.print("-"+ ANSI_RESET);
        }
        else {
            if (peca.getCor() == Cores.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printPecaCapturada(List<XadrezPecas> capturada) { //imprimir a lista de pe?as capturadas
		List<XadrezPecas> branca = capturada.stream().filter(x -> x.getCor() == Cores.BRANCO).collect(Collectors.toList());
		List<XadrezPecas> preto = capturada.stream().filter(x -> x.getCor() == Cores.PRETO).collect(Collectors.toList());
		System.out.print("Pe?as capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE); //para garantir que seja imprimida a cor Branca
		System.out.print(Arrays.toString(branca.toArray())); //jeito de imprimir uma array de valores no JAVA
		System.out.println(ANSI_RESET);// para resetar a cor da impress?o
		
		System.out.print("Pe?as capturadas: ");
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW); //para garantir que seja imprimida a cor Preta
		System.out.print(Arrays.toString(preto.toArray())); //jeito de imprimir uma array de valores no JAVA
		System.out.print(ANSI_RESET);// para resetar a cor da impress?o
		
	}
	
}
