package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	private int turno;
	private Cores atualJogador;
	private boolean check;
	private boolean checkMate;
	
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
			throw new XadrezExcecao("Você não pode se colocar em xeque");
		}
		
		check = (testarCheck(oponente(atualJogador))) ? true : false;
		
		if(testarCheckMate(oponente(atualJogador))) {
			checkMate = true;
		}
		else {
		proximoTurno();
		}
		return (XadrezPecas) capturadaPeca;
		
	}
	
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca capturadaPeca = tabuleiro.removerPeca(destino);
		tabuleiro.lugarPeca(p, destino);
		
		if(capturadaPeca != null) {
			pecasNoTabuleiro.remove(capturadaPeca);
			pecasCapturadas.add(capturadaPeca);
		}
		
		return capturadaPeca;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturadaPeca) {
		Peca p = tabuleiro.removerPeca(destino);
		tabuleiro.lugarPeca(p, origem);
		
		if (capturadaPeca != null) {
			tabuleiro.lugarPeca(capturadaPeca, destino);
			pecasCapturadas.remove(capturadaPeca);
			pecasCapturadas.add(capturadaPeca);
		}
	}
	
	private void validadeOrigemPosicao(Posicao posicao) {
		if (!tabuleiro.pecaExistente(posicao)) {
			throw new XadrezExcecao("Não existe peça nessa posição de origem");
		}
		
		if(atualJogador != ((XadrezPecas)tabuleiro.peca(posicao)).getCor()) { //verificar se a pessoa escolhida é válida 
		throw new XadrezExcecao("A peça escolhida não é sua!");
		}
		
		if (!tabuleiro.peca(posicao).movimentoPossivelDaPeca()) {
			throw new XadrezExcecao ("Não existe movimentos para a peça escolhida");
		}
	}
	
	private void validadeDestinoPosicao(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new XadrezExcecao("A peça escolhida não pode se mover para essa posição");
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
		throw new IllegalStateException("Não existe" + cor + "Rei no tabuleiro"); // isso não pode acontecer
	}
	
	private boolean testarCheck(Cores cor) { //testa se o rei está em xeque
		Posicao reiPosicao = rei(cor).getXadrezPosicao().toPosicao();
		List<Peca> oponentePeca = pecasNoTabuleiro.stream().filter(x -> ((XadrezPecas)x).getCor() == oponente(cor)).collect(Collectors.toList()); //filtro para encontrar a cor da peça do oponente (peça do oponente)
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
		List<Peca> list =pecasNoTabuleiro.stream().filter(x -> ((XadrezPecas)x).getCor() == cor).collect(Collectors.toList()); //filtro para encontrar a cor da peça do oponente (peça do oponente)
	    for (Peca p : list) {
	    	boolean [][] part = p.possivelMovimento();
	    	for (int i=0; i<tabuleiro.getFileiras(); i++) {
	    		for (int j=0; j<tabuleiro.getColunas(); j++) {
	    			if(part[i][j]) {
	    				Posicao origem = ((XadrezPecas)p).getXadrezPosicao().toPosicao();
	    			    Posicao destino = new Posicao(i,j);
	    			    Peca capturadaPeca = fazerMover(origem , destino); //Movimento da peça da origem para o destino
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
	
	private void LugarNovaPeca(char coluna, int fileira, XadrezPecas peca) {//Para instanciar as peças do xadrez informando o sistema do xadrez ao invés de matriz
		tabuleiro.lugarPeca(peca, new XadrezPosicao(coluna, fileira).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void iniciaPartida () {
		LugarNovaPeca('c', 2, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('d', 2, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('e', 2, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('e', 1, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('c', 1, new Torre(tabuleiro, Cores.BRANCO));
		LugarNovaPeca('d', 1, new Rei(tabuleiro, Cores.BRANCO));

		LugarNovaPeca('c', 7, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('c', 8, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('d', 7, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('e', 7, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('e', 8, new Torre(tabuleiro, Cores.PRETO));
		LugarNovaPeca('d', 8, new Rei(tabuleiro, Cores.PRETO));
	}
	
}
