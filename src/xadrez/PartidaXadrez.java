package xadrez;

import tabuleiro.Tabuleiro;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
	}
	
	public XadrezPecas[][] getPecas(){
		XadrezPecas[][] part = new XadrezPecas[tabuleiro.getFileiras()][tabuleiro.getColunas()];
	    for (int i=0; i<tabuleiro.getFileiras(); i++){
	    	for (int j=0; j<tabuleiro.getColunas(); j++) {
	    		part[i][j] = (XadrezPecas) tabuleiro.pecas(i, j);
	    	}
	    }
	    return part;
	}
	
	
}
