package application;
import entities.Banco;
import entities.Cliente;
import entities.Conta;
import entities.ContaCorrente;
import entities.ContaPoupanca;


public class App {
    public static void main(String[] args) {

        Cliente maria = new Cliente();
        maria.setNome("Maria de nazaré");

        Conta cc = new ContaCorrente(maria);
        Conta poupanca = new ContaPoupanca(maria);

        cc.depositar(100);
        cc.transferir(20, poupanca);

        Banco banco = new Banco("Digital");
        banco.adicionarConta(cc);
        banco.adicionarConta(poupanca);

        banco.imprimirExtrato();
    }
}
