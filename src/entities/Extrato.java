package entities;

import java.time.LocalDateTime;

public class Extrato {

    protected double valor;
    protected TipoMovimentacao tipo;
    protected LocalDateTime dataHoraMovimentacao;

    public enum TipoMovimentacao {
        DEPOSITO, SAQUE, TRANSFERENCIA_RECEBIDA, TRANSFERENCIA_ENVIADA;
    }

    public Extrato(double valor, TipoMovimentacao tipo, LocalDateTime dataHoraMovimentacao) {
        this.valor = valor;
        this.tipo = tipo;
        this.dataHoraMovimentacao = dataHoraMovimentacao;
    }

    public double getValor() {
        return valor;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public LocalDateTime getDataHoraMovimentacao() {
        return dataHoraMovimentacao;
    }
}
