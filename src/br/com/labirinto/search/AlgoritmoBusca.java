package br.com.labirinto.search;

import br.com.labirinto.model.Celula;
import br.com.labirinto.model.Labirinto;
import br.com.labirinto.model.Posicao;
import java.util.*;

public class AlgoritmoBusca {
    private static final int[][] MOVIMENTOS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<Posicao> buscarCaminho(Labirinto labirinto) {
        PriorityQueue<Celula> listaAberta = new PriorityQueue<>();
        Set<Posicao> listaFechada = new HashSet<>();

        Posicao inicio = labirinto.getPosicaoRato();
        Posicao destino = labirinto.getPosicaoQueijo();

        listaAberta.add(new Celula(inicio, null, 0, calcularManhattan(inicio, destino)));

        while (!listaAberta.isEmpty()) {
            Celula atual = listaAberta.poll();
            Posicao posAtual = atual.getPosicao();

            if (posAtual.equals(destino)) {
                return reconstruirCaminho(atual);
            }

            listaFechada.add(posAtual);

            for (int[] mov : MOVIMENTOS) {
                int nX = posAtual.getX() + mov[0];
                int nY = posAtual.getY() + mov[1];
                Posicao vizinhoPos = new Posicao(nX, nY);

                if (labirinto.isMovimentoValido(nX, nY) && !listaFechada.contains(vizinhoPos)) {
                    int novoG = atual.getCustoG() + 1;
                    int h = calcularManhattan(vizinhoPos, destino);
                    Celula novoVizinho = new Celula(vizinhoPos, atual, novoG, h);

                    boolean adicionar = true;
                    for (Celula c : listaAberta) {
                        if (c.getPosicao().equals(vizinhoPos) && c.getCustoG() <= novoG) {
                            adicionar = false; // Caminho melhor ou igual para cá
                            break;
                        }
                    }

                    if (adicionar) {
                        listaAberta.add(novoVizinho);
                    }
                }
            }
        }
        return null;
    }

    private int calcularManhattan(Posicao a, Posicao b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private List<Posicao> reconstruirCaminho(Celula celula) {
        List<Posicao> caminho = new ArrayList<>();
        Celula atual = celula;
        while (atual != null) {
            caminho.add(atual.getPosicao());
            atual = atual.getPai();
        }
        Collections.reverse(caminho);
        return caminho;
    }
}