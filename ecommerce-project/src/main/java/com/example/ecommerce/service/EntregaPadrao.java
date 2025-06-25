package main.java.com.example.ecommerce.service;

import main.java.com.example.ecommerce.model.interfaces.Entrega;

public class EntregaPadrao implements Entrega {
    public double calcularFrete() {
        return 15.00; // Exemplo de valor de frete para entrega padrão
    }
}