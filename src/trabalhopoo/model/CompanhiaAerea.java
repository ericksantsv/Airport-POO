/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package trabalhopoo.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import trabalhopoo.dao.CompanhiaAereaDAO;

/**
 *
 *
 * @author Gonçalves
 */
public class CompanhiaAerea {

//atributos
    int id;
    String nome;
    String abreviacao;
    LocalDate data_criacao;
    LocalDate data_modificacao;

    //construtor vazio
    public CompanhiaAerea() {
    }

    //construtor para criar um novo
    public CompanhiaAerea(String nome, String abreviacao, LocalDate data_criacao, LocalDate data_modificacao) {
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    //construtor completo
    public CompanhiaAerea(int id, String nome, String abreviacao, LocalDate data_criacao, LocalDate data_modificacao) {
        this.id = id;
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    //getters e setters
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

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.abreviacao);
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
        final CompanhiaAerea other = (CompanhiaAerea) obj;
        return Objects.equals(this.abreviacao, other.abreviacao);
    }

    public static void crudCompanhiaAerea(CompanhiaAerea[] companhias, Scanner scan) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- CRUD Companhias Aereas ---");
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
                    CompanhiaAereaDAO.cadastrar(companhias, scan);
                    break;
                case 2:
                    CompanhiaAereaDAO.listar(companhias);
                    break;
                case 3:
                    CompanhiaAereaDAO.listar(companhias);
                    CompanhiaAereaDAO.editar(companhias, scan);
                    break;
                case 4:
                    CompanhiaAereaDAO.listar(companhias);
                    CompanhiaAereaDAO.deletar(companhias, scan);
                    break;
                case 5:
                    menu = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }
}
