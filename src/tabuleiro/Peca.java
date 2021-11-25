package tabuleiro;

public abstract class Peca {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] possivelMovimento();
	
	public boolean possivelMovimento(Posicao posicao) {//receber posições possiveis de uma peça
		return possivelMovimento()[posicao.getFileira()][posicao.getColuna()];
	}
	
	public boolean movimentoPossivelDaPeca() {
		boolean[][] part = possivelMovimento();
		for (int i=0; i<part.length; i++) {
			 for(int j=0; j<part.length; j++) {
				 if (part[i][j]) {
					return true; 
				 }
			 }
		}
		return false;
	}

}
