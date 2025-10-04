/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.model;

import java.time.LocalDate;

/**
 *=>CRUD de voo. Informações importantes: id, origem, destino, data, duração, 
 * companhia aérea, capacidade, estado (programado, embarque, decolado, 
 * atrasado, cancelado) , data_criacao, data_modificacao. 
 * Coloque a capacidade pequena para ilustrar o cenário de voo cheio.
 * @author erick
 */
public class Voo {
    int id;
    String origem;
    String destino;
    LocalDate data;
    double duracao;
    CompanhiaAerea companhiaAerea;
    int capacidade;
    String estado;
    LocalDate dataCriacao;
    LocalDate dataModificacao;
    
    
}
