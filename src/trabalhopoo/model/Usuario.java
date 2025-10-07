package trabalhopoo.model;

import java.util.Objects;
import java.util.Scanner;

public class Usuario {

    private static void crudPassageiro(Scanner scan, Passageiro[] passageiros) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void crudVoo(Scanner scan, Voo[] voos, CompanhiaAerea[] companhias) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //atributos
    private int id;
    private String login;
    private String senha;

    //construtores
    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    //getters e setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.login);
        hash = 17 * hash + Objects.hashCode(this.senha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(this.login, other.login) &&
               Objects.equals(this.senha, other.senha);
    }

    public Usuario criaUsuario(Scanner scan) {
        System.out.println("Registre o seu login e senha");
        System.out.print("Usuário: ");
        String login = scan.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scan.nextLine().trim();

        return new Usuario(login, senha);
    }

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
    
    // ---------------- Menu Admin ----------------
    public static void menuAdmin(Scanner scan, Passageiro[] passageiros, Voo[] voos, CompanhiaAerea[] companhias) {
        boolean admMenu = true;
        while (admMenu) {
            System.out.println("\n===== Menu Administração =====");
            System.out.println("1 - CRUD Passageiro");
            System.out.println("2 - CRUD Voo");
            System.out.println("3 - Voltar");
            System.out.print("Escolha uma opção: ");
            int admOpc = scan.nextInt();
            scan.nextLine();

            switch (admOpc) {
                case 1:
                    crudPassageiro(scan, passageiros);
                    break;
                case 2:
                    crudVoo(scan, voos, companhias);
                    break;
                case 3:
                    admMenu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
    
    
    public static void exibirUsuario(Usuario[] usuarios) {
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
