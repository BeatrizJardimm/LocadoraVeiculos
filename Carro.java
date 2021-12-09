package br.pucpr.locadora;

import java.util.Scanner;

public class Carro extends Principal {

    Scanner ler = new Scanner(System.in);

    double valorDiaria;
    String descricao;
    String placa;
    int passageiros;

    public String cadastraCarro() {
        String carro = "";

        try {
            System.out.print("\nInsira o valor da diária (formato: 000,00): ");
            this.valorDiaria = ler.nextDouble();

            System.out.print("");
            String vazio = ler.nextLine();

            System.out.print("Insira a descrição do veículo: ");
            this.descricao = ler.nextLine();

            System.out.print("Insira a placa do veículo (sem hífens): ");
            this.placa = ler.nextLine();

            System.out.print("Entre com a quantidade de passageiros: ");
            this.passageiros = ler.nextInt();

            carro = "Passageiros: " + passageiros + " | Descrição: " + descricao + " | Placa: " + placa + " | Valor/Diária: " + valorDiaria + '\n';

            if ('\n' + GetArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastroCarros.txt", true) == carro){

                System.out.println("Esse carro já foi cadastrado.");

            } else{

                System.out.println("Carro cadastrado! \nDados: " + carro);
                AppendArquivo(carro, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastroCarros.txt");
                AppendArquivo(carro, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\carrosDisponiveis.txt");
            }

        } catch (Exception e) {
            System.out.println("Erro na entrada de dados.");
        }

        return carro;
    }

    public String alugaCarro(Principal cliente) {

        double seguro = 0;
        double valorAluguel = 0;

        try{

            System.out.print("\nEntre com a placa do carro que gostaria de alugar (sem hífens): ");
            String placa = ler.nextLine();

            if (GetPlaca(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\carrosLocados.txt").equals(placa)) {

                //verifica se o veiculo está alugado
                System.out.println("Esse carro está indisponível no momento.");

            } else {

                String diaria = GetArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastroCarros.txt", true);
                String[] valor = diaria.split("Valor/Diária: ");

                valorDiaria = Double.parseDouble(valor[1]); //retorna o valor da diaria

                System.out.print("Entre com a quantidada de dias de locação: ");
                int dias = ler.nextInt();

                System.out.print("");
                String vazio = ler.nextLine();

                System.out.print("Entre com a data de locação (dd/mm/aaaa): ");
                String data = ler.nextLine();

                System.out.print("O carro tem seguro? (s/n): ");
                String seg = ler.nextLine();

                System.out.print("Entre com a porcentagem de desconto (sem o %): ");
                double desconto = ler.nextDouble();

                if (seg.equals("s")) {
                    seguro = 0.05 * valorDiaria * (1 + passageiros / 20);

                    if (12.0 < desconto || desconto < 0.0) {
                        System.out.println("Valor de desconto inválido");

                    } else {

                        valorAluguel = (valorDiaria * dias) + seguro;
                        valorAluguel = valorAluguel - (valorAluguel * (desconto / 100));

                        System.out.println("O valor total do aluguel do carro será: R$" + valorAluguel);
                        System.out.print(" \nGostaria de alugar esse veículo? (s/n): ");
                        String aluga = ler.next();

                        if (aluga.equals("s")){
                            System.out.println("Carro alugado. Deverá ser devolvido em " + dias + " dias.");

                            AppendOutroArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastroCarros.txt", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\carrosLocados.txt");

                            GetArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\carrosDisponiveis.txt", false);

                        } else{
                            System.out.println("\nRetornando ao menu...");
                        }
                    }

                } else if (seg.equals("n")){
                    seguro = 0;

                    if (12.0 < desconto || desconto < 0.0) {
                        System.out.println("Valor de desconto inválido");

                    } else {

                        valorAluguel = (valorDiaria * dias) + seguro;
                        valorAluguel = valorAluguel - (valorAluguel * (desconto / 100));

                        System.out.println("O valor total do aluguel do carro será: R$" + valorAluguel);
                        System.out.print("\nGostaria de alugar esse veículo? (s/n): ");
                        String aluga = ler.next();

                        if (aluga.equals("s")){
                            System.out.println("Carro alugado. Deverá ser devolvido em " + dias + " dias.");
                            AppendOutroArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastroCarros.txt", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\carrosLocados.txt");

                            GetArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\carrosDisponiveis.txt", false);

                        }

                    }

                } else{
                    System.out.println("Entrada inválida");
                }
            }

        } catch (Exception e) {
            System.out.println("Erro na entrada de dados.");
        }

        return "\nRetornando ao menu...";
    }
}
