package entities;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nome;
    private List<Conta> contas = new ArrayList<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarConta(Conta conta) {
        if (conta == null) {
            System.out.println("Conta inválida");
            return;
        }
        contas.add(conta);
    }

    public void imprimirExtrato() {
        System.out.println("=== Banco " + nome + " ===");
        for (Conta conta : contas ) {
            conta.imprimirExtrato();
        }
    }

}