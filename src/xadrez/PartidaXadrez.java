package xadrez;

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
	
	private void LugarNovaPeca(char coluna, int fileira, XadrezPecas peca) {//Para instanciar as peças do xadrez informando o sistema do xadrez ao invés de matriz
		tabuleiro.lugarPeca(peca, new XadrezPosicao(coluna, fileira).toPosicao());
	}
	
	private void iniciaPartida () {
		LugarNovaPeca('b', 6, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('e' , 8, new Rei(tabuleiro, Cores.PRETO));
		LugarNovaPeca('e', 1, new Torre(tabuleiro, Cores.BRANCO));
	}
	
}
