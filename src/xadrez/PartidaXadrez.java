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
	
	private void LugarNovaPeca(char coluna, int fileira, XadrezPecas peca) {//Para instanciar as pe�as do xadrez informando o sistema do xadrez ao inv�s de matriz
		tabuleiro.lugarPeca(peca, new XadrezPosicao(coluna, fileira).toPosicao());
	}
	
	private void iniciaPartida () {
		LugarNovaPeca('c', 2, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('d', 2, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('e', 2, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('e', 1, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('d', 1, new Rei(tabuleiro, Cores.BRANCO));

		LugarNovaPeca('c', 7, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('c', 8, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('d', 7, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('e', 7, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('e', 8, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('d', 8, new Rei(tabuleiro, Cores.PRETO));
	}
	
}
