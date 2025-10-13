/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;
import trabalhopoo.model.VooAssentos;

/**
 *
 * int id; double valor; Voo voo; Passageiro passageiro; //Codigo id + voo
 * LocalDate dataCriacao; LocalDate dataModificacao;
 *
 * @author erick
 */
public class TicketDAO {

    public static void criar(Ticket[] ticket, Passageiro passageiro, Voo[] voos, Scanner scan) {
        for (int i = 0; i < ticket.length; i++) {
            if (ticket[i] == null) {
                //Arrumar valor
                double valor = 10;
                Voo voo = VooDAO.escolherVoo(voos, scan);

                ticket[i] = new Ticket(i + 1, valor, voo, passageiro, LocalDate.now(), LocalDate.now());
                passageiro.setTicket(ticket);

                break;
            }
        }
    }

    public static void listarReservas(Usuario usuario) {

        Ticket[] tickets = usuario.getPassageiro().getTicket();
        System.out.println("\n--- Lista de passagens ---");

        for (Ticket t : tickets) {
            if (t != null) {
                Voo v = t.getVoo();

                String codigoAssento = "Não reservado";
                VooAssentos[] assentos = v.getVooAssentos(); // vetor de assentos do voo
                if (assentos != null) {
                    for (VooAssentos a : assentos) {
                        if (a != null && a.getPassageiro() == usuario.getPassageiro()) {
                            codigoAssento = a.getCodigoAssento();
                            break;
                        }
                    }
                }
                System.out.println("\nNumero: " + t.getId()
                        + "\n| Origem: " + v.getOrigem()
                        + "\n| Destino: " + v.getDestino()
                        + "\n| Duracao: " + v.getDuracao()
                        + "\n| Companhia aerea" + v.getCompanhiaAerea().getNome()
                        + "\n| Data: " + v.getData()
                        + "\n| Status: " + v.getEstado()
                        + "\n| Assento: " + codigoAssento
                );
            }
        }
    }

    //Cadastra um novo ticket para passageiro
    public static void comprarTicketPassageiro(Usuario usuario, Voo[] voos, Scanner scan) {
        Ticket[] tickets = usuario.getPassageiro().getTicket();
        boolean criado = false;
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] == null) {
                //Arrumar valor
                double valor = 10;

                Voo voo = VooDAO.escolherVoo(voos, scan);

                tickets[i] = new Ticket(i + 1, valor, voo, usuario.getPassageiro(), LocalDate.now(), LocalDate.now());
                VooAssentosDAO.reservarAssentoSemLogin(voo, usuario.getPassageiro(), scan);
                System.out.println("Passagem criada para " + usuario.getPassageiro().getNome());
                criado = true;
                break;
            }
        }
        if (!criado) {
            System.out.println("Erro: nao ha espaço disponível para criar novo ticket.");
        }
    }

    public static Voo criarRetornandoVoo(Ticket[] ticket, Passageiro passageiro, Voo[] voos, Scanner scan) {
        for (int i = 0; i < ticket.length; i++) {
            if (ticket[i] == null) {
                double valor = 10;

                // Aqui o passageiro escolhe o voo
                Voo vooEscolhido = VooDAO.escolherVoo(voos, scan);

                // Cria o ticket
                ticket[i] = new Ticket(i + 1, valor, vooEscolhido, passageiro, LocalDate.now(), LocalDate.now());

                // Vincula o ticket ao passageiro
                passageiro.setTicket(ticket);

                System.out.println("Ticket criado com sucesso para o voo " + vooEscolhido.getId() + "!");

                // Retorna o voo que foi escolhido
                return vooEscolhido;
            }
        }

        System.out.println("Erro: nao ha espaço disponível para criar novo ticket.");
        return null;
    }

    public static void cancelarPassagem(Usuario usuario, Scanner scan) {
        listarReservas(usuario);
        Ticket[] tickets = usuario.getPassageiro().getTicket();
        System.out.print("\nDigite o número da passagem que deseja cancelar: ");
        int num = scan.nextInt();
        scan.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < tickets.length; i++) {
            Ticket t = tickets[i];
            if (t != null && t.getId() == num) {
                encontrado = true;
                Voo voo = t.getVoo();

                // Libera o assento correspondente
                VooAssentos[] assentos = voo.getVooAssentos();
                for (int j = 0; j < assentos.length; j++) {
                    VooAssentos a = assentos[j];
                    if (a != null && a.getPassageiro() == usuario.getPassageiro()) {
                        assentos[j].setPassageiro(null); // libera o assento
                        break;
                    }
                }

                tickets[i] = null; // remove o ticket
                System.out.println("Passagem cancelada com sucesso!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("❌ Nenhuma passagem encontrada com esse número.");
        }

    }

}
