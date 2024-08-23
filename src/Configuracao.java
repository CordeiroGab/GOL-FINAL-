public class Configuracao {
    private int largura;
    private int altura;
    private int geracoes;
    private int velocidade;
    private String populacao;
    private int vizinhanca;

    private boolean larguraValida;
    private boolean alturaValida;
    private boolean geracoesValida;
    private boolean velocidadeValida;
    private boolean populacaoValida;
    private boolean vizinhancaValida;
    private static final int VIZINHANCA_DEFAULT = 3;

    public Configuracao(String[] args) {//A classe Configuracao processa os parâmetros de linha de comando para configurar o jogo
        largura = -1;
        altura = -1;
        geracoes = -1;
        velocidade = -1;
        vizinhanca = VIZINHANCA_DEFAULT;
        populacao = null;
        larguraValida = false;
        alturaValida = false;
        geracoesValida = false;
        velocidadeValida = false;
        populacaoValida = false;
        vizinhancaValida = false;
        parseArgs(args);
    }

    private void parseArgs(String[] args) {// Lê os parâmetros fornecidos e os valida
        for (String arg : args) {
            if (arg.startsWith("w=")) {
                largura = parseInt(arg.substring(2));
                larguraValida = largura > 0;
            } else if (arg.startsWith("h=")) {
                altura = parseInt(arg.substring(2));
                alturaValida = altura > 0;
            } else if (arg.startsWith("g=")) {
                geracoes = parseInt(arg.substring(2));
                geracoesValida = geracoes >= 0;
            } else if (arg.startsWith("s=")) {
                velocidade = parseInt(arg.substring(2));
                velocidadeValida = velocidade > 0 && velocidade <= 1000; // Validação para a velocidade
            } else if (arg.startsWith("p=")) {
                populacao = arg.substring(2);
                populacaoValida = !populacao.isEmpty();
            } else if (arg.startsWith("n=")) {
                vizinhanca = parseInt(arg.substring(2));
                vizinhancaValida = vizinhanca >= 1 && vizinhanca <= 5; // Supondo que os tipos válidos são de 1 a 5
            }
        }

        // Se vizinhanca não foi definido, usa o valor padrão
        if (!vizinhancaValida) {
            vizinhanca = VIZINHANCA_DEFAULT;
            vizinhancaValida = true; // Marcar como válido pois estamos usando o valor padrão
        }
    }

    private int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getGeracoes() {
        return geracoes > 0 ? geracoes : Integer.MAX_VALUE;
    }

    public int getVelocidade() {
        return velocidade > 0 ? velocidade : 300;
    }

    public String getPopulacao() {
        return populacao;
    }

    public int getVizinhanca() {
        return vizinhanca;
    }

    public boolean parametrosValidos() {
        return larguraValida && alturaValida && geracoesValida && velocidadeValida && populacaoValida && vizinhancaValida;
    }

    public void exibirConfiguracoes() { // Imprime os parâmetros
        System.out.println("Parâmetros:");
        System.out.println("Largura = [" + (larguraValida ? largura : "Invalido") + "]");
        System.out.println("Altura = [" + (alturaValida ? altura : "Invalido") + "]");
        System.out.println("Gerações = [" + (geracoesValida ? geracoes : "Não presente") + "]");
        System.out.println("Velocidade = [" + (velocidadeValida ? velocidade : "Não presente") + "]");
        System.out.println("População = [" + (populacaoValida ? populacao : "Não presente") + "]");
        System.out.println("Tipo de vizinhança = [" + vizinhanca + "]"); // Sempre exibe o valor, válido ou padrão
    }
}





