package main.java.com.example.ecommerce.service;

import main.java.com.example.ecommerce.model.interfaces.Entrega;

public class EntregaNaLoja implements Entrega { // A Entrega do Pedido deve ser realizada de 3 formas diferentes: ... retirada na loja 
    @Override
    public double calcularFrete() {
        return 0.00; // Retirada na loja geralmente não tem custo de frete
    }
}