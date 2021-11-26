package xadrez;

import tabuleiro.Posicao;

public class XadrezPosicao {

	private char coluna;
	private int fileira;
	
	public XadrezPosicao(char coluna, int fileira) {
		if(coluna < 'a' || coluna > 'h' || fileira < 1 || fileira > 8) { //para verificar a posi��o do xadrez.
			throw new XadrezExcecao("Erro para a posi��o de xadrez. Os valores v�lidos s�o de a1 a h8");
		}
		this.coluna = coluna;
		this.fileira = fileira;
	}

	public char getColuna() {
		return coluna;
	}

	public int getFileira() {
		return fileira;
	}

	protected Posicao toPosicao() {
		return new Posicao(8 - fileira, coluna - 'a');//converte a posi��o de xadrez para uma posi��o comum de matriz 
	}
	protected static XadrezPosicao paraPosicao(Posicao posicao) { //converte a posi��o de matriz para uma posi��o de xadrez
		return new XadrezPosicao((char)('a' + posicao.getColuna()), 8 - posicao.getFileira());
	}
	
	@Override
	public String toString() {
		return "" + coluna + fileira;
	}
}
