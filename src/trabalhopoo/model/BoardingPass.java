package trabalhopoo.model;

import java.time.LocalDateTime;

public class BoardingPass {

    private int id;
    private Passageiro passageiro;
    private Voo voo;
    private String assento;
    private LocalDateTime dataEmissao;
    private boolean embarcado;

    public BoardingPass(int id, Passageiro passageiro, Voo voo, String assento) {
        this.id = id;
        this.passageiro = passageiro;
        this.voo = voo;
        this.assento = assento;
        this.dataEmissao = LocalDateTime.now();
        this.embarcado = false;
    }

    // --- Getters e Setters ---
    public int getId() {
        return id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public boolean isEmbarcado() {
        return embarcado;
    }

    public void setEmbarcado(boolean embarcado) {
        this.embarcado = embarcado;
    }

    // --- Método para exibir o cartão ---
    public void exibirBoardingPass() {
        System.out.println("\n===== BOARDING PASS =====");
        System.out.println("Passageiro: " + passageiro.getNome());
        System.out.println("Voo: " + voo.getOrigem() + " → " + voo.getDestino());
        System.out.println("Data do Voo: " + voo.getData());
        System.out.println("Assento: " + assento);
        System.out.println("Data de Emissão: " + dataEmissao);
        System.out.println("Embarcado: " + (embarcado ? "✅ Sim" : "❌ Não"));
        System.out.println("==========================\n");
    }
}
