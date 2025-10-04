/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalhopoo;

import java.util.Scanner;

/**
 *
 * @author erick
 */
public class TrabalhoPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean menu = true;
        Scanner scan = new Scanner(System.in);
        
        while (menu) {

            System.out.println("\n\n ===== Menu ====="
                    + "\n1 - Login como Administrador"
                    + "\n2 - Login como Funcionario"
                    + "\n3 - Login como Passageiro"
                    + "\n4 - Sair"
            );
            System.out.print("Insira a opcao..: ");
            int opc = scan.nextInt();
            
            switch(opc) {
                case 1:
                    //metodo admin
                    break;
                case 2:
                    //metodo funcionario
                    break;
                case 3:
                    //metodo passageiro
                    break;
                case 4:
                    System.out.println("\n\nSaindo...");
                    menu = false;
                    break;
                default:
                    System.out.println("\n\nOpcao invalida, digite novamente...");
                    break;
            }
        }

    }

}
