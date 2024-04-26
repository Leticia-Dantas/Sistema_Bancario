package sistemaBancario_Let;

public class Loja {
	private Conta conta;

    public Loja(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }
}

