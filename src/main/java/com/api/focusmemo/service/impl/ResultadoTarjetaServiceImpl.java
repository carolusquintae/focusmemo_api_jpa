package com.api.focusmemo.service.impl;

import com.api.focusmemo.dto.ResultadoTarjetaDTO;
import com.api.focusmemo.model.ResultadoTarjeta;
import com.api.focusmemo.model.Tarjeta;
import com.api.focusmemo.model.Usuario;
import com.api.focusmemo.repository.ResultadoTarjetaRepository;
import com.api.focusmemo.repository.TarjetaRepository;
import com.api.focusmemo.repository.UsuarioRepository;
import com.api.focusmemo.service.ResultadoTarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultadoTarjetaServiceImpl implements ResultadoTarjetaService {

    private final ResultadoTarjetaRepository repo;
    private final UsuarioRepository userRepo;
    private final TarjetaRepository tarjetaRepo;

    @Override
    @Transactional
    public ResultadoTarjetaDTO guardar(ResultadoTarjetaDTO dto) {
        Usuario user = userRepo.findById(dto.idUsuario()).orElseThrow();
        Tarjeta card = tarjetaRepo.findById(dto.idTarjeta()).orElseThrow();
        
        ResultadoTarjeta rt = ResultadoTarjeta.builder()
                                .usuario(user)
                                .tarjeta(card)
                                .calidadRespuesta(dto.calidadRespuesta())
                                .build();
        
        return mapToDTO(repo.save(rt));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultadoTarjetaDTO> listarPorUsuario(Long idUsuario) {
        return repo
                 .findByUsuarioIdUsuario(idUsuario)
                 .stream()
                 .map(rt -> this.mapToDTO(rt))
                 .collect(Collectors.toList());
    }

    private ResultadoTarjetaDTO mapToDTO(ResultadoTarjeta rt) {
        return ResultadoTarjetaDTO.builder()
                    .idResultado(rt.getIdResultado())
                    .idUsuario(rt.getUsuario().getIdUsuario())
                    .idTarjeta(rt.getTarjeta().getIdTarjeta())
                    .calidadRespuesta(rt.getCalidadRespuesta())
                    .fecha(rt.getFecha())
                    .build();
    }
}
