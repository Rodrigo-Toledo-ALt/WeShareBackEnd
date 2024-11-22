package org.example.wesharebackend.controladores;

import lombok.AllArgsConstructor;
import org.example.wesharebackend.DTO.PagoDTO;
import org.example.wesharebackend.DTO.UsuarioDTO;
import org.example.wesharebackend.servicios.PagoService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/pago")
@AllArgsConstructor
public class PagoController {

    private PagoService PagoService;

    @PostMapping("/añadir")
    private PagoDTO añadirPago(@RequestBody PagoDTO pagoDTO){

        return PagoService.añadirPago(pagoDTO);
    }

    @GetMapping("/verBalances")
    private List<Double> verBalances(@RequestParam Integer id_grupo){
        return PagoService.VerBalances(id_grupo);
    }

    @GetMapping("/verPagos")
    private List<PagoDTO> verPagos(@RequestParam Integer id_grupo){
        return PagoService.verPagos(id_grupo);
    }


}
