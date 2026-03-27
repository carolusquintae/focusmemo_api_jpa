package com.api.focusmemo.service.impl;

import com.api.focusmemo.dto.TarjetaDTO;
import com.api.focusmemo.exception.ResourceNotFoundException;
import com.api.focusmemo.model.Mazo;
import com.api.focusmemo.model.Tarjeta;
import com.api.focusmemo.repository.MazoRepository;
import com.api.focusmemo.repository.TarjetaRepository;
import com.api.focusmemo.service.TarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarjetaServiceImpl implements TarjetaService {

    private final TarjetaRepository tarjetaRepository;
    private final MazoRepository mazoRepository;

    @Override
    @Transactional
    public TarjetaDTO crear(TarjetaDTO tarjetaDTO) {
        Mazo mazo = mazoRepository
                        .findById(tarjetaDTO.idMazo())
                        .orElseThrow(() -> new ResourceNotFoundException("Mazo no encontrado con ID: " + tarjetaDTO.idMazo()));

        Tarjeta tarjeta = Tarjeta.builder()
                             .mazo(mazo)
                             .pregunta(tarjetaDTO.pregunta())
                             .respuesta(tarjetaDTO.respuesta())
                             .activa(true)
                             .build();

        tarjeta = tarjetaRepository.save(tarjeta);
        return mapToDTO(tarjeta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDTO> listarPorMazo(Long idMazo) {
        return tarjetaRepository
                    .findByMazoIdMazo(idMazo).stream()
                    .map(t -> this.mapToDTO(t))
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TarjetaDTO buscarPorId(Long id) {
        Tarjeta tarjeta = tarjetaRepository
                            .findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Tarjeta no encontrada con ID: " + id));
        return mapToDTO(tarjeta);
    }

    @Override
    @Transactional
    public TarjetaDTO actualizar(Long id, TarjetaDTO tarjetaDTO) {
        Tarjeta tarjeta = tarjetaRepository
                            .findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Tarjeta no encontrada con ID: " + id));

        tarjeta.setPregunta(tarjetaDTO.pregunta());
        tarjeta.setRespuesta(tarjetaDTO.respuesta());
        tarjeta.setActiva(tarjetaDTO.activa());
        
        tarjeta = tarjetaRepository.save(tarjeta);
        return mapToDTO(tarjeta);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!tarjetaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarjeta no encontrada con ID: " + id);
        }
        tarjetaRepository.deleteById(id);
    }

    private TarjetaDTO mapToDTO(Tarjeta tarjeta) {
        return TarjetaDTO.builder()
                    .idTarjeta(tarjeta.getIdTarjeta())
                    .idMazo(tarjeta.getMazo().getIdMazo())
                    .pregunta(tarjeta.getPregunta())
                    .respuesta(tarjeta.getRespuesta())
                    .activa(tarjeta.isActiva())
                    .build();
    }
}
