package sistemaBancario_Let;



public class SistemaBancario {
	public static void main(String[] args) {
       
		 Banco banco = new Banco();
	        Loja loja1 = new Loja(banco.getContaLoja1());
	        Loja loja2 = new Loja(banco.getContaLoja2());

	        Funcionario[] funcionariosLoja1 = new Funcionario[2];
	        Funcionario[] funcionariosLoja2 = new Funcionario[2];

	        for (int i = 0; i < 2; i++) {
	            funcionariosLoja1[i] = new Funcionario(loja1.getConta(), banco.getContasInvestimentos()[i]);
	            funcionariosLoja2[i] = new Funcionario(loja2.getConta(), banco.getContasInvestimentos()[i + 2]);
	        }

	        Cliente[] clientes = new Cliente[5];
	        for (int i = 0; i < 5; i++) {
	            clientes[i] = new Cliente(banco.getContasClientes()[i]);
	        }

	        // Inicia clientes
	        for (Cliente cliente : clientes) {
	            cliente.start();
	        }

	        try {
	            // Aguarda a finalização das compras dos clientes
	            for (Cliente cliente : clientes) {
	                cliente.join();
	            }

	            // Paga funcionários e clientes das lojas
	            banco.pagarFuncionarios(loja1.getConta(), "loja1");
	            banco.pagarFuncionarios(loja2.getConta(), "loja2");

	            // Inicia os funcionários e aguarda o término
	            for (Funcionario funcionario : funcionariosLoja1) {
	                funcionario.start();
	            }
	            for (Funcionario funcionario : funcionariosLoja2) {
	                funcionario.start();
	            }
	            for (Funcionario funcionario : funcionariosLoja1) {
	                funcionario.join();
	            }
	            for (Funcionario funcionario : funcionariosLoja2) {
	                funcionario.join();
	            }

	            // Atualiza os saldos das contas das lojas
	            loja1.getConta().sacar(loja1.getConta().getSaldo());
	            loja2.getConta().sacar(loja2.getConta().getSaldo());

	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Após pagamento dos funcionários e clientes, mostra o saldo final das contas de investimento
	        System.out.println("Saldo final das contas de investimento:");
	        for (Conta conta : banco.getContasInvestimentos()) {
	            System.out.println("Saldo: R$" + conta.getSaldo());
	        }

	        // Mostra o saldo final das contas dos funcionários
	        System.out.println("Saldo final das contas dos funcionários:");
	        for (Conta conta : banco.getContasFuncionarios()) {
	            System.out.println("Saldo: R$" + conta.getSaldoFuncionario());
	        }

	        // Mostra o saldo final das contas dos clientes
	        System.out.println("Saldo final das contas dos clientes:");
	        for (Conta conta : banco.getContasClientes()) {
	            System.out.println("Saldo: R$" + conta.getSaldo());
	        }

	        // Mostra o saldo final das contas das lojas
	        System.out.println("Saldo final das contas das lojas:");
	        System.out.println("Loja 1: R$ " + loja1.getConta().getSaldo());
	        System.out.println("Loja 2: R$ " + loja2.getConta().getSaldo());
    }
}
