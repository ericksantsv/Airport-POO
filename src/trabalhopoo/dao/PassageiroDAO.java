package trabalhopoo.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;

public class PassageiroDAO {

    //Inicializa vetores
    public static Passageiro[] inicializarPassageiros() {
        Passageiro[] passageiros = new Passageiro[10];

        passageiros[0] = new Passageiro(1, "Joao Silva", LocalDate.parse("1990-05-12"), "123.456.789-00", LocalDateTime.now(), LocalDateTime.now());
        passageiros[1] = new Passageiro(2, "Maria Oliveira", LocalDate.parse("1988-11-30"), "987.654.321-00", LocalDateTime.now(), LocalDateTime.now());
        passageiros[2] = new Passageiro(3, "Carlos Pereira", LocalDate.parse("1995-02-20"), "321.987.654-11", LocalDateTime.now(), LocalDateTime.now());
        passageiros[3] = new Passageiro(4, "Ana Souza", LocalDate.parse("2000-07-10"), "654.321.987-22", LocalDateTime.now(), LocalDateTime.now());
        passageiros[4] = new Passageiro(5, "Bruno Gomes", LocalDate.parse("1998-03-15"), "111.222.333-44", LocalDateTime.now(), LocalDateTime.now());

        return passageiros;
    }

    public static void cadastrar(Passageiro[] passageiros, Scanner scan) {
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

    public static void deletar(Passageiro[] passageiros, Scanner scan) {
        System.out.print("Informe o ID do passageiro para deletar: ");
        int idDel = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < passageiros.length; i++) {
            if (passageiros[i] != null && passageiros[i].getId() == idDel) {
                passageiros[i] = null;
                System.out.println("Passageiro deletado!");
                break;
            }
        }
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
