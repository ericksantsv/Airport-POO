package trabalhopoo.model;

import java.util.Objects;
import java.util.Scanner;
import trabalhopoo.dao.CompanhiaAereaDAO;
import trabalhopoo.dao.PassageiroDAO;
import trabalhopoo.dao.RelatoriosDAO;

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
        System.out.println("\n--- MENU ADMINISTRADOR ---");
        System.out.println("1 - Gerenciar Passageiros");
        System.out.println("2 - Gerenciar Aeroportos");
        System.out.println("3 - Gerenciar Companhias Aéreas");
        System.out.println("4 - Gerenciar Voos");
        System.out.println("5 - Gerenciar Tickets");
        System.out.println("6 - Gerenciar Assentos de Voo");
        System.out.println("7 - Relatórios Gerenciais");
        System.out.println("8 - Voltar");
        System.out.print("Escolha uma opção: ");
        int admOpc = scan.nextInt();
        scan.nextLine();

        switch (admOpc) {
            case 1:
                // Chama CRUD de Passageiros
                Passageiro.crudPassageiro(passageiros, scan);
                break;
            case 2:
                // Ainda não implementado
                System.out.println("Gerenciamento de Aeroportos ainda não implementado.");
                break;
            case 3:
                // Chama CRUD de Companhias Aéreas
                
                
                break;
            case 4:
                // Chama CRUD de Voos
                Voo.crudVoo(voos, companhias, scan);
                break;
            case 5:
                // Ainda não implementado
                System.out.println("Gerenciamento de Tickets ainda não implementado.");
                break;
            case 6:
                // Ainda não implementado
                System.out.println("Gerenciamento de Assentos de Voo ainda não implementado.");
                break;
            case 7:
                // Chama Relatórios (a implementar)
                //RelatoriosDAO.gerarRelatorios(passageiros, voos, tickets, scan);
                break;
            case 8:
                admMenu = false;
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}


}
