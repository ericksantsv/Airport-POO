/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalhopoo;

import java.time.LocalDate;
import java.util.Scanner;
import trabalhopoo.model.Aeroporto;
import trabalhopoo.model.CompanhiaAerea;
import trabalhopoo.model.Passageiro;
import trabalhopoo.model.Usuario;
import static trabalhopoo.model.Usuario.loginAdmin;
import trabalhopoo.model.Voo;

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
        Passageiro passageiros = new Passageiro();
        
        CompanhiaAerea companhiaAerea[] = new CompanhiaAerea[10];
        Voo voos[] = new Voo[50];
        
        Usuario usuario[] = new Usuario[10];
        
        //Companhias Pré-criadas
        companhiaAerea[0] = new CompanhiaAerea("AzulRio","AZR",LocalDate.now(),LocalDate.now()); 
        companhiaAerea[1] = new CompanhiaAerea("LatamAir","LTA",LocalDate.now(),LocalDate.now()); 
        companhiaAerea[2] = new CompanhiaAerea("SolAereo","SLA",LocalDate.now(),LocalDate.now()); 
        companhiaAerea[3] = new CompanhiaAerea("VentoLeste","VLE",LocalDate.now(),LocalDate.now()); 
        companhiaAerea[4] = new CompanhiaAerea("NorteSky","NSK",LocalDate.now(),LocalDate.now()); 
        
        //Voos pré-criados
        voos[0] = new Voo(1, "Uberaba", "São Paulo", LocalDate.parse("2025-10-10"), 2.5, companhiaAerea[0], 5, "Programado", LocalDate.now(), LocalDate.now());
        voos[1] = new Voo(2, "Uberaba", "Rio de Janeiro", LocalDate.parse("2025-10-11"), 3.0, companhiaAerea[1], 3, "Programado", LocalDate.now(), LocalDate.now());
        voos[2] = new Voo(3, "Uberaba", "Belo Horizonte", LocalDate.parse("2025-10-12"), 1.5, companhiaAerea[2], 4, "Programado", LocalDate.now(), LocalDate.now());
        voos[3] = new Voo(4, "Uberaba", "Brasília", LocalDate.parse("2025-10-13"), 4.0, companhiaAerea[3], 6, "Programado", LocalDate.now(), LocalDate.now());
        voos[4] = new Voo(5, "Uberaba", "Curitiba", LocalDate.parse("2025-10-14"), 3.5, companhiaAerea[4], 5, "Programado", LocalDate.now(), LocalDate.now());
        
        //Usuario pré-criados
        usuario[0] = new Usuario("goncalves", "goncalves");
        usuario[1] = new Usuario("erick", "erick");
        usuario[2] = new Usuario("nico", "nico");
        usuario[3] = new Usuario("bruna", "bruna");
        usuario[4] = new Usuario("dudu", "dudu");
        
        while (menu) {

            System.out.println("\n\n ===== Menu ====="
                    + "\n1 - Painel de voo"
                    + "\n2 - Administracao"
                    + "\n3 - Compra de passagem"
                    + "\n4 - Gestao de passagem"
                    + "\n5 - Sair"
            );
            System.out.print("Insira a opcao..: ");
            int opc = scan.nextInt();
            scan.nextLine(); //  Para limpar o buffer para o login funcionar
             
            switch (opc) {
                case 1:
                    Voo.exibirVoos(voos);
                    break;
                case 2:
                    //login administrativo - fazer o login adm
                    Usuario admin = Usuario.loginAdmin(scan, usuario);
                    if (admin != null) {
                        menuAdmin(scan, passageiros, voos, companhiaAerea);
                    } else {
                        System.out.println("Login ou senha incorretos!");
                    }
                    
                    break;
                case 3:
                    //passageiro.criarPassageiro();
                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Saindo do programa....");
                    menu = false;
                    break;
                default:
                    System.out.println("\n\nOpcao invalida, digite novamente...");
                    
                    break;
            }
        }

    }

    private static void menuAdmin(Scanner scan, Passageiro passageiros, Voo[] voos, CompanhiaAerea[] companhiaAerea) {
         boolean admMenu = true;
        while(admMenu){
             System.out.println("\n\n ===== Menu Administracao ====="
                + "\n1 - CRUD Passageiro"
                + "\n2 - CRUD Voo"
                + "\n3 - Voltar");
        System.out.print("Escolha uma opcao: ");
        int admOpc = scan.nextInt();
        scan.nextLine();

            switch(admOpc){
                case 1:
                    //crudPassageiro(scan, passageiros);
                    break;
                case 2:
                    //crudVoo(scan, voos, companhiaAerea);
                    break;
                case 3:
                    admMenu = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }
 }

