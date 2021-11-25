package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		iniciaPartida();
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
		return (XadrezPecas) capturadaPeca;
	}
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca capturadaPeca = tabuleiro.removerPeca(destino);
		tabuleiro.lugarPeca(p, destino);
		return capturadaPeca;
	}
	
	private void validadeOrigemPosicao(Posicao posicao) {
		if (!tabuleiro.pecaExistente(posicao)) {
			throw new XadrezExcecao("Não existe peça nessa posição de origem");
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
	
	private void LugarNovaPeca(char coluna, int fileira, XadrezPecas peca) {//Para instanciar as peças do xadrez informando o sistema do xadrez ao invés de matriz
		tabuleiro.lugarPeca(peca, new XadrezPosicao(coluna, fileira).toPosicao());
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
