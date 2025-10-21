package trabalhopoo.model;

import java.util.Objects;
import java.util.Scanner;
import trabalhopoo.dao.BoardingPassDAO;
import trabalhopoo.dao.CheckInDAO;
import trabalhopoo.dao.DespachoBagagemDAO;
import trabalhopoo.dao.TicketDAO;
import trabalhopoo.dao.UsuarioDAO;
import trabalhopoo.dao.VooDAO;

public class Usuario {

    //atributos
    private int id;
    private String login;
    private String senha;
    private String tipo;
    private Passageiro passageiro;
    
    //construtores
    public Usuario() {
    }

    public Usuario(String login, String senha, String tipo) {
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
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
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Usuario other = (Usuario) obj;
        return Objects.equals(this.login, other.login)
                && Objects.equals(this.senha, other.senha);
    }

    // ---------------- Menu Admin ----------------
    public static void menuAdmin(Scanner scan, Passageiro[] passageiros, Voo[] voos, CompanhiaAerea[] companhias, Usuario[] usuarios) {
        boolean admMenu = true;
        while (admMenu) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1 - Gerenciar Passageiros");
            System.out.println("2 - Gerenciar Companhias Aereas");
            System.out.println("3 - Gerenciar Voos");
            System.out.println("4 - Gerenciar Tickets");
            System.out.println("5 - Gerenciar Assentos de Voo");
            System.out.println("6 - Gerenciar usuarios");
            System.out.println("7 - Relatorios Gerenciais");
            System.out.println("8 - Voltar");
            System.out.print("Escolha uma opcao: ");
            int admOpc = scan.nextInt();
            scan.nextLine();

            switch (admOpc) {
                case 1:
                    // Chama CRUD de Passageiros
                    Passageiro.crudPassageiro(passageiros, scan);
                    break;
                case 2:
                    // Ainda não implementado
                    CompanhiaAerea.crudCompanhiaAerea(companhias, scan);
                    break;
                case 3:
                    Voo.crudVoo(voos, companhias, scan);
                    break;
                case 4:
                    //Ticket
                    
                    break;
                case 5:
                    // Ainda não implementado
                    VooAssentos.crudAssentos(voos, passageiros, scan);
                    break;
                case 6:
                    crudUsuario(usuarios, scan);
                    break;
                case 7:
                    // Chama Relatórios (a implementar)
                    //RelatoriosDAO.gerarRelatorios(passageiros, voos, tickets, scan);
                    break;
                case 8:
                    admMenu = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    public static void crudUsuario(Usuario[] usuarios, Scanner scan) {
        boolean userMenu = true;
        while (userMenu) {
            System.out.println("\n--- CRUD Usuarios ---");
            System.out.println("1 - Criar Users");
            System.out.println("2 - Listar Users");
            System.out.println("3 - Voltar");
            System.out.print("Escolha uma opcao: ");
            int usrOpc = scan.nextInt();
            scan.nextLine();

            switch (usrOpc) {
                case 1:
                    UsuarioDAO.criaUsuarioAdm(scan, usuarios);
                    break;
                case 2:
                    UsuarioDAO.listarUsuario(usuarios);
                    break;
                case 3:
                    userMenu = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }
    // ------------------- Menu Funcionário ---------------
    public static void menuFuncionario(Scanner scan, Passageiro[] passageiros, Voo[] voos, CheckIn[] checkIns, BoardingPass[] boardingPasses, DespachoBagagem[] bagagens) {
        boolean funcMenu = true;

        while (funcMenu) {
            System.out.println("\n--- MENU FUNCIONARIO ---");
            System.out.println("1 - Fazer Check-in de Passageiro");
            System.out.println("2 - Despachar Bagagem");
            System.out.println("3 - Listar BoardingPass");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opcao: ");
            int opc = scan.nextInt();
            scan.nextLine();

            switch (opc) {
                case 1:
                    CheckInDAO.aprovarCheckIn(checkIns, boardingPasses, scan);
                    break;
                case 2:
                    DespachoBagagemDAO.despacharBagagem(checkIns, bagagens, scan);
                    break;
                case 3:
                    BoardingPassDAO.listarBoardingPasses(boardingPasses);
                    break;
                case 4:
                    funcMenu = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

        // ------------------- Menu Passageiro -------------------
    public static void menuPassageiro(Scanner scan, Usuario usuario, Voo[] voos, CheckIn[] checkIns, DespachoBagagem[] bagagens) {
        boolean menuPass = true;

        while (menuPass) {
            System.out.println("\n--- MENU PASSAGEIRO ---");
            System.out.println("1 - Buscar Voos");
            System.out.println("2 - Comprar Passagem");
            System.out.println("3 - Consultar Minhas Reservas");
            System.out.println("4 - Cancelar Passagem");
            System.out.println("5 - Fazer Check-in");
            System.out.println("6 - Voltar");
            System.out.print("Escolha uma opcao: ");
            int opc = scan.nextInt();
            scan.nextLine();

            switch (opc) {
                case 1:
                    VooDAO.buscarVoos(voos, scan);
                    break;
                case 2:
                    TicketDAO.comprarTicketPassageiro(usuario, voos, scan);
                    break;
                case 3:
                    TicketDAO.listarReservas(usuario, checkIns, bagagens);
                    break;
                case 4:
                    TicketDAO.cancelarPassagem(usuario, checkIns, bagagens, scan);
                    break;
                case 5:
                    CheckInDAO.solicitarCheckIn(usuario, checkIns, scan);
                    break;
                case 6:
                    menuPass = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }


}
