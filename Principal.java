package br.pucpr.locadora;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        try{
            Carro carro = new Carro();
            Moto moto = new Moto();
            Principal cliente = new Principal();

            while (true){

                System.out.print("\n--> MENU: " + '\n' +
                        "1 - Cadastrar um carro." + '\n' +
                        "2 - Cadastrar uma moto." + '\n' +
                        "3 - Alugar carro." + '\n' +
                        "4 - Alugar moto." + '\n' +
                        "5 - Cadastra cliente." + '\n' +
                        "6 - Listagem de clientes." + '\n' +
                        "7 - Listagem de carros cadastrados." + '\n' +
                        "8 - Listagem de motos cadastradas." + '\n' +
                        "9 - Listagem de carros locados." + '\n' +
                        "10 - Listagem de motos locadas." + '\n' +
                        "11 - Listagem de carros disponíveis." + '\n' +
                        "12 - Listagem de motos disponíveis." + '\n' +
                        "13 - Sair do sistema" + '\n' +
                        "Selecione: ");

                int opcao = ler.nextInt();

                if (opcao == 1) { //cadastra carro
                    carro.cadastraCarro();

                } else if (opcao ==2){ //cadastra moto
                    moto.cadastraMoto();

                } else if (opcao ==3){ //aluga carro
                    if (cliente.autentica()){
                        System.out.println(carro.alugaCarro(cliente));
                    }

                } else if (opcao == 4){ //aluga moto
                    if (cliente.autentica()){
                        System.out.println(moto.alugaMoto(cliente));
                    }

                } else if (opcao == 5){ //cadastra cliente
                    cliente.cadastro();

                } else if (opcao == 6){ //lista clientes
                    System.out.println('\n');
                    GetArquivo("all", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\Cliente.txt", true);

                } else if (opcao == 7){ //lista carros
                    System.out.println('\n');
                    GetArquivo("all", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastroCarros.txt", true);

                } else if (opcao == 8) { //lista motos
                    System.out.println('\n');
                    GetArquivo("all", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\cadastraMotos.txt", true);

                } else if (opcao == 9){ //lista carros locados
                    System.out.println('\n');
                    GetArquivo("all", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\carrosLocados.txt", true);

                } else if (opcao == 10){ //lista motos locadas
                    System.out.println('\n');
                    GetArquivo("all", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\motosLocadas.txt", true);

                } else if (opcao == 11){ //lista de carros disponiveis
                    System.out.println('\n');
                    GetArquivo("all", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\carrosDisponiveis.txt", true);

                } else if (opcao == 12){ //lista de motos disponiveis
                    System.out.println('\n');
                    GetArquivo("all", "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\motosDisponiveis.txt", true);

                } else if (opcao == 13){ //encerra o programa

                    System.out.println(" \n *** Programa encerrado ***");
                    System.exit(0);

                } else {
                    System.out.println(" \n Selecione um número válido. \n");
                }
            }

        } catch(Exception e){
            System.out.println("Erro na entrada de dados.");
        }
    }

    String nome;
    String cpf;
    String usuario;

    /** Funções **/

    public boolean cadastro(){
        try{
            System.out.print("\nEntre com seu nome: ");
            nome = ler.nextLine();

            System.out.print("Entre com seu CPF (apenas números): ");
            cpf = ler.nextLine();

            usuario = nome + '|' + cpf + '\n';   //guarda o username e o hash

            //verifica se o user já existe
            if (GetArquivo(usuario, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\Cliente.txt", true) != "Erro."){
                System.out.println("Esse usuário já existe");
                return false;

            } else{  //insere um novo usuário no arquivo
                AppendArquivo(usuario + '\n', "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\Cliente.txt");
                System.out.println("Usuário cadastrado com sucesso!");
                return true;
            }
        } catch (Exception e){
            System.out.println("Erro na entrada de dados.");
        }
        return false;
    }

    public boolean autentica(){

        try{
            System.out.print("\nDigite seu nome: ");
            nome = ler.nextLine();

            System.out.print("Digite seu CPF (apenas números): ");
            cpf = ler.nextLine();

            usuario = nome + '|' + cpf; //guarda o username e o MD5

            //verifica se o usuário se encontra no arquivo
            if (GetArquivo(usuario, "C:\\Users\\Usuario\\poo-pucpr\\src\\br\\pucpr\\locadora\\Cliente.txt", true) != "Erro."){
                System.out.println("\nBem vindo "+ nome + "!" + " Alugue seu veiculo:");
                return true;

            } else{ //usuário não está no arquivo
                System.out.println("\nUsuário não encontrado.");
                return false;
            }
        } catch (Exception e){
            System.out.println("Erro na entrada de dados.");
        }
        return false;
    }

    public static void AppendArquivo(String conteudo, String nomeArquivo){
        try {
            Writer bw;
            bw = new BufferedWriter(new FileWriter(nomeArquivo, true));
            bw.write(conteudo);
            bw.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void AppendOutroArquivo(String conteudo, String nomeArquivoOrigem, String nomeArquivoDestino){
        try {
            Writer bw;
            bw = new BufferedWriter(new FileWriter(nomeArquivoDestino, true));

            String descricao = GetArquivo(conteudo, nomeArquivoOrigem, true);

            if (descricao == "Erro."){

                System.out.println("Erro na lista de veículos alugados.");

            } else{
                bw.write(descricao);
                bw.close();
            }

            bw.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String GetArquivo(String conteudo, String nomeArquivo, boolean manter){

        String retorno = "Erro.";

        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));

            if (manter == true){
                if (conteudo == "all"){ //pega todo o conteudo da lista

                    while (br.ready()) {              //enquanto houver mais linhas
                        String linha = br.readLine(); //lê a proxima linha

                        System.out.println(linha);

                    }
                    br.close();

                    return retorno;

                } else { //para um conteudo especifico
                    while (br.ready()) {              //enquanto houver mais linhas
                        String linha = br.readLine(); //lê a proxima linha

                        if (linha.toLowerCase(Locale.ROOT).contains(conteudo.toLowerCase(Locale.ROOT))){  //verifica se o conteudo da linha bate com o digitado

                            retorno =  '\n' + linha;

                            return retorno;
                        }
                    }
                    br.close();
                }
            } else if (!manter){

                //para um conteudo especifico
                while (br.ready()) {              //enquanto houver mais linhas
                    String linha = br.readLine(); //lê a proxima linha

                    if (linha.toLowerCase(Locale.ROOT).contains(conteudo.toLowerCase(Locale.ROOT))){  //verifica se o conteudo da linha bate com o digitado

                        linha.replace(linha, "");

                        return retorno;
                    }
                }
                br.close();

            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retorno;
    }

    public static String GetPlaca(String conteudo, String nomeArquivo){

        String retorno = "Erro.";

        try {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));

            while (br.ready()) {              //enquanto houver mais linhas
                String linha = br.readLine(); //lê a proxima linha

                if (linha.contains(conteudo)){  //verifica se o conteudo da linha bate com o digitado

                    return conteudo;
                }

            }
            br.close();

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retorno;
    }
}
