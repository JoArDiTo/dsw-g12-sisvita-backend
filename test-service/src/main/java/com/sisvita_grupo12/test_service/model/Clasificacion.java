package com.sisvita_grupo12.test_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clasificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Clasificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_clasificacion;

    private Integer minimo;
    private Integer maximo;
    private String interpretacion;

    @OneToMany(mappedBy = "clasificacion")
    private List<Test> tests;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_test", referencedColumnName = "id_tipo_test")
    private TipoTest tipo_test;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_semaforo", referencedColumnName = "id_semaforo")
    private Semaforo semaforo;
}
