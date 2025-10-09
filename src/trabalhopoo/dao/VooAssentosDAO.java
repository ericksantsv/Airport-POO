package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.*;

public class VooAssentosDAO {

    public static void reservarAssento(Voo[] voos, Passageiro[] passageiros, Scanner scan) {
        System.out.print("ID do voo: ");
        int idVoo = scan.nextInt();
        scan.nextLine();

        Voo vooSelecionado = null;
        for (Voo voo : voos) {
            if (voo != null && voo.getId() == idVoo) {
                vooSelecionado = voo;
                break;
            }
        }

        if (vooSelecionado == null) {
            System.out.println("Voo não encontrado!");
            return;
        }

        VooAssentos[] assentos = vooSelecionado.getVooAssentos();

        System.out.println("\nAssentos disponíveis:");
        for (int i = 0; i < assentos.length; i++) {
            if (assentos[i].getPassageiro() == null) {
                System.out.println(i + " - " + assentos[i].getCodigoAssento() + " [LIVRE]");
            } else {
                System.out.println(i + " - " + assentos[i].getCodigoAssento() + " [OCUPADO]");
            }
        }

        System.out.print("Número do assento a reservar: ");
        int numAssento = scan.nextInt();
        scan.nextLine();

        if (numAssento < 0 || numAssento >= assentos.length) {
            System.out.println("Assento inválido!");
            return;
        }

        if (assentos[numAssento].getPassageiro() != null) {
            System.out.println("Assento já ocupado!");
            return;
        }

        System.out.print("ID do passageiro: ");
        int idPass = scan.nextInt();
        scan.nextLine();

        Passageiro passageiroSelecionado = null;
        for (Passageiro pass : passageiros) {
            if (pass != null && pass.getId() == idPass) {
                passageiroSelecionado = pass;
                break;
            }
        }

        if (passageiroSelecionado == null) {
            System.out.println("Passageiro não encontrado!");
            return;
        }

        assentos[numAssento].setPassageiro(passageiroSelecionado);
        assentos[numAssento].setDataModificacao(LocalDate.now());

        System.out.println("Assento reservado com sucesso!");
    }

    public static void reservarAssentoSemLogin(Voo vooSelecionado, Passageiro passageiro, Scanner scan) {
        if (vooSelecionado == null || passageiro == null) {
            System.out.println("Voo ou passageiro inválido!");
            return;
        }

        VooAssentos[] assentos = vooSelecionado.getVooAssentos();

        System.out.println("\nAssentos disponíveis:");
        for (int i = 0; i < assentos.length; i++) {
            if (assentos[i].getPassageiro() == null) {
                System.out.println(i + " - " + assentos[i].getCodigoAssento() + " [LIVRE]");
            } else {
                System.out.println(i + " - " + assentos[i].getCodigoAssento() + " [OCUPADO]");
            }
        }

        System.out.print("Número do assento a reservar: ");
        int numAssento = scan.nextInt();
        scan.nextLine();

        if (numAssento < 0 || numAssento >= assentos.length) {
            System.out.println("Assento inválido!");
            return;
        }

        if (assentos[numAssento].getPassageiro() != null) {
            System.out.println("Assento já ocupado!");
            return;
        }

        assentos[numAssento].setPassageiro(passageiro);
        assentos[numAssento].setDataModificacao(LocalDate.now());

        System.out.println("Assento " + assentos[numAssento].getCodigoAssento() + " reservado com sucesso para " + passageiro.getNome() + "!");
    }

    public static void listarAssentos(Voo[] voos, Scanner scan) {
        System.out.print("ID do voo: ");
        int idVoo = scan.nextInt();
        scan.nextLine();

        Voo vooSelecionado = null;
        for (Voo voo : voos) {
            if (voo != null && voo.getId() == idVoo) {
                vooSelecionado = voo;
                break;
            }
        }

        if (vooSelecionado == null) {
            System.out.println("Voo não encontrado!");
            return;
        }

        VooAssentos[] assentos = vooSelecionado.getVooAssentos();

        System.out.println("\n--- Lista de Assentos do Voo ---");

        for (VooAssentos assento : assentos) {
            if (assento != null) {
                String status = (assento.getPassageiro() == null)
                        ? "LIVRE"
                        : "OCUPADO por " + assento.getPassageiro().getNome();

                System.out.println("ID: " + assento.getId()
                        + " | Código: " + assento.getCodigoAssento()
                        + " | Status: " + status);
            }
        }
    }

    public static void deletarAssento(Voo[] voos, Scanner scan) {
        System.out.print("ID do voo: ");
        int idVoo = scan.nextInt();
        scan.nextLine();

        Voo vooSelecionado = null;
        for (Voo voo : voos) {
            if (voo != null && voo.getId() == idVoo) {
                vooSelecionado = voo;
                break;
            }
        }

        if (vooSelecionado == null) {
            System.out.println("Voo não encontrado!");
            return;
        }

        VooAssentos[] assentos = vooSelecionado.getVooAssentos();

        System.out.print("ID do assento para desocupar: ");
        int idAssento = scan.nextInt();
        scan.nextLine();

        for (VooAssentos a : assentos) {
            if (a != null && a.getId() == idAssento) {
                if (a.getPassageiro() == null) {
                    System.out.println("Esse assento já está vazio.");
                } else {
                    a.setPassageiro(null);
                    a.setDataModificacao(LocalDate.now());
                    System.out.println("Assento desocupado com sucesso!");
                }
                return;
            }
        }

        System.out.println("Assento não encontrado.");
    }

}
