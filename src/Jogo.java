import java.util.Scanner;

public class Jogo {
    private final Grade grade;
    private final int geracoes;
    private final int velocidade;
    private final int vizinhanca;

    public Jogo(int linha, int coluna, int geracoes, int velocidade, String populacaoInicial, int vizinhanca) {
        this.grade = new Grade(linha, coluna);
        this.grade.inicializaGrade(populacaoInicial);
        this.geracoes = geracoes;
        this.velocidade = velocidade;
        this.vizinhanca = vizinhanca;
    }
    //Executa o loop principal que simula cada geração, atualizando e exibindo a grade até que as condições de término sejam alcançadas (todas as células mortas, número máximo de gerações, ou interrupção pelo usuário).
    public void iniciar() {
        int geracao = 0;
        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        while (continuar) {
            limparConsole(); // Limpa o console a cada geração

            // Exibir parâmetros antes de cada geração
            System.out.println("Parâmetros:");
            System.out.println("Largura = [" + grade.getColuna() + "]");
            System.out.println("Altura = [" + grade.getLinha() + "]");
            System.out.println("Gerações = [" + geracoes + "]");
            System.out.println("Velocidade = [" + velocidade + "]");
            System.out.println("População = [" + (grade.isPopulacaoAleatoria() ? "rnd" : "Rnd") + "]");
            System.out.println("Tipo de vizinhança = [" + vizinhanca + "]");

            System.out.println("Geração " + (geracao + 1) + ":");
            grade.exibir();
            grade.atualizar(vizinhanca);
            geracao++;

            if (grade.todasCelulasMortas()) {
                System.out.println("Todas as células estão mortas.");
                grade.exibir(); // Exibe o quadro final uma vez mais
                continuar = false; // Para o loop após exibir o quadro final
            }

            // Verifica se é o momento de pausar
            boolean devePausar = (geracoes == Integer.MAX_VALUE && geracao % 50 == 0) ||
                    (geracoes != Integer.MAX_VALUE && geracao == geracoes / 2);

            if (devePausar) {
                System.out.println("Deseja continuar o jogo? (Enter para continuar, 's' para sair)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    continuar = false;
                }
            }

            if (geracoes > 0 && geracao >= geracoes) {
                continuar = false; // Para se o número máximo de gerações for alcançado
            }

            try {
                Thread.sleep(velocidade);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fim das gerações");
    }
    //Utiliza o método limparConsole() para limpar o terminal entre as gerações.
    private void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}




