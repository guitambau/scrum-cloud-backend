package com.scrumcloud.scrumcloud.repository;

import com.scrumcloud.scrumcloud.dto.EquipeDTO;
import com.scrumcloud.scrumcloud.dto.UsuarioDTO;
import com.scrumcloud.scrumcloud.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

   @Query("SELECT new com.scrumcloud.scrumcloud.dto.EquipeDTO(e) FROM Equipe e WHERE e.usuario.id = :idUser")
    public List<EquipeDTO> buscarPorIdUsuario(Long idUser);

   @Query("SELECT new com.scrumcloud.scrumcloud.dto.UsuarioDTO(user.id, user.nome, user.email, user.tipoUsuario) " +
           "FROM Equipe e " +
           "INNER JOIN e.listaUsuarios user " +
           "WHERE e.id = :idEquipe")
    public List<UsuarioDTO> buscarIntegrantesEquipe(Long idEquipe);
}
