public class Main {
    public static void main(String[] args) { //Cria uma instância da classe Configuracao, que processa os parâmetros de linha de comando (args).
        Configuracao config = new Configuracao(args);

        if (config.parametrosValidos()) { //Verifica se os parâmetros fornecidos são válidos.
            config.exibirConfiguracoes();
            //Cria uma instância do jogo, passando os parâmetros validados.
            Jogo jogo = new Jogo(
                    config.getAltura(),
                    config.getLargura(),
                    config.getGeracoes(),
                    config.getVelocidade(),
                    config.getPopulacao(),
                    config.getVizinhanca()
            );
            jogo.iniciar();
        } else {
            config.exibirConfiguracoes();
            System.out.println("Parâmetros inválidos. Encerrando.");
        }
    }
}


