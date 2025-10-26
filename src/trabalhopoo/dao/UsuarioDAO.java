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
    public static Usuario[] inicializarUsuariosComPassageiros(Passageiro[] passageiros) {
        // 3 fixos + passageiros não nulos
        int countPassageiros = 0;
        for (Passageiro p : passageiros) {
            if (p != null) {
                countPassageiros++;
            }
        }

        Usuario[] usuarios = new Usuario[3 * countPassageiros];

        usuarios[0] = new Usuario("goncalves", "goncalves", "adm");
        usuarios[1] = new Usuario("erick", "erick", "adm");
        usuarios[2] = new Usuario("dudu", "dudu", "funcionario");

        int index = 3; // começa depois dos 3 fixos
        for (Passageiro p : passageiros) {
            if (p != null) {
                usuarios[index] = new Usuario(p.getNome().toLowerCase().replace(" ", ""), "pass" + (index - 2), "passageiro");
                usuarios[index].setPassageiro(p);
                index++;
            }
        }

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

        String login;
        boolean loginExistente;

        do {
            System.out.print("Usuario: ");
            login = scan.nextLine().trim();
            if (login.isEmpty()) {
                System.out.println("Login nao pode ser vazio!");
                loginExistente = true;
                continue;
            }
            loginExistente = false;
            for (Usuario u : usuarios) {
                if (u != null && u.getLogin().equalsIgnoreCase(login)) {
                    System.out.println("Esse login ja esta em uso. Tente outro.");
                    loginExistente = true;
                    break;
                }
            }
        } while (loginExistente);

        String senha;
        while (true) {
            System.out.print("Senha: ");
            senha = scan.nextLine().trim();
            if (senha.isEmpty()) {
                System.out.println("Senha nao pode ser vazia!");
            } else {
                break;
            }
        }

        // Vetor com os tipos disponiveis
        String[] tipos = {"adm", "funcionario"};

        // Mostra opcoes para o usuario escolher
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
                scan.nextLine(); // limpar entrada invalida
            }
            System.out.println("Opcao invalida. Tente novamente.");
        }

        String tipoSelecionado = tipos[escolha - 1]; // pega o tipo correspondente

        // Adiciona no vetor de usuarios
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
            if (u != null && !u.getTipo().equalsIgnoreCase("passageiro")) { // Apenas ADM/funcionario
                System.out.println("\n| Login: " + u.getLogin()
                        + "\n| Senha: " + u.getSenha()
                        + "\n| Tipo: " + u.getTipo()
                );
            }
        }
    }

    public static void editarUsuario(Scanner scan, Usuario[] usuarios) {
        System.out.print("Digite o login do usuario que deseja editar: ");
        String login = scan.nextLine();

        Usuario encontrado = null;
        for (Usuario u : usuarios) {
            if (u != null && u.getLogin().equalsIgnoreCase(login)) {
                encontrado = u;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }
        if (encontrado.getTipo().equalsIgnoreCase("passageiro")) {
            System.out.println("Nao e permitido editar passageiros!");
            return;
        }

        // Validacao para login novo nao ser vazio ou repetido
        String novoLogin;
        while (true) {
            System.out.print("Novo login: ");
            novoLogin = scan.nextLine().trim();
            if (novoLogin.isEmpty()) {
                System.out.println("Login nao pode ser vazio!");
                continue;
            }
            boolean repetido = false;
            for (Usuario u : usuarios) {
                if (u != null && u != encontrado && u.getLogin().equalsIgnoreCase(novoLogin)) {
                    repetido = true;
                    break;
                }
            }
            if (repetido) {
                System.out.println("Login ja existe! Tente outro.");
                continue;
            }
            break;
        }
        encontrado.setLogin(novoLogin);

        // Validacao para senha nao ser vazia
        String novaSenha;
        while (true) {
            System.out.print("Nova senha: ");
            novaSenha = scan.nextLine().trim();
            if (novaSenha.isEmpty()) {
                System.out.println("Senha nao pode ser vazia!");
            } else {
                break;
            }
        }
        encontrado.setSenha(novaSenha);

        // Validacao para tipo valido
        String novoTipo;
        while (true) {
            System.out.print("Novo tipo (adm/funcionario): ");
            novoTipo = scan.nextLine().trim().toLowerCase();
            if (!novoTipo.equals("adm") && !novoTipo.equals("funcionario")) {
                System.out.println("Tipo invalido! Escolha 'adm' ou 'funcionario'.");
            } else {
                break;
            }
        }
        encontrado.setTipo(novoTipo);

        System.out.println("Usuario atualizado com sucesso!");
    }

    public static void removerUsuario(Scanner scan, Usuario[] usuarios) {
        System.out.println("\n=== Remover Usuario ===");
        System.out.print("Digite o login do usuario que deseja remover: ");
        String login = scan.nextLine().trim();

        for (int i = 0; i < usuarios.length; i++) {
            Usuario u = usuarios[i];
            if (u != null && u.getLogin().equalsIgnoreCase(login)) {
                if (u.getTipo().equalsIgnoreCase("passageiro")) {
                    System.out.println("Voce nao pode remover um usuario do tipo Passageiro!");
                    return;
                }

                System.out.print("Tem certeza que deseja remover este usuario? (s/n): ");
                String confirm = scan.nextLine().trim().toLowerCase();
                if (confirm.equals("s")) {
                    usuarios[i] = null;
                    System.out.println("Usuario removido com sucesso!");
                } else {
                    System.out.println("Remocao cancelada.");
                }
                return;
            }
        }

        System.out.println("Usuario nao encontrado!");
    }

}
