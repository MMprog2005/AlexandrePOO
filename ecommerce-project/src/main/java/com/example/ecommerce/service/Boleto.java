package main.java.com.example.ecommerce.service;

public class Boleto extends FormaPagamento { // As formas de Pagamento podem ser: Boleto @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + String.format("%.2f", valor) + " realizado com Boleto.");
        System.out.println("Boleto gerado. Data de vencimento: " + java.time.LocalDate.now().plusDays(5));
        // Lógica para gerar boleto
    }
}