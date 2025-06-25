package main.java.com.example.ecommerce.service;

import main.java.com.example.ecommerce.model.interfaces.Entrega;

public class EntregaRapida implements Entrega { // A Entrega do Pedido deve ser realizada de 3 formas diferentes: ... entrega rápida @Override
    public double calcularFrete() {
        return 30.00; // Exemplo de valor de frete para entrega rápida
    }
}