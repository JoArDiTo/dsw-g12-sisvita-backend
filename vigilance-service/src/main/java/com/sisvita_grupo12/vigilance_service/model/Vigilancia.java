package com.sisvita_grupo12.vigilance_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vigilancia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vigilancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_vigilancia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_diagnostico", referencedColumnName = "id_diagnostico")
    private Diagnostico diagnostico;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tratamiento", referencedColumnName = "id_tratamiento")
    private Tratamiento tratamiento;


    private String observacion;
    private String fundamentacion;
}
