import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TesteHash {
    public static void main(String[] args) {
        int tamanhoTabela = 6000;

        System.out.println("Iniciando a leitura do arquivo...");
        List<String> nomes = lerArquivo("female_names.txt");
        if (nomes.isEmpty()) {
            System.out.println("Erro: O arquivo está vazio ou não foi lido corretamente.");
            return;
        } else {
            System.out.println("Arquivo lido com sucesso. Total de nomes: " + nomes.size());
        }

        TabelaHash tabela1 = new TabelaHash1(tamanhoTabela);
        TabelaHash tabela2 = new TabelaHash2(tamanhoTabela);

        long tempoInicio, tempoFim;

        System.out.println("Iniciando inserção na TabelaHash1...");
        tempoInicio = System.nanoTime();
        for (String nome : nomes) {
            tabela1.inserir(nome);
        }
        tempoFim = System.nanoTime();
        System.out.println("TabelaHash1 - Tempo de inserção: " + (tempoFim - tempoInicio) + " ns");
        System.out.println("TabelaHash1 - Número de colisões: " + tabela1.getColisoes());


        System.out.println("Iniciando inserção na TabelaHash2...");
        tempoInicio = System.nanoTime();
        for (String nome : nomes) {
            tabela2.inserir(nome);
        }
        tempoFim = System.nanoTime();
        System.out.println("TabelaHash2 - Tempo de inserção: " + (tempoFim - tempoInicio) + " ns");
        System.out.println("TabelaHash2 - Número de colisões: " + tabela2.getColisoes());

        System.out.println("Distribuição de chaves - TabelaHash1:");
        int[] distribuicao1 = tabela1.getDistribuicao();
        for (int i = 0; i < distribuicao1.length; i++) {
            if (distribuicao1[i] > 0) {
                System.out.println("Posição " + i + ": " + distribuicao1[i] + " chaves");
            }
        }

        System.out.println("Distribuição de chaves - TabelaHash2:");
        int[] distribuicao2 = tabela2.getDistribuicao();
        for (int i = 0; i < distribuicao2.length; i++) {
            if (distribuicao2[i] > 0) {
                System.out.println("Posição " + i + ": " + distribuicao2[i] + " chaves");
            }
        }
    }

    private static List<String> lerArquivo(String nomeArquivo) {
        List<String> nomes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                nomes.add(linha.trim());
            }
            System.out.println("Leitura do arquivo concluída.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return nomes;
    }
}
