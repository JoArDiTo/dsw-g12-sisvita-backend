package com.sisvita_grupo12.test_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "alternativa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Alternativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_alternativa;
    private String contenido;
    private Integer puntaje;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_test", referencedColumnName = "id_tipo_test")
    private TipoTest tipo_test;

    @OneToMany(mappedBy = "alternativa", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;
}
