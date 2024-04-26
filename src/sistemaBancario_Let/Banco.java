package sistemaBancario_Let;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
	private Conta[] contasFuncionarios;
    private Conta[] contasInvestimentos;
    private Conta[] contasClientes;
    private Conta contaLoja1;
    private Conta contaLoja2;
    private Lock lock = new ReentrantLock();

    public Banco() {
        contasFuncionarios = new Conta[4];
        contasInvestimentos = new Conta[4];
        contasClientes = new Conta[5];
     // Inicialização das contas dos funcionários
        for (int i = 0; i < 4; i++) {
            contasFuncionarios[i] = new Conta(0);
            contasInvestimentos[i] = new Conta(0);
        }
     // Inicialização das contas dos clientes com saldo inicial de R$1000
        for (int i = 0; i < 5; i++) {
            contasClientes[i] = new Conta(1000);
        }
        contaLoja1 = new Conta(1400);
        contaLoja2 = new Conta(1400);
    }
    
 // Método para transferir dinheiro entre contas
    public void transferir(Conta origem, Conta destino, double valor) {
        lock.lock(); //O lock fazendo a trava
        try {
            origem.sacar(valor);
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " realizada.");
            System.out.println("Saldo final da conta origem: R$" + origem.getSaldo());
            System.out.println("Saldo final da conta destino: R$" + destino.getSaldo());
        } finally {
            lock.unlock();
        }
    }
 // Método para pagar funcionários de uma loja
    public void pagarFuncionarios(Conta contaLoja, String nomeLoja) {
        lock.lock(); //O lock fazendo a trava
        try {
            System.out.println("Pagamento de funcionários da " + nomeLoja + " foi realizado.");
            double totalPagamento = 1400; // pagamento dos funcionários
            for (Conta contaCliente : contasClientes) {
                totalPagamento += contaCliente.getSaldo(); // adiciona o saldo de cada cliente
                contaCliente.sacar(contaCliente.getSaldo()); // esvazia a conta do cliente
            }
            contaLoja.sacar(totalPagamento); // paga funcionários e clientes
        } finally {
            lock.unlock();
        }
    }
    
    // getters das contas
    public Conta[] getContasFuncionarios() {
        return contasFuncionarios;
    }

    public Conta[] getContasInvestimentos() {
        return contasInvestimentos;
    }

    public Conta[] getContasClientes() {
        return contasClientes;
    }

    public Conta getContaLoja1() {
        return contaLoja1;
    }

    public Conta getContaLoja2() {
        return contaLoja2;
    }
}


