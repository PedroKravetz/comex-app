package br.com.alura.comex.dto.request;

import br.com.alura.comex.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CadastrarCategoriaRequest {

    @NotBlank
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s.,;:'\"()\\[\\]-]{2,100}$")
    private String nome;

    public Categoria toCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
