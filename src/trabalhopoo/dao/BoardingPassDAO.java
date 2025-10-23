package trabalhopoo.dao;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.DespachoBagagem;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;

public class BoardingPassDAO {

    public static void emitirBoardingPass(Passageiro[] passageiros, Voo[] voos, Scanner scan) {
        System.out.print("Digite o nome do passageiro: ");
        String nome = scan.nextLine();

        for (Passageiro p : passageiros) {
            if (p != null && p.getNome().equalsIgnoreCase(nome)) {
                System.out.println("\n===== BOARDING PASS =====");
                System.out.println("Passageiro: " + p.getNome());
                //System.out.println("Check-in: " + (p.isCheckIn() ? " Sim" : " Não"));
                // System.out.println("Bagagem: " + (p.isBagagemDespachada() ? "🧳 Despachada" : "? Não despachada"));
                System.out.println("=========================\n");
                return;
            }
        }

        System.out.println("Passageiro nao encontrado!");
    }

    // Método para listar todos os boarding passes
    public static void listarBoardingPasses(BoardingPass[] boardingPasses) {
        System.out.println("\n--- Lista de Boarding Passes ---");

        boolean temBP = false;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        if (boardingPasses == null || boardingPasses.length == 0) {
            System.out.println("Nenhum boarding pass emitido.");
            return;
        }

        for (BoardingPass bp : boardingPasses) {
            if (bp != null) {
                temBP = true;

                Passageiro p = bp.getPassageiro();
                Voo v = bp.getVoo();

                System.out.println("-------------------------------------------");
                System.out.println("ID do BP: " + bp.getId());
                System.out.println("Passageiro: " + (p != null ? p.getNome() : "Desconhecido"));
                System.out.println("Documento: " + (p != null ? p.getDocumento() : "N/A"));
                System.out.println("Voo: " + (v != null ? v.getOrigem() + " | " + v.getDestino() : "Sem voo associado"));
                System.out.println("Assento: " + bp.getAssento());
                System.out.println("Data de Emissao: "
                        + (bp.getDataEmissao() != null ? bp.getDataEmissao().format(formato) : "N/A"));
                System.out.println("Embarcado: " + (bp.isEmbarcado() ? "Sim" : "Nao"));
                System.out.println("-------------------------------------------");
            }
        }

        if (!temBP) {
            System.out.println("Nenhum boarding pass emitido.");
        }
    }

    // Método para listar apenas boarding passes de um passageiro específico
    public static void listarBoardingPasses(BoardingPass[] boardingPasses, Passageiro passageiro) {
        System.out.println("\n--- Seus Boarding Passes ---");

        boolean temBP = false;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        if (boardingPasses == null || boardingPasses.length == 0) {
            System.out.println("Nenhum boarding pass encontrado para voce.");
            return;
        }

        for (BoardingPass bp : boardingPasses) {
            if (bp != null && bp.getPassageiro() != null && bp.getPassageiro().equals(passageiro)) {
                temBP = true;
                Voo v = bp.getVoo();

                System.out.println("-------------------------------------------");
                System.out.println("ID do BP: " + bp.getId());
                System.out.println("Voo: " + (v != null ? v.getOrigem() + " → " + v.getDestino() : "Sem voo associado"));
                System.out.println("Assento: " + bp.getAssento());
                System.out.println("Data de Emissao: "
                        + (bp.getDataEmissao() != null ? bp.getDataEmissao().format(formato) : "N/A"));
                System.out.println("Embarcado: " + (bp.isEmbarcado() ? "Sim" : "Nao"));
                System.out.println("-------------------------------------------");
            }
        }

        if (!temBP) {
            System.out.println("Nenhum boarding pass encontrado para voce.");
        }
    }
    
    public static void embarcarNoVoo(Usuario usuario, CheckIn[] checkIns, DespachoBagagem[] bagagens, BoardingPass[] boardingPasses, Scanner scan) {
    Passageiro passageiro = usuario.getPassageiro();
    Ticket[] tickets = passageiro.getTicket();

    System.out.println("\n--- Tickets disponiveis para embarque ---");

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    boolean temDisponivel = false;

    for (Ticket t : tickets) {
        if (t != null) {
            Voo v = t.getVoo();
            v.atualizarEstadoAutomatico();

            // Verifica se o voo esta em embarque
            boolean vooEmbarque = v.getEstado().equalsIgnoreCase("Embarque");

            // Verifica check-in aprovado
            boolean checkInAprovado = false;
            for (CheckIn c : checkIns) {
                if (c != null && c.getTicket() == t && c.isAprovado()) {
                    checkInAprovado = true;
                    break;
                }
            }

            // Verifica boarding pass emitido
            boolean boardingPassEmitido = false;
            BoardingPass bpTicket = null;
            for (BoardingPass bp : boardingPasses) {
                if (bp != null && bp.getVoo() == v && bp.getPassageiro() == passageiro) {
                    boardingPassEmitido = true;
                    bpTicket = bp;
                    break;
                }
            }

            // Verifica bagagem despachada
            boolean bagagemDespachada = false;
            for (DespachoBagagem d : bagagens) {
                if (d != null && d.getTicket() == t) {
                    bagagemDespachada = true;
                    break;
                }
            }

            // Exibe somente se o passageiro pode embarcar
            if (vooEmbarque && checkInAprovado && boardingPassEmitido && bagagemDespachada) {
                System.out.println("\n--------------------------------------");
                System.out.println("| Numero Ticket: " + t.getId());
                System.out.println("| Codigo: " + t.getCodigo());
                System.out.println("| Origem: " + v.getOrigem());
                System.out.println("| Destino: " + v.getDestino());
                System.out.println("| Data/Hora: " + v.getData().format(formato));
                System.out.println("| Companhia: " + v.getCompanhiaAerea().getNome());
                System.out.println("| Estado do voo: " + v.getEstado());
                System.out.println("| Check-in: Aprovado");
                System.out.println("| Boarding pass: Emitido");
                System.out.println("| Bagagem: Despachada");
                temDisponivel = true;
            }
        }
    }

    if (!temDisponivel) {
        System.out.println("\nNenhum ticket elegivel para embarque neste momento.");
        return;
    }

    // Escolha do ticket
    System.out.print("\nDigite o numero do ticket que deseja embarcar: ");
    int idTicket = scan.nextInt();
    scan.nextLine();

    for (Ticket t : tickets) {
        if (t != null && t.getId() == idTicket) {
            Voo v = t.getVoo();

            // Busca boarding pass correspondente
            for (BoardingPass bp : boardingPasses) {
                if (bp != null && bp.getVoo() == v && bp.getPassageiro() == passageiro) {
                    bp.setEmbarcado(true);
                    System.out.println("\nEmbarque realizado com sucesso! Boa viagem!");
                    return;
                }
            }

            System.out.println("Erro: boarding pass nao encontrado.");
            return;
        }
    }

    System.out.println("Ticket invalido.");
}

}
