package trabalhopoo.dao;

import java.util.Scanner;
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

        System.out.println("Passageiro não encontrado!");
    }
}
