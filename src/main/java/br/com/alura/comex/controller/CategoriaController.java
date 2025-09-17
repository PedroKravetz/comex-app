package br.com.alura.comex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.request.CadastrarCategoriaRequest;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<String> cadastra(@RequestBody @Valid CadastrarCategoriaRequest cadastrarCategoria,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Categoria categoria = cadastrarCategoria.toCategoria();
        categoria.setAtiva(true);
        try {
            categoriaService.cadastrar(categoria);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
