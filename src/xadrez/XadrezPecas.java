package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class XadrezPecas extends Peca {
	
	private Cores cor;
	private int contadorDeMovimento;

	public XadrezPecas(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public int getContadorDeMovimento() {
		return contadorDeMovimento;
	}
	
	public Cores getCor() {
		return cor;
	}
	
	public void aumentarMovimentacaoPeca() {
		contadorDeMovimento ++;
	}
	
	public void diminuirMovimentacaoPeca() {
		contadorDeMovimento --;
	}
	
	protected XadrezPosicao getXadrezPosicao() {
		return XadrezPosicao.paraPosicao(posicao);//converte a posição de matriz para uma posição de xadrez
	}
	
	protected boolean existeUmaPecaPosicaoOponente(Posicao posicao) { //Para verificar se existe uma peça do oponente na posição
		XadrezPecas p = (XadrezPecas) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
