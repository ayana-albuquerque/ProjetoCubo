package org.example;

import java.util.Random;

public class Conta {
    private static int proximoNumeroConta = 1;
    private int numeroConta;
    private double saldo;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;
    private String tipoConta;

    public Conta() {
        this.numeroConta = proximoNumeroConta++;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public void exibirExtrato() {
        System.out.println("Extrato da Conta #" + numeroConta);
        System.out.println("Tipo: " + tipoConta);
        System.out.println("Nome: " + nome + " " + sobrenome);
        System.out.println("CPF: " + cpf);
        System.out.println("Saldo: R$" + saldo);
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNome() {
        return nome;
    }
    public static int getAgencia() {
        return 1989;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getSenha() {
        return senha;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }
}
