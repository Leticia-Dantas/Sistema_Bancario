package sistemaBancario_Let;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Funcionario extends Thread{
	private Conta contaSalario;
    private Conta contaInvestimento;
    private static final double SALARIO = 1400;
    private static final double PERCENTUAL_SALARIO = 0.8; // 80% do salário é mantido na conta do funcionário
    private static final double PERCENTUAL_INVESTIMENTO = 0.2; // 20% do salário é investido
    private Lock lock = new ReentrantLock(); // Lock para garantir acesso seguro às contas
    static double saldoFinalFuncionario;
    
    public Funcionario(Conta contaSalario, Conta contaInvestimento) {
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
    }
   
    public void run() {
        try {
            lock.lock(); // Bloqueia o acesso às contas
            // Calcular o valor do investimento e o valor restante do salário
            double investimento = SALARIO * PERCENTUAL_INVESTIMENTO;
            double saldoSalario = SALARIO * PERCENTUAL_SALARIO;

           
            // Depositar o saldo restante (80% do salário) na conta do funcionário
            contaSalario.depositar(saldoSalario);
            
            // Sacar o valor do investimento da conta do salário
            contaSalario.sacar(investimento);

            // Depositar o valor do investimento na conta de investimento
            contaInvestimento.depositar(investimento);
            
            // Atualizar o saldo do funcionário na conta
            saldoSalario = contaSalario.getSaldo();
            
        } finally {
            lock.unlock(); // Libera o acesso às contas
        }
    }
    public double getsaldoFinalFuncionario() {
		return saldoFinalFuncionario = saldoFinalFuncionario + contaSalario.getSaldo();
	}
    
}
