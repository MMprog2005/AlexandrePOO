package main.java.com.example.ecommerce.service;

public abstract class FormaPagamento { // O Pedido deve ter uma forma de Pagamento. 
    public abstract void pagar(double valor);
}