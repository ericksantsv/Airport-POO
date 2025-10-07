package trabalhopoo.model;

import java.util.Objects;
import java.util.Scanner;

public class Usuario {
    //atributos
    private int id;
    public String login;
    public String senha;

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
                    Passageiro.crudPassageiro(passageiros, scan);
                    break;
                case 2:
                    Voo.crudVoo(voos, companhias, scan);
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
}
