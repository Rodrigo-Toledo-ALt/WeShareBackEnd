package org.example.wesharebackend.controladores;

import lombok.AllArgsConstructor;
import org.example.wesharebackend.DTO.GrupoDTO;
import org.example.wesharebackend.DTO.UsuarioDTO;
import org.example.wesharebackend.servicios.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;

    @GetMapping("/listarAmigos")
    public List<UsuarioDTO> obtenerAmigo(@RequestParam Integer id_usuario){
        return usuarioService.obtenerAmigos(id_usuario);
    }

    @GetMapping("/listarGrupos")
    public List<GrupoDTO> obtenerGrupos(@RequestParam Integer id_usuario){
        return usuarioService.obtenerGrupos(id_usuario);
    }

}
