package trabalhopoo.dao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import trabalhopoo.model.BoardingPass;
import trabalhopoo.model.CheckIn;
import trabalhopoo.model.Voo;
import trabalhopoo.model.CompanhiaAerea;
import trabalhopoo.model.DespachoBagagem;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.VooAssentos;

public class VooDAO {

    //Incializa Vetores
    public static Voo[] inicializarVoos(CompanhiaAerea[] companhiaAerea) {
        Voo[] voos = new Voo[50];
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        voos[0] = new Voo(1, 500.00, "Uberaba", "Sao Paulo", LocalDateTime.parse("2025-10-10 08:30", formato), LocalTime.of(1, 45), companhiaAerea[0], 5, "Concluido", LocalDateTime.now(), LocalDateTime.now());
        voos[1] = new Voo(2, 670.00, "Sao Paulo", "Rio de Janeiro", LocalDateTime.parse("2025-10-09 14:00", formato), LocalTime.of(2, 10), companhiaAerea[1], 3, "Concluido", LocalDateTime.now(), LocalDateTime.now());
        voos[2] = new Voo(3, 800.00, "Rio de Janeiro", "Curitiba", LocalDateTime.parse("2025-10-08 09:15", formato), LocalTime.of(1, 25), companhiaAerea[2], 4, "Concluido", LocalDateTime.now(), LocalDateTime.now());
        voos[3] = new Voo(4, 400.00, "Curitiba", "Brasilia", LocalDateTime.parse("2025-10-07 18:00", formato), LocalTime.of(1, 35), companhiaAerea[3], 6, "Cancelado", LocalDateTime.now(), LocalDateTime.now());
        voos[4] = new Voo(5, 200.00, "Brasilia", "Uberaba", LocalDateTime.parse("2025-10-06 06:45", formato), LocalTime.of(1, 50), companhiaAerea[4], 10, "Concluido", LocalDateTime.now(), LocalDateTime.now());
        voos[5] = new Voo(6, 350.00, "Uberaba", "Curitiba", LocalDateTime.parse("2025-10-05 11:00", formato), LocalTime.of(2, 15), companhiaAerea[2], 5, "Cancelado", LocalDateTime.now(), LocalDateTime.now());
        voos[6] = new Voo(7, 250.00, "Sao Paulo", "Brasilia", LocalDateTime.parse("2025-10-04 07:20", formato), LocalTime.of(1, 30), companhiaAerea[0], 8, "Concluido", LocalDateTime.now(), LocalDateTime.now());
        voos[7] = new Voo(8, 100.00, "Brasilia", "Rio de Janeiro", LocalDateTime.parse("2025-10-03 09:10", formato), LocalTime.of(1, 10), companhiaAerea[1], 6, "Concluido", LocalDateTime.now(), LocalDateTime.now());
        voos[8] = new Voo(9, 200.00, "Curitiba", "Uberaba", LocalDateTime.parse("2025-10-02 15:45", formato), LocalTime.of(1, 55), companhiaAerea[3], 7, "Concluido", LocalDateTime.now(), LocalDateTime.now());
        voos[9] = new Voo(10, 500.00, "Rio de Janeiro", "Sao Paulo", LocalDateTime.parse("2025-10-01 21:00", formato), LocalTime.of(1, 40), companhiaAerea[4], 9, "Cancelado", LocalDateTime.now(), LocalDateTime.now());
        voos[10] = new Voo(11, 234.00, "Uberaba", "Brasilia", LocalDateTime.parse("2025-11-03 09:10", formato), LocalTime.of(1, 10), companhiaAerea[1], 6, "Programado", LocalDateTime.now(), LocalDateTime.now());
        voos[11] = new Voo(12, 800.00, "Sao Paulo", "Uberaba", LocalDateTime.parse("2025-11-02 15:45", formato), LocalTime.of(1, 55), companhiaAerea[3], 7, "Programado", LocalDateTime.now(), LocalDateTime.now());
        voos[12] = new Voo(13, 1000.00, "Brasilia", "Curitiba", LocalDateTime.parse("2025-10-25 12:11", formato), LocalTime.of(1, 40), companhiaAerea[4], 9, "Programado", LocalDateTime.now(), LocalDateTime.now());

        return voos;
    }

    public static void cadastrar(Voo[] voos, CompanhiaAerea[] companhias, Scanner scan) {
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] == null) {

                System.out.print("Origem: ");
                String origem = scan.nextLine();

                System.out.print("Destino: ");
                String destino = scan.nextLine();

                System.out.print("Informe o valor da viagem: R$");
                double valor = scan.nextInt();
                scan.nextLine(); // limpar buffer

                // Data e hora completas do voo
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String dataHoraStr;
                LocalDateTime dataHora = null;
                while (true) {
                    System.out.print("Data e hora do voo (AAAA-MM-DD HH:MM): ");
                    dataHoraStr = scan.nextLine().trim();
                    if (dataHoraStr.isEmpty()) {
                        System.out.println("Data e hora não podem ser vazios!");
                        continue;
                    }
                    try {
                        dataHora = LocalDateTime.parse(dataHoraStr, formato);
                        break;
                    } catch (Exception e) {
                        System.out.println("Formato inválido! Use o padrão AAAA-MM-DD HH:MM.");
                    }
                }

                // Duração do voo em horas e minutos
                String duracaoStr;
                LocalTime duracao = null;
                while (true) {
                    System.out.print("Duracao (HH:MM): ");
                    duracaoStr = scan.nextLine().trim();
                    if (duracaoStr.isEmpty()) {
                        System.out.println("Duracao nao pode ser vazia!");
                        continue;
                    }
                    try {
                        duracao = LocalTime.parse(duracaoStr, DateTimeFormatter.ofPattern("HH:mm"));
                        break;
                    } catch (Exception e) {
                        System.out.println("Formato de duracao inválido! Use HH:MM.");
                    }
                }

                System.out.println("\nEscolha a companhia aeea:");
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

                voos[i] = new Voo(i + 1, valor, origem, destino, dataHora, duracao, companhiaEscolhida, capacidade, "Programado", LocalDateTime.now(), LocalDateTime.now());

                System.out.println("\nVoo cadastrado com sucesso!");
                break;
            }
        }
    }

    public static void editarVoo(
            Voo[] voos,
            Ticket[] tickets,
            BoardingPass[] boardingPasses,
            Scanner scan) {

        VooDAO.listar(voos);

        System.out.print("Informe o ID do voo para editar: ");
        int idEdit = scan.nextInt();
        scan.nextLine();

        Voo vooEditar = null;

        // Encontrar o voo
        for (Voo v : voos) {
            if (v != null && v.getId() == idEdit) {
                vooEditar = v;
                break;
            }
        }

        if (vooEditar == null) {
            System.out.println("Voo nao encontrado!");
            return;
        }

        System.out.println("Editando voo: " + vooEditar.getId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        // Editar origem
        System.out.print("Origem (" + vooEditar.getOrigem() + "): ");
        String origem = scan.nextLine();
        if (!origem.isEmpty()) {
            vooEditar.setOrigem(origem);
        }

        // Editar destino
        System.out.print("Destino (" + vooEditar.getDestino() + "): ");
        String destino = scan.nextLine();
        if (!destino.isEmpty()) {
            vooEditar.setDestino(destino);
        }

        // Editar data
        System.out.print("Data (yyyy/MM/dd HH:mm) (" + vooEditar.getData().format(formatter) + "): ");
        String dataStr = scan.nextLine();
        if (!dataStr.isEmpty()) {
            vooEditar.setData(LocalDateTime.parse(dataStr, formatter));
        }

        // Editar duração
        System.out.print("Duração (HH:mm) (" + vooEditar.getDuracao() + "): ");
        String duracaoStr = scan.nextLine();
        if (!duracaoStr.isEmpty()) {
            String[] hm = duracaoStr.split(":");
            vooEditar.setDuracao(LocalTime.of(Integer.parseInt(hm[0]), Integer.parseInt(hm[1])));
        }

        // Editar valor
        System.out.print("Valor (" + vooEditar.getValor() + "): ");
        String valorStr = scan.nextLine();
        if (!valorStr.isEmpty()) {
            vooEditar.setValor(Double.parseDouble(valorStr));
        }

        // Atualizar data de modificação do voo
        vooEditar.setDataModificacao(LocalDateTime.now());

        System.out.println("Voo e todos os registros associados atualizados com sucesso!");
    }

    public static void deletar(Voo[] voos, Ticket[] tickets, VooAssentos[] assentos, CheckIn[] checkIns, DespachoBagagem[] bagagens, BoardingPass[] boardingPasses, Scanner scan) {

        VooDAO.listar(voos);
        System.out.print("Informe o ID do voo para deletar: ");
        int idDel = scan.nextInt();
        scan.nextLine();

        Voo vooDeletar = null;

        // Encontrar o voo
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] != null && voos[i].getId() == idDel) {
                vooDeletar = voos[i];
                break;
            }
        }

        if (vooDeletar == null) {
            System.out.println("Voo nao encontrado!");
            return;
        }

        // Confirmação
        System.out.print("Certeza? Isso irá deletar todos os dados associados a este voo (tickets, assentos, check-ins, bagagens, boarding passes) [S/N]: ");
        String confirm = scan.nextLine();
        if (!confirm.equalsIgnoreCase("S")) {
            System.out.println("Operacao cancelada.");
            return;
        }

        // Deletar tickets associados
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null && tickets[i].getVoo() == vooDeletar) {
                tickets[i] = null;
            }
        }

        // Liberar assentos
        if (vooDeletar.getVooAssentos() != null) {
            for (VooAssentos a : vooDeletar.getVooAssentos()) {
                if (a != null) {
                    a.setPassageiro(null);
                }
            }
        }

        // Deletar check-ins
        for (int i = 0; i < checkIns.length; i++) {
            if (checkIns[i] != null && checkIns[i].getTicket().getVoo() == vooDeletar) {
                checkIns[i] = null;
            }
        }

        // Deletar bagagens
        for (int i = 0; i < bagagens.length; i++) {
            if (bagagens[i] != null && bagagens[i].getTicket().getVoo() == vooDeletar) {
                bagagens[i] = null;
            }
        }

        // Deletar boarding passes
        for (int i = 0; i < boardingPasses.length; i++) {
            if (boardingPasses[i] != null && boardingPasses[i].getVoo() == vooDeletar) {
                boardingPasses[i] = null;
            }
        }

        // Deletar o voo
        for (int i = 0; i < voos.length; i++) {
            if (voos[i] == vooDeletar) {
                voos[i] = null;
                break;
            }
        }

        System.out.println("Voo e todos os registros associados foram deletados!");
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
                System.out.println("| Valor: R$ " + String.format("%.2f", v.getValor()));
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
                System.out.println("| Valor: R$ " + String.format("%.2f", v.getValor()));
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
                            && !estado.equalsIgnoreCase("Cancelado") && !estado.equalsIgnoreCase("Concluido") && !estado.equalsIgnoreCase("Decolado");

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
                        System.out.println("| Valor: R$ " + String.format("%.2f", v.getValor()));
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

        System.out.println("\n--- Voos disponíveis para cancelamento ---");
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
            System.out.println("Nenhum voo disponível para cancelamento.");
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
            System.out.println("Voo inválido ou não pode ser cancelado.");
            return;
        }

        // Cancela o voo
        vooCancelar.setEstado("Cancelado");
        System.out.println("Voo " + vooCancelar.getId() + " cancelado com sucesso.");

        // Libera todos os assentos ocupados
        if (vooCancelar.getVooAssentos() != null) {
            for (VooAssentos a : vooCancelar.getVooAssentos()) {
                if (a != null && a.getPassageiro() != null) {
                    a.setPassageiro(null);
                }
            }
            System.out.println("Todos os assentos do voo foram liberados.");
        }

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
