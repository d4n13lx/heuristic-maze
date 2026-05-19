package br.com.labirinto.model;

public class Celula implements Comparable<Celula> {
    private final Posicao posicao;
    private final Celula pai;
    private final int custoG;
    private final int custoH;

    public Celula(Posicao posicao, Celula pai, int custoG, int custoH) {
        this.posicao = posicao;
        this.pai = pai;
        this.custoG = custoG;
        this.custoH = custoH;
    }

    public int getCustoF() { return custoG + custoH; }
    public Posicao getPosicao() { return posicao; }
    public Celula getPai() { return pai; }
    public int getCustoG() { return custoG; }

    @Override
    public int compareTo(Celula outra) {
        return Integer.compare(this.getCustoF(), outra.getCustoF());
    }
}