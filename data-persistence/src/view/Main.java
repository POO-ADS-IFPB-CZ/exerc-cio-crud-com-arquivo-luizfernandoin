package view;

import dao.PessoaDao;
import model.Pessoa;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PessoaDao pessoaDao = new PessoaDao();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Salvar uma pessoa");
            System.out.println("2. Listar todas as pessoas");
            System.out.println("3. Deletar uma pessoa pelo e-mail");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o e-mail: ");
                    String email = scanner.nextLine();
                    Pessoa novaPessoa = new Pessoa(nome, email);
                    if (pessoaDao.addPessoa(novaPessoa)) {
                        System.out.println("Pessoa adicionada com sucesso!");
                    } else {
                        System.out.println("E-mail já cadastrado!");
                    }
                    break;
                case 2:
                    System.out.println("Lista de pessoas:");
                    for (Pessoa p : pessoaDao.getPessoas()) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    System.out.print("Digite o e-mail da pessoa a ser deletada: ");
                    String emailToDelete = scanner.nextLine();
                    if (pessoaDao.removePessoa(emailToDelete)) {
                        System.out.println("Pessoa deletada com sucesso!");
                    } else {
                        System.out.println("E-mail não encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != 4);

        scanner.close();
    }
}
