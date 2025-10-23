package trabalhopoo.dao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.Voo;
import trabalhopoo.model.CompanhiaAerea;

public class VooDAO {

    //Incializa Vetores
    public static Voo[] inicializarVoos(CompanhiaAerea[] companhiaAerea) {
        Voo[] voos = new Voo[50];
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        voos[0] = new Voo(1, "Uberaba", "Sao Paulo", LocalDateTime.parse("2025-10-10 08:30", formato), LocalTime.of(2, 30), companhiaAerea[0], 5, "Programado", LocalDateTime.now(), LocalDateTime.now());
        voos[1] = new Voo(2, "Uberaba", "Rio de Janeiro", LocalDateTime.parse("2025-10-11 14:00", formato), LocalTime.of(2, 30), companhiaAerea[1], 3, "Programado", LocalDateTime.now(), LocalDateTime.now());
        voos[2] = new Voo(3, "Uberaba", "Belo Horizonte", LocalDateTime.parse("2025-10-12 09:15", formato), LocalTime.of(2, 30), companhiaAerea[2], 4, "Programado", LocalDateTime.now(), LocalDateTime.now());
        voos[3] = new Voo(4, "Uberaba", "Brasilia", LocalDateTime.parse("2025-10-13 18:00", formato), LocalTime.of(2, 30), companhiaAerea[3], 6, "Programado", LocalDateTime.now(), LocalDateTime.now());
        voos[4] = new Voo(5, "Uberaba", "Curitiba", LocalDateTime.parse("2025-10-14 06:45", formato), LocalTime.of(2, 30), companhiaAerea[4], 10, "Programado", LocalDateTime.now(), LocalDateTime.now());
        voos[5] = new Voo(6, "Roraima", "Paraguai", LocalDateTime.parse("2025-10-23 11:00", formato), LocalTime.of(0, 4), companhiaAerea[2], 5, "Programado", LocalDateTime.now(), LocalDateTime.now());

        return voos;
    }

    public static void cadastrar(Voo[] voos, CompanhiaAerea[] companhias, Scanner scan) {
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] == null) {

                System.out.print("Origem: ");
                String origem = scan.nextLine();

                System.out.print("Destino: ");
                String destino = scan.nextLine();

                // Data e hora completas do voo
                System.out.print("Data e hora do voo (AAAA-MM-DD HH:MM): ");
                String dataHoraStr = scan.nextLine();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formato);

                // Duração do voo em horas e minutos
                System.out.print("Duracao (HH:MM): ");
                String duracaoStr = scan.nextLine();
                LocalTime duracao = LocalTime.parse(duracaoStr, DateTimeFormatter.ofPattern("HH:mm"));

                System.out.println("\nEscolha a companhia aérea:");
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

                    for (CompanhiaAerea comp : companhias) {
                        if (comp != null && comp.getId() == idEscolhido) {
                            companhiaEscolhida = comp;
                            break;
                        }
                    }

                    if (companhiaEscolhida == null) {
                        System.out.println("ID invalido. Tente novamente.\n");
                    }
                }

                System.out.print("Capacidade: ");
                int capacidade = scan.nextInt();
                scan.nextLine();

                voos[i] = new Voo(i + 1, origem, destino, dataHora, duracao, companhiaEscolhida, capacidade, "Programado", LocalDateTime.now(), LocalDateTime.now());

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

            vEdit.setDataModificacao(LocalDateTime.now());

            System.out.println("Voo atualizado!");
        } else {
            System.out.println("Voo nao encontrado!");
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
        System.out.println("\n===== Lista de Voos =====");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Voo v : voos) {
            if (v != null) {
                // Atualiza o estado automaticamente antes de exibir
                v.atualizarEstadoAutomatico();

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

                System.out.println("\n--------------------------------------");
                System.out.println("| Numero: " + v.getId());
                System.out.println("| Origem: " + v.getOrigem());
                System.out.println("| Destino: " + v.getDestino());
                System.out.println("| Data/Hora: " + v.getData().format(formato));
                System.out.println("| Duracao: " + duracaoFormatada);
                System.out.println("| Companhia: " + v.getCompanhiaAerea().getNome());
                System.out.println("| Capacidade: " + v.getCapacidade());
                System.out.println("| Estado: " + v.getEstado());
            }
        }
    }

    public static void listarOrigens(Voo[] voos) {
        System.out.println("\nOrigens disponiveis:");
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
        System.out.println("\nDestinos disponiveis:");
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
        String origem = scan.nextLine().trim();

        listarDestinos(voos);
        System.out.print("\nDigite o destino desejado: ");
        String destino = scan.nextLine().trim();

        System.out.println("\n===== Voos encontrados =====");
        boolean achou = false;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Voo v : voos) {
            if (v != null
                    && v.getOrigem().equalsIgnoreCase(origem)
                    && v.getDestino().equalsIgnoreCase(destino)) {

                // Atualiza estado automaticamente
                v.atualizarEstadoAutomatico();

                // Formatar duração (LocalTime → "2h30min")
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

                System.out.println("\n--------------------------------------");
                System.out.println("| Numero: " + v.getId());
                System.out.println("| Origem: " + v.getOrigem());
                System.out.println("| Destino: " + v.getDestino());
                System.out.println("| Data/Hora: " + v.getData().format(formato));
                System.out.println("| Duracao: " + duracaoFormatada);
                System.out.println("| Companhia: " + v.getCompanhiaAerea().getNome());
                System.out.println("| Capacidade: " + v.getCapacidade());
                System.out.println("| Estado: " + v.getEstado());
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum voo encontrado para essa rota.");
        }
    }

    public static Voo escolherVoo(Voo[] voos, Scanner scan) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatoDuracao = DateTimeFormatter.ofPattern("HH:mm");

        while (true) {
            listarOrigens(voos);
            System.out.print("\nDigite a origem desejada: ");
            String origem = scan.nextLine().trim();

            listarDestinos(voos);
            System.out.print("\nDigite o destino desejado: ");
            String destino = scan.nextLine().trim();

            Voo[] encontrados = new Voo[voos.length];
            int count = 0;

            for (Voo v : voos) {
                if (v != null) {
                    // Atualiza o estado do voo automaticamente
                    v.atualizarEstadoAutomatico();

                    String estado = v.getEstado();

                    // Verifica se o voo ainda está disponível
                    boolean vooDisponivel = !estado.equalsIgnoreCase("Atrasado")
                            && !estado.equalsIgnoreCase("Cancelado") && !estado.equalsIgnoreCase("Concluido");

                    if (vooDisponivel
                            && v.getOrigem().equalsIgnoreCase(origem)
                            && v.getDestino().equalsIgnoreCase(destino)) {

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

                        System.out.println("\n===== Voo encontrado =====");
                        System.out.println("| Numero: " + v.getId());
                        System.out.println("| Origem: " + v.getOrigem());
                        System.out.println("| Destino: " + v.getDestino());
                        System.out.println("| Data/Hora: " + v.getData().format(formatoData));
                        System.out.println("| Duracao: " + duracaoFormatada);
                        System.out.println("| Companhia: " + v.getCompanhiaAerea().getNome());
                        System.out.println("| Capacidade: " + v.getCapacidade());
                        System.out.println("| Estado: " + v.getEstado());

                        encontrados[count] = v;
                        count++;
                    }
                }
            }

            if (count == 0) {
                System.out.println("\nNenhum voo disponivel para essa rota. Tente novamente.\n");
            } else {
                System.out.print("\nDigite o numero do voo que deseja embarcar: ");
                int idEscolhido = scan.nextInt();
                scan.nextLine(); // limpa o buffer

                boolean encontrado = false;
                for (int i = 0; i < count; i++) {
                    if (encontrados[i] != null && encontrados[i].getId() == idEscolhido) {
                        System.out.println("\nPassagem comprada com sucesso!");
                        encontrado = true;
                        return encontrados[i];
                    }
                }

                if (!encontrado) {
                    System.out.println("ID invalido. Tente novamente.\n");
                }
            }
        }
    }

    public static void cancelarVoo(Voo[] voos, BoardingPass[] boardingPasses, Scanner scan) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Atualiza estado de todos os voos antes de listar
        for (Voo v : voos) {
            if (v != null) {
                v.atualizarEstadoAutomatico();
            }
        }

        System.out.println("\n--- Voos disponiveis para cancelamento ---");
        boolean temVoo = false;
        for (Voo v : voos) {
            if (v != null) {
                String estado = v.getEstado();
                if (estado.equalsIgnoreCase("Programado") || estado.equalsIgnoreCase("Embarque")) {
                    System.out.println("ID: " + v.getId()
                            + " | Origem: " + v.getOrigem()
                            + " | Destino: " + v.getDestino()
                            + " | Data/Hora: " + v.getData().format(formato)
                            + " | Estado: " + v.getEstado());
                    temVoo = true;
                }
            }
        }

        if (!temVoo) {
            System.out.println("Nenhum voo disponivel para cancelamento.");
            return;
        }

        // Solicita ID do voo para cancelar
        System.out.print("Digite o ID do voo que deseja cancelar: ");
        int idVoo = scan.nextInt();
        scan.nextLine(); // limpar buffer

        // Procura o voo escolhido
        Voo vooCancelar = null;
        for (Voo v : voos) {
            if (v != null && v.getId() == idVoo) {
                vooCancelar = v;
                break;
            }
        }

        if (vooCancelar == null
                || (!vooCancelar.getEstado().equalsIgnoreCase("Programado")
                && !vooCancelar.getEstado().equalsIgnoreCase("Embarque"))) {
            System.out.println("Voo invalido ou nao pode ser cancelado.");
            return;
        }

        // Cancela o voo
        vooCancelar.setEstado("Cancelado");
        System.out.println("Voo " + vooCancelar.getId() + " cancelado com sucesso.");

        // Atualiza boarding passes relacionados
        if (boardingPasses != null) {
            for (BoardingPass bp : boardingPasses) {
                if (bp != null && bp.getVoo() == vooCancelar) {
                    bp.setEmbarcado(false);
                }
            }
        }
    }

}
