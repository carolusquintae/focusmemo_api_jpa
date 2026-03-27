package com.api.focusmemo.service.impl;

import com.api.focusmemo.dto.MazoDTO;
import com.api.focusmemo.exception.ResourceNotFoundException;
import com.api.focusmemo.model.Mazo;
import com.api.focusmemo.model.Usuario;
import com.api.focusmemo.repository.MazoRepository;
import com.api.focusmemo.repository.UsuarioRepository;
import com.api.focusmemo.service.MazoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MazoServiceImpl implements MazoService {

    private final MazoRepository mazoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public MazoDTO crear(MazoDTO mazoDTO) {
        Usuario usuario = usuarioRepository
                            .findById(mazoDTO.idUsuario())
                            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + mazoDTO.idUsuario()));

        Mazo mazo = Mazo.builder()
                      .usuario(usuario)
                      .nombre(mazoDTO.nombre())
                      .descripcion(mazoDTO.descripcion())
                      .color(mazoDTO.color())
                      .build();

        mazo = mazoRepository.save(mazo);
        return mapToDTO(mazo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MazoDTO> listarTodos() {
        return mazoRepository.findAll()
                    .stream()
                    .map(mazo -> this.mapToDTO(mazo))
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MazoDTO> listarPorUsuario(Long idUsuario) {
        return mazoRepository
                    .findByUsuarioIdUsuario(idUsuario).stream()
                    .map(mazo -> this.mapToDTO(mazo))
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MazoDTO buscarPorId(Long id) {
        Mazo mazo = mazoRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Mazo no encontrado con ID: " + id));
        return mapToDTO(mazo);
    }

    @Override
    @Transactional
    public MazoDTO actualizar(Long id, MazoDTO mazoDTO) {
        Mazo mazo = mazoRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Mazo no encontrado con ID: " + id));

        mazo.setNombre(mazoDTO.nombre());
        mazo.setDescripcion(mazoDTO.descripcion());
        mazo.setColor(mazoDTO.color());
        
        mazo = mazoRepository.save(mazo);
        return mapToDTO(mazo);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!mazoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mazo no encontrado con ID: " + id);
        }
        mazoRepository.deleteById(id);
    }

    private MazoDTO mapToDTO(Mazo mazo) {
        return MazoDTO.builder()
                    .idMazo(mazo.getIdMazo())
                    .idUsuario(mazo.getUsuario().getIdUsuario())
                    .nombre(mazo.getNombre())
                    .descripcion(mazo.getDescripcion())
                    .color(mazo.getColor())
                    .build();
    }
}
