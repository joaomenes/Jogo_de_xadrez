package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Queen;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	private int turno;
	private Cores atualJogador;
	private boolean check;
	private boolean checkMate;
	private XadrezPecas vulnerabilidade;
	private XadrezPecas promocao;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();	
			
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
	    turno = 1;
	    atualJogador = Cores.BRANCO;
		iniciaPartida();
		}
	
	public int getTurno() {
		return turno;
	}
	
	public Cores getAtualJogador() {
		return atualJogador;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public XadrezPecas getVulnerabilidade() {
		return vulnerabilidade;
	}
	
	public XadrezPecas getPromocao() {
		return promocao;
	}
	
	public XadrezPecas[][] getPecas(){
		XadrezPecas[][] part = new XadrezPecas[tabuleiro.getFileiras()][tabuleiro.getColunas()];
	    for (int i=0; i<tabuleiro.getFileiras(); i++){
	    	for (int j=0; j<tabuleiro.getColunas(); j++) {
	    		part[i][j] = (XadrezPecas) tabuleiro.pecas(i, j);
	    	}
	    }
	    return part;
	}
	
	public boolean [][] possivelMovimento(XadrezPosicao origemPosicao){
		Posicao posicao = origemPosicao.toPosicao();
		validadeOrigemPosicao(posicao);
		return tabuleiro.peca(posicao).possivelMovimento();
		
	}
	
	public XadrezPecas executarMovimentoXadrez(XadrezPosicao origemPosicao, XadrezPosicao destinoPosicao) {
		Posicao origem = origemPosicao.toPosicao();
		Posicao destino = destinoPosicao.toPosicao();
		validadeOrigemPosicao(origem);
		validadeDestinoPosicao(origem, destino);
		Peca capturadaPeca = fazerMover(origem, destino);
		
		if(testarCheck(atualJogador)) {
			desfazerMovimento(origem, destino, capturadaPeca);
			throw new XadrezExcecao("Voc? n?o pode se colocar em xeque");
		}
		
		XadrezPecas moveuPeca = (XadrezPecas)tabuleiro.peca(destino);
		
		//Movimento de promo??o
		promocao = null;
		if(moveuPeca instanceof Peao) {
			if ((moveuPeca.getCor() == Cores.BRANCO && destino.getFileira() == 0) || (moveuPeca.getCor() == Cores.PRETO && destino.getFileira() == 0)) {
				promocao = (XadrezPecas)tabuleiro.peca(destino);
				promocao = substituirPeca("Q");
			}
		}
		
		check = (testarCheck(oponente(atualJogador))) ? true : false;
		
		if(testarCheckMate(oponente(atualJogador))) {
			checkMate = true;
		}
		else {
		proximoTurno();
		}
		
		//pecial movimento en passant (Vulnerabilidade)
		if(moveuPeca instanceof Peao && (destino.getFileira() == origem.getFileira() - 2 || destino.getFileira() == origem.getFileira() + 2 )) {
			vulnerabilidade = moveuPeca;
		}
		else {
			vulnerabilidade = null;
		}
		return (XadrezPecas) capturadaPeca;
		
	}
	
	public XadrezPecas substituirPeca(String tipo) {
		if (promocao == null) {
			throw new IllegalStateException("N?o h? pe?a para ser promovida");
		}
		if(!tipo.equals("B") && !tipo.equals("Q") && !tipo.equals("R")) {
			return promocao;
		}
		
		Posicao pos = promocao.getXadrezPosicao().toPosicao();
		Peca p = tabuleiro.removerPeca(pos);
		pecasNoTabuleiro.remove(p);
		
		XadrezPecas novaPeca = novaPeca(tipo, promocao.getCor());
		tabuleiro.lugarPeca(novaPeca, pos);
		pecasNoTabuleiro.add(novaPeca);
		
		return novaPeca;
	}
	
	private XadrezPecas novaPeca (String tipo, Cores cor) {
		if (tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if (tipo.equals("Q")) return new Queen(tabuleiro, cor);
		if (tipo.equals("C")) return new Cavalo(tabuleiro, cor);
	    return new Torre(tabuleiro, cor);
	}
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		XadrezPecas p = (XadrezPecas) tabuleiro.removerPeca(origem);
		p.aumentarMovimentacaoPeca();
		Peca capturadaPeca = tabuleiro.removerPeca(destino);
		tabuleiro.lugarPeca(p, destino);
		
		if(capturadaPeca != null) {
			pecasNoTabuleiro.remove(capturadaPeca);
			pecasCapturadas.add(capturadaPeca);
		}
		//espcial movimneto Roque  pequeno, rei
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getFileira(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getFileira(), origem.getColuna() + 1);
			XadrezPecas torre = (XadrezPecas)tabuleiro.removerPeca(origemTorre);
			tabuleiro.lugarPeca(torre, destinoTorre);
			torre.aumentarMovimentacaoPeca();			
		}
		//espcial movimneto Roque  grande, rei
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getFileira(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getFileira(), origem.getColuna() - 1);
			XadrezPecas torre = (XadrezPecas)tabuleiro.removerPeca(origemTorre);
			tabuleiro.lugarPeca(torre, destinoTorre);
			torre.aumentarMovimentacaoPeca();			
		}
		
		//movimento especial vulnerabilidade
		if(p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && capturadaPeca == null) {
				Posicao peaoPosicao;
				if (p.getCor() == Cores.BRANCO) {
					peaoPosicao = new Posicao(destino.getFileira() + 1, destino.getColuna());
				}
				else {
					peaoPosicao = new Posicao(destino.getFileira() - 1, destino.getColuna());

				}
				capturadaPeca = tabuleiro.removerPeca(peaoPosicao);
				pecasCapturadas.add(capturadaPeca);
				pecasNoTabuleiro.remove(capturadaPeca);
			}
		}

		return capturadaPeca;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturadaPeca) {
		XadrezPecas p = (XadrezPecas) tabuleiro.removerPeca(destino);
		p.diminuirMovimentacaoPeca();
		tabuleiro.lugarPeca(p, origem);
		
		if (capturadaPeca != null) {
			tabuleiro.lugarPeca(capturadaPeca, destino);
			pecasCapturadas.remove(capturadaPeca);
			pecasCapturadas.add(capturadaPeca);
		}
		//espcial movimneto Roque  pequeno, rei
				if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
					Posicao origemTorre = new Posicao(origem.getFileira(), origem.getColuna() + 3);
					Posicao destinoTorre = new Posicao(origem.getFileira(), origem.getColuna() + 1);
					XadrezPecas torre = (XadrezPecas)tabuleiro.removerPeca(destinoTorre);
					tabuleiro.lugarPeca(torre, origemTorre);
					torre.diminuirMovimentacaoPeca();			
				}
				//espcial movimneto Roque  grande, rei
				if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
					Posicao origemTorre = new Posicao(origem.getFileira(), origem.getColuna() - 4);
					Posicao destinoTorre = new Posicao(origem.getFileira(), origem.getColuna() - 1);
					XadrezPecas torre = (XadrezPecas)tabuleiro.removerPeca(destinoTorre);
					tabuleiro.lugarPeca(torre, origemTorre);
					torre.diminuirMovimentacaoPeca();			
				}
				
				//movimento especial vulnerabilidade
				if(p instanceof Peao) {
					if (origem.getColuna() != destino.getColuna() && capturadaPeca == vulnerabilidade) {
						XadrezPecas peao = (XadrezPecas)tabuleiro.removerPeca(destino);
						Posicao peaoPosicao;
						if (p.getCor() == Cores.BRANCO) {
							peaoPosicao = new Posicao(3, destino.getColuna());
						}
						else {
							peaoPosicao = new Posicao(4, destino.getColuna());
						}
						tabuleiro.lugarPeca(peao, peaoPosicao);
					}
				}
	         }
	
	private void validadeOrigemPosicao(Posicao posicao) {
		if (!tabuleiro.pecaExistente(posicao)) {
			throw new XadrezExcecao("N?o existe pe?a nessa posi??o de origem");
		}
		
		if(atualJogador != ((XadrezPecas)tabuleiro.peca(posicao)).getCor()) { //verificar se a pessoa escolhida ? v?lida 
		throw new XadrezExcecao("A pe?a escolhida n?o ? sua!");
		}
		
		if (!tabuleiro.peca(posicao).movimentoPossivelDaPeca()) {
			throw new XadrezExcecao ("N?o existe movimentos para a pe?a escolhida");
		}
	}
	
	private void validadeDestinoPosicao(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new XadrezExcecao("A pe?a escolhida n?o pode se mover para essa posi??o");
		}
	}
	
	private void proximoTurno() {
		turno++;
		atualJogador = (atualJogador == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
		
	}
	
	private Cores oponente(Cores cor) {
		return (cor == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
	}
	
	private XadrezPecas rei (Cores cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((XadrezPecas)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (XadrezPecas)p;
			}
		}
		throw new IllegalStateException("N?o existe" + cor + "Rei no tabuleiro"); // isso n?o pode acontecer
	}
	
	private boolean testarCheck(Cores cor) { //testa se o rei est? em xeque
		Posicao reiPosicao = rei(cor).getXadrezPosicao().toPosicao();
		List<Peca> oponentePeca = pecasNoTabuleiro.stream().filter(x -> ((XadrezPecas)x).getCor() == oponente(cor)).collect(Collectors.toList()); //filtro para encontrar a cor da pe?a do oponente (pe?a do oponente)
	    for (Peca p : oponentePeca) {
	    	boolean[][] part = p.possivelMovimento();
	    	if (part[reiPosicao.getFileira()][reiPosicao.getColuna()]) {
	    		return true;
	    	}
	    }return false;
	}
	
	private boolean testarCheckMate (Cores cor) {
		if (!testarCheck(cor)) {
			return false;
		}
		List<Peca> list =pecasNoTabuleiro.stream().filter(x -> ((XadrezPecas)x).getCor() == cor).collect(Collectors.toList()); //filtro para encontrar a cor da pe?a do oponente (pe?a do oponente)
	    for (Peca p : list) {
	    	boolean [][] part = p.possivelMovimento();
	    	for (int i=0; i<tabuleiro.getFileiras(); i++) {
	    		for (int j=0; j<tabuleiro.getColunas(); j++) {
	    			if(part[i][j]) {
	    				Posicao origem = ((XadrezPecas)p).getXadrezPosicao().toPosicao();
	    			    Posicao destino = new Posicao(i,j);
	    			    Peca capturadaPeca = fazerMover(origem , destino); //Movimento da pe?a da origem para o destino
	    			    boolean testarCheck = testarCheck(cor);
	    			    desfazerMovimento(origem, destino, capturadaPeca);
	    			    if(!testarCheck) {
	    			    	return false;
	    			    }
	    			}
	    		}
	    	}
	    }
	    return true;
	}
	
	private void  LugarNovaPeca(char coluna, int fileira, XadrezPecas peca) {//Para instanciar as pe?as do xadrez informando o sistema do xadrez ao inv?s de matriz
		tabuleiro.lugarPeca(peca, new XadrezPosicao(coluna, fileira).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void iniciaPartida () {
		    LugarNovaPeca('a', 1, new Torre(tabuleiro, Cores.BRANCO));
		    LugarNovaPeca('b', 1, new Cavalo(tabuleiro, Cores.BRANCO));
		    LugarNovaPeca('c', 1, new Bispo(tabuleiro, Cores.BRANCO));
		    LugarNovaPeca('d', 1, new Queen(tabuleiro, Cores.BRANCO));
		    LugarNovaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO, this));
		    LugarNovaPeca('f', 1, new Bispo(tabuleiro, Cores.BRANCO));
		    LugarNovaPeca('g', 1, new Cavalo(tabuleiro, Cores.BRANCO));
		    LugarNovaPeca('h', 1, new Torre(tabuleiro, Cores.BRANCO));
		    LugarNovaPeca('a', 2, new Peao(tabuleiro, Cores.BRANCO, this));
		    LugarNovaPeca('b', 2, new Peao(tabuleiro, Cores.BRANCO, this));
		    LugarNovaPeca('c', 2, new Peao(tabuleiro, Cores.BRANCO, this));
		    LugarNovaPeca('d', 2, new Peao(tabuleiro, Cores.BRANCO, this));
		    LugarNovaPeca('e', 2, new Peao(tabuleiro, Cores.BRANCO, this));
		    LugarNovaPeca('f', 2, new Peao(tabuleiro, Cores.BRANCO, this));
		    LugarNovaPeca('g', 2, new Peao(tabuleiro, Cores.BRANCO, this));
		    LugarNovaPeca('h', 2, new Peao(tabuleiro, Cores.BRANCO, this));

		    LugarNovaPeca('a', 8, new Torre(tabuleiro, Cores.PRETO));
		    LugarNovaPeca('b', 8, new Cavalo(tabuleiro, Cores.PRETO));
		    LugarNovaPeca('c', 8, new Bispo(tabuleiro, Cores.PRETO));
		    LugarNovaPeca('d', 8, new Queen(tabuleiro, Cores.PRETO));
		    LugarNovaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO, this));
		    LugarNovaPeca('f', 8, new Bispo(tabuleiro, Cores.PRETO));
		    LugarNovaPeca('g', 8, new Cavalo(tabuleiro, Cores.PRETO));
		    LugarNovaPeca('h', 8, new Torre(tabuleiro, Cores.PRETO));
		    LugarNovaPeca('a', 7, new Peao(tabuleiro, Cores.PRETO, this));
		    LugarNovaPeca('b', 7, new Peao(tabuleiro, Cores.PRETO, this));
		    LugarNovaPeca('c', 7, new Peao(tabuleiro, Cores.PRETO, this));
		    LugarNovaPeca('d', 7, new Peao(tabuleiro, Cores.PRETO, this));
		    LugarNovaPeca('e', 7, new Peao(tabuleiro, Cores.PRETO, this));
		    LugarNovaPeca('f', 7, new Peao(tabuleiro, Cores.PRETO, this));
		    LugarNovaPeca('g', 7, new Peao(tabuleiro, Cores.PRETO, this));
		    LugarNovaPeca('h', 7, new Peao(tabuleiro, Cores.PRETO, this));
	}
	
}
