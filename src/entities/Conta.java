package entities;

import interfaces.IConta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entities.Extrato.TipoMovimentacao;

public abstract class Conta implements IConta{

    private  static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    protected  int agencia;
    protected  int numero;
    private  BigDecimal saldo = BigDecimal.ZERO;
    private List<Extrato> movimentacoes = new ArrayList<>();

    protected Cliente cliente;

    protected  Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;

    } 

    protected  void imprimirInfosComuns(){
        System.out.println(String.format("Titular: %s", cliente.getNome()));
        System.out.println(String.format("Agência: %d", agencia));
        System.out.println(String.format("Número: %d", numero));
        System.out.println(String.format("Saldo: %.2f", saldo));
    }

    protected  void registrar(Extrato.TipoMovimentacao tipo, double valor){
        Extrato extrato = new Extrato(valor, tipo, LocalDateTime.now());
        movimentacoes.add(extrato);    
    }

    protected boolean receber(double valor) {  
       if (valor <= 0){
            System.out.println("Impossível realizar a transação com valor " + valor);
            return false;
        } else {
            this.saldo = this.saldo.add(BigDecimal.valueOf(valor));
            return true;
        }
    }

    private boolean retirar(double valor) {  
        if (valor <= 0){
            System.out.println("Impossível realizar a transação com valor " + valor);
            return false;
        } else if (BigDecimal.valueOf(valor).compareTo(this.saldo) > 0) {
            System.out.println("Saldo insuficiente para realizar a operação" );
            return false;
        } else {
            this.saldo = this.saldo.subtract(BigDecimal.valueOf(valor)); 
            return true;
        }
    }

    @Override
    public void depositar(double valor) { 
        if (this.receber(valor)){
            this.registrar(TipoMovimentacao.DEPOSITO, valor);
        } 
    }

    @Override
    public void sacar(double valor) {  
        if (this.retirar(valor)) {
            this.registrar(TipoMovimentacao.SAQUE, valor);
        }
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {   
        if (contaDestino == null) {
            System.out.println("Conta destino inválida");
            return;
        }
        if (contaDestino == this) {
            System.out.println("Impossível transferir para a própria conta");
            return;
        }
        if (this.retirar(valor)){
            this.registrar(TipoMovimentacao.TRANSFERENCIA_ENVIADA , valor);
            contaDestino.receberTransferencia(valor);
        }
    }

    @Override 
    public void receberTransferencia(double valor){
        if (receber(valor)) {
            this.registrar(TipoMovimentacao.TRANSFERENCIA_RECEBIDA, valor);
        }
    }

    @Override 
    public void exibirExtrato(TipoMovimentacao tipo){
        for (Extrato movimentacao : movimentacoes) {
            if (movimentacao.getTipo() == tipo) {
                imprimirMovimentacao(movimentacao);
            }
        }
    }

    protected void imprimirMovimentacoes(){
        System.out.println("Movimentações:");
        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        }
        for (Extrato movimentacao : movimentacoes) {
            imprimirMovimentacao(movimentacao);
        }
    }

    private void imprimirMovimentacao(Extrato movimentacao) {
        System.out.println(String.format("%s - %s - R$ %.2f", 
        movimentacao.getDataHoraMovimentacao().format(FORMATO_DATA),
        movimentacao.getTipo(),
        movimentacao.getValor()));
    }

}
