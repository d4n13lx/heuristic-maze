package br.com.labirinto.model;

import java.util.Random;

public class Labirinto {
    private final int linhas;
    private final int colunas;
    private final char[][] mapa;
    private final Posicao posicaoRato;
    private Posicao posicaoQueijo;

    public Labirinto(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.mapa = new char[linhas][colunas];
        this.posicaoRato = new Posicao(0, 0); // RF02: Rato sempre em [0,0]
    }

    public void gerar() {
        Random rand = new Random();

        // 1. Cria paredes externas, mas deixa a [0,0] como porta de entrada
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                // Se for borda E NÃO for a posição [0,0], vira parede
                if ((i == 0 || i == linhas - 1 || j == 0 || j == colunas - 1) && !(i == 0 && j == 0)) {
                    mapa[i][j] = '#';
                } else {
                    mapa[i][j] = ' ';
                }
            }
        }

        // 2. Coloca o Rato na entrada [0,0]
        mapa[0][0] = 'R';

        // Garante que o rato consiga entrar no labirinto abrindo os espaços adjacentes
        if (colunas > 1) mapa[0][1] = ' ';
        if (linhas > 1) mapa[1][0] = ' ';

        // 3. Coloca o Queijo em um lugar aleatório apenas no MIOLO do mapa
        int qX, qY;
        do {
            // Sorteia entre 1 e tamanho-2 para o queijo não cair em cima das paredes externas
            qX = rand.nextInt(linhas - 2) + 1;
            qY = rand.nextInt(colunas - 2) + 1;
        } while (qX == 0 && qY == 0); // Segurança extra

        posicaoQueijo = new Posicao(qX, qY);
        mapa[qX][qY] = 'Q';

        // 4. Espalha paredes internas (obstáculos) apenas no miolo do mapa
        for (int i = 1; i < linhas - 1; i++) {
            for (int j = 1; j < colunas - 1; j++) {
                if (i == qX && j == qY) continue; // Não apaga o queijo
                
                // Protege a entrada para não bloquear o rato logo no primeiro passo
                if ((i == 1 && j == 1) || (i == 0 && j == 1) || (i == 1 && j == 0)) continue; 

                // Porcentagem de chance de gerar um obstáculo interno
                if (rand.nextDouble() < 0.38) { 
                    mapa[i][j] = '#';
                }
            }
        }
    }

    public boolean isMovimentoValido(int x, int y) {
        return x >= 0 && x < linhas && y >= 0 && y < colunas && mapa[x][y] != '#';
    }

    public int getLinhas() { return linhas; }
    public int getColunas() { return colunas; }
    public char[][] getMapa() { return mapa; }
    public Posicao getPosicaoRato() { return posicaoRato; }
    public Posicao getPosicaoQueijo() { return posicaoQueijo; }
}