package main.java.com.example.ecommerce.model;

import main.java.com.example.ecommerce.service.FormaPagamento;
import main.java.com.example.ecommerce.model.interfaces.Entrega; // Importar Entrega para o carrinho

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private Endereco endereco;
    private List<Pedido> pedidos;
    private FormaPagamento formaPagamento;
    private AtendenteVirtual assistenteVirtual;
    private List<Produto> carrinho; // Adicionado para representar o carrinho

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.pedidos = new ArrayList<>();
        this.carrinho = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public AtendenteVirtual getAssistenteVirtual() {
        return assistenteVirtual;
    }

    public void setAssistenteVirtual(AtendenteVirtual assistenteVirtual) {
        this.assistenteVirtual = assistenteVirtual;
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void adicionarProdutoAoCarrinho(Produto produto) {
        this.carrinho.add(produto);
        System.out.println(produto.getNome() + " adicionado ao carrinho de " + this.nome);
    }

    public void removerProdutoDoCarrinho(Produto produto) {
        this.carrinho.remove(produto);
        System.out.println(produto.getNome() + " removido do carrinho de " + this.nome);
    }

    // Métodos de funcionalidade
    public void criarConta() {
        System.out.println("Conta criada para o cliente: " + this.nome);
    }

    public void registrarEndereco(Endereco endereco) {
        this.setEndereco(endereco);
        System.out.println("Endereço registrado para " + this.nome + ": " + endereco.getRua() + ", " + endereco.getNumero());
    }

    public void registrarFormaPagamento(FormaPagamento formaPagamento) {
        this.setFormaPagamento(formaPagamento);
        System.out.println("Forma de pagamento registrada para " + this.nome + ": " + formaPagamento.getClass().getSimpleName());
    }

    // Sobrecarga: adicionarProduto (no sentido de adicionar um produto ao carrinho)
    public void adicionarProduto(Produto produto) { // Adicionar Produto ao carrinho 
        adicionarProdutoAoCarrinho(produto);
    }

    public void entrarContatoComAtendenteVirtual(AtendenteVirtual atendente) { // O Cliente pode entrar em contato com um Atendente Virtual. 
        this.setAssistenteVirtual(atendente);
        atendente.atender(this);
    }

    public Pedido realizarPedido(List<Produto> produtos, FormaPagamento formaPagamento, Entrega tipoEntrega) { // Um Pedido pode conter vários Produtos 
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Não é possível realizar um pedido sem produtos no carrinho.");
            return null;
        }

        double total = 0;
        for (Produto p : produtos) {
            total += p.getPreco();
        }

        // Aplica desconto se for PIX
        if (formaPagamento instanceof main.java.com.example.ecommerce.service.Pix) { // Caso a forma de pagamento seja PIX, aplique um desconto de 15% 
            total *= 0.85; // 15% de desconto
            System.out.println("Desconto de 15% aplicado para pagamento via PIX.");
        }

        Pedido novoPedido = new Pedido(this, produtos, formaPagamento, tipoEntrega);
        this.addPedido(novoPedido);
        System.out.println("Pedido de R$" + String.format("%.2f", total) + " realizado por " + this.nome + " com " + formaPagamento.getClass().getSimpleName() + " e entrega " + tipoEntrega.getClass().getSimpleName() + ".");

        // Gerar nota fiscal após o pedido ser gerado
        novoPedido.gerarNota(); // Cada pedido deve gerar uma Nota Fiscal com informações do cliente e pedido. A Nota Fiscal existe apenas se um Pedido for gerado 
        return novoPedido;
    }
}