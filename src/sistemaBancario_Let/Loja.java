package sistemaBancario_Let;

public class Loja {
	private Conta conta;
	// Construtor que recebe uma conta como parâmetro e associa à loja.
    public Loja(Conta conta) {
        this.conta = conta;
    }
    // Método para obter a conta da loja.
    public Conta getConta() {
        return conta;
    }
}

