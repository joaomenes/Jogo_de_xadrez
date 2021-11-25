package xadrez.pecas;



import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.XadrezPecas;

public class Rei extends XadrezPecas {

	public Rei(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		XadrezPecas p = (XadrezPecas)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	
	@Override
	public boolean[][] possivelMovimento() {
		boolean[][] part = new boolean[getTabuleiro().getFileiras()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0,0);
		
		//para cima
		p.setValores(posicao.getFileira() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		//para baixo
		p.setValores(posicao.getFileira() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		//para esquerda
		p.setValores(posicao.getFileira(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		//para direita
		p.setValores(posicao.getFileira(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		//para noroeste
		p.setValores(posicao.getFileira() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		//para nordeste
		p.setValores(posicao.getFileira() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			part[p.getFileira()][p.getColuna()] = true;
		}
		
		//para sudoeste
		p.setValores(posicao.getFileira() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		part[p.getFileira()][p.getColuna()] = true;
		}
		
		//para sudeste
		p.setValores(posicao.getFileira() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		part[p.getFileira()][p.getColuna()] = true;
		}
		
		return part;
	}

}
