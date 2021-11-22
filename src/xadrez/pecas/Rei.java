package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.XadrezPecas;

public class Rei extends XadrezPecas {

	public Rei(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

}