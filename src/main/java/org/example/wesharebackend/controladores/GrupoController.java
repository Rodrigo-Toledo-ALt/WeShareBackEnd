package org.example.wesharebackend.controladores;


import lombok.AllArgsConstructor;
import org.example.wesharebackend.DTO.GrupoDTO;
import org.example.wesharebackend.DTO.UsuarioDTO;
import org.example.wesharebackend.modelos.Grupo;
import org.example.wesharebackend.modelos.Usuario;
import org.example.wesharebackend.servicios.GrupoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
@AllArgsConstructor
public class GrupoController {

private GrupoService grupoService;

    @PostMapping("/crear")
    private GrupoDTO crearGrupo(@RequestBody GrupoDTO grupoDTO){
        return grupoService.crearGrupo(grupoDTO);}

    @PostMapping("/participantes/nuevo")
    private List<UsuarioDTO> añadirParticipante(@RequestParam Integer id_usuario, @RequestParam Integer id_grupo){
        return grupoService.añadirParticipante(id_usuario, id_grupo);
    }

    //Este metodo añade a un usuarioa a un grupo utilizando solo sus ids en la base de datos
    // y devuelve la lista de participantes

    @GetMapping("/verParticipantesGrupo")
    private List<UsuarioDTO> verParticipantesGrupo(@RequestParam Integer id_grupo){
        return grupoService.verParticipantesGrupo(id_grupo);
    }

    @DeleteMapping("/eliminarParticipantesGrupo") //Por algún motivo este metodo no elimina a participante con id =1
    private List<UsuarioDTO> eliminarParticipantesGrupo(@RequestParam Integer id_usuario, @RequestParam Integer id_grupo){
        return grupoService.eliminarParticipantesGrupo(id_usuario, id_grupo);
    }

}
