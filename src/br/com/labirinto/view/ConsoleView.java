package br.com.labirinto.view;

import br.com.labirinto.model.Labirinto;
import br.com.labirinto.model.Posicao;
import java.util.List;

public class ConsoleView {

    public void exibirLabirintoPuro(Labirinto labirinto) {
        char[][] mapa = labirinto.getMapa();
        for (int i = 0; i < labirinto.getLinhas(); i++) {
            for (int j = 0; j < labirinto.getColunas(); j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void animarCaminho(Labirinto labirinto, List<Posicao> caminho) {
        char[][] mapa = labirinto.getMapa();
        int linhas = labirinto.getLinhas();
        int colunas = labirinto.getColunas();

        // Clona a matriz original para realizar a animação sem estragar o mapa base
        char[][] tela = new char[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            System.arraycopy(mapa[i], 0, tela[i], 0, colunas);
        }

        System.out.println("\nIniciando Animação do Rato...");

        for (int t = 0; t < caminho.size(); t++) {
            Posicao posAtual = caminho.get(t);

            // Simula uma limpeza de tela pulando linhas no console
            for (int i = 0; i < 10; i++) System.out.println();

            System.out.println("Passo: " + t + " / " + (caminho.size() - 1));

            // Se já se moveu, transforma a posição anterior no rastro '.'
            if (t > 0) {
                Posicao posAnterior = caminho.get(t - 1);
                tela[posAnterior.getX()][posAnterior.getY()] = '.';
            }

            // Atualiza o local do Rato
            if (!posAtual.equals(labirinto.getPosicaoQueijo())) {
                tela[posAtual.getX()][posAtual.getY()] = 'R';
            } else {
                tela[posAtual.getX()][posAtual.getY()] = 'Q'; // Mantém o queijo visível no fim
            }

            // Imprime a matriz contendo estritamente #, R, Q e o rastro .
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    System.out.print(tela[i][j] + " ");
                }
                System.out.println();
            }

            try {
                Thread.sleep(500); // Delay de meio segundo por passo da animação
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}