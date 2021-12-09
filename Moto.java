package br.pucpr.locadora;

import java.util.Scanner;

public class Moto extends Principal{

    Scanner ler = new Scanner(System.in);
    double valorDiaria;
    String descricao;
    String placa;
    String partidaElet;

    public String cadastraMoto(){

        String moto = "";

        try{
            System.out.print("\nInsira o valor da diária (formato: 000,00): ");
            valorDiaria = ler.nextDouble();

            System.out.print("");
            String vazio = ler.nextLine();

            System.out.print("Insira a descrição do veículo: ");
            descricao = ler.nextLine();

            System.out.print("Insira a placa do veículo (sem hífens): ");
            placa = ler.nextLine();

            System.out.print("A moto tem partida elétrica (s/n): ");
            partidaElet = ler.nextLine();

            if (partidaElet.equals("s") || partidaElet.equals("n")){
                moto = "Partida elétrica: " + partidaElet + " | Descrição: " + descricao + " | Placa: " + placa + " | Valor/Diária: " + valorDiaria + '\n';

                if ('\n' + GetArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastraMotos.txt", true) == moto){

                    System.out.println("Essa moto já foi cadastrada.");

                } else{

                    System.out.println("Moto cadastrada! \nDados: " + moto);
                    AppendArquivo(moto, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastraMotos.txt");
                    AppendArquivo(moto, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\motosDisponiveis.txt");
                }

            } else{
                System.out.println("Entrada inválida.");
            }


        } catch(Exception e){
            System.out.println("Erro na entrada de dados.");
        }

        return moto;
    }

    public String alugaMoto(Principal cliente){

        double seguro = 0;
        double valorAluguel = 0;

        try{
            System.out.print("Entre com a placa da moto que gostaria de alugar (sem hífens): ");
            String placa = ler.nextLine();

            if (GetPlaca(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\motosLocadas.txt").equals(placa)) {

                System.out.println("Essa moto está indisponível no momento.");

            } else {

                String diaria = GetArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastraMotos.txt", true);
                String[] valor = diaria.split("Valor/Diária: ");
                valorDiaria = Double.parseDouble(valor[1]); //retorna o valor da diaria

                System.out.print("Entre com a quantidada de dias de locação: ");
                int dias = ler.nextInt();

                System.out.print("");
                String vazio = ler.nextLine();

                System.out.print("Entre com a data de locação (dd/mm/aaaa): ");
                String data = ler.nextLine();

                System.out.print("A moto tem seguro? (s/n): ");
                String seg = ler.nextLine();

                System.out.print("Entre com a porcentagem de desconto (sem o %): ");
                double desconto = ler.nextDouble();

                if (seg.equals("s")){
                    seguro = 0.09 * valorDiaria;
                }

                if (12.0 < desconto || desconto < 0.0){
                    System.out.println("Valor de desconto inválido");

                } else {

                    valorAluguel = (valorDiaria * dias) + seguro;
                    valorAluguel = valorAluguel - (valorAluguel * (desconto/100));

                    System.out.println("O valor total do aluguel da moto será: R$" + valorAluguel);
                    System.out.print("\nGostaria de alugar esse veículo? (s/n): ");
                    String aluga = ler.next();

                    if (aluga.equals("s")){
                        System.out.println("Moto alugada");
                        AppendOutroArquivo(placa, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastraMotos.txt", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\motosLocadas.txt");
                    }

                }
            }

        } catch(Exception e){
            System.out.println("Placa não encontrada no sistema.");
        }

        return "\nRetornando ao menu...";
    }
}
