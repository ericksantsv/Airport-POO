/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.util.Objects;
import java.util.Scanner;
import trabalhopoo.model.Usuario;

/**
 *
 * @author erick
 */
public class UsuarioDAO {

    public static Usuario loginAdmin(Scanner scan, Usuario[] usuarios) {
        System.out.print("Login: ");
        String login = scan.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scan.nextLine().trim();

        for (Usuario u : usuarios) {
            if (u != null && Objects.equals(login, u.getLogin()) && Objects.equals(senha, u.getSenha())) {
                return u;
            }
        }
        return null; // login incorreto
    }

    public static Usuario criaUsuario(Scanner scan, Usuario[] usuarios) {
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

        // Adiciona no vetor
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = new Usuario(login, senha);
                System.out.println("Usuario registrado com sucesso!");
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
                System.out.println("\n| Login: " + u.login
                        + "\n| Senha: " + u.senha
                );
            }
        }
    }
}
