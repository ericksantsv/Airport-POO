package trabalhopoo;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Aeroporto;
import trabalhopoo.model.CompanhiaAerea;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Usuario;
import trabalhopoo.model.Voo;

public class TrabalhoPOO {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Passageiro[] passageiros = new Passageiro[10];
        CompanhiaAerea[] companhiaAerea = new CompanhiaAerea[10];
        Voo[] voos = new Voo[50];
        Usuario[] usuarios = new Usuario[10];

        // --- Companhias Pré-criadas ---
        companhiaAerea[0] = new CompanhiaAerea("AzulRio", "AZR", LocalDate.now(), LocalDate.now());
        companhiaAerea[1] = new CompanhiaAerea("LatamAir", "LTA", LocalDate.now(), LocalDate.now());
        companhiaAerea[2] = new CompanhiaAerea("SolAereo", "SLA", LocalDate.now(), LocalDate.now());
        companhiaAerea[3] = new CompanhiaAerea("VentoLeste", "VLE", LocalDate.now(), LocalDate.now());
        companhiaAerea[4] = new CompanhiaAerea("NorteSky", "NSK", LocalDate.now(), LocalDate.now());

        // --- Voos Pré-criados ---
        voos[0] = new Voo(1, "Uberaba", "São Paulo", LocalDate.parse("2025-10-10"), 2.5, companhiaAerea[0], 5, "Programado", LocalDate.now(), LocalDate.now());
        voos[1] = new Voo(2, "Uberaba", "Rio de Janeiro", LocalDate.parse("2025-10-11"), 3.0, companhiaAerea[1], 3, "Programado", LocalDate.now(), LocalDate.now());
        voos[2] = new Voo(3, "Uberaba", "Belo Horizonte", LocalDate.parse("2025-10-12"), 1.5, companhiaAerea[2], 4, "Programado", LocalDate.now(), LocalDate.now());
        voos[3] = new Voo(4, "Uberaba", "Brasília", LocalDate.parse("2025-10-13"), 4.0, companhiaAerea[3], 6, "Programado", LocalDate.now(), LocalDate.now());
        voos[4] = new Voo(5, "Uberaba", "Curitiba", LocalDate.parse("2025-10-14"), 3.5, companhiaAerea[4], 5, "Programado", LocalDate.now(), LocalDate.now());

        // --- Usuários Pré-criados ---
        usuarios[0] = new Usuario("goncalves", "goncalves");
        usuarios[1] = new Usuario("erick", "erick");
        usuarios[2] = new Usuario("nico", "nico");
        usuarios[3] = new Usuario("bruna", "bruna");
        usuarios[4] = new Usuario("dudu", "dudu");

        boolean menu = true;
        while (menu) {
            System.out.println("\n\n===== Menu Principal =====");
            System.out.println("1 - Painel de voo");
            System.out.println("2 - Administração");
            System.out.println("3 - Compra de passagem");
            System.out.println("4 - Gestão de passagem");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            int opc = scan.nextInt();
            scan.nextLine();

            switch (opc) {
                case 1:
                    Voo.exibirVoos(voos);
                    break;
                case 2:
                    Usuario admin = Usuario.loginAdmin(scan, usuarios);
                    if (admin != null) {
                        Usuario.menuAdmin(scan, passageiros, voos, companhiaAerea);
                    } else {
                        System.out.println("Login ou senha incorretos!");
                    }
                    break;
                case 3:
                    comprarPassagem(scan, passageiros, voos);
                    break;
                case 4:
                    System.out.println("Funcionalidade de gestão de passagem ainda não implementada.");
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }


   
    // ---------------- Compra de passagem ----------------
    private static void comprarPassagem(Scanner scan, Passageiro[] passageiros, Voo[] voos) {
        System.out.println("\n--- Compra de Passagem ---");
        System.out.print("Digite seu nome: "); String nome = scan.nextLine();
        System.out.print("Digite seu documento: "); String doc = scan.nextLine();

        // Procura passageiro
        Passageiro p = null;
        for (Passageiro pass : passageiros) {
            if (pass != null && pass.getDocumento().equals(doc)) { p = pass; break; }
        }

        if (p == null) {
            System.out.println("Passageiro não encontrado. Cadastrando novo...");
            for (int i = 0; i < passageiros.length; i++) {
                if (passageiros[i] == null) {
                    p = new Passageiro(i + 1, nome, LocalDate.MIN, doc, LocalDate.MIN, LocalDate.MIN);
                    passageiros[i] = p;
                    break;
                }
            }
        }

        // Lista voos disponíveis
        Voo.exibirVoos(voos);
        System.out.print("Escolha o ID do voo para comprar: ");
        int idVoo = scan.nextInt(); scan.nextLine();
        Voo vooEscolhido = null;
        for (Voo v : voos) {
            if (v != null && v.getId() == idVoo) { vooEscolhido = v; break; }
        }

        if (vooEscolhido != null) {
            System.out.println("Passagem comprada com sucesso para " + vooEscolhido.getDestino() + "!");
        } else {
            System.out.println("Voo inválido!");
        }
    }
}
