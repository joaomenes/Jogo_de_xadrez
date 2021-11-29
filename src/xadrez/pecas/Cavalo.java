package xadrez.pecas;



import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.XadrezPecas;

public class Cavalo extends XadrezPecas {

	public Cavalo(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}

	private boolean podeMover(Posicao posicao) {
		XadrezPecas p = (XadrezPecas)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	
	@Override
	public boolean[][] possivelMovimento() {
		boolean[][] part = new boolean[getTabuleiro().getFileiras()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0,0);
		
		
		p.setValores(posicao.getFileira() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		
		p.setValores(posicao.getFileira() - 2 , posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		
		p.setValores(posicao.getFileira() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
	
		p.setValores(posicao.getFileira() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		
		p.setValores(posicao.getFileira() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		
		p.setValores(posicao.getFileira() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		
		p.setValores(posicao.getFileira() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		part[p.getFileira()][p.getColuna()] = true;
		}
		
		
		p.setValores(posicao.getFileira() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		part[p.getFileira()][p.getColuna()] = true;
		}
		
		return part;
	}

}
