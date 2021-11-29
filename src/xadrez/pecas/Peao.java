package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.XadrezPecas;

public class Peao extends XadrezPecas {

	public Peao(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro, cor);	
	}

	@Override
	public boolean[][] possivelMovimento() {
    boolean[][] part = new boolean[getTabuleiro().getFileiras()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//para cima
		if(getCor() == Cores.BRANCO) {
			p.setValores(posicao.getFileira() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {
				part[p.getFileira()][p.getColuna()] = true;
			}
			
			//Regra de primeiro movimento do Peão
			p.setValores(posicao.getFileira() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getFileira() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().pecaExistente(p2) && getContadorDeMovimento() == 0 ) {
				part[p.getFileira()][p.getColuna()] = true;
				}
		    
			//casas em diagonais
			p.setValores(posicao.getFileira() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {
				part[p.getFileira()][p.getColuna()] = true;
				
			}
				p.setValores(posicao.getFileira() - 1, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {
					part[p.getFileira()][p.getColuna()] = true;
			}
		}
		
		else { // movimentos para peões das peças pretas
			p.setValores(posicao.getFileira() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {
				part[p.getFileira()][p.getColuna()] = true;
			}
			
			//Regra de primeiro movimento do Peão
			p.setValores(posicao.getFileira() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getFileira() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().pecaExistente(p2) && getContadorDeMovimento() == 0 ) {
				part[p.getFileira()][p.getColuna()] = true;
				}
		    
			//casas em diagonais
			p.setValores(posicao.getFileira() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {
				part[p.getFileira()][p.getColuna()] = true;
				
			}
				p.setValores(posicao.getFileira() + 1, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p)) {
					part[p.getFileira()][p.getColuna()] = true;
			}
		}
		
		return part;
	            }
	public String toString() {
		return "p";
	}
	        }
	    
