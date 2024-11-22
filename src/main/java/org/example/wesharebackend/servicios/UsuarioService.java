package org.example.wesharebackend.servicios;

import lombok.AllArgsConstructor;
import org.example.wesharebackend.DTO.GrupoDTO;
import org.example.wesharebackend.DTO.UsuarioDTO;
import org.example.wesharebackend.modelos.Grupo;
import org.example.wesharebackend.modelos.Usuario;
import org.example.wesharebackend.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> obtenerAmigos(Integer id_usuario){

        List<Usuario> Usuarios = usuarioRepository.findAmigosByUsuarioId(id_usuario);
        List<UsuarioDTO> UsuariosDTO = new ArrayList<>();

        for (Usuario u: Usuarios) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(u.getId());
            usuarioDTO.setNombre(u.getNombre());
            usuarioDTO.setCorreo(u.getCorreo());
            usuarioDTO.setContraseña(u.getContraseña());
            UsuariosDTO.add(usuarioDTO);
        }
        return UsuariosDTO;
    }

    public List<GrupoDTO> obtenerGrupos(Integer id_usuario){

        List<Grupo> Grupos = usuarioRepository.findGruposByUsuarioId(id_usuario);
        List<GrupoDTO> GruposDTO = new ArrayList<>();

        for (Grupo g: Grupos) {
            GrupoDTO grupoDTO = new GrupoDTO();
            grupoDTO.setId(g.getId());
            grupoDTO.setNombre(g.getNombre());
            GruposDTO.add(grupoDTO);
        }
       return GruposDTO;
    }


}
