package com.sisvita_grupo12.test_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "semaforo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Semaforo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_semaforo;

    private String color;

    @OneToMany(mappedBy = "semaforo")
    private List<Clasificacion> clasificacion;
}
