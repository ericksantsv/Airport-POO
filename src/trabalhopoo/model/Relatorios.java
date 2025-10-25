/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo.model;

import java.util.Scanner;
import trabalhopoo.dao.RelatoriosDAO;

/**
 *
 * @author erick
 */
public class Relatorios {
    
    public static void menuRelatorios(Voo[] voos, Ticket[] tickets, Scanner scan) {
        boolean userMenu = true;
        while (userMenu) {
            System.out.println("\n--- Relatorios ---");
            System.out.println("1 - Passageiros que deixaram em determinada cidade ");
            System.out.println("2 - Passageiros que chegaram em determinada cidade");
            System.out.println("3 - Receita de companhia aerea no periodo");
            System.out.print("Escolha uma opcao: ");
            int usrOpc = scan.nextInt();
            scan.nextLine();

            switch (usrOpc) {
                case 1:
                    RelatoriosDAO.passageirosQueDeixaramCidade(voos, tickets, scan);
                    break;
                case 2:

                    break;
                case 3:
                    
                    break;
                case 4:
                    userMenu = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }
    
}
