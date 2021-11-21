package tabuleiro;

public class Positicao {

	private int fileira;
	private int coluna;
	
	public Positicao(int fileira, int coluna) {
		this.fileira = fileira;
		this.coluna = coluna;
	}

	public int getFileira() {
		return fileira;
	}

	public void setFileira(int fileira) {
		this.fileira = fileira;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	@Override
	public String toString() {
		return fileira + "," + coluna;
	}
	
}
