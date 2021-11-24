package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class XadrezPecas extends Peca {
	
	private Cores cor;

	public XadrezPecas(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cores getCor() {
		return cor;
	}
	
	protected boolean existeUmaPecaPosicaoOponente(Posicao posicao) { //Para verificar se existe uma pe�a do oponente na posi��o
		XadrezPecas p = (XadrezPecas) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
