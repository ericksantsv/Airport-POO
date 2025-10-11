/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import trabalhopoo.dao.PassageiroDAO;

/**
 * CRUD de passageiro. 
 * Informações importantes: id, nome, nascimento, documento
 * login, senha, data_criacao, data_modificacao
 * 
 * @author erick
 */
public class Passageiro {
    int id;
    String nome;
    LocalDate nascimento;
    String documento;
    Ticket[] ticket;
    Usuario usuario;
    LocalDate data_criacao;
    LocalDate data_modificacao;
    
    public Passageiro() {
    
    }
    
        public Passageiro(int id, String nome, LocalDate nascimento, String documento,LocalDate data_criacao, LocalDate data_modificacao) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.documento = documento;
        this.ticket = new Ticket[10];
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }


    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public LocalDate getData_modificacao() {
        return data_modificacao;
    }

    public void setData_modificacao(LocalDate data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    public Ticket[] getTicket() {
        return ticket;
    }

    public void setTicket(Ticket[] ticket) {
        this.ticket = ticket;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.documento);
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
        final Passageiro other = (Passageiro) obj;
        return Objects.equals(this.documento, other.documento);
    }
    
    public static void crudPassageiro(Passageiro[] passageiros, Scanner scan) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- CRUD Passageiro ---");
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
                    PassageiroDAO.cadastrar(passageiros, scan);
                    break;

                case 2:
                    PassageiroDAO.listar(passageiros);
                    break;

                case 3:
                    PassageiroDAO.editar(passageiros, scan);
                    break;

                case 4:
                    PassageiroDAO.deletar(passageiros, scan);
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

}
