package org.example;

public class ContaPoupanca extends Conta{
    private double taxaRendimento;

    public ContaPoupanca(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

}
