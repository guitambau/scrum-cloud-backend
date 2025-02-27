package com.scrumcloud.scrumcloud.service;

import com.scrumcloud.scrumcloud.dto.EmailListDTO;
import com.scrumcloud.scrumcloud.dto.EquipeDTO;
import com.scrumcloud.scrumcloud.dto.UsuarioDTO;
import com.scrumcloud.scrumcloud.model.Equipe;
import com.scrumcloud.scrumcloud.model.Usuario;
import com.scrumcloud.scrumcloud.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private UsuarioService usuarioService;


    public Equipe inserirEquipe(EquipeDTO objRef) {

        Usuario user = usuarioService.buscarPorId(objRef.getUsuario());

        Equipe equipe = new Equipe();

        equipe.setNome(objRef.getNome());
        equipe.setDescricao(objRef.getDescricao());
        equipe.setDataCriacao(LocalDate.now());
        equipe.setUsuario(user);

        return equipeRepository.save(equipe);
    }

    public List<EquipeDTO> buscarEquipesPorIdUsuario(Long id){
        return equipeRepository.buscarPorIdUsuario(id);
    }

    public List<UsuarioDTO> buscarIntegrantesEquipe(Long idEquipe) {
        return equipeRepository.buscarIntegrantesEquipe(idEquipe);
    }

    public EquipeDTO buscarPorId(Long id) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        EquipeDTO dto = new EquipeDTO();
        dto.setId(equipe.get().getId());
        dto.setUsuario(equipe.get().getUsuario().getId());
        dto.setNomeUser(equipe.get().getUsuario().getNome());
        dto.setNome(equipe.get().getNome());

        return dto;
    }

    public Equipe inserirUsuarioEquipe(Usuario user, Long idTime) {
        Optional<Equipe> equipeRef = equipeRepository.findById(idTime);

        List<Usuario> listaAux = equipeRef.get().getListaUsuarios();
        listaAux.add(user);

        equipeRef.get().setListaUsuarios(listaAux);

        Equipe equipe = new Equipe();

        equipe.setId(equipeRef.orElseThrow().getId());
        equipe.setNome(equipeRef.orElseThrow().getNome());
        equipe.setDescricao(equipeRef.orElseThrow().getDescricao());
        equipe.setDataCriacao(equipeRef.orElseThrow().getDataCriacao());
        equipe.setUsuario(equipeRef.orElseThrow().getUsuario());
        equipe.setListaUsuarios(equipeRef.orElseThrow().getListaUsuarios());


        return equipeRepository.save(equipe);
    }


}
