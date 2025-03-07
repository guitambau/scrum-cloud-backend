package com.scrumcloud.scrumcloud.service;

import com.scrumcloud.scrumcloud.dto.AuthDTO;
import com.scrumcloud.scrumcloud.dto.UsuarioDTO;
import com.scrumcloud.scrumcloud.model.Usuario;
import com.scrumcloud.scrumcloud.repository.UsuarioRepository;
import com.scrumcloud.scrumcloud.utils.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario inserir(Usuario usuario) {
       UsuarioDTO user = findByEmail(usuario.getEmail());

        if(user == null) {
            return repository.save(usuario);
        } else {
            return null;
        }
    }

    public Usuario cadastroDev(Usuario usuario) {
        UsuarioDTO user = findByEmail(usuario.getEmail());

        if(user == null) {
           return repository.save(usuario);
        } else {
            return null;
        }
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public UsuarioDTO findByEmail(String email){
        return repository.findByEmail(email);
    }

    public UsuarioDTO authentication(AuthDTO user){
        UsuarioDTO auth = repository.authentication(user.getEmail(), user.getSenha());

        return auth;
    }

    public Usuario buscarPorId(Long id){
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }
}
