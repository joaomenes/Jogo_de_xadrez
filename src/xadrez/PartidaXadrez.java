package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		iniciaPartida();
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
	
	private void iniciaPartida () {
		tabuleiro.lugarPeca(new Torre(tabuleiro, Cores.BRANCO), new Posicao(2, 1));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cores.PRETO) , new Posicao(0,4));
		tabuleiro.lugarPeca(new Torre(tabuleiro, Cores.BRANCO), new Posicao(7, 4));
	}
	
}
