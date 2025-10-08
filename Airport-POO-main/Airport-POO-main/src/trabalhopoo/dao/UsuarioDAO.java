/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.util.Objects;
import java.util.Scanner;
import trabalhopoo.model.CompanhiaAerea;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;
import trabalhopoo.model.VooAssentos;

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
      public static void menuAdmin(Scanner scan, Passageiro[] passageiros, CompanhiaAerea[] companhias, Voo[] voos, Ticket[] tickets, VooAssentos[] assentos) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1 - CRUD Passageiros");
            System.out.println("2 - CRUD Companhias Aéreas");
            System.out.println("3 - CRUD Voos");
            System.out.println("4 - CRUD Tickets");
            System.out.println("5 - CRUD Assentos de Voo");
            System.out.println("6 - Relatórios Gerenciais");
            System.out.println("7 - Voltar");
            System.out.print("Escolha: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    Passageiro.crudPassageiro(passageiros, scan);
                    break;
                case 2:
                    CompanhiaAereaDAO.crudCompanhia(companhias, scan);
                    break;
                case 3:
                    VooDAO.cadastrar(voos, companhias, scan);
                    break;
                case 4:
                   // TicketDAO.(tickets, passageiros, voos, scan);
                    break;
                case 5:
                    VooAssentosDAO.crudAssentos(assentos, voos, passageiros, scan);
                    break;
                case 6:
                    RelatoriosDAO.gerarRelatorios(passageiros, voos, tickets, scan);
                    break;
                case 7:
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
}
