package interfaces;

import entities.Extrato.TipoMovimentacao;

public interface IConta {

    void sacar(double valor);

    void depositar(double valor);

    void transferir(double valor, IConta contaDestino);

    void imprimirExtrato();

    void receberTransferencia(double valor);

    void exibirExtrato(TipoMovimentacao tipo);
}
