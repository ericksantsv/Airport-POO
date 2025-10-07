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
    
    public static Usuario loginAdmin(Scanner scan, Usuario[] usuarios){
        System.out.print("Login: ");
        String login = scan.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scan.nextLine().trim();

        for(Usuario u : usuarios){
            if(u != null && Objects.equals(login, u.getLogin()) && Objects.equals(senha, u.getSenha())){
                return u;
            }
        }
        return null; // login incorreto
    }
    
    public Usuario criaUsuario(Scanner scan) {
        System.out.println("Registre o seu login e senha");
        System.out.print("Usuário: ");
        String login = scan.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scan.nextLine().trim();

        return new Usuario(login, senha);
    }
    
    public static void listarUsuario(Usuario[] usuarios) {
        System.out.println("\n===== Usuários =====");
        for (Usuario u : usuarios) {
            if (u != null) {
                System.out.println("\n| Login: " + u.login
                        + "\n| Senha: " + u.senha
                );
            }
        }
    }
    
}
