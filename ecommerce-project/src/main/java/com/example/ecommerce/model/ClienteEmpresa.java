package main.java.com.example.ecommerce.model;

public class ClienteEmpresa extends Cliente {
    private String cnpj;

    public ClienteEmpresa(String nome, String cpf, String email, String cnpj) {
        super(nome, cpf, email);
        this.cnpj = cnpj;
        
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // Sobrescrita do método criarConta, se houver lógica específica para empresas
    @Override
    public void criarConta() {
        System.out.println("Conta empresarial criada para: " + getNome() + " (CNPJ: " + this.cnpj + ")");
    }
}