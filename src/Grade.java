import java.util.Random;

public class Grade {
    private final int[][] grade;
    private final int linha;
    private final int coluna;
    private final boolean populacaoAleatoria;

    public Grade(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.populacaoAleatoria = false;
        this.grade = new int[linha][coluna];
    }

    public void inicializaGrade(String populacaoInicial) { //Inicializa a grade de acordo com a população fornecida ou gera uma população aleatória se a opção "rnd" for passada.
        if (populacaoInicial != null && !populacaoInicial.isEmpty() && !populacaoInicial.equals("rnd")) {
            String[] linhas = populacaoInicial.split("#");
            for (int i = 0; i < linhas.length && i < linha; i++) {
                for (int j = 0; j < linhas[i].length() && j < coluna; j++) {
                    grade[i][j] = linhas[i].charAt(j) == '1' ? 1 : 0;
                }
            }
        } else {
            gradeAleatoria();
        }
    }

    private void gradeAleatoria() {
        Random random = new Random();
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                grade[i][j] = random.nextInt(2);
            }
        }
    }

    public void atualizar(int vizinhanca) {//Calcula a próxima geração de células, aplicando as regras do Jogo da Vida com base no tipo de vizinhança.
        int[][] novaGrade = new int[linha][coluna];

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                int vizinhosVivos = contagemDeVizinhos(i, j, vizinhanca);

                if (grade[i][j] == 1) {
                    if (vizinhosVivos < 2 || vizinhosVivos > 3) {
                        novaGrade[i][j] = 0;
                    } else {
                        novaGrade[i][j] = 1;
                    }
                } else {
                    if (vizinhosVivos == 3) {
                        novaGrade[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < linha; i++) {
            System.arraycopy(novaGrade[i], 0, grade[i], 0, coluna);
        }
    }

    private int contagemDeVizinhos(int x, int y, int vizinhanca) {//Conta quantos vizinhos vivos uma célula tem, utilizando diferentes padrões de vizinhança
        int contador = 0;

        int[][] vizinhos = switch (vizinhanca) {
            case 1 -> // Vizinhança Von Neumann
                    new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
            case 2 -> // Vizinhança Diagonal
                    new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
            case 3 -> // Vizinhança Moore
                    new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
            case 4 -> // Vizinhança Customizada 1
                    new int[][]{{-2, 0}, {0, -2}, {0, 2}, {2, 0}};
            case 5 -> // Vizinhança Customizada 2
                    new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {2, 2}};
            default -> new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        };

        for (int[] v : vizinhos) {
            int novaLinha = x + v[0];
            int novaColuna = y + v[1];

            if (novaLinha >= 0 && novaLinha < linha && novaColuna >= 0 && novaColuna < coluna) {
                contador += grade[novaLinha][novaColuna];
            }
        }

        return contador;
    }

    public void exibir() {//Mostra a grade no console, usando cores para diferenciar células vivas e mortas.
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String RED = "\u001B[31m";

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if (grade[i][j] == 1) {
                    System.out.print(GREEN + " . " + RESET);
                } else {
                    System.out.print(RED + " 0 " + RESET);
                }
            }
            System.out.println();
        }
    }

    public boolean todasCelulasMortas() {//Verifica se todas as células na grade estão mortas.
        for (int[] linha : grade) {
            for (int celula : linha) {
                if (celula == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public boolean isPopulacaoAleatoria() {
        return populacaoAleatoria;
    }

}


