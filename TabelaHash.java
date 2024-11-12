public abstract class TabelaHash {
    protected int tamanho;
    protected String[] tabela;
    protected int colisoes;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new String[tamanho];
        this.colisoes = 0;
    }

    protected abstract int funcaoHash(String chave);

    public void inserir(String chave) {
        int indice = funcaoHash(chave);
        int tentativas = 0;
        while (tabela[indice] != null && tentativas < tamanho) {
            colisoes++;
            indice = (indice + 1) % tamanho;  // Sondagem linear para tratar colisões
            tentativas++;
        }
        if (tentativas < tamanho) {
            tabela[indice] = chave;
        } else {
            System.out.println("Erro: tabela hash cheia, não é possível inserir a chave: " + chave);
        }
    }

    public int getColisoes() {
        return colisoes;
    }

    public int[] getDistribuicao() {
        int[] distribuicao = new int[tamanho];
        for (String chave : tabela) {
            if (chave != null) {
                distribuicao[funcaoHash(chave)]++;
            }
        }
        return distribuicao;
    }
}

// Implementação da primeira função hash usando hashCode()
class TabelaHash1 extends TabelaHash {
    public TabelaHash1(int tamanho) {
        super(tamanho);
    }

    @Override
    protected int funcaoHash(String chave) {
        return Math.abs(chave.hashCode() % tamanho);
    }
}

// Implementação da segunda função hash personalizada
class TabelaHash2 extends TabelaHash {
    public TabelaHash2(int tamanho) {
        super(tamanho);
    }

    @Override
    protected int funcaoHash(String chave) {
        int hash = 7;
        for (int i = 0; i < chave.length(); i++) {
            hash = (hash * 31 + chave.charAt(i)) % tamanho;
        }
        return hash;
    }
}
