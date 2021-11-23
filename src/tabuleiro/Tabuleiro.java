package tabuleiro;

public class Tabuleiro {
	
	private int fileiras;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int fileiras, int colunas) {
		if (fileiras < 1 || colunas < 1) {
			throw new TabuleiroExcecao("Erro criando tabuleiro, é necessário ter uma linha ou uma coluna");
		}
		this.fileiras = fileiras;
		this.colunas = colunas;
		pecas = new Peca[fileiras][colunas];
	}

	public int getFileiras() {
		return fileiras;
	}

	public int getColunas() {
		return colunas;
	}

	public Peca pecas (int fileira , int coluna) {
		if (!posicaoExistente(fileira, coluna)) {
			throw new TabuleiroExcecao("Essa posição não existe no tabuleiro");			
		}
		return pecas[fileira][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroExcecao("Essa posição não existe no tabuleiro");			
		}
		return pecas[posicao.getFileira()][posicao.getColuna()];
	}
	
	public void lugarPeca(Peca peca, Posicao posicao) {
		if (pecaExistente(posicao)) {
			throw new TabuleiroExcecao("Já tem uma peça nesta posição " + posicao);
		}
		pecas[posicao.getFileira()][posicao.getColuna()]= peca;
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if(!posicaoExistente(posicao)) {
			throw new TabuleiroExcecao("Essa posição não existe no tabuleiro");
		}
		if (peca(posicao)==null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getFileira()][posicao.getColuna()] = null;
		return aux;
	}
	
	private boolean posicaoExistente(int fileira, int coluna) {
		return fileira >= 0 && fileira < fileiras  && coluna >= 0 && coluna < colunas; //para verificar se há linhas  e colunas no tabuleiro 
	}
	
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getFileira(), posicao.getColuna());
	}
	
	public boolean pecaExistente (Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroExcecao("Essa posição não existe no tabuleiro");			
		}
		return peca(posicao) != null;
	}
	

}
