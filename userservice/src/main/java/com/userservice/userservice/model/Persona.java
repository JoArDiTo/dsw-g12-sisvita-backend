/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.userservice.userservice.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Usuario
 */

@Entity
@Table(name="persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Persona {
    @Id
    private String documento;
    private String tipo_documento;
    private String nombre;
    @Column(name = "apellido_paterno", length = 50) //Se puede especificar columnas para mapeo
    private String apellidoPaterno;
    private String apellido_materno;
    private String telefono;
    private Date fecha_nacimiento;
    private String sexo;
    
    @OneToOne
    @JoinColumn(name = "idUbigeo")
    private Ubigeo ubigeo;
  
}
