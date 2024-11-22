package org.example.wesharebackend.servicios;


import lombok.AllArgsConstructor;
import org.example.wesharebackend.DTO.GrupoDTO;
import org.example.wesharebackend.DTO.PagoDTO;
import org.example.wesharebackend.DTO.UsuarioDTO;
import org.example.wesharebackend.modelos.Pago;
import org.example.wesharebackend.modelos.Usuario;
import org.example.wesharebackend.repositorios.GrupoRepository;
import org.example.wesharebackend.repositorios.PagoRepository;
import org.example.wesharebackend.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;
    private GrupoRepository grupoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public PagoDTO añadirPago(PagoDTO pagoDTO) {

        Pago pago = new Pago();
        pago.setImporte(pagoDTO.getImporte());
        pago.setDescripcion(pagoDTO.getDescripcion());
        if(pagoDTO.getFecha() == null){
            pago.setFecha(LocalDate.now());
        }else{
            pago.setFecha(LocalDate.parse(pagoDTO.getFecha()));
        }
        pago.setGrupo(grupoRepository.getById(pagoDTO.getGrupoId()));
        pago.setUsuario(usuarioRepository.getById(pagoDTO.getUsuarioId()));



        pagoRepository.save(pago);

        GrupoDTO grupoDTO = new GrupoDTO();

        grupoDTO.setNombre(pago.getGrupo().getNombre());
        grupoDTO.setId(pago.getGrupo().getId());
        grupoDTO.setFechaCreacion(pago.getGrupo().getFechaCreacion());

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setNombre(pago.getUsuario().getNombre());
        usuarioDTO.setId(pago.getUsuario().getId());
        usuarioDTO.setCorreo(pago.getUsuario().getCorreo());
        usuarioDTO.setContraseña(pago.getUsuario().getContraseña());

        pagoDTO.setId(pago.getId());
        pagoDTO.setGrupoDTO(grupoDTO);
        pagoDTO.setUsuarioDTO(usuarioDTO);
        pagoDTO.setFecha(String.valueOf(pago.getFecha()));

        return pagoDTO;
    }

    //VerBalances de todos los integrantes del grupo
    // Recibe un grupoID
    //JSON --> lista de balances y usuarios asociados

    //Balance = pagos - BalanceGrupo

    //BalanceGrupo = Todos Pagos de un grupo / Número de integrantes Hecho

    //Metodo Calcular BalanceGrupo(Integer GrupoId) Hecho



    public List<Double> VerBalances(Integer GrupoId){

        List<Usuario> Usuarios = usuarioRepository.findUsuariosByGrupoId(GrupoId);



        List<UsuarioDTO> UsuariosDTO = new ArrayList<>();
        List<Double> BalancesGrupo = new ArrayList<>();

        for (Usuario u: Usuarios){

            Double BalanceUsuario =  CalcularBalance(GrupoId, u.getId()) ;
            BalancesGrupo.add(BalanceUsuario);

        }

        for (Usuario u: Usuarios){
            UsuarioDTO UsuarioDTO = new UsuarioDTO();
            UsuarioDTO.setId(u.getId());
            UsuarioDTO.setNombre(u.getNombre());
            UsuarioDTO.setCorreo(u.getCorreo());
            UsuarioDTO.setContraseña(u.getContraseña());
            UsuariosDTO.add(UsuarioDTO);
        }

        //Esto es el hashmap de prueba pero no lo va a devolver
        HashMap<UsuarioDTO, Double> BalanceUsuarios = new HashMap<>();

            for (int i = 0; i < UsuariosDTO.size(); i++){
                BalanceUsuarios.put(UsuariosDTO.get(i), BalancesGrupo.get(i));
            }

        //lo que realmente va a devolver es BalancesGrupo


        return  BalancesGrupo;
    }
    



    public Double CalcularBalance(Integer GrupoId, Integer UsuarioId){

        Double totalGrupo = pagoRepository.findTotalGrupo(GrupoId);
        Integer numUsuarios = pagoRepository.findNumUsuarioByGrupoId(GrupoId);
        Double totalUsuario = pagoRepository.findTotalUsuario(UsuarioId, GrupoId);


        double BalanceUsuario;
        return BalanceUsuario  = totalGrupo / numUsuarios - totalUsuario;
    }




    public List<PagoDTO> verPagos(Integer idGrupo) {

        List<PagoDTO> pagosDTO= new ArrayList<>();


        List<Pago> pagos = pagoRepository.findPagosByGrupoId(idGrupo);

        for (Pago p: pagos){
            PagoDTO pagoDTO = new PagoDTO();
            pagoDTO.setId(p.getId());
            pagoDTO.setImporte(p.getImporte());
            pagoDTO.setDescripcion(p.getDescripcion());
            pagoDTO.setFecha(String.valueOf(p.getFecha()));
            pagoDTO.setUsuarioId(p.getUsuario().getId());
            pagoDTO.setGrupoId(p.getGrupo().getId());

            pagosDTO.add(pagoDTO);
        }

        return pagosDTO;
    }
}
