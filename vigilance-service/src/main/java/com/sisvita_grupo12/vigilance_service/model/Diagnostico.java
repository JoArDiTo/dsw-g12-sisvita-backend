package com.sisvita_grupo12.vigilance_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "diagnostico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_diagnostico;

    private String descripcion;
    private String fundamentacion;

    @OneToMany(mappedBy = "diagnostico")
    private List<Vigilancia> vigilancia;
}
