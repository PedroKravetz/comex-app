package br.com.alura.comex.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.request.CadastrarProdutosRequest;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<String> cadastra(@RequestBody @Valid CadastrarProdutosRequest body, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Produto produto = body.toProduto();
        Optional<Categoria> categoria = categoriaService.getCategoria(body.getIdCategoria());
        if (!categoria.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        produto.setCategoria(categoria.get());

        produtoService.cadastrar(produto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
