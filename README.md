# 🐭 Labirinto com Busca Heurística (A*)

O objetivo central do sistema é gerar um labirinto interativo e utilizar um algoritmo de busca com heurística para encontrar, de forma automática e ótima, o caminho do rato até o queijo.

---

## 🚀 Funcionalidades Implementadas

O sistema atende aos requisitos funcionais:

* O programa gera ferramentas que permitem criar labirintos na forma de matriz/grade.
* O rato (`R`) inicia obrigatoriamente na posição inicial `[0,0]` do labirinto.
* O queijo (`Q`) é posicionado de forma totalmente aleatória no mapa.
* As paredes (`#`) são geradas aleatoriamente, mas o algoritmo de geração garante que sempre exista um caminho possível entre o rato e o queijo.
* O rato move-se automaticamente respeitando apenas os movimentos ortogonais permitidos: cima, baixo, esquerda e direita.
* A solução inclui a visualização no console com a animação passo a passo do rato percorrendo o caminho encontrado.

---

## 🧠 Algoritmo e Heurística

Para resolver o problema central de encontrar o caminho no labirinto, o projeto utiliza o algoritmo **A* (A-Estrela)**. Este algoritmo foi escolhido por combinar o custo real da movimentação com uma estimativa heurística até o objetivo. 

A heurística implementada na classe `AlgoritmoBusca.java` é a **Distância de Manhattan**, que é a abordagem mais recomendada e comum para labirintos em grade ortogonal. Ela é calculada através da soma das diferenças absolutas entre as coordenadas atuais do rato e a posição destino (queijo).

---

## 📂 Estrutura do Projeto

O código-fonte foi desenvolvido em Java e dividido em pacotes lógicos para separar o modelo, a busca e a visualização:

* **`br.com.labirinto.model`**: Contém as classes de estrutura de dados (`Posicao`, `Celula`) e a matriz do jogo (`Labirinto`).
* **`br.com.labirinto.search`**: Contém a classe `AlgoritmoBusca`, que processa a lógica matemática do A* e a heurística de Manhattan.
* **`br.com.labirinto.view`**: Contém a classe `ConsoleView`, responsável por desenhar a grade e controlar a animação de tempo (delay) no terminal.
* **`br.com.labirinto`**: Contém a classe principal `Main`, que orquestra a execução.

---

## 📖 Manual de Execução

Este manual descreve como compilar e executar a ferramenta interativa.
Necessita do **JDK 17**.

**Passo a passo via Terminal/Console:**

1. Faça o clone deste repositório ou baixe os arquivos fonte.
   ```bash

   git clone https://github.com/d4n13lx/heuristic-maze
   ```
2. Abra o terminal e navegue até a pasta `src` do projeto:
   ```bash
   cd /projeto/src
   javac br/com/labirinto/**/*.java br/com/labirinto/*.java
   java br.com.labirinto.Main
   ```
