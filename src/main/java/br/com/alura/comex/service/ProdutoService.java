package br.com.alura.comex.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.alura.comex.dto.request.CadastrarProdutosRequest;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<String> cadastrar(CadastrarProdutosRequest body) {
        Produto produto = body.toProduto();
        Optional<Categoria> categoria = categoriaService.getCategoria(body.getIdCategoria());
        if (!categoria.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        produto.setCategoria(categoria.get());

        produtoRepository.save(produto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
