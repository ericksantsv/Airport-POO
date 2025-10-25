/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.DespachoBagagem;
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

    public static Ticket[] inicializarTickets(Passageiro[] passageiros, Voo[] voos) {
        if (voos == null || passageiros == null) {
            return new Ticket[0];
        }

        Ticket[] tickets = new Ticket[passageiros.length * 2]; // reserva espaço extra
        int index = 0;
        double[] valoresExemplo = {150, 200, 250, 300, 350, 400, 450, 500, 550, 600};

        int contadorVoo = 0; // garante que todos os voos concluidos sejam usados

        for (int i = 0; i < passageiros.length; i++) {
            Passageiro p = passageiros[i];
            if (p == null) {
                continue;
            }

            // Seleciona voo concluído de forma circular
            Voo vooPrincipal = null;
            int tentativas = 0; // evita loop infinito se não houver voos concluídos
            while (tentativas < voos.length) {
                Voo v = voos[contadorVoo % voos.length];
                contadorVoo++;
                tentativas++;
                if (v != null && v.getEstado().equalsIgnoreCase("Concluido")) {
                    vooPrincipal = v;
                    break;
                }
            }

            if (vooPrincipal == null) {
                continue; // pula passageiro se não houver voo concluído
            }
            // Cria ticket principal
            Ticket t1 = new Ticket(index + 1, valoresExemplo[i % valoresExemplo.length], vooPrincipal, p);
            tickets[index++] = t1;
            p.adicionarTicket(t1);

            // Cria ticket extra a cada 3 passageiros, em outro voo concluído
            if (i % 3 == 0) {
                Voo vooExtra = null;
                tentativas = 0;
                while (tentativas < voos.length) {
                    Voo v = voos[contadorVoo % voos.length];
                    contadorVoo++;
                    tentativas++;
                    if (v != null && v.getEstado().equalsIgnoreCase("Concluido")) {
                        vooExtra = v;
                        break;
                    }
                }

                if (vooExtra != null) {
                    Ticket t2 = new Ticket(index + 1, valoresExemplo[(i + 1) % valoresExemplo.length], vooExtra, p);
                    tickets[index++] = t2;
                    p.adicionarTicket(t2);
                }
            }
        }

        return tickets;
    }

    public static void criar(Ticket[] ticket, Passageiro passageiro, Voo[] voos, Scanner scan) {
        for (int i = 0; i < ticket.length; i++) {
            if (ticket[i] == null) {
                //Arrumar valor
                double valor = 10;
                Voo voo = VooDAO.escolherVoo(voos, scan);

                ticket[i] = new Ticket(i + 1, valor, voo, passageiro);
                passageiro.setTicket(ticket);

                break;
            }
        }
    }

    public static void listarReservas(Usuario usuario, CheckIn[] checkIns, DespachoBagagem[] bagagens) {
        Ticket[] tickets = usuario.getPassageiro().getTicket();
        System.out.println("\n--- Lista de passagens ---");

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Ticket t : tickets) {
            if (t != null) {
                Voo v = t.getVoo();

                // Atualiza o estado do voo antes de exibir
                v.atualizarEstadoAutomatico();

                // -------- Assento --------
                String codigoAssento = "Nao reservado";
                VooAssentos[] assentos = v.getVooAssentos();
                if (assentos != null) {
                    for (VooAssentos a : assentos) {
                        if (a != null && a.getPassageiro() == usuario.getPassageiro()) {
                            codigoAssento = a.getCodigoAssento();
                            break;
                        }
                    }
                }

                // -------- Check-in --------
                String statusCheckIn = "Nao solicitado";
                boolean boardingPassEmitido = false;
                for (CheckIn c : checkIns) {
                    if (c != null && c.getTicket() == t) {
                        if (c.isAprovado()) {
                            statusCheckIn = "Aprovado";
                            boardingPassEmitido = true;
                        } else {
                            statusCheckIn = "Aguardando aprovacao";
                        }
                        break;
                    }
                }

                // -------- Despacho de Bagagem --------
                String statusBagagem = "Nao despachada";
                for (DespachoBagagem d : bagagens) {
                    if (d != null && d.getTicket() == t) {
                        statusBagagem = "Despachada em " + d.getDataCriacao().format(formato);
                        break;
                    }
                }

                // -------- Formatar duração --------
                int horas = v.getDuracao().getHour();
                int minutos = v.getDuracao().getMinute();

                String duracaoFormatada;
                if (horas > 0 && minutos > 0) {
                    duracaoFormatada = horas + "h" + minutos + "min";
                } else if (horas > 0) {
                    duracaoFormatada = horas + "h";
                } else {
                    duracaoFormatada = minutos + "min";
                }

                // -------- Exibição --------
                System.out.println("\n| Numero: " + t.getId()
                        + "\n| Codigo: " + t.getCodigo()
                        + "\n| Origem: " + v.getOrigem()
                        + "\n| Destino: " + v.getDestino()
                        + "\n| Duracao: " + duracaoFormatada
                        + "\n| Companhia aerea: " + v.getCompanhiaAerea().getNome()
                        + "\n| Data do voo: " + v.getData().format(formato)
                        + "\n| Status do voo: " + v.getEstado()
                        + "\n| Assento: " + codigoAssento
                        + "\n| Check-in: " + statusCheckIn
                        + "\n| Boarding pass: " + (boardingPassEmitido ? "Emitido" : "Nao emitido")
                        + "\n| Bagagem: " + statusBagagem
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

                tickets[i] = new Ticket(i + 1, valor, voo, usuario.getPassageiro());
                VooAssentosDAO.reservarAssentoSemLogin(voo, usuario.getPassageiro(), scan);
                System.out.println("Passagem criada para " + usuario.getPassageiro().getNome());
                criado = true;
                break;
            }
        }
        if (!criado) {
            System.out.println("Erro: nao ha espaco disponivel para criar novo ticket.");
        }
    }

    public static Voo criarRetornandoVoo(Ticket[] ticket, Passageiro passageiro, Voo[] voos, Scanner scan) {
        for (int i = 0; i < ticket.length; i++) {
            if (ticket[i] == null) {
                double valor = 10;

                // Aqui o passageiro escolhe o voo
                Voo vooEscolhido = VooDAO.escolherVoo(voos, scan);

                // Cria o ticket
                ticket[i] = new Ticket(i + 1, valor, vooEscolhido, passageiro);

                // Vincula o ticket ao passageiro
                passageiro.setTicket(ticket);

                System.out.println("Ticket criado com sucesso para o voo " + vooEscolhido.getId() + "!");

                // Retorna o voo que foi escolhido
                return vooEscolhido;
            }
        }

        System.out.println("Erro: nao ha espaço disponivel para criar novo ticket.");
        return null;
    }

    public static void cancelarPassagem(Usuario usuario, CheckIn[] checkIns, DespachoBagagem[] bagagens, Scanner scan) {
        Passageiro passageiro = usuario.getPassageiro();
        Ticket[] tickets = passageiro.getTicket();

        // Mostra as reservas atuais
        listarReservas(usuario, checkIns, bagagens);

        System.out.print("\nDigite o numero da passagem que deseja cancelar: ");
        int num = scan.nextInt();
        scan.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < tickets.length; i++) {
            Ticket t = tickets[i];
            if (t != null && t.getId() == num) {
                encontrado = true;
                Voo voo = t.getVoo();

                // 🔹 1. Cancela o check-in, se existir
                for (int j = 0; j < checkIns.length; j++) {
                    CheckIn c = checkIns[j];
                    if (c != null && c.getTicket() == t) {
                        checkIns[j] = null;
                        System.out.println("Check-in associado foi cancelado.");
                        break;
                    }
                }

                // 🔹 2. Libera o assento
                VooAssentos[] assentos = voo.getVooAssentos();
                for (VooAssentos a : assentos) {
                    if (a != null && a.getPassageiro() == passageiro) {
                        a.setPassageiro(null);
                        break;
                    }
                }

                // 🔹 3. Remove o ticket
                tickets[i] = null;
                System.out.println("Passagem cancelada com sucesso!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhuma passagem encontrada com esse numero.");
        }
    }

}
