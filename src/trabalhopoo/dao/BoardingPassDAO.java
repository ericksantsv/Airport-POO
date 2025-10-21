package trabalhopoo.dao;

import java.util.Scanner;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.Passageiro;
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
        for (BoardingPass bp : boardingPasses) {
            if (bp != null) {
                temBP = true;
                Passageiro p = bp.getPassageiro();
                Voo v = bp.getVoo();

                System.out.println("-------------------------------------------");
                System.out.println("ID do BP: " + bp.getId());
                System.out.println("Passageiro: " + p.getNome());
                System.out.println("Documento: " + p.getDocumento());
                System.out.println("Voo: " + v.getOrigem() + " -> " + v.getDestino());
                System.out.println("Assento: " + bp.getAssento());
                System.out.println("Data de emissao: " + bp.getDataEmissao());
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
        for (BoardingPass bp : boardingPasses) {
            if (bp != null && bp.getPassageiro() == passageiro) {
                temBP = true;
                Voo v = bp.getVoo();

                System.out.println("-------------------------------------------");
                System.out.println("ID do BP: " + bp.getId());
                System.out.println("Voo: " + v.getOrigem() + " → " + v.getDestino());
                System.out.println("Assento: " + bp.getAssento());
                System.out.println("Data de emissao: " + bp.getDataEmissao());
                System.out.println("Embarcado: " + (bp.isEmbarcado() ? "Sim" : "Nao"));
                System.out.println("-------------------------------------------");
            }
        }

        if (!temBP) {
            System.out.println("Nenhum boarding pass encontrado para voce.");
        }
    }
}
