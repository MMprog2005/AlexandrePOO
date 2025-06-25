package main.java.com.example.ecommerce.model;

public class NotaFiscal {
    private Cliente cliente; // Cada pedido deve gerar uma Nota Fiscal com informações do cliente 
    private Pedido pedido; // Cada pedido deve gerar uma Nota Fiscal com informações do ... pedido. 
    private int numero; // A Nota Fiscal existe apenas se um Pedido for gerado 

    private static int proximoNumeroNota = 1; // Para gerar números de nota fiscal sequenciais

    public NotaFiscal(Cliente cliente, Pedido pedido) {
        this.cliente = cliente;
        this.pedido = pedido;
        this.numero = proximoNumeroNota++;
    }

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void imprimirNota() {
        System.out.println("\n--- Nota Fiscal #" + this.numero + " ---");
        System.out.println("Cliente: " + cliente.getNome() + (cliente instanceof ClienteEmpresa ? " (CNPJ: " + ((ClienteEmpresa)cliente).getCnpj() + ")" : " (CPF: " + cliente.getCpf() + ")"));
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Endereço: " + cliente.getEndereco().getRua() + ", " + cliente.getEndereco().getNumero() + " - " + cliente.getEndereco().getBairro() + " - CEP: " + cliente.getEndereco().getCep());
        System.out.println("--------------------");
        System.out.println("Pedido #" + pedido.getNumeroPedido());
        System.out.println("Produtos:");
        for (Produto p : pedido.getProdutos()) {
            System.out.println("  - " + p.getNome() + " (R$" + String.format("%.2f", p.getPreco()) + ")");
        }
        System.out.println("--------------------");
        System.out.println("Forma de Pagamento: " + pedido.getFormaPagamento().getClass().getSimpleName());
        System.out.println("Tipo de Entrega: " + pedido.getTipoEntrega().getClass().getSimpleName());
        System.out.println("Valor Total: R$" + String.format("%.2f", pedido.calcularTotal())); // O desconto de PIX já é aplicado no cálculo do pedido
        System.out.println("--------------------\n");
    }
}