package tabuleiro;

public class Tabuleiro {
	
	private int fileiras;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int fileira, int colunas) {
		this.fileiras = fileira;
		this.colunas = colunas;
		pecas = new Peca[fileira][colunas];
	}

	public int getFileiras() {
		return fileiras;
	}

	public void setFileiras(int linhas) {
		this.fileiras = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	public Peca pecas (int linha , int coluna) {
		return pecas[linha][coluna];
	}
	
	public Peca pecas(Posicao posicao) {
		return pecas[posicao.getFileira()][posicao.getColuna()];
	}
	
	
	

}
