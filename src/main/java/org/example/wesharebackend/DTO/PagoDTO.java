package org.example.wesharebackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.wesharebackend.modelos.Grupo;
import org.example.wesharebackend.modelos.Usuario;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {

    private Integer id;
    private Double importe;
    private String descripcion;
    private String fecha;
    private Integer usuarioId;
    private Integer grupoId;
    private UsuarioDTO usuarioDTO;
    private GrupoDTO grupoDTO;

}
