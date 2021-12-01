package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.XadrezPecas;

public class Queen extends XadrezPecas {

	public Queen(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] possivelMovimento() {
		boolean[][] part = new boolean[getTabuleiro().getFileiras()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//para cima
		p.setValores(posicao.getFileira() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {//enquanto a posicao p existir e estiver vaga será verdadeira
			part[p.getFileira()][p.getColuna()] = true;
		p.setFileira(p.getFileira() - 1);
	}
		if(getTabuleiro().posicaoExistente(p) && existeUmaPecaPosicaoOponente(p)) {
			part[p.getFileira()][p.getColuna()] = true;	
		}
		
		//para esquerda
		p.setValores(posicao.getColuna(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {//enquanto a posicao p existir e estiver vaga será verdadeira
			part[p.getFileira()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
			}
		if(getTabuleiro().posicaoExistente(p) && existeUmaPecaPosicaoOponente(p)) {
			part[p.getFileira()][p.getColuna()] = true;	
			}
		
		//para direita
		p.setValores(posicao.getColuna(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {//enquanto a posicao p existir e estiver vaga será verdadeira
			part[p.getFileira()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
			}
		if(getTabuleiro().posicaoExistente(p) && existeUmaPecaPosicaoOponente(p)) {
			part[p.getFileira()][p.getColuna()] = true;	
			}
		
		//para baixo
		p.setValores(posicao.getFileira() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {//enquanto a posicao p existir e estiver vaga será verdadeira
			part[p.getFileira()][p.getColuna()] = true;
			p.setFileira(p.getFileira() + 1);
			}
		if(getTabuleiro().posicaoExistente(p) && existeUmaPecaPosicaoOponente(p)) {
			part[p.getFileira()][p.getColuna()] = true;	
			}
		
		//para noroeste
		    p.setValores(posicao.getFileira() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {//enquanto a posicao p existir e estiver vaga será verdadeira
			part[p.getFileira()][p.getColuna()] = true;
		    p.setValores(p.getFileira() - 1, p.getColuna() - 1);
			}
		if(getTabuleiro().posicaoExistente(p) && existeUmaPecaPosicaoOponente(p)) {
		    part[p.getFileira()][p.getColuna()] = true;	
			}
				
		//para nordeste
			p.setValores(posicao.getFileira() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {//enquanto a posicao p existir e estiver vaga será verdadeira
			part[p.getFileira()][p.getColuna()] = true;
		    p.setValores(p.getFileira() - 1, p.getColuna() + 1);
			}
		if(getTabuleiro().posicaoExistente(p) && existeUmaPecaPosicaoOponente(p)) {
			part[p.getFileira()][p.getColuna()] = true;	
			}
				
		//para sudeste
			p.setValores(posicao.getColuna() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {//enquanto a posicao p existir e estiver vaga será verdadeira
			part[p.getFileira()][p.getColuna()] = true;
			p.setValores(p.getFileira() + 1, p.getColuna() + 1);
			}
		if(getTabuleiro().posicaoExistente(p) && existeUmaPecaPosicaoOponente(p)) {
		    part[p.getFileira()][p.getColuna()] = true;	
			}
				
		//para sudoeste
			p.setValores(posicao.getFileira() + 1, posicao.getColuna() - 1 );
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {//enquanto a posicao p existir e estiver vaga será verdadeira
			part[p.getFileira()][p.getColuna()] = true;
			p.setValores(p.getFileira() + 1, p.getColuna() - 1);
			}
		if(getTabuleiro().posicaoExistente(p) && existeUmaPecaPosicaoOponente(p)) {
			part[p.getFileira()][p.getColuna()] = true;	
			}
		return part;
	}
}
