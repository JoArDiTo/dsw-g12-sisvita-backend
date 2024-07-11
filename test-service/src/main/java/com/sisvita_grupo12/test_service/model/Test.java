package com.sisvita_grupo12.test_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_test;

    private Integer resultado;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_test", referencedColumnName = "id_tipo_test")
    private TipoTest tipo_test;

    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    private Integer idPaciente;

    @JoinColumn(name = "id_vigilancia", referencedColumnName = "id_vigilancia")
    private Integer id_vigilancia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_clasificacion", referencedColumnName = "id_clasificacion")
    private Clasificacion clasificacion;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

}
