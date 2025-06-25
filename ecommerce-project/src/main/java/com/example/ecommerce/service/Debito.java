package main.java.com.example.ecommerce.service;

public class Debito extends FormaPagamento { 
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + String.format("%.2f", valor) + " realizado com Cartão de Débito.");
        // Lógica para processar pagamento com cartão de débito
    }
}