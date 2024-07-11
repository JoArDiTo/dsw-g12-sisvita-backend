package com.sisvita_grupo12.vigilance_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tratamiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tratamiento;

    private String descripcion;
    private String fundamentacion;

    @OneToMany(mappedBy = "tratamiento")
    private List<Vigilancia> vigilancia;

}
