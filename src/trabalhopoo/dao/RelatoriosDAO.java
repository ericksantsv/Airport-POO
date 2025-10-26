package trabalhopoo.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import trabalhopoo.model.CompanhiaAerea;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Ticket;
import trabalhopoo.model.Voo;

public class RelatoriosDAO {

    public static void passageirosQueDeixaramCidade(Voo[] voos, Ticket[] tickets, Scanner scan) {
        System.out.println("\n===== Cidades de Origem Disponíveis =====");
        String[] origens = new String[voos.length];
        int count = 0;

        // Coleta origens únicas manualmente
        for (Voo v : voos) {
            if (v != null && v.getOrigem() != null) {
                boolean repetido = false;
                for (int i = 0; i < count; i++) {
                    if (origens[i].equalsIgnoreCase(v.getOrigem())) {
                        repetido = true;
                        break;
                    }
                }
                if (!repetido) {
                    origens[count] = v.getOrigem();
                    count++;
                }
            }
        }

        // Exibe as origens sem repetir
        for (int i = 0; i < count; i++) {
            System.out.println("- " + origens[i]);
        }

        System.out.print("\nDigite a cidade de origem desejada: ");
        String origemEscolhida = scan.nextLine().trim();

        boolean encontrou = false;
        int contador = 0;

        System.out.println("\n===== Passageiros que partiram de " + origemEscolhida + " =====");

        for (Ticket t : tickets) {
            if (t != null) {
                Voo v = t.getVoo();
                if (v != null
                        && v.getOrigem().equalsIgnoreCase(origemEscolhida)
                        && v.getEstado().equalsIgnoreCase("Concluido")) {

                    Passageiro p = t.getPassageiro();
                    System.out.println("• " + p.getNome() + " (CPF: " + p.getDocumento() + ")");
                    contador++;
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum passageiro encontrado que tenha partido de " + origemEscolhida + ".");
        } else {
            System.out.println("\nTotal de passageiros que deixaram " + origemEscolhida + ": " + contador);
        }
    }

    public static void passageirosQueChegaramCidade(Voo[] voos, Ticket[] tickets, Scanner scan) {
        System.out.println("\n===== Cidades de Destino Disponiveis =====");
        String[] destinos = new String[voos.length];
        int count = 0;

        // Coleta destinos únicos manualmente
        for (Voo v : voos) {
            if (v != null && v.getDestino() != null) {
                boolean repetido = false;
                for (int i = 0; i < count; i++) {
                    if (destinos[i].equalsIgnoreCase(v.getDestino())) {
                        repetido = true;
                        break;
                    }
                }
                if (!repetido) {
                    destinos[count] = v.getDestino();
                    count++;
                }
            }
        }

        // Exibe os destinos sem repetir
        for (int i = 0; i < count; i++) {
            System.out.println("- " + destinos[i]);
        }

        System.out.print("\nDigite a cidade de destino desejada: ");
        String destinoEscolhido = scan.nextLine().trim();

        boolean encontrou = false;
        int contador = 0;

        System.out.println("\n===== Passageiros que chegaram em " + destinoEscolhido + " =====");

        // Cria um array temporário para não repetir passageiros
        Passageiro[] passageirosMostrados = new Passageiro[tickets.length];
        int mostradosCount = 0;

        for (Ticket t : tickets) {
            if (t != null) {
                Voo v = t.getVoo();
                Passageiro p = t.getPassageiro();
                if (v != null
                        && p != null
                        && v.getDestino().equalsIgnoreCase(destinoEscolhido)
                        && v.getEstado().equalsIgnoreCase("Concluido")) {

                    // Verifica se o passageiro já foi listado
                    boolean jaMostrado = false;
                    for (int i = 0; i < mostradosCount; i++) {
                        if (passageirosMostrados[i] == p) {
                            jaMostrado = true;
                            break;
                        }
                    }

                    if (!jaMostrado) {
                        System.out.println("• " + p.getNome() + " (CPF: " + p.getDocumento() + ")");
                        passageirosMostrados[mostradosCount++] = p;
                        contador++;
                        encontrou = true;
                    }
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum passageiro encontrado que tenha chegado em " + destinoEscolhido + ".");
        } else {
            System.out.println("\nTotal de passageiros que chegaram em " + destinoEscolhido + ": " + contador);
        }
    }

    public static void calcularReceita(CompanhiaAerea[] companhias, Passageiro[] passageiros, Scanner scan) {
        // Define o formato de data
        DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Lista companhias
        System.out.println("\n--- Companhias Aéreas ---");
        for (CompanhiaAerea c : companhias) {
            if (c != null) {
                System.out.println(c.getId() + " - " + c.getNome() + " (" + c.getAbreviacao() + ")");
            }
        }

        System.out.print("Digite o ID da companhia: ");
        int idCompanhia = scan.nextInt();
        scan.nextLine();

        CompanhiaAerea companhiaSelecionada = null;
        for (CompanhiaAerea c : companhias) {
            if (c != null && c.getId() == idCompanhia) {
                companhiaSelecionada = c;
                break;
            }
        }

        if (companhiaSelecionada == null) {
            System.out.println("Companhia nao encontrada!");
            return;
        }

        // Ler período
        System.out.print("Data inicio (dd/MM/yyyy HH:mm): ");
        String inicioStr = scan.nextLine();
        System.out.print("Data fim (dd/MM/yyyy HH:mm): ");
        String fimStr = scan.nextLine();

        LocalDateTime inicio;
        LocalDateTime fim;

        try {
            inicio = LocalDateTime.parse(inicioStr, FORMATO_DATA);
            fim = LocalDateTime.parse(fimStr, FORMATO_DATA);
        } catch (Exception e) {
            System.out.println("Formato de data invalido!");
            return;
        }

        // Calcular receita
        double receita = 0;

        for (Passageiro p : passageiros) {
            if (p == null || p.getTicket() == null) {
                continue;
            }

            for (Ticket t : p.getTicket()) {
                if (t != null && t.getVoo() != null
                        && t.getVoo().getCompanhiaAerea() != null
                        && t.getVoo().getCompanhiaAerea().equals(companhiaSelecionada)) {

                    LocalDateTime dataVoo = t.getVoo().getData();
                    if ((dataVoo.isEqual(inicio) || dataVoo.isAfter(inicio))
                            && (dataVoo.isEqual(fim) || dataVoo.isBefore(fim))) {
                        receita += t.getValor();
                    }
                }
            }
        }

        System.out.println("\nReceita da companhia " + companhiaSelecionada.getNome()
                + " no periodo de " + inicio.format(FORMATO_DATA) + " ate " + fim.format(FORMATO_DATA)
                + " e: R$ " + String.format("%.2f", receita));
    }

}
