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
    public static void menuAdmin(Scanner scan, Ticket[] tickets, Passageiro[] passageiros, Voo[] voos, CheckIn[]checkIns, DespachoBagagem[] bagagens, CompanhiaAerea[] companhias, Usuario[] usuarios, BoardingPass[] boarding) {
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
                    Passageiro.crudPassageiro(passageiros, voos, tickets, checkIns, bagagens, boarding, usuarios, scan);
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
                    VooAssentos.crudAssentos(voos, passageiros, scan, boarding);
                    break;
                case 6:
                    crudUsuario(usuarios, scan);
                    break;
                case 7:
                    Relatorios.menuRelatorios(voos, tickets, companhias, passageiros, scan);
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

    public static void crudUsuario(Usuario[] usuarios, Scanner scan) {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n--- CRUID Usuarios ---");
            System.out.println("1 - Criar Usuario");
            System.out.println("2 - Listar Usuarios");
            System.out.println("3 - Editar Usuario");
            System.out.println("4 - Remover Usuario");
            System.out.println("5 - Voltar");
            System.out.print("Escolha uma ocpao: ");

            int opc = scan.nextInt();
            scan.nextLine();

            switch (opc) {
                case 1:
                    UsuarioDAO.criaUsuarioAdm(scan, usuarios);
                    break;
                case 2:
                    UsuarioDAO.listarUsuario(usuarios);
                    break;
                case 3:
                    UsuarioDAO.editarUsuario(scan, usuarios);
                    break;
                case 4:
                    UsuarioDAO.removerUsuario(scan, usuarios);
                    break;
                case 5:
                    rodando = false;
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
            System.out.println("\n--- MENU FUNCIONaRIO ---");
            System.out.println("1 - Fazer Check-in de Passageiro");
            System.out.println("2 - Despachar Bagagem");
            System.out.println("3 - Listar BoardingPass");
            System.out.println("4 - Cancelar Voo");
            System.out.println("5 - Voltar");
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
                    VooDAO.cancelarVoo(voos, boardingPasses, scan);
                    break;
                case 5:
                    funcMenu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    // ------------------- Menu Passageiro -------------------
    public static void menuPassageiro(Scanner scan, Usuario usuario, Voo[] voos, CheckIn[] checkIns, DespachoBagagem[] bagagens, BoardingPass[] boarding) {
        boolean menuPass = true;

        while (menuPass) {
            System.out.println("\n--- MENU PASSAGEIRO ---");
            System.out.println("1 - Buscar Voos");
            System.out.println("2 - Comprar Passagem");
            System.out.println("3 - Consultar Minhas Reservas");
            System.out.println("4 - Cancelar Passagem");
            System.out.println("5 - Fazer Check-in");
            System.out.println("6 - Embarcar no voo");
            System.out.println("7 - Voltar");
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
                    BoardingPassDAO.embarcarNoVoo(usuario, checkIns, bagagens, boarding, scan);
                    break;
                case 7:
                    menuPass = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

}
