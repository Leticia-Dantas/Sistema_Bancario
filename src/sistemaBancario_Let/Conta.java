package sistemaBancario_Let;

public class Conta {
	 private double saldo;
	 private double saldoFuncionario;

	    public Conta(double saldo) {
	        this.saldo = saldo;
	    }

	    public synchronized void depositar(double valor) {
	        saldo += valor;
	    }

	    public synchronized void sacar(double valor) {
	        saldo -= valor;
	    }

	    public synchronized double getSaldo() {
	        return saldo;
	    }

	    public double getSaldoFuncionario() {
			return saldoFuncionario =+ 1120;
		}

		public void setSaldoFuncionario(double saldoFuncionario) {
			this.saldoFuncionario = saldoFuncionario;
		}

	
	    
}



