/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;
import trabalhopoo.model.VooAssentos;

/**
 *
 * @author erick
 */
public class CheckInDAO {

    public static void inicializarCheckIns(Ticket[] tickets, CheckIn[] checkIns) {
        int ciIndex = 0;
        for (Ticket t : tickets) {
            if (t != null) {
                Voo v = t.getVoo();
                if (v.getEstado().equalsIgnoreCase("Concluido")) { // só aprova check-in de voos concluídos
                    checkIns[ciIndex] = new CheckIn(ciIndex + 1, t, t.getPassageiro().getDocumento());
                    checkIns[ciIndex].setDataCriacao(t.getVoo().getData());
                    checkIns[ciIndex].setAprovado(true); // aprova automaticamente
                    ciIndex++;
                }
            }
        }
    }

    public static void solicitarCheckIn(Usuario usuario, CheckIn[] checkIns, Scanner scan) {
        Passageiro passageiro = usuario.getPassageiro();
        Ticket[] tickets = passageiro.getTicket();

        System.out.println("\n--- Tickets disponiveis para check-in ---");
        boolean temTicketDisponivel = false;

        LocalDateTime agora = LocalDateTime.now();

        // 1. Filtra tickets válidos para check-in
        for (Ticket t : tickets) {
            if (t != null) {
                boolean jaSolicitado = false;
                for (CheckIn c : checkIns) {
                    if (c != null && c.getTicket() == t) {
                        jaSolicitado = true;
                        break;
                    }
                }

                if (!jaSolicitado) {
                    Voo voo = t.getVoo();
                    if (voo != null) {
                        LocalDateTime dataVoo = voo.getData();
                        LocalDateTime limiteCheckIn = dataVoo.minusHours(24);

                        // O check-in é permitido se o horário atual estiver entre "limite" e "data do voo"
                        if (agora.isAfter(limiteCheckIn) && agora.isBefore(dataVoo)) {
                            System.out.println("Numero: " + t.getId()
                                    + " | Codigo: " + t.getCodigo()
                                    + " | Voo: " + voo.getOrigem() + " -> " + voo.getDestino()
                                    + " | Horario: " + voo.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                            temTicketDisponivel = true;
                        }
                    }
                }
            }
        }

        if (!temTicketDisponivel) {
            System.out.println("Nenhum ticket disponivel para check-in (somente nas 24h anteriores ao voo).");
            return;
        }

        // 2. Solicita o número do ticket
        System.out.print("\nDigite o numero do ticket que deseja fazer check-in: ");
        int idTicket = scan.nextInt();
        scan.nextLine();

        // 3. Procura o ticket escolhido e verifica o horário
        Ticket ticketEscolhido = null;
        for (Ticket t : tickets) {
            if (t != null && t.getId() == idTicket) {
                Voo voo = t.getVoo();
                if (voo == null) {
                    continue;
                }

                boolean jaSolicitado = false;
                for (CheckIn c : checkIns) {
                    if (c != null && c.getTicket() == t) {
                        jaSolicitado = true;
                        break;
                    }
                }

                if (jaSolicitado) {
                    System.out.println("Este ticket ja possui solicitacao de check-in.");
                    return;
                }

                LocalDateTime dataVoo = voo.getData();
                LocalDateTime limiteCheckIn = dataVoo.minusHours(24);

                // Regras simples usando comparação direta
                if (agora.isBefore(limiteCheckIn)) {
                    System.out.println("Ainda nao e possivel fazer check-in (apenas 24h antes do voo).");
                    return;
                } else if (agora.isAfter(dataVoo)) {
                    System.out.println("O voo ja partiu. Nao e possivel realizar check-in.");
                    return;
                }

                ticketEscolhido = t;
                break;
            }
        }

        if (ticketEscolhido == null) {
            System.out.println("Ticket nao encontrado ou invalido para check-in.");
            return;
        }

        // 4. Registra o check-in
        for (int i = 0; i < checkIns.length; i++) {
            if (checkIns[i] == null) {
                checkIns[i] = new CheckIn(i + 1, ticketEscolhido, passageiro.getDocumento());
                System.out.println("Solicitacao de check-in enviada com sucesso!");
                return;
            }
        }

        System.out.println("Erro: capacidade maxima de check-ins atingida.");
    }

    public static void listarPendentes(CheckIn[] checkIns) {
        System.out.println("\n--- Check-ins pendentes ---");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (CheckIn c : checkIns) {
            if (c != null && !c.isAprovado()) {
                Ticket t = c.getTicket();
                Voo v = t.getVoo();

                String dataFormatada = v.getData() != null ? v.getData().format(formato) : "Data indisponível";

                System.out.println("Número: " + c.getId()
                        + " | Código: " + t.getCodigo()
                        + " | Passageiro: " + t.getPassageiro().getNome()
                        + " | Voo: " + v.getOrigem() + " -> " + v.getDestino()
                        + " | Data/Hora: " + dataFormatada);
            }
        }
    }

    public static void aprovarCheckIn(CheckIn[] checkIns, BoardingPass[] boardingPasses, Scanner scan) {
        // 1. Verifica se existem check-ins pendentes
        boolean temPendentes = false;
        for (CheckIn c : checkIns) {
            if (c != null && !c.isAprovado()) {
                temPendentes = true;
                break;
            }
        }

        if (!temPendentes) {
            System.out.println("Nao ha check-ins pendentes para aprovar.");
            return; // sai do método
        }

        // 2. Lista os check-ins pendentes
        listarPendentes(checkIns);

        // 3. Solicita o ID do check-in a aprovar
        System.out.print("Digite o ID do check-in para aprovar: ");
        int id = scan.nextInt();
        scan.nextLine();

        // 4. Procura o check-in e aprova
        for (CheckIn c : checkIns) {
            if (c != null && c.getId() == id && !c.isAprovado()) {
                c.setAprovado(true);
                c.setDataModificacao(LocalDateTime.now());

                Ticket t = c.getTicket();
                Voo v = t.getVoo();

                // 5. Procura o assento já ocupado pelo passageiro
                String codigoAssento = null;
                for (VooAssentos a : v.getVooAssentos()) {
                    if (a.getPassageiro() == t.getPassageiro()) {
                        codigoAssento = a.getCodigoAssento();
                        break;
                    }
                }

                if (codigoAssento == null) {
                    System.out.println("Erro: assento não encontrado para o passageiro neste voo.");
                    return;
                }

                // 6. Cria boarding pass usando o código do assento existente
                for (int j = 0; j < boardingPasses.length; j++) {
                    if (boardingPasses[j] == null) {
                        boardingPasses[j] = new BoardingPass(j + 1, t.getPassageiro(), v, codigoAssento);
                        System.out.println("Check-in aprovado! Boarding pass emitido para " + t.getPassageiro().getNome() + " | Assento: " + codigoAssento);
                        return;
                    }
                }
            }
        }

        System.out.println("Check-in nao encontrado ou ja aprovado.");
    }

}
