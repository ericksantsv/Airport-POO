package trabalhopoo.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.DespachoBagagem;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;
import trabalhopoo.model.VooAssentos;

public class PassageiroDAO {

    //Inicializa vetores
    public static Passageiro[] inicializarPassageiros() {
        Passageiro[] passageiros = new Passageiro[50];

        passageiros[0] = new Passageiro(1, "Joao Silva", LocalDate.parse("1990-05-12"), "123.456.789-00", LocalDateTime.now(), LocalDateTime.now());
        passageiros[1] = new Passageiro(2, "Maria Oliveira", LocalDate.parse("1988-11-30"), "987.654.321-00", LocalDateTime.now(), LocalDateTime.now());
        passageiros[2] = new Passageiro(3, "Carlos Pereira", LocalDate.parse("1995-02-20"), "321.987.654-11", LocalDateTime.now(), LocalDateTime.now());
        passageiros[3] = new Passageiro(4, "Ana Souza", LocalDate.parse("2000-07-10"), "654.321.987-22", LocalDateTime.now(), LocalDateTime.now());
        passageiros[4] = new Passageiro(5, "Bruno Gomes", LocalDate.parse("1998-03-15"), "111.222.333-44", LocalDateTime.now(), LocalDateTime.now());

        // novos passageiros
        passageiros[5] = new Passageiro(6, "Fernanda Lima", LocalDate.parse("1992-09-08"), "222.333.444-55", LocalDateTime.now(), LocalDateTime.now());
        passageiros[6] = new Passageiro(7, "Rafael Costa", LocalDate.parse("1985-12-01"), "333.444.555-66", LocalDateTime.now(), LocalDateTime.now());
        passageiros[7] = new Passageiro(8, "Patricia Martins", LocalDate.parse("1997-04-19"), "444.555.666-77", LocalDateTime.now(), LocalDateTime.now());
        passageiros[8] = new Passageiro(9, "Lucas Fernandes", LocalDate.parse("1991-06-22"), "555.666.777-88", LocalDateTime.now(), LocalDateTime.now());
        passageiros[9] = new Passageiro(10, "Carla Ribeiro", LocalDate.parse("1989-03-03"), "666.777.888-99", LocalDateTime.now(), LocalDateTime.now());
        passageiros[10] = new Passageiro(11, "Marcos Vinicius", LocalDate.parse("1993-07-12"), "777.888.999-00", LocalDateTime.now(), LocalDateTime.now());
        passageiros[11] = new Passageiro(12, "Juliana Costa", LocalDate.parse("1996-01-28"), "888.999.000-11", LocalDateTime.now(), LocalDateTime.now());
        passageiros[12] = new Passageiro(13, "Felipe Souza", LocalDate.parse("1994-10-05"), "999.000.111-22", LocalDateTime.now(), LocalDateTime.now());
        passageiros[13] = new Passageiro(14, "Aline Rodrigues", LocalDate.parse("1990-08-17"), "000.111.222-33", LocalDateTime.now(), LocalDateTime.now());
        passageiros[14] = new Passageiro(15, "Tiago Almeida", LocalDate.parse("1987-05-25"), "111.222.333-44", LocalDateTime.now(), LocalDateTime.now());
        passageiros[15] = new Passageiro(16, "Renata Oliveira", LocalDate.parse("1995-02-14"), "222.111.333-55", LocalDateTime.now(), LocalDateTime.now());
        passageiros[16] = new Passageiro(17, "Bruno Henrique", LocalDate.parse("1988-11-30"), "333.222.444-66", LocalDateTime.now(), LocalDateTime.now());
        passageiros[17] = new Passageiro(18, "Camila Andrade", LocalDate.parse("1999-09-02"), "444.333.555-77", LocalDateTime.now(), LocalDateTime.now());
        passageiros[18] = new Passageiro(19, "Gustavo Pereira", LocalDate.parse("1991-07-18"), "555.444.666-88", LocalDateTime.now(), LocalDateTime.now());
        passageiros[19] = new Passageiro(20, "Natália Rocha", LocalDate.parse("1993-03-09"), "666.555.777-99", LocalDateTime.now(), LocalDateTime.now());
        passageiros[20] = new Passageiro(21, "Eduardo Lima", LocalDate.parse("1986-12-25"), "777.666.888-00", LocalDateTime.now(), LocalDateTime.now());
        passageiros[21] = new Passageiro(22, "Letícia Carvalho", LocalDate.parse("1998-06-03"), "888.777.999-11", LocalDateTime.now(), LocalDateTime.now());
        passageiros[22] = new Passageiro(23, "André Gonçalves", LocalDate.parse("1992-04-27"), "999.888.000-22", LocalDateTime.now(), LocalDateTime.now());
        passageiros[23] = new Passageiro(24, "Sabrina Torres", LocalDate.parse("1990-10-13"), "000.999.111-33", LocalDateTime.now(), LocalDateTime.now());
        passageiros[24] = new Passageiro(25, "Rodrigo Barbosa", LocalDate.parse("1989-08-29"), "111.000.222-44", LocalDateTime.now(), LocalDateTime.now());

        return passageiros;
    }

    public static void cadastrar(Passageiro[] passageiros, Usuario[] usuarios, Scanner scan) {
        for (int i = 0; i < passageiros.length; i++) {
            if (passageiros[i] == null) {
                System.out.print("Nome: ");
                String nome = scan.nextLine();

                System.out.print("Nascimento (AAAA-MM-DD): ");
                LocalDate nasc = LocalDate.parse(scan.nextLine());

                System.out.print("Documento: ");
                String doc = scan.nextLine();

                passageiros[i] = new Passageiro(i + 1, nome, nasc, doc, LocalDateTime.now(), LocalDateTime.now());
                System.out.println("Passageiro cadastrado!");
                
                UsuarioDAO.criaUsuario(scan, usuarios, passageiros[i]);
                
                break;
            }
        }
    }

    public static void cadastrarSemLogin(Passageiro[] passageiros, Voo[] voos, Usuario[] usuarios, Scanner scan) {
        for (int i = 0; i < passageiros.length; i++) {
            if (passageiros[i] == null) {
                System.out.print("Nome: ");
                String nome = scan.nextLine();

                System.out.print("Nascimento (AAAA-MM-DD): ");
                LocalDate nasc = LocalDate.parse(scan.nextLine());

                System.out.print("Documento: ");
                String doc = scan.nextLine();

                passageiros[i] = new Passageiro(i, nome, nasc, doc, LocalDateTime.now(), LocalDateTime.now());
                System.out.println("Passageiro cadastrado!");

                Voo vooEscolhido = TicketDAO.criarRetornandoVoo(passageiros[i].getTicket(), passageiros[i], voos, scan);

                if (vooEscolhido != null) {
                    // Agora chama a reserva de assento passando o voo selecionado
                    VooAssentosDAO.reservarAssentoSemLogin(vooEscolhido, passageiros[i], scan);
                }

                System.out.println("\nQuase finalizado! Vamos registrar o seu usuario agora\n");
                Usuario usuarioCriado = UsuarioDAO.criaUsuario(scan, usuarios, passageiros[i]);
                if (usuarioCriado != null) {
                    passageiros[i].setUsuario(usuarioCriado);
                }

                break;
            }
        }
    }

    public static void listar(Passageiro[] passageiros) {
        System.out.println("\n--- Lista de Passageiros ---");
        for (Passageiro p : passageiros) {
            if (p != null) {
                System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getDocumento());
            }
        }
    }

    public static void editar(Passageiro[] passageiros, Scanner scan) {
        System.out.print("Informe o ID do passageiro para editar: ");
        int idEdit = scan.nextInt();
        scan.nextLine();

        Passageiro pEdit = buscarPorId(passageiros, idEdit);

        if (pEdit != null) {
            System.out.print("Novo nome: ");
            pEdit.setNome(scan.nextLine());

            System.out.print("Novo documento: ");
            pEdit.setDocumento(scan.nextLine());

            pEdit.setData_modificacao(LocalDateTime.now());

            System.out.println("Passageiro atualizado!");
        } else {
            System.out.println("Passageiro nao encontrado!");
        }
    }

    public static void deletar(Passageiro[] passageiros, Ticket[] tickets, Voo[] voos, CheckIn[] checkIns, DespachoBagagem[] bagagens, BoardingPass[] boardingPasses, Scanner scan) {
    
    PassageiroDAO.listar(passageiros);
    System.out.print("Informe o ID do passageiro para deletar: ");
    int idDel = scan.nextInt();
    scan.nextLine();

    Passageiro passageiroDeletar = null;

    // Encontrar e remover o passageiro
    for (int i = 0; i < passageiros.length; i++) {
        if (passageiros[i] != null && passageiros[i].getId() == idDel) {
            passageiroDeletar = passageiros[i];
            passageiros[i] = null;
            System.out.println("Passageiro deletado!");
            break;
        }
    }

    if (passageiroDeletar == null) {
        System.out.println("Passageiro não encontrado!");
        return;
    }

    // Remover tickets do passageiro
    for (int i = 0; i < tickets.length; i++) {
        if (tickets[i] != null && tickets[i].getPassageiro() == passageiroDeletar) {
            tickets[i] = null;
        }
    }

    // Liberar assentos ocupados pelo passageiro
    for (Voo v : voos) {
        if (v != null) {
            for (VooAssentos a : v.getVooAssentos()) {
                if (a != null && a.getPassageiro() == passageiroDeletar) {
                    a.setPassageiro(null);
                }
            }
        }
    }

    // Remover check-ins do passageiro
    for (int i = 0; i < checkIns.length; i++) {
        if (checkIns[i] != null && checkIns[i].getTicket().getPassageiro() == passageiroDeletar) {
            checkIns[i] = null;
        }
    }

    // Remover bagagens do passageiro
    for (int i = 0; i < bagagens.length; i++) {
        if (bagagens[i] != null && bagagens[i].getTicket().getPassageiro() == passageiroDeletar) {
            bagagens[i] = null;
        }
    }

    // Remover boarding passes do passageiro
    for (int i = 0; i < boardingPasses.length; i++) {
        if (boardingPasses[i] != null && boardingPasses[i].getPassageiro() == passageiroDeletar) {
            boardingPasses[i] = null;
        }
    }

    System.out.println("Todos os registros associados ao passageiro foram removidos.");
}

    public static Passageiro buscarPorId(Passageiro[] passageiros, int id) {
        for (Passageiro p : passageiros) {
            if (p != null && p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    //alterando aqui pra baixo
    public static void fazerCheckIn(Passageiro[] passageiros, Scanner scan) {
        System.out.print("Digite o nome do passageiro: ");
        String nome = scan.nextLine();

        for (Passageiro p : passageiros) {
            if (p != null && p.getNome().equalsIgnoreCase(nome)) {
                // p.setCheckIn(true);
                System.out.println(" Check-in realizado para " + p.getNome());
                return;
            }
        }
        System.out.println("Passageiro nao encontrado!");
    }

    public static void despacharBagagem(Passageiro[] passageiros, Scanner scan) {
        System.out.print("Digite o nome do passageiro: ");
        String nome = scan.nextLine();

        for (Passageiro p : passageiros) {
            if (p != null && p.getNome().equalsIgnoreCase(nome)) {
                //p.setBagagemDespachada(true);
                System.out.println(" Bagagem despachada para " + p.getNome());
                return;
            }
        }
        System.out.println("Passageiro nao encontrado!");
    }

}
