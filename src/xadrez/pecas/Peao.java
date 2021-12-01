package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.XadrezPecas;

public class Peao extends XadrezPecas {
	
	private PartidaXadrez partidaXadrez;

	public Peao(Tabuleiro tabuleiro, Cores cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);	
		this.partidaXadrez = partidaXadrez;
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
				
				//movimento de vulnerabilidade peças brancas
				
			if(posicao.getFileira() == 3) {
				Posicao esquerda = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExistente(esquerda) && existeUmaPecaPosicaoOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getVulnerabilidade()) {
				   part[esquerda.getFileira() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getFileira(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExistente(direita) && existeUmaPecaPosicaoOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getVulnerabilidade()) {
				   part[direita.getFileira() - 1][direita.getColuna()] = true;
				}
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
				
				//movimento de vulnerabilidade peças pretos
				
			if(posicao.getFileira() == 4) {
			      Posicao esquerda = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);
			   if(getTabuleiro().posicaoExistente(esquerda) && existeUmaPecaPosicaoOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getVulnerabilidade()) {
				  part[esquerda.getFileira() + 1][esquerda.getColuna()] = true;
			}
				  Posicao direita = new Posicao(posicao.getFileira(), posicao.getColuna() + 1);
			    if(getTabuleiro().posicaoExistente(direita) && existeUmaPecaPosicaoOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getVulnerabilidade()) {
				  part[direita.getFileira() + 1][direita.getColuna()] = true;
					}
		      }
		}
		
		return part;
	            }
	public String toString() {
		return "p";
	}
	        }
	    
