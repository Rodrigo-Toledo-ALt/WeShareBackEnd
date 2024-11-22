package org.example.wesharebackend.repositorios;

import org.example.wesharebackend.modelos.Grupo;
import org.example.wesharebackend.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u JOIN u.grupos g WHERE g.id = :grupoId")
    List<Usuario> findUsuariosByGrupoId(@Param("grupoId") Integer grupoId);

    @Query("SELECT u.amigos FROM Usuario u WHERE u.id = :usuarioId")
    List<Usuario> findAmigosByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT u.grupos FROM Usuario u WHERE u.id = :usuarioId")
    List<Grupo> findGruposByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
