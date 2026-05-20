package br.com.labirinto;

import br.com.labirinto.model.Labirinto;
import br.com.labirinto.model.Posicao;
import br.com.labirinto.search.AlgoritmoBusca;
import br.com.labirinto.view.ConsoleView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SISTEMA DE BUSCA A* - LABIRINTO ===");
        System.out.print("Informe a quantidade de linhas: ");
        int linhas = scanner.nextInt();
        System.out.print("Informe a quantidade de colunas: ");
        int colunas = scanner.nextInt();
        if (linhas < 3 || colunas < 3) {
            System.out.println("Aviso: Tamanho fornecido menor que 3x3. Forçando tamanho padrão de 5x5.");
            linhas = 5;
            colunas = 5;
        }

        AlgoritmoBusca busca = new AlgoritmoBusca();
        ConsoleView view = new ConsoleView();
        Labirinto labirinto = new Labirinto(linhas, colunas);
        List<Posicao> caminho = null;

        System.out.println("\nGerando labirinto e validando caminho...");

        int tentativasLimitadas = 0;
        while (tentativasLimitadas < 1000) { // Limite de segurança para evitar loop infinito
            labirinto.gerar(); // O model apenas gera o mapa
            caminho = busca.buscarCaminho(labirinto); // O search apenas busca

            if (caminho != null) {
                break; // Achamos um mapa com caminho possível
            }
            tentativasLimitadas++;
        }

        System.out.println("\nLabirinto Inicial:");
        view.exibirLabirintoPuro(labirinto);

        if (caminho != null) {
            int custoG = caminho.size() - 1; // Subtrai a célula inicial para contar os "passos"
            System.out.println("\nCaminho encontrado com sucesso!");
            System.out.println("Quantidade de passos (Custo G): " + custoG);

            // Roda a animação passo a passo exigida
            view.animarCaminho(labirinto, caminho);

            System.out.println("\nLabirinto Resolvido com Sucesso!");
        } else {
            System.out.println("Aviso: Nenhum caminho possível encontrado entre o Rato e o Queijo");
        }

        scanner.close();
    }
}