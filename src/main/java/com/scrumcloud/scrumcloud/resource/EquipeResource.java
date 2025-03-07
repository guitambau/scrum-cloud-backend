package com.scrumcloud.scrumcloud.resource;

import com.scrumcloud.scrumcloud.dto.EmailListDTO;
import com.scrumcloud.scrumcloud.dto.EquipeDTO;
import com.scrumcloud.scrumcloud.dto.UsuarioDTO;
import com.scrumcloud.scrumcloud.model.Equipe;
import com.scrumcloud.scrumcloud.model.Usuario;
import com.scrumcloud.scrumcloud.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/equipe")
public class EquipeResource {

    @Autowired
    private EquipeService equipeService;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastroEquipe(@RequestBody EquipeDTO equipe) {

        equipeService.inserirEquipe(equipe);

        return ResponseEntity.ok(201);
    }

    @GetMapping("/buscarPorIdUsuario/{idUser}")
    public ResponseEntity<List<EquipeDTO>> buscarPorIdUsuario(@PathVariable Long idUser) {
        List<EquipeDTO> list = equipeService.buscarEquipesPorIdUsuario(idUser);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscarIntegrantesEquipe/{idEquipe}")
    public ResponseEntity<List<UsuarioDTO>> buscarIntegrantesEquipe(@PathVariable Long idEquipe) {
        List<UsuarioDTO> listUser = equipeService.buscarIntegrantesEquipe(idEquipe);

        return ResponseEntity.ok(listUser);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<EquipeDTO> buscarPorId(@PathVariable Long id) {
        EquipeDTO equipe = equipeService.buscarPorId(id);

        return ResponseEntity.ok(equipe);
    }

}
