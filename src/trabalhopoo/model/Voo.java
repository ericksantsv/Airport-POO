/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Scanner;
import trabalhopoo.dao.VooDAO;

/**
 * =>CRUD de voo. Informações importantes: id, origem, destino, data, duração,
 * companhia aérea, capacidade, estado (programado, embarque, decolado,
 * atrasado, cancelado) , data_criacao, data_modificacao. Coloque a capacidade
 * pequena para ilustrar o cenário de voo cheio.
 *
 * @author erick
 */
public class Voo {

    private int id;
    private String origem;
    private String destino;
    private LocalDateTime data;
    private LocalTime duracao;
    private double valor;
    private CompanhiaAerea companhiaAerea;
    private VooAssentos[] vooAssentos;
    private int capacidade;
    private String estado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Voo() {

    }

    public Voo(int id,double valor, String origem, String destino, LocalDateTime data, LocalTime duracao, CompanhiaAerea companhiaAerea, int capacidade, String estado, LocalDateTime dataCriacao, LocalDateTime dataModificacao) {
        this.id = id;
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.duracao = duracao;
        this.companhiaAerea = companhiaAerea;
        this.capacidade = capacidade;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
        this.vooAssentos = new VooAssentos[capacidade];
        for (int i = 0; i < capacidade; i++) {
            String codigo = gerarCodigoAssento(i); // Ex: A1, A2, B1, etc.
            this.vooAssentos[i] = new VooAssentos(i + 1, this, codigo, null, LocalDateTime.now(), null);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }

    public CompanhiaAerea getCompanhiaAerea() {
        return companhiaAerea;
    }

    public void setCompanhiaAerea(CompanhiaAerea companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public VooAssentos[] getVooAssentos() {
        return vooAssentos;
    }

    public void setVooAssentos(VooAssentos[] vooAssentos) {
        this.vooAssentos = vooAssentos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.origem);
        hash = 13 * hash + Objects.hashCode(this.destino);
        hash = 13 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voo other = (Voo) obj;
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        return Objects.equals(this.data, other.data);
    }

    public static void crudVoo(Voo[] voos, Ticket[] tickets, VooAssentos[] assentos, CheckIn[] checkIns, DespachoBagagem[] bagagens, BoardingPass[] boardingPasses, CompanhiaAerea[] companhias, Scanner scan) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- CRUD Voo ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Editar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    VooDAO.cadastrar(voos, companhias, scan);
                    break;

                case 2:
                    VooDAO.listar(voos);
                    break;

                case 3:
                    VooDAO.editarVoo(voos, tickets, boardingPasses, scan);
                    break;

                case 4:
                    VooDAO.deletar(voos, tickets, assentos, checkIns, bagagens, boardingPasses, scan);
                    break;

                case 5:
                    menu = false;
                    break;

                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    public static String gerarCodigoAssento(int index) {
        char letra = (char) ('A' + (index / 6));
        int numero = (index % 6) + 1;
        return letra + String.valueOf(numero);
    }

    public void atualizarEstadoAutomatico() {
        if (this.estado.equalsIgnoreCase("Cancelado")) {
            return; // não muda se já estiver cancelado
        }

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime partida = this.data;
        LocalDateTime chegadaPrevista = partida.plusHours(this.duracao.getHour()).plusMinutes(this.duracao.getMinute());

        if (agora.isBefore(partida.minusHours(2))) {
            this.estado = "Programado";
        } else if (agora.isAfter(partida.minusHours(2)) && agora.isBefore(partida)) {
            this.estado = "Embarque";
        } else if (agora.isAfter(partida) && agora.isBefore(chegadaPrevista)) {
            this.estado = "Decolado";
        } else if (agora.isAfter(chegadaPrevista)) {
            this.estado = "Concluido";
        } else if (agora.isAfter(partida.plusMinutes(15)) && this.estado.equalsIgnoreCase("Programado")) {
            this.estado = "Atrasado";
        }
    }

    public boolean temAssentoLivre() {
        for (VooAssentos a : this.vooAssentos) {
            if (a.getPassageiro() == null) {
                return true;
            }
        }
        return false;
    }

}
