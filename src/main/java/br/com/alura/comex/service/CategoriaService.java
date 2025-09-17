package br.com.alura.comex.service;

import java.util.Optional;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void cadastrar(Categoria categoria) throws PSQLException {
        if (categoria == null)
            return;
        categoriaRepository.save(categoria);
    }

    public Optional<Categoria> getCategoria(Long id) {
        return categoriaRepository.findById(id);
    }
}
