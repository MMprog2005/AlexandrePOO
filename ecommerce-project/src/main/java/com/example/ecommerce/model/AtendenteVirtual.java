package main.java.com.example.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class AtendenteVirtual {
    private String nome;
    private int id;
    private List<Cliente> clientesAtendidos; 

    public AtendenteVirtual(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.clientesAtendidos = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cliente> getClientesAtendidos() {
        return clientesAtendidos;
    }

    public void atender(Cliente cliente) { // Um Atendente Virtual pode atender vários Clientes this.clientesAtendidos.add(cliente);
        System.out.println("Atendente Virtual " + this.nome + " está atendendo o cliente: " + cliente.getNome());
        // Aqui poderia haver uma lógica de interação com o cliente
    }

}