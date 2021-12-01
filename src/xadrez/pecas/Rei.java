package xadrez.pecas;



import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.XadrezPecas;
import xadrez.pecas.Torre;

public class Rei extends XadrezPecas {
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cores cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;		
	}
	
	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		XadrezPecas p = (XadrezPecas)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testTorreRoque(Posicao posicao) {
		XadrezPecas  p = (XadrezPecas)getTabuleiro().peca(posicao);
		return p  != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorDeMovimento() == 0;
		
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
		
		//Movimento especial Roque
		if (getContadorDeMovimento() == 0 && !partidaXadrez.getCheck()) {
			//Movimento especial roque pequeno ao lado do rei
			Posicao posi1 = new Posicao(posicao.getFileira(), posicao.getColuna() + 3);
			if (testTorreRoque(posi1)) {
				Posicao p1 = new Posicao(posicao.getFileira(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getFileira(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null){
					part[posicao.getFileira()][posicao.getColuna() + 2] = true;
				}
			} 
			//Movimento especial roque grande ao lado da rainha
			Posicao posi2 = new Posicao(posicao.getFileira(), posicao.getColuna() - 4);
			if (testTorreRoque(posi2)) {
				Posicao p1 = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getFileira(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getFileira(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null){
					part[posicao.getFileira()][posicao.getColuna() - 2] = true;
				}
			} 
		}
		return part;
	}
}
