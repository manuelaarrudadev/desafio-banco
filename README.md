# Desafio Banco

Simulação de um banco digital em Java, feita para praticar programação orientada a objetos. O programa roda no console e não usa dependências externas.

## Funcionalidades

- Conta corrente e conta poupança, com agência e número gerados automaticamente
- Depósito, saque e transferência entre contas
- Extrato com dados da conta, saldo e histórico de movimentações (data, tipo e valor)
- Filtro do extrato por tipo de movimentação
- Banco que reúne as contas e imprime os extratos de todas

Operações inválidas são recusadas sem alterar o saldo: valor zero ou negativo, saldo insuficiente, conta de destino nula e transferência para a própria conta.

## Conceitos de POO aplicados

| Conceito | Onde aparece |
|---|---|
| Abstração | Classe abstrata `Conta` e interface `IConta` |
| Encapsulamento | `saldo` e `movimentacoes` são privados e só mudam pelas operações da conta |
| Herança | `ContaCorrente` e `ContaPoupanca` estendem `Conta` |
| Polimorfismo | `Banco` chama `imprimirExtrato()` e cada tipo de conta imprime o seu |

## Estrutura

```
src/
├── application/App.java       Ponto de entrada, com um exemplo de uso
├── entities/
│   ├── Banco.java             Reúne as contas
│   ├── Cliente.java           Titular da conta
│   ├── Conta.java             Regras comuns às contas
│   ├── ContaCorrente.java
│   ├── ContaPoupanca.java
│   └── Extrato.java           Registro de uma movimentação
└── interfaces/IConta.java     Operações de uma conta
```

## Como executar

É necessário ter o JDK instalado. O projeto foi testado com o Java 21. Na pasta raiz, execute:

```
javac -encoding UTF-8 -d bin -sourcepath src src/application/App.java
java -cp bin application.App
```

## Exemplo de saída

```
=== Banco Digital ===
--- Extrato da conta corrente ----
Titular: Maria de nazaré
Agência: 1
Número: 1
Saldo: 80,00
Movimentações:
08/11/2026 18:42:08 - DEPOSITO - R$ 100,00
08/11/2026 18:42:08 - TRANSFERENCIA_ENVIADA - R$ 20,00
--- Extrato da conta poupança ----
Titular: Maria de nazaré
Agência: 1
Número: 2
Saldo: 20,00
Movimentações:
08/11/2026 18:42:08 - TRANSFERENCIA_RECEBIDA - R$ 20,00
```
