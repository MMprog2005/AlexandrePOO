package main.java.com.example.ecommerce.service;

public class Pix extends FormaPagamento { // As formas de Pagamento podem ser: ... PIX. @Override
    public void pagar(double valor) { // Caso a forma de pagamento seja PIX, aplique um desconto de 15% 
        System.out.println("Pagamento de R$" + String.format("%.2f", valor) + " realizado com PIX.");
        System.out.println("Chave PIX: 123.456.789-00");
        // Lógica para gerar chave PIX
    }
}