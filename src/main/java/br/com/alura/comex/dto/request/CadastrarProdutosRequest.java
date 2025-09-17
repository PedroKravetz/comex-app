package br.com.alura.comex.dto.request;

import br.com.alura.comex.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CadastrarProdutosRequest {

    @NotBlank
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s.,;:'\"()\\[\\]-]{2,100}$")
    private String nome;

    @NotNull
    @Positive
    private double preco;

    @Pattern(regexp = "^[\\p{L}\\p{N}\\s.,;:'\"()\\[\\]-]{2,100}$")
    private String descricao;

    @NotNull
    @PositiveOrZero
    private long quantidade;

    @NotNull
    private Long idCategoria;

    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        return produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

}
