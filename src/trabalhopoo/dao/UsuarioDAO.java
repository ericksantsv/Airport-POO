/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.util.Scanner;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Usuario;

/**
 *
 * @author erick
 */
public class UsuarioDAO {

    //Inicializa Vetores
    public static Usuario[] inicializarUsuarios() {
        Usuario[] usuarios = new Usuario[10];

        usuarios[0] = new Usuario("goncalves", "goncalves", "adm");
        usuarios[1] = new Usuario("erick", "erick", "adm");
        usuarios[2] = new Usuario("nico", "nico", "passageiro");
        usuarios[3] = new Usuario("bruna", "bruna", "passageiro");
        usuarios[4] = new Usuario("dudu", "dudu", "funcionario");

        return usuarios;
    }

    public static Usuario loginAdmin(Scanner scan, Usuario[] usuarios) {
        System.out.print("Login: ");
        String login = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();

        for (Usuario u : usuarios) {
            if (u != null && u.getLogin().equalsIgnoreCase(login) && u.getSenha().equals(senha)) {
                if (u.getTipo().equalsIgnoreCase("adm")) {
                    System.out.println("Login de administrador bem-sucedido!");
                    return u;
                } else {
                    System.out.println("Acesso negado! Apenas administradores podem entrar aqui.");
                    return null;
                }
            }
        }

        System.out.println("Login ou senha incorretos!");
        return null;
    }

    public static Usuario loginFuncionario(Scanner scan, Usuario[] usuarios) {
        System.out.print("Login: ");
        String login = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();

        for (Usuario u : usuarios) {
            if (u != null && u.getLogin().equalsIgnoreCase(login) && u.getSenha().equals(senha)) {
                if (u.getTipo().equalsIgnoreCase("funcionario")) {
                    System.out.println("Login de funcionario bem-sucedido!");
                    return u;
                } else {
                    System.out.println("Acesso negado! Apenas funcionarios podem entrar aqui.");
                    return null;
                }
            }
        }

        System.out.println("Login ou senha incorretos!");
        return null;
    }

    public static Usuario loginPassageiro(Scanner scan, Usuario[] usuarios) {
        System.out.print("Login: ");
        String login = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();

        for (Usuario u : usuarios) {
            if (u != null && u.getLogin().equalsIgnoreCase(login) && u.getSenha().equals(senha)) {
                if (!u.getTipo().equalsIgnoreCase("passageiro")) {
                    System.out.println("Acesso negado! Apenas passageiros podem entrar aqui.");
                    return null;
                }

                if (u.getPassageiro() == null) {
                    System.out.println("Este usuario nao esta vinculado a um passageiro!");
                    return null;
                }

                System.out.println("Login de passageiro bem-sucedido!");
                return u;
            }
        }

        System.out.println("Login ou senha incorretos!");
        return null;
    }

    public static Usuario criaUsuario(Scanner scan, Usuario[] usuarios, Passageiro passageiro) {
        System.out.println("Registre o seu login e senha");

        String login;
        boolean loginExistente;

        do {
            System.out.print("Digite o login: ");
            login = scan.nextLine();
            loginExistente = false;

            for (Usuario u : usuarios) {
                if (u != null && u.getLogin().equalsIgnoreCase(login)) {
                    System.out.println("Esse login já esta em uso. Tente outro.");
                    loginExistente = true;
                    break;
                }
            }

        } while (loginExistente);

        System.out.print("Senha: ");
        String senha = scan.nextLine().trim();

        // Adiciona no vetor
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = new Usuario(login, senha, "passageiro");
                usuarios[i].setPassageiro(passageiro);
                System.out.println("Usuario registrado com sucesso!");
                return usuarios[i];
            }
        }

        System.out.println("Erro: limite de usuarios atingido!");
        return null;
    }

    public static Usuario criaUsuarioAdm(Scanner scan, Usuario[] usuarios) {
        System.out.println("Registre o seu login e senha");

        System.out.print("Usuario: ");
        String login = scan.nextLine().trim();

        // Verifica se já existe esse login
        for (Usuario u : usuarios) {
            if (u != null && u.getLogin().equalsIgnoreCase(login)) {
                System.out.println("Esse login ja esta em uso. Tente outro.");
                return null;
            }
        }

        System.out.print("Senha: ");
        String senha = scan.nextLine().trim();

        // Vetor com os tipos disponíveis
        String[] tipos = {"passageiro", "adm", "funcionario"};

        // Mostra opções para o usuário escolher
        System.out.println("Escolha o tipo de usuario:");
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + " - " + tipos[i]);
        }

        int escolha = 0;
        while (true) {
            System.out.print("Digite o numero correspondente: ");
            if (scan.hasNextInt()) {
                escolha = scan.nextInt();
                scan.nextLine(); // limpar buffer
                if (escolha >= 1 && escolha <= tipos.length) {
                    break;
                }
            } else {
                scan.nextLine(); // limpar entrada inválida
            }
            System.out.println("Opcao invalida. Tente novamente.");
        }

        String tipoSelecionado = tipos[escolha - 1]; // pega o tipo correspondente

        // Adiciona no vetor de usuários
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = new Usuario(login, senha, tipoSelecionado);
                System.out.println("Usuario registrado com sucesso! Tipo: " + tipoSelecionado);
                return usuarios[i];
            }
        }

        System.out.println("Erro: limite de usuarios atingido!");
        return null;
    }

    public static void listarUsuario(Usuario[] usuarios) {
        System.out.println("\n===== Usuarios =====");
        for (Usuario u : usuarios) {
            if (u != null) {
                System.out.println("\n| Login: " + u.getLogin()
                        + "\n| Senha: " + u.getSenha()
                        + "\n| Tipo: " + u.getTipo()
                );
            }
        }
    }
}
