package sistemaBancario_Let;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Cliente extends Thread {
	private Conta conta;
	    private static final int GASTO_ALTO = 200;
	    private static final int GASTO_BAIXO = 100;
	   	private int gastoAtual = GASTO_ALTO; // Começa com gasto alto
	   	private Lock lock = new ReentrantLock(); // Lock para garantir acesso seguro à conta

	 // Construtor que recebe uma conta como parâmetro e associa à cliente.
	   	public Cliente(Conta conta) {
	        this.conta = conta;
	   	}
	 // Método para Compras do cliente.
	    public void run() {
	        while (true) {
	            try {
	                lock.lock(); // Bloqueia o acesso à conta :)Deu certo!
	                if (conta.getSaldo() >= gastoAtual) {
	                    conta.sacar(gastoAtual);
	                    System.out.println("Cliente gastou: R$" + gastoAtual);
	                    // Alterna entre gasto alto e baixo
	                    if (gastoAtual == GASTO_ALTO) {
	                        gastoAtual = GASTO_BAIXO;
	                    } else {
	                        gastoAtual = GASTO_ALTO;
	                    }
	                } else {
	                    break;
	                }
	            } finally {
	                lock.unlock(); // Libera o acesso à conta
	            }
	            // Simulação da compra
	            try {
	                Thread.sleep(100); // Tempo de espera entre compras
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}


