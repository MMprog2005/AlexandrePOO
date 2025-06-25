package main.java.com.example.ecommerce.main;

import main.java.com.example.ecommerce.model.*;
import main.java.com.example.ecommerce.model.interfaces.Entrega;
import main.java.com.example.ecommerce.service.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema de E-commerce...\n");

        // 1. Criar um Cliente 
        Cliente cliente1 = new Cliente("João Silva", "123.456.789-00", "joao.silva@email.com");
        cliente1.criarConta(); // Criar uma conta; 

        // 2. Registrar endereço do Cliente 
        Endereco endCliente1 = new Endereco("Rua das Flores", 100, "12345-678", "Centro");
        cliente1.registrarEndereco(endCliente1); // Registrar endereço; 

        // 3. Registrar forma de pagamento do Cliente 
        FormaPagamento pixPagamento = new Pix();
        cliente1.registrarFormaPagamento(pixPagamento); // Registrar forma de pagamento; 

        // 4. Criar alguns produtos
        Produto prod1 = new Produto("Smartphone X", 2500.00, "Celular de última geração");
        Produto prod2 = new Produto("Fone Bluetooth", 250.00, "Fone sem fio com cancelamento de ruído");
        Produto prod3 = new Produto("Smart TV 50'", 3500.00, "TV 4K com Android TV");

        // 5. Adicionar produtos ao carrinho do cliente 
        cliente1.adicionarProdutoAoCarrinho(prod1);
        cliente1.adicionarProdutoAoCarrinho(prod2);
        cliente1.adicionarProdutoAoCarrinho(prod3);

        System.out.println("\nCarrinho do " + cliente1.getNome() + ":");
        for (Produto p : cliente1.getCarrinho()) {
            System.out.println("  - " + p.getNome() + " (R$" + String.format("%.2f", p.getPreco()) + ")");
        }

        // 6. Cliente realiza um Pedido 
        System.out.println("\n--- Realizando Pedido 1 ---");
        Entrega entregaPadrao = new EntregaPadrao(); // A Entrega do Pedido
        Pedido pedido1 = cliente1.realizarPedido(cliente1.getCarrinho(), pixPagamento, entregaPadrao); 
        if (pedido1 != null) {
            System.out.println("Frete do Pedido 1: R$" + String.format("%.2f", pedido1.getTipoEntrega().calcularFrete()));
            // Simular o pagamento
            pedido1.getFormaPagamento().pagar(pedido1.calcularTotal());
        }

        // 7. Testando outro cliente (empresa) 
        System.out.println("\n--- Cliente Empresa ---");
        ClienteEmpresa empresa1 = new ClienteEmpresa("Tech Solutions Ltda.", "00.111.222/0001-33", "contato@techsolutions.com", "00.111.222/0001-33");
        empresa1.criarConta();
        Endereco endEmpresa = new Endereco("Av. Paulista", 1500, "01310-000", "Bela Vista");
        empresa1.registrarEndereco(endEmpresa);
        FormaPagamento boletoPagamento = new Boleto();  
        empresa1.registrarFormaPagamento(boletoPagamento);

        empresa1.adicionarProdutoAoCarrinho(prod3);
        empresa1.adicionarProdutoAoCarrinho(new Produto("Servidor Rack", 15000.00, "Servidor de alto desempenho"));

        System.out.println("\n--- Realizando Pedido 2 ---");
        Entrega entregaRapida = new EntregaRapida();  
        Pedido pedido2 = empresa1.realizarPedido(empresa1.getCarrinho(), boletoPagamento, entregaRapida);
        if (pedido2 != null) {
            System.out.println("Frete do Pedido 2: R$" + String.format("%.2f", pedido2.getTipoEntrega().calcularFrete()));
            pedido2.getFormaPagamento().pagar(pedido2.calcularTotal());
        }

        // 8. Testando atendente virtual 
        System.out.println("\n--- Atendimento Virtual ---");
        AtendenteVirtual botAtendente = new AtendenteVirtual("Assistente Bot", 1);
        cliente1.entrarContatoComAtendenteVirtual(botAtendente); // O Cliente entrar em contato com o assistente. 
        empresa1.entrarContatoComAtendenteVirtual(botAtendente);

        // 9. Testando retirada na loja
        System.out.println("\n--- Pedido com Retirada na Loja ---");
        Cliente cliente2 = new Cliente("Maria Oliveira", "987.654.321-11", "maria.oliveria@email.com");
        cliente2.criarConta();
        Endereco endCliente2 = new Endereco("Rua Nova", 50, "98765-432", "Jardim");
        cliente2.registrarEndereco(endCliente2);
        FormaPagamento debitoPagamento = new Debito(); // formar de pagammento
        cliente2.registrarFormaPagamento(debitoPagamento);

        cliente2.adicionarProdutoAoCarrinho(new Produto("Mouse Gamer", 150.00, "Mouse de alta precisão"));

        Entrega retiradaNaLoja = new EntregaNaLoja(); // ... retirada na loja 
        Pedido pedido3 = cliente2.realizarPedido(cliente2.getCarrinho(), debitoPagamento, retiradaNaLoja);
        if (pedido3 != null) {
            System.out.println("Frete do Pedido 3: R$" + String.format("%.2f", pedido3.getTipoEntrega().calcularFrete()));
            pedido3.getFormaPagamento().pagar(pedido3.calcularTotal());
        }

        System.out.println("\nSistema de E-commerce encerrado.");
    }
}