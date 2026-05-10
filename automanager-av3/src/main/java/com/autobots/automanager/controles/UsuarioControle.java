package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.modelo.AdicionadorLinkUsuario;
import com.autobots.automanager.repositorios.UsuarioRepositorio;
import com.autobots.automanager.repositorios.VeiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Autowired
    private VeiculoRepositorio veiculoRepositorio;

    @Autowired
    private AdicionadorLinkUsuario adicionadorLink;

    @GetMapping
    public ResponseEntity<List<Usuario>> obterUsuarios() {
        List<Usuario> usuarios = repositorio.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        adicionadorLink.adicionarLink(usuarios);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable Long id) {
        Optional<Usuario> opt = repositorio.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = opt.get();
        adicionadorLink.adicionarLink(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Usuario salvo = repositorio.save(usuario);
        adicionadorLink.adicionarLink(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> opt = repositorio.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario existente = opt.get();
        if (usuario.getNome() != null) existente.setNome(usuario.getNome());
        if (usuario.getNomeSocial() != null) existente.setNomeSocial(usuario.getNomeSocial());
        if (usuario.getEmail() != null) existente.setEmail(usuario.getEmail());
        if (usuario.getDataNascimento() != null) existente.setDataNascimento(usuario.getDataNascimento());
        if (usuario.getPerfil() != null && !usuario.getPerfil().isEmpty()) {
            existente.setPerfil(usuario.getPerfil());
        }
        if (usuario.getCredencial() != null) existente.setCredencial(usuario.getCredencial());
        if (usuario.getEndereco() != null) existente.setEndereco(usuario.getEndereco());
        Usuario atualizado = repositorio.save(existente);
        adicionadorLink.adicionarLink(atualizado);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{usuarioId}/veiculo/{veiculoId}")
    public ResponseEntity<Usuario> vincularVeiculo(@PathVariable Long usuarioId, @PathVariable Long veiculoId) {
        Optional<Usuario> optUsuario = repositorio.findById(usuarioId);
        Optional<Veiculo> optVeiculo = veiculoRepositorio.findById(veiculoId);
        if (optUsuario.isEmpty() || optVeiculo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = optUsuario.get();
        usuario.getVeiculos().add(optVeiculo.get());
        Usuario salvo = repositorio.save(usuario);
        adicionadorLink.adicionarLink(salvo);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{usuarioId}/veiculo/{veiculoId}")
    public ResponseEntity<Usuario> desvincularVeiculo(@PathVariable Long usuarioId, @PathVariable Long veiculoId) {
        Optional<Usuario> optUsuario = repositorio.findById(usuarioId);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = optUsuario.get();
        usuario.getVeiculos().removeIf(v -> v.getId().equals(veiculoId));
        Usuario salvo = repositorio.save(usuario);
        adicionadorLink.adicionarLink(salvo);
        return ResponseEntity.ok(salvo);
    }
}
