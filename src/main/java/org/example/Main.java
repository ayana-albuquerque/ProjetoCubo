package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Conta> contas = new ArrayList<>();
    private static Conta contaAtual;

    public static void main(String[] args) {
        int escolhaPrimeiroMenu;

        do {
            exibirMenuPrincipal();
            escolhaPrimeiroMenu = scanner.nextInt();

            switch (escolhaPrimeiroMenu) {
                case 1:
                    acessarConta();
                    if (contaAtual != null) {
                        menuOperacoes();
                    }
                    break;
                case 2:
                    criarConta();
                    break;
                case 3:
                    verificarListaDeContas();
                    break;
                case 0:
                    System.out.println("Obrigado por utilizar o Banco! Até mais.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolhaPrimeiroMenu != 0);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1 - Acessar Conta");
        System.out.println("2 - Criar Conta");
        System.out.println("3 - Verificar Lista de Contas");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuOperacoes() {
        int escolhaSegundoMenu;

        do {
            exibirMenuOperacoes();
            escolhaSegundoMenu = scanner.nextInt();

            switch (escolhaSegundoMenu) {
                case 1:
                    depositar();
                    break;
                case 2:
                    sacar();
                    break;
                case 3:
                    realizarPix();
                    break;
                case 4:
                    exibirExtrato();
                    break;
                case 0:
                    System.out.println("Saindo da conta. Voltando ao Menu Principal.");
                    contaAtual = null;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolhaSegundoMenu != 0);
    }

    private static void exibirMenuOperacoes() {
        System.out.println("\n=== Menu de Operações ===");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - PIX");
        System.out.println("4 - Ver Extrato");
        System.out.println("0 - Sair da Conta");
        System.out.print("Escolha uma operação: ");
    }

    private static void acessarConta() {
        System.out.print("Informe o número da conta: ");
        int numeroConta = scanner.nextInt();
        contaAtual = encontrarContaPorNumero(numeroConta);

        if (contaAtual == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.print("Informe a senha da conta: ");
        String senha = scanner.next();

        if (!contaAtual.verificarSenha(senha)) {
            System.out.println("Senha incorreta. Acesso à conta cancelado.");
            contaAtual = null;
        }
    }

    private static void criarConta() {
        System.out.print("Informe seu nome: ");
        String nome = scanner.next();

        System.out.print("Informe seu sobrenome: ");
        String sobrenome = scanner.next();

        System.out.print("Informe seu CPF: ");
        String cpf = scanner.next();

        System.out.print("Escolha o tipo de conta (1 - Conta Corrente, 2 - Conta Poupança): ");
        int tipoConta = scanner.nextInt();

        Conta novaConta;
        if (tipoConta == 1) {
            System.out.print("Informe sua renda mensal para a Conta Corrente: ");
            double limiteChequeEspecial = scanner.nextDouble();
            novaConta = new ContaCorrente(limiteChequeEspecial);
            novaConta.setTipoConta("Conta Corrente");
        } else if (tipoConta == 2) {
            System.out.print("Informe a taxa de rendimento para a Conta Poupança: ");
            double taxaRendimento = scanner.nextDouble();
            novaConta = new ContaPoupanca(taxaRendimento);
            novaConta.setTipoConta("Conta Poupança");
        } else {
            System.out.println("Tipo de conta inválido. Criando Conta Padrão.");
            novaConta = new Conta();
        }

        novaConta.setNome(nome);
        novaConta.setSobrenome(sobrenome);
        novaConta.setCpf(cpf);

        System.out.print("Defina a senha da conta: ");
        String senha = scanner.next();
        novaConta.setSenha(senha);

        System.out.println("\nConta criada com sucesso!");
        System.out.println("Nome: " + nome + " " + sobrenome);
        System.out.println("Número da Conta: " + novaConta.getNumeroConta());
        System.out.println("Número da Agência: " + Conta.getAgencia());

        contas.add(novaConta);
        scanner.nextLine();
    }

    private static void depositar() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta acessada. Acesse uma conta primeiro.");
            return;
        }

        System.out.print("Informe o valor para depósito: ");
        double valor = scanner.nextDouble();
        contaAtual.depositar(valor);
        System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
    }

    private static void sacar() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta acessada. Acesse uma conta primeiro.");
            return;
        }

        System.out.print("Informe o valor para saque: ");
        double valor = scanner.nextDouble();
        if (contaAtual.sacar(valor)) {
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para o saque.");
        }
    }

    private static void realizarPix() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta acessada. Acesse uma conta primeiro.");
            return;
        }

        System.out.print("Informe o número da conta destinatária: ");
        int numeroContaDestinatario = scanner.nextInt();

        Conta contaDestinatario = encontrarContaPorNumero(numeroContaDestinatario);

        if (contaDestinatario != null) {
            System.out.print("Informe o valor para Pix: ");
            double valor = scanner.nextDouble();

            if (contaAtual.sacar(valor)) {
                contaDestinatario.depositar(valor);
                System.out.println("Pix de R$" + valor + " realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para realizar o Pix.");
            }
        } else {
            System.out.println("Conta destinatária não encontrada.");
        }
    }
    private static void exibirExtrato() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta acessada. Acesse uma conta primeiro.");
            return;
        }
        contaAtual.exibirExtrato();
    }
    private static void verificarListaDeContas() {
        System.out.println("\nLista de Contas:");
        for (Conta conta : contas) {
            System.out.println("Número da Conta: " + conta.getNumeroConta());
            System.out.println("Nome: " + conta.getNome() + " " + conta.getSobrenome());
            System.out.println("CPF: " + conta.getCpf());
            System.out.println("Saldo: R$" + conta.getSaldo());
            System.out.println("Senha: " + conta.getSenha());
            System.out.println("------------");
        }
    }
    private static Conta encontrarContaPorNumero(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }
}