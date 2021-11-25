package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	private int turno;
	private Cores atualJogador;
	
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
		proximoTurno();
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
	
	private void validadeOrigemPosicao(Posicao posicao) {
		if (!tabuleiro.pecaExistente(posicao)) {
			throw new XadrezExcecao("N�o existe pe�a nessa posi��o de origem");
		}
		
		if(atualJogador != ((XadrezPecas)tabuleiro.peca(posicao)).getCor()) { //verificar se a pessoa escolhida � v�lida 
		throw new XadrezExcecao("A pe�a escolhida n�o � sua!");
		}
		
		if (!tabuleiro.peca(posicao).movimentoPossivelDaPeca()) {
			throw new XadrezExcecao ("N�o existe movimentos para a pe�a escolhida");
		}
	}
	
	private void validadeDestinoPosicao(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new XadrezExcecao("A pe�a escolhida n�o pode se mover para essa posi��o");
		}
	}
	
	private void proximoTurno() {
		turno++;
		atualJogador = (atualJogador == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
		
	}
	
	private void LugarNovaPeca(char coluna, int fileira, XadrezPecas peca) {//Para instanciar as pe�as do xadrez informando o sistema do xadrez ao inv�s de matriz
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
