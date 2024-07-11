package com.sisvita_grupo12.test_service.service;

import com.sisvita_grupo12.test_service.dto.ClasificacionRequest;
import com.sisvita_grupo12.test_service.dto.ClasificacionResponse;
import com.sisvita_grupo12.test_service.model.Clasificacion;
import com.sisvita_grupo12.test_service.repository.ClasificacionRepository;
import com.sisvita_grupo12.test_service.dto.TipoTestResponse;
import com.sisvita_grupo12.test_service.repository.TipoTestRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClasificacionService {

        private final ClasificacionRepository clasificacionRepository;
        private final TipoTestRepository tipoTestRepository;

        public Clasificacion createClasificacion(ClasificacionRequest clasificacionRequest) {
            Clasificacion clasificacion = new Clasificacion();
            clasificacion.setMinimo(clasificacionRequest.getMinimo());
            clasificacion.setMaximo(clasificacionRequest.getMaximo());
            clasificacion.setInterpretacion(clasificacionRequest.getInterpretacion());
            clasificacion.setTipo_test(tipoTestRepository.findById(clasificacionRequest.getId_tipo_test()).orElseThrow());
            return clasificacionRepository.save(clasificacion);
        }

        public List<ClasificacionResponse> getAllClasificaciones() {
            List<Clasificacion> clasificaciones = clasificacionRepository.findAll();
            return clasificaciones.stream().map(this::mapToClasificacionResponse).toList();
        }

        private ClasificacionResponse mapToClasificacionResponse(Clasificacion clasificacion) {
            return ClasificacionResponse.builder()
                    .id_clasificacion(clasificacion.getId_clasificacion())
                    .minimo(clasificacion.getMinimo())
                    .maximo(clasificacion.getMaximo())
                    .interpretacion(clasificacion.getInterpretacion())
                    .tipo_test(TipoTestResponse.builder()
                            .id_tipo_test(clasificacion.getTipo_test().getId_tipo_test())
                            .nombre(clasificacion.getTipo_test().getNombre())
                            .autor(clasificacion.getTipo_test().getAutor())
                            .descripcion(clasificacion.getTipo_test().getDescripcion())
                            .build())
                    .build();
        }

        public ClasificacionResponse getClasificacionById(Integer id) {
            Clasificacion clasificacion = clasificacionRepository.findById(id).orElseThrow();
            return mapToClasificacionResponse(clasificacion);
        }
}
