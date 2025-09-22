package br.com.alura.comex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.dto.request.AutenticacaoRequest;
import br.com.alura.comex.dto.response.ResponseTokenJWT;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import br.com.alura.comex.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoRequest autenticacaoRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoRequest.getLogin(),
                autenticacaoRequest.getSenha());
        Authentication authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new ResponseTokenJWT(tokenJWT));
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity newUser(@RequestBody @Valid AutenticacaoRequest json, UriComponentsBuilder uriBuilder) {

        Usuario user = new Usuario();

        String encriptedPasswd = new BCryptPasswordEncoder().encode(json.getSenha());

        user.setEmail(json.getLogin());
        user.setSenha(encriptedPasswd);

        usuarioRepository.save(user);
        return ResponseEntity.ok().build();

    }

}
