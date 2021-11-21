package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public class XadrezPecas extends Peca {
	
	private Cores cor;

	public XadrezPecas(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cores getCor() {
		return cor;
	}


	
	
	

}
