package trabalhopoo;

import java.util.Scanner;
import trabalhopoo.dao.BoardingPassDAO;
import trabalhopoo.dao.CheckInDAO;
import trabalhopoo.dao.CompanhiaAereaDAO;
import trabalhopoo.dao.DespachoBagagemDAO;
import trabalhopoo.dao.PassageiroDAO;
import trabalhopoo.dao.TicketDAO;
import trabalhopoo.dao.UsuarioDAO;
import trabalhopoo.dao.VooDAO;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.CompanhiaAerea;
import trabalhopoo.model.DespachoBagagem;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;

public class TrabalhoPOO {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        CompanhiaAerea[] companhiaAerea = CompanhiaAereaDAO.inicializarCompanhias();
        Voo[] voos = VooDAO.inicializarVoos(companhiaAerea);
        Passageiro[] passageiros = PassageiroDAO.inicializarPassageiros();
        Ticket[] tickets = TicketDAO.inicializarTickets(passageiros, voos);

        Usuario[] usuarios = UsuarioDAO.inicializarUsuariosComPassageiros(passageiros);

        CheckIn[] checkIns = new CheckIn[tickets.length];
        CheckInDAO.inicializarCheckIns(tickets, checkIns);

        DespachoBagagem[] bagagens = new DespachoBagagem[tickets.length];
        DespachoBagagemDAO.inicializarBagagens(tickets, checkIns, bagagens);
        
        BoardingPass[] boardingPasses = new BoardingPass[tickets.length];
        BoardingPassDAO.inicializarBoardingPasses(tickets, boardingPasses, checkIns, bagagens);

        boolean menu = true;
        while (menu) {
            System.out.println("\n\n===== Menu Principal =====");
            System.out.println("1 - Ver Painel de voo");
            System.out.println("2 - Buscar voos");
            System.out.println("3 - Compra de passagem");
            System.out.println("4 - Login administrador");
            System.out.println("5 - Login funcionario");
            System.out.println("6 - Login passageiro");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opcao: ");
            int opc = scan.nextInt();
            scan.nextLine();

            switch (opc) {
                case 1:
                    VooDAO.listar(voos);
                    break;
                case 2:
                    VooDAO.buscarVoos(voos, scan);
                    break;
                case 3:
                    PassageiroDAO.cadastrarSemLogin(passageiros, voos, usuarios, scan);
                    break;
                case 4:
                    Usuario admin = UsuarioDAO.loginAdmin(scan, usuarios);
                    if (admin != null) {
                        Usuario.menuAdmin(scan, tickets, passageiros, voos, checkIns, bagagens, companhiaAerea, usuarios, boardingPasses);
                    }
                    break;
                case 5:
                    Usuario funcionario = UsuarioDAO.loginFuncionario(scan, usuarios);
                    if (funcionario != null) {
                        Usuario.menuFuncionario(scan, passageiros, voos, checkIns, boardingPasses, bagagens);
                    }
                    break;
                case 6:
                    Usuario passageiroUser = UsuarioDAO.loginPassageiro(scan, usuarios);
                    if (passageiroUser != null) {
                        Usuario.menuPassageiro(scan, passageiroUser, voos, checkIns, bagagens, boardingPasses);
                    }
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
