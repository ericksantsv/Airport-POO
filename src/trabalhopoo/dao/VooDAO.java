package trabalhopoo.dao;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Voo;
import trabalhopoo.model.CompanhiaAerea;

public class VooDAO {

    public static void cadastrar(Voo[] voos, CompanhiaAerea[] companhias, Scanner scan) {
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] == null) {
                System.out.print("Origem: ");
                String origem = scan.nextLine();

                System.out.print("Destino: ");
                String destino = scan.nextLine();

                System.out.print("Data (AAAA-MM-DD): ");
                LocalDate data = LocalDate.parse(scan.nextLine());

                System.out.print("Duração (horas): ");
                double dur = scan.nextDouble();
                scan.nextLine();

                System.out.println("Escolha a companhia aérea:");
                for (int j = 0; j < companhias.length; j++) {
                    if (companhias[j] != null) {
                        System.out.println(companhias[j].getId() + " - " + companhias[j].getNome());
                    }
                }

                CompanhiaAerea companhiaEscolhida = null;
                while (companhiaEscolhida == null) {
                    System.out.print("Digite o ID da companhia: ");
                    int idEscolhido = scan.nextInt();
                    scan.nextLine();

                    // Procura a companhia com o ID digitado
                    for (CompanhiaAerea comp : companhias) {
                        if (comp != null && comp.getId() == idEscolhido) {
                            companhiaEscolhida = comp;
                            break;
                        }
                    }

                    if (companhiaEscolhida == null) {
                        System.out.println("ID inválido. Tente novamente.\n");
                    }
                }

                System.out.print("Capacidade: ");
                int cap = scan.nextInt();
                scan.nextLine();

                voos[i] = new Voo(i + 1, origem, destino, data, dur, companhiaEscolhida, cap, "Programado", LocalDate.now(), LocalDate.now());

                System.out.println("\nVoo cadastrado com sucesso!");
                break;
            }
        }
    }

    public static void editar(Voo[] voos, Scanner scan) {
        System.out.print("Informe o ID do voo para editar: ");
        int idEdit = scan.nextInt();
        scan.nextLine();

        Voo vEdit = null;
        for (Voo v : voos) {
            if (v != null && v.getId() == idEdit) {
                vEdit = v;
                break;
            }
        }

        if (vEdit != null) {
            System.out.print("Nova origem: ");
            vEdit.setOrigem(scan.nextLine());

            System.out.print("Novo destino: ");
            vEdit.setDestino(scan.nextLine());

            vEdit.setDataModificacao(LocalDate.now());

            System.out.println("Voo atualizado!");
        } else {
            System.out.println("Voo não encontrado!");
        }
    }

    public static void deletar(Voo[] voos, Scanner scan) {
        System.out.print("Informe o ID do voo para deletar: ");
        int idDel = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < voos.length; i++) {
            if (voos[i] != null && voos[i].getId() == idDel) {
                voos[i] = null;
                System.out.println("Voo deletado!");
                break;
            }
        }
    }

    public static void listar(Voo[] voos) {
        System.out.println("\n ===== Voos =====");
        for (Voo v : voos) {
            if (v != null) {
                System.out.println("\n| Numero: " + v.getId()
                        + "\n| Origem: " + v.getOrigem()
                        + "\n| Destino: " + v.getDestino()
                        + "\n| Data: " + v.getData()
                        + "\n| Duracao: " + v.getDuracao()
                        + "\n| Companhia: " + v.getCompanhiaAerea().getNome()
                        + "\n| Capacidade: " + v.getCapacidade()
                        + "\n| Estado: " + v.getEstado()
                );
            }
        }
    }

    public static void listarOrigens(Voo[] voos) {
        System.out.println("\nOrigens disponíveis:");
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] != null) {
                boolean repetido = false;
                // Verifica se já foi exibida
                for (int j = 0; j < i; j++) {
                    if (voos[j] != null && voos[j].getOrigem().equalsIgnoreCase(voos[i].getOrigem())) {
                        repetido = true;
                        break;
                    }
                }
                if (!repetido) {
                    System.out.println("- " + voos[i].getOrigem());
                }
            }
        }
    }

    public static void listarDestinos(Voo[] voos) {
        System.out.println("\nDestinos disponíveis:");
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] != null) {
                boolean repetido = false;
                for (int j = 0; j < i; j++) {
                    if (voos[j] != null && voos[j].getDestino().equalsIgnoreCase(voos[i].getDestino())) {
                        repetido = true;
                        break;
                    }
                }
                if (!repetido) {
                    System.out.println("- " + voos[i].getDestino());
                }
            }
        }
    }

    public static void buscarVoos(Voo[] voos, Scanner scan) {
        listarOrigens(voos);
        System.out.print("\nDigite a origem desejada: ");
        String origem = scan.nextLine();

        listarDestinos(voos);
        System.out.print("\nDigite o destino desejado: ");
        String destino = scan.nextLine();

        System.out.println("\nVoos encontrados:");
        boolean achou = false;
        for (Voo v : voos) {
            if (v != null && v.getOrigem().equalsIgnoreCase(origem) && v.getDestino().equalsIgnoreCase(destino)) {
                System.out.println("\n| Numero: " + v.getId()
                        + "\n| Origem: " + v.getOrigem()
                        + "\n| Destino: " + v.getDestino()
                        + "\n| Data: " + v.getData()
                        + "\n| Duracao: " + v.getDuracao()
                        + "\n| Companhia: " + v.getCompanhiaAerea().getNome()
                        + "\n| Capacidade: " + v.getCapacidade()
                        + "\n| Estado: " + v.getEstado()
                );
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum voo encontrado para essa rota.");
        }
    }

    public static Voo escolherVoo(Voo[] voos, Scanner scan) {
        while (true) {
            listarOrigens(voos);
            System.out.print("\nDigite a origem desejada: ");
            String origem = scan.nextLine();

            listarDestinos(voos);
            System.out.print("\nDigite o destino desejado: ");
            String destino = scan.nextLine();

            // Cria um array temporário para armazenar os voos encontrados
            Voo[] encontrados = new Voo[voos.length];
            int count = 0;

            for (Voo v : voos) {
                if (v != null && v.getOrigem().equalsIgnoreCase(origem) && v.getDestino().equalsIgnoreCase(destino)) {
                    System.out.println(" ===== Voo encontrado ====="
                            + "\n| Numero: " + v.getId()
                            + "\n| Origem: " + v.getOrigem()
                            + "\n| Destino: " + v.getDestino()
                            + "\n| Data: " + v.getData()
                            + "\n| Duracao: " + v.getDuracao()
                            + "\n| Companhia: " + v.getCompanhiaAerea().getNome()
                            + "\n| Capacidade: " + v.getCapacidade()
                            + "\n| Estado: " + v.getEstado()
                    );
                    encontrados[count++] = v; // adiciona aos encontrados
                }
            }

            if (count == 0) {
                System.out.println("\nNenhum voo encontrado para essa rota. Tente novamente.\n");
            } else {
                System.out.print("\nDigite o numero do voo que deseja embarcar: ");
                int idEscolhido = scan.nextInt();
                scan.nextLine(); // limpar buffer

                for (int i = 0; i < count; i++) {
                    if (encontrados[i].getId() == idEscolhido) {
                        System.out.println("\n Passagem comprada com sucesso");
                        return encontrados[i];
                    }
                }

                System.out.println("ID inválido. Tente novamente.\n");
            }
        }
    }
}
