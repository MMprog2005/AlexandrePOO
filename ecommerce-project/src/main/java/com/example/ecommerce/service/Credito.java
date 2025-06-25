package main.java.com.example.ecommerce.service;

public class Credito extends FormaPagamento { // As formas de Pagamento podem ser: ... Crédito @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + String.format("%.2f", valor) + " realizado com Cartão de Crédito.");
        // Lógica para processar pagamento com cartão de crédito
    }
}