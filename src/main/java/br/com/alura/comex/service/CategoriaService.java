package br.com.alura.comex.service;

import java.util.Optional;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.comex.dto.request.CadastrarCategoriaRequest;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void cadastrar(CadastrarCategoriaRequest cadastrarCategoria) throws PSQLException {
        Categoria categoria = cadastrarCategoria.toCategoria();
        categoria.setAtiva(true);
        categoriaRepository.save(categoria);
    }

    public Optional<Categoria> getCategoria(Long id) {
        return categoriaRepository.findById(id);
    }
}
