package main.java.com.example.ecommerce.model;

import main.java.com.example.ecommerce.service.FormaPagamento;
import main.java.com.example.ecommerce.model.interfaces.Entrega; // Importar a interface Entrega

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente; // Um Pedido pode conter apenas um Cliente 
    private List<Produto> produtos; // Um Pedido pode conter vários Produtos 
    private FormaPagamento formaPagamento; // O Pedido deve ter uma forma de Pagamento. 
    private NotaFiscal notaFiscal; // Cada pedido deve gerar uma Nota Fiscal 
    private Entrega tipoEntrega; // A Entrega do Pedido deve ser realizada de 3 formas diferentes: Entrega padrão, entrega rápida ou retirada na loja 

    private static int proximoNumeroPedido = 1; // Para gerar números de pedido sequenciais
    private int numeroPedido;

    public Pedido(Cliente cliente, List<Produto> produtos, FormaPagamento formaPagamento, Entrega tipoEntrega) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>(produtos); // Cria uma nova lista para evitar modificações externas
        this.formaPagamento = formaPagamento;
        this.tipoEntrega = tipoEntrega;
        this.numeroPedido = proximoNumeroPedido++;
    }

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Entrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(Entrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    public void gerarNota() { // Cada pedido deve gerar uma Nota Fiscal com informações do cliente e pedido. A Nota Fiscal existe apenas se um Pedido for gerado 
        if (this.notaFiscal == null) {
            this.notaFiscal = new NotaFiscal(this.cliente, this);
            System.out.println("Nota Fiscal gerada para o pedido #" + this.numeroPedido);
            this.notaFiscal.imprimirNota();
        } else {
            System.out.println("Nota Fiscal já existe para o pedido #" + this.numeroPedido);
        }
    }
}