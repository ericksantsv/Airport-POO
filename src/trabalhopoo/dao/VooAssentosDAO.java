package trabalhopoo.dao;

import java.time.LocalDateTime;
import java.util.Scanner;
import trabalhopoo.model.*;

public class VooAssentosDAO {

    public static void reservarAssento(Voo[] voos, Passageiro[] passageiros, Scanner scan) {
        VooDAO.listar(voos);
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
            System.out.println("Voo nao encontrado!");
            return;
        }

        VooAssentos[] assentos = vooSelecionado.getVooAssentos();

        System.out.println("\nAssentos disponiveis:");
        for (int i = 0; i < assentos.length; i++) {
            if (assentos[i].getPassageiro() == null) {
                System.out.println((i + 1) + " - " + assentos[i].getCodigoAssento() + " [LIVRE]");
            } else {
                System.out.println((i + 1) + " - " + assentos[i].getCodigoAssento() + " [OCUPADO]");
            }
        }

        System.out.print("Numero do assento a reservar: ");
        int numAssento = scan.nextInt() - 1;
        scan.nextLine();

        if (numAssento < 0 || numAssento >= assentos.length) {
            System.out.println("Assento invalido!");
            return;
        }

        if (assentos[numAssento].getPassageiro() != null) {
            System.out.println("Assento ja ocupado!");
            return;
        }

        PassageiroDAO.listar(passageiros);
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
            System.out.println("Passageiro nao encontrado!");
            return;
        }

        assentos[numAssento].setPassageiro(passageiroSelecionado);
        assentos[numAssento].setDataModificacao(LocalDateTime.now());

        System.out.println("Assento reservado com sucesso!");
    }

    public static void reservarAssentoSemLogin(Voo vooSelecionado, Passageiro passageiro, Scanner scan) {
        if (vooSelecionado == null || passageiro == null) {
            System.out.println("Voo ou passageiro invalido!");
            return;
        }

        VooAssentos[] assentos = vooSelecionado.getVooAssentos();
        boolean assento = true;
        while (assento) {
            System.out.println("\nAssentos disponiveis:");
            for (int i = 0; i < assentos.length; i++) {
                if (assentos[i].getPassageiro() == null) {
                    System.out.println((i + 1) + " - " + assentos[i].getCodigoAssento() + " [LIVRE]");
                } else {
                    System.out.println((i + 1) + " - " + assentos[i].getCodigoAssento() + " [OCUPADO]");
                }
            }

            System.out.print("Numero do assento a reservar: ");
            int numAssento = scan.nextInt() - 1;
            scan.nextLine();

            if (numAssento < 0 || numAssento >= assentos.length) {
                System.out.println("Assento invalido!, escolha novamente.");
            } else if (assentos[numAssento].getPassageiro() != null) {
                System.out.println("Assento ja ocupado! Escolha novamente.");
            } else {

                assentos[numAssento].setPassageiro(passageiro);
                assentos[numAssento].setDataModificacao(LocalDateTime.now());

                System.out.println("Assento " + assentos[numAssento].getCodigoAssento() + " reservado com sucesso para " + passageiro.getNome() + "!");
                assento = false;
            }
        }
    }

    public static void listarAssentos(Voo[] voos, BoardingPass[] boardingPasses, Scanner scan) {
        VooDAO.listar(voos);
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
            System.out.println("Voo nao encontrado!");
            return;
        }

        VooAssentos[] assentos = vooSelecionado.getVooAssentos();

        System.out.println("\n--- Lista de Assentos do Voo ---");

        for (VooAssentos assento : assentos) {
            if (assento != null) {
                String status;

                if (assento.getPassageiro() == null) {
                    status = "LIVRE";
                } else {
                    // Procurar boarding pass do passageiro para esse voo
                    BoardingPass bpDoPassageiro = null;
                    for (BoardingPass bp : boardingPasses) {
                        if (bp != null && bp.getPassageiro() == assento.getPassageiro()
                                && bp.getVoo() == vooSelecionado) {
                            bpDoPassageiro = bp;
                            break;
                        }
                    }

                    String embarqueStatus = (bpDoPassageiro != null && bpDoPassageiro.isEmbarcado())
                            ? "embarcado"
                            : "nao embarcado";

                    status = "OCUPADO por " + assento.getPassageiro().getNome() + " - " + embarqueStatus;
                }

                System.out.println("ID: " + assento.getId()
                        + " | Codigo: " + assento.getCodigoAssento()
                        + " | Status: " + status);
            }
        }
    }

    public static void deletarAssento(Voo[] voos, Scanner scan) {
        VooDAO.listar(voos);
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
            System.out.println("Voo nao encontrado!");
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
                        + " | Codigo: " + assento.getCodigoAssento()
                        + " | Status: " + status);
            }
        }

        System.out.print("ID do assento para desocupar: ");
        int idAssento = scan.nextInt();
        scan.nextLine();

        for (VooAssentos a : assentos) {
            if (a != null && a.getId() == idAssento) {
                if (a.getPassageiro() == null) {
                    System.out.println("Esse assento ja esta vazio.");
                } else {
                    a.setPassageiro(null);
                    a.setDataModificacao(LocalDateTime.now());
                    System.out.println("Assento desocupado com sucesso!");
                }
                return;
            }
        }

        System.out.println("Assento nao encontrado.");
    }

}
